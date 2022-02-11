package indi.uhyils.pojo.entity.impl;

import indi.uhyils.annotation.Nullable;
import indi.uhyils.context.DynamicContext;
import indi.uhyils.facade.DynamicCodeFacade;
import indi.uhyils.loader.DynamicClassLoader;
import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.entity.RemoteDynamicCodeEntityInterface;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.compiler.JavaStringCompiler;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

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


    public RemoteDynamicCodeEntityImpl(Map<String, String> headers) {
        init(headers);
    }

    /**
     * 初始化 参数
     *
     * @param headers
     */
    private void init(Map<String, String> headers) {
        String mark = headers.get(DynamicContext.APP_MARK_KEY);
        String serviceMark = SpringUtil.getProperty("dynamic.mark", "allService");
        // 如果没有匹配到,就直接执行并返回
        this.matchSuccess = Objects.equals(mark, serviceMark);

        // 要替换的组id
        String groupId = headers.get(DynamicContext.DYNAMIC_GROUP_CODE);
        this.groupId = new Identifier(Long.parseLong(groupId));

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
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(compile);
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
    public Object permanentDynamic(Supplier<Object> pjp) {
        // 1. 编译
        Map<String, byte[]> compile = compile();
        // 2. 发送MQ消息,告诉本应用的其他机器也替换 todo
        // 3. 构建classLoader
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(compile);
        // 4. 使用现在的ClassLoader替换,并且将使用的ClassLoader添加到 回滚ClassLoader的队列中
        replaceClassLoaderToContent(dynamicClassLoader);
        DynamicContext.nowClassLoader = dynamicClassLoader;
        // 5. 执行业务
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
