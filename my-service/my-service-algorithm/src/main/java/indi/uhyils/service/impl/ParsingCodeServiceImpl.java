package indi.uhyils.service.impl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.service.ParsingCodeService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.ExceptionUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.compiler.JavaStringCompiler;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 21时23分
 */
@Service
public class ParsingCodeServiceImpl implements ParsingCodeService {

    /**
     * 动态执行和解析的文件名称
     */
    private static final String FILE_NAME = "Test";

    /**
     * 文件类型
     */
    private static final String SUFFIX = ".java";

    /**
     * 要执行的方法名称
     */
    private static final String EXECUTE_METHOD_NAME = "main";

    @Override
    public String executeCode(String classValue) {
        if (StringUtils.isEmpty(classValue)) {
            return null;
        }
        try {
            JavaStringCompiler javaStringCompiler = new JavaStringCompiler();
            HashMap<String, String> fileSourceMap = new HashMap<>(1);
            fileSourceMap.put(FILE_NAME + SUFFIX, classValue);
            Map<String, byte[]> compile = javaStringCompiler.compile(fileSourceMap);
            Class<?> clazz = javaStringCompiler.loadClass(FILE_NAME, compile);
            Object obj = clazz.newInstance();
            Method main = clazz.getDeclaredMethod(EXECUTE_METHOD_NAME);
            main.setAccessible(Boolean.TRUE);
            Object invoke = main.invoke(obj);
            return JSON.toJSONString(invoke);
        } catch (IOException e) {
            LogUtil.error(e);
            Asserts.throwException("IO异常");
        } catch (ClassNotFoundException e) {
            LogUtil.error(e);
            Asserts.throwException("找不到正确的类名称,正确的类名称应为:" + FILE_NAME);
        } catch (InstantiationException e) {
            LogUtil.error(e);
            Asserts.throwException("初始化异常,类应该有空的构造器");
        } catch (IllegalAccessException e) {
            LogUtil.error(e);
            Asserts.throwException("访问权限异常,方法应该是public," + EXECUTE_METHOD_NAME);
        } catch (NoSuchMethodException e) {
            LogUtil.error(e);
            Asserts.throwException("方法不存在异常,方法应该是: " + EXECUTE_METHOD_NAME);
        } catch (InvocationTargetException e) {
            LogUtil.error(e);
            Asserts.throwException("方法执行异常,异常内容: \n" + ExceptionUtil.parseException(e));
        }
        Asserts.throwException("未知异常");
        return null;
    }
}
