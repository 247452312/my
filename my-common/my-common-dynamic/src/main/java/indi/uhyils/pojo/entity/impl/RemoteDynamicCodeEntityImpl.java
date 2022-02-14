package indi.uhyils.pojo.entity.impl;

import com.rabbitmq.client.ConfirmListener;
import indi.uhyils.annotation.Nullable;
import indi.uhyils.context.DynamicContext;
import indi.uhyils.facade.DynamicCodeFacade;
import indi.uhyils.loader.DynamicClassLoader;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.entity.RemoteDynamicCodeEntityInterface;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.StringUtil;
import indi.uhyils.util.compiler.JavaStringCompiler;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 动态代码主表远程实体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月11日 18时53分
 */
public class RemoteDynamicCodeEntityImpl implements RemoteDynamicCodeEntityInterface {

    /**
     * 组id
     */
    private Identifier groupId;

    /**
     * 匹配是否成功
     */
    private Boolean matchSuccess;

    /**
     * 是否是临时替换
     */
    private Boolean temp;

    /**
     * 本体,需要远程获取
     */
    private List<DynamicCodeDTO> dynamicCodeDTOs;


    /**
     * 应用mark
     */
    private String mark;


    public RemoteDynamicCodeEntityImpl(Map<String, String> headers) {
        init(headers);
    }

    public RemoteDynamicCodeEntityImpl(List<DynamicCodeDTO> dynamicCodeDTOs) {
        List<Integer> groupIds = dynamicCodeDTOs.stream().map(DynamicCodeDTO::getGroupId).distinct().collect(Collectors.toList());
        Asserts.assertTrue(groupIds.size() == 1, "动态代码一次只能修改一个group");
        this.dynamicCodeDTOs = dynamicCodeDTOs;
        this.mark = SpringUtil.getProperty(DynamicContext.DYNAMIC_MARK_KEY, DynamicContext.DYNAMIC_MARK_DEFAULT_VALUE);
        this.groupId = new Identifier(groupIds.get(0).longValue());
        this.matchSuccess = true;
        this.temp = false;

    }

    /**
     * 初始化 参数
     *
     * @param headers
     */
    private void init(Map<String, String> headers) {
        String mark = headers.get(DynamicContext.APP_MARK_KEY);
        String serviceMark = SpringUtil.getProperty(DynamicContext.DYNAMIC_MARK_KEY, DynamicContext.DYNAMIC_MARK_DEFAULT_VALUE);
        this.mark = serviceMark;
        // 如果没有匹配到,就直接执行并返回
        this.matchSuccess = Objects.equals(mark, serviceMark);

        if (matchSuccess) {
            // 要替换的组id
            String groupId = headers.get(DynamicContext.DYNAMIC_GROUP_CODE);
            if (StringUtil.isNotEmpty(groupId)) {
                this.groupId = new Identifier(Long.parseLong(groupId));
            }
            // 是否是临时
            String temp = headers.get(DynamicContext.TEMP_KEY);
            if (Objects.equals(temp, DynamicContext.TEMP_VALUE_Y)) {
                // 临时
                this.temp = true;
            } else if (Objects.equals(temp, DynamicContext.TEMP_VALUE_N)) {
                // 永久
                this.temp = false;
            } else {
                Asserts.throwException(DynamicContext.TEMP_KEY + "错误:{}", temp);
            }
        }
    }

    @Override
    public Identifier getUnique() {
        return groupId;
    }

    @Override
    public void setUnique(Identifier unique) {
        Asserts.throwException("动态代码主表远程实体 不允许修改唯一标识,要修改成的标识为:{}", unique.getId());
    }

    @Override
    public boolean haveId() {
        return groupId != null;
    }

    @Override
    public boolean notHaveId() {
        return !haveId();
    }

    @Override
    public void fillDynamicCode(DynamicCodeFacade facade) {
        this.dynamicCodeDTOs = facade.getByGroupId(groupId);
    }

    @Override
    public Boolean isMatchSuccess() {
        return this.matchSuccess;
    }

    @Override
    public Boolean isTemp() {
        return temp;
    }

    @Override
    public Object tempDynamic(Supplier<Object> pjp) {
        // 1. 编译
        Map<String, byte[]> compile = compile();
        // 2. 获取临时替换后的classLoader
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(compile, groupId.getId().intValue());
        // 3. 临时classLoader替换当前线程classLoader
        ClassLoader classLoader = replaceClassLoaderToContent(dynamicClassLoader);
        // 4. 执行业务
        try {
            return pjp.get();
        } finally {
            // 5. 将之前的classLoader替换回来
            replaceClassLoaderToContent(classLoader);
        }
    }

    @Override
    public Object permanentDynamic(Supplier<Object> pjp, Boolean sendMq) {
        // 1. 编译
        Map<String, byte[]> compile = compile();
        // 2. 过滤已经修改过的
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader instanceof DynamicClassLoader) {
            DynamicClassLoader dynamicClassLoader = (DynamicClassLoader) contextClassLoader;
            int groupId = dynamicClassLoader.getGroupId();
            if (Objects.equals(groupId, this.groupId.getId().intValue())) {
                return pjp.get();
            }
        }
        // 3. 发送MQ消息,告诉本应用的其他机器也替换
        if (sendMq) {
            MqUtil.sendConfirmMsg(DynamicContext.DYNAMIC_MQ_EXCHANGE_NAME, this.mark, new ConfirmListener() {

                @Override
                public void handleAck(long l, boolean b) throws IOException {
                    LogUtil.warn(this, "动态代码替换任务处理成功");
                }

                @Override
                public void handleNack(long l, boolean b) throws IOException {
                    LogUtil.warn(this, "动态代码替换任务处理失败");
                }
            }, dynamicCodeDTOs);
        }
        // 4. 构建classLoader
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(compile, groupId.getId().intValue());
        // 5. 使用现在的ClassLoader替换,并且将使用的ClassLoader添加到 回滚ClassLoader的队列中
        replaceClassLoaderToContent(dynamicClassLoader);
        DynamicContext.nowClassLoader = dynamicClassLoader;
        // 6. 执行业务
        return pjp.get();
    }

    @Override
    public void replaceClassLoaderFromContent() {
        ClassLoader lastClassLoader = DynamicContext.nowClassLoader;
        Thread.currentThread().setContextClassLoader(lastClassLoader);
    }

    @Override
    public ClassLoader replaceClassLoaderToContent(ClassLoader classLoader) {
        Thread thread = Thread.currentThread();
        ClassLoader lastClassLoader = thread.getContextClassLoader();
        thread.setContextClassLoader(classLoader);
        return lastClassLoader;
    }

    /**
     * 编译对应的类
     *
     * @return
     */
    @Nullable
    private Map<String, byte[]> compile() {
        JavaStringCompiler javaStringCompiler = new JavaStringCompiler();
        Map<String, String> classCodes = new HashMap<>(dynamicCodeDTOs.size());
        for (DynamicCodeDTO dynamicCodeDTO : dynamicCodeDTOs) {
            String className = dynamicCodeDTO.getClassName();
            String content = dynamicCodeDTO.getContent();
            // 替换为路径
            if (className.contains(".")) {
                className = className.replace(".", "/") + ".java";
            }
            classCodes.put(className, content);
        }
        Map<String, byte[]> compile = null;
        try {
            compile = javaStringCompiler.compile(classCodes);

        } catch (IOException e) {
            LogUtil.error(this, e);
        }
        return compile;
    }

}
