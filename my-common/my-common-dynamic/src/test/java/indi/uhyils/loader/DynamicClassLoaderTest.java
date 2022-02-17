package indi.uhyils.loader;

import indi.uhyils.util.LogUtil;
import indi.uhyils.util.compiler.JavaStringCompiler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月15日 12时23分
 */
class DynamicClassLoaderTest {

    @Test
    void loadClass() throws IOException {

        String studentClass = "package indi.uhyils.loader;\n"
                              + "\n"
                              + "/**\n"
                              + " * @author uhyils <247452312@qq.com>\n"
                              + " * @date 文件创建日期 2022年02月15日 12时30分\n"
                              + " */\n"
                              + "public class Student {\n"
                              + "\n"
                              + "\n"
                              + "    private String name;\n"
                              + "\n"
                              + "    public String getName() {\n"
                              + "        return \"123\";\n"
                              + "    }\n"
                              + "\n"
                              + "    public void setName(String name) {\n"
                              + "        this.name = name;\n"
                              + "    }\n"
                              + "}\n";

        JavaStringCompiler javaStringCompiler = new JavaStringCompiler();
        HashMap<String, String> fileSourceMap = new HashMap<>();
        fileSourceMap.put("indi/uhyils/loader/Student.java", studentClass);
        Map<String, byte[]> compile = javaStringCompiler.compile(fileSourceMap);

        TestClassLoader testClassLoader = new TestClassLoader(compile);

        Thread thread = new Thread(() -> {
            Class<?> aClass = null;
            try {
                aClass = Thread.currentThread().getContextClassLoader().loadClass("indi.uhyils.loader.Student");
            } catch (ClassNotFoundException e) {
                LogUtil.error(this, e);
            }
            Student o = null;
            try {
                o = (Student) aClass.newInstance();
            } catch (InstantiationException e) {
                LogUtil.error(this, e);
            } catch (IllegalAccessException e) {
                LogUtil.error(this, e);
            }
            String name1 = o.getName();
            System.out.println("classForname出来的name:" + name1);
            Student student = new Student();
            String name = student.getName();
            System.out.println("内部" + name);
            ClassLoader classLoader = student.getClass().getClassLoader();
            System.out.println("内部classLoader:" + classLoader);
            System.out.println("内部线程classLoader:" + Thread.currentThread().getContextClassLoader());
        });
        thread.setContextClassLoader(testClassLoader);
        thread.start();
        Student student = new Student();
        String name = student.getName();
        System.out.println("外部" + name);
        ClassLoader classLoader = student.getClass().getClassLoader();
        System.out.println("外部classLoader:" + classLoader);
        System.out.println("外部线程classLoader:" + Thread.currentThread().getContextClassLoader());


    }
}
