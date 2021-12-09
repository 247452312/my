package indi.uhyils.util.compiler;

import indi.uhyils.util.LogUtil;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年02月25日 07时32分
 * @Version 1.0
 */
public class Demo {

    private static final String s = "package indi.uhyils;"
                                    + "public class H {\n" +
                                    "    public int add(int a, int b) {\n" +
                                    "        return a + b;\n" +
                                    "    }\n" +
                                    "}";

    private static final String c = "package indi.uhyils.model;"
                                    + "import indi.uhyils.H;"
                                    + "public class C {\n" +
                                    "\n" +
                                    "    public int add(int a, int b) {\n" +
                                    "        H h = new H();\n" +
                                    "        return h.add(a, b);\n" +
                                    "    }\n" +
                                    "}";

    public static void main(String[] args) throws Exception {
        JavaStringCompiler javaStringCompiler = new JavaStringCompiler();
        HashMap<String, String> fileSourceMap = new HashMap<>(2);
        fileSourceMap.put("indi/uhyils/H.java", s);
        fileSourceMap.put("indi/uhyils/model/C.java", c);
        Map<String, byte[]> compile = javaStringCompiler.compile(fileSourceMap);
        Class<?> c = javaStringCompiler.loadClass("indi.uhyils.model.C", compile);
        Object o = c.newInstance();
        Method[] declaredMethods = c.getDeclaredMethods();
        Method add = declaredMethods[0];
        add.setAccessible(Boolean.TRUE);
        Object invoke = add.invoke(o, 1, 2);
        LogUtil.info(invoke.toString());
    }

}
