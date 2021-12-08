package indi.uhyils.pojo.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.annotation.Default;
import indi.uhyils.exception.AlgorithmException;
import indi.uhyils.pojo.DO.AlgorithmDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.AlgorithmRepository;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.compiler.JavaStringCompiler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 算法表(Algorithm)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月09日 20时58分06秒
 */
public class Algorithm extends AbstractDoEntity<AlgorithmDO> {

    /**
     * 启动类
     */
    private static final String MAIN_CLASS_NAME = "Algorithm";

    /**
     * 启动方法名称
     */
    private static final String MAIN_METHOD_NAME = "cell";

    /**
     * 类名标识符
     */
    private static final String CLASS_NAME_INDEX_MARK = "public class";

    @Default
    public Algorithm(AlgorithmDO data) {
        super(data);
    }

    public Algorithm(Long id) {
        super(id, new AlgorithmDO());
    }

    public Algorithm(Long id, AlgorithmRepository rep) {
        super(id, new AlgorithmDO());
        completion(rep);
    }

    public Algorithm(Identifier id) {
        super(id, new AlgorithmDO());
    }

    /**
     * 执行方法
     *
     * @param requestBody
     *
     * @return
     */
    public Object cell(Object... requestBody) {
        AlgorithmDO algorithmDO = toData();
        String body = algorithmDO.getBody();
        JSONArray jsonArray = JSON.parseArray(body);

        JavaStringCompiler javaStringCompiler = new JavaStringCompiler();
        HashMap<String, String> fileSourceMap = new HashMap<>(jsonArray.size());
        // 解析java文件名称
        for (Object fileBody : jsonArray) {
            String fileBodyStr = (String) fileBody;
            String fileName = parseFileName(fileBodyStr);
            fileSourceMap.put(fileName + ".java", fileBodyStr);
        }

        Object invoke;
        try {
            Map<String, byte[]> compile = javaStringCompiler.compile(fileSourceMap);
            Class<?> c = javaStringCompiler.loadClass(MAIN_CLASS_NAME, compile);
            Object o = c.newInstance();
            Optional<Method> first = Arrays.stream(c.getDeclaredMethods()).filter(t -> Objects.equals(t.getName(), MAIN_METHOD_NAME)).findFirst();
            if (!first.isPresent()) {
                throw new AlgorithmException("不存在指定入口方法:" + MAIN_METHOD_NAME);
            }
            Method declaredMethod = first.get();
            declaredMethod.setAccessible(Boolean.TRUE);
            invoke = declaredMethod.invoke(o, requestBody);
            LogUtil.info("动态执行算法:" + algorithmDO.getName());
            LogUtil.info("结果为:\n" + invoke.toString());
        } catch (Exception e) {
            LogUtil.error(this, e);
            throw new AlgorithmException(e);
        }
        return invoke;
    }


    /**
     * 从java文件字符串中解析文件名称
     *
     * @param fileBodyStr
     *
     * @return
     */
    private String parseFileName(String fileBodyStr) {
        StringBuilder result = new StringBuilder();
        int index = fileBodyStr.indexOf(CLASS_NAME_INDEX_MARK);
        boolean startIng = false;
        for (int i = index + CLASS_NAME_INDEX_MARK.length(); i < fileBodyStr.length(); i++) {
            char c = fileBodyStr.charAt(i);
            if (!startIng) {
                if (c == ' ') {
                    startIng = true;
                    while (fileBodyStr.length() > i && fileBodyStr.charAt(i) == ' ') {
                        i++;
                    }
                }
            }

            if (startIng) {
                char classNameChar = fileBodyStr.charAt(i);
                if (classNameChar == ' ') {
                    break;
                }
                result.append(classNameChar);
            }
        }
        return result.toString();
    }
}
