package indi.uhyils.util;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月07日 12时32分
 */
public class JavaCompilerUtil {

    /**
     * 获取java的编译器
     */
    private static JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    private static Pattern pattern = Pattern.compile("package\\s+\\S+\\s*;");

    private static Pattern classPattern = Pattern.compile("class\\s+\\S+\\s+\\{");

    public static JavaCompilerResult getResult(String resource, String methodName, String[] methodType, Object[] args) throws Exception {
        DiagnosticCollector<JavaFileObject> diagnosticsCollector = new DiagnosticCollector<>();
        Map<String, ByteJavaFileObject> javaFileObjectMap = new ConcurrentHashMap<>();
        long startTime = System.currentTimeMillis();
        String fullClassName = getFullClassName(resource);
        //标准的内容管理器,更换成自己的实现，覆盖部分方法
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnosticsCollector, null, null);
        JavaFileManager javaFileManager = new StringJavaFileManage(standardFileManager, javaFileObjectMap);
        //构造源代码对象
        JavaFileObject javaFileObject = new StringJavaFileObject(fullClassName, resource);
        //获取一个编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, javaFileManager, diagnosticsCollector, null, null, Arrays.asList(javaFileObject));
        //设置编译耗时
        long compilerTime = System.currentTimeMillis() - startTime;
        Boolean call = task.call();
        if (!call) {
            throw new Exception("编译失败");
        }
        System.out.println("编译用时: " + compilerTime + "ms");

        PrintStream out = System.out;
        long runTakeTime = 0;
        String outPrint = null;
        Object invoke = null;
        try {
            long runStartTime = System.currentTimeMillis();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            //PrintStream PrintStream = new PrintStream("/Users/andy/Desktop/tem.sql"); //输出到文件
            System.setOut(printStream);

            StringClassLoader scl = new StringClassLoader(javaFileObjectMap);
            Class<?> aClass = scl.findClass(fullClassName);
            Class<?>[] methodParamType = new Class[methodType.length];
            for (int i = 0; i < methodType.length; i++) {
                try {
                    Class<?> paramClass = Class.forName(methodType[i]);
                    methodParamType[i] = paramClass;
                } catch (Exception e) {
                    methodParamType[i] = scl.findClass(methodType[i]);
                }
            }

            Method main = aClass.getMethod(methodName, methodParamType);
            //调用指定方法
            invoke = main.invoke(null, args);
            //设置运行耗时
            runTakeTime = System.currentTimeMillis() - runStartTime;
            //设置打印输出的内容
            outPrint = outputStream.toString("utf-8");

        } finally {
            //还原默认打印的对象
            System.setOut(out);
            System.out.println("运行耗时:" + runTakeTime + "ms");
            System.out.println("输出: " + outPrint);
            System.out.println("结果:" + invoke);
        }
        return JavaCompilerResult.build(invoke, compilerTime, runTakeTime, outPrint);
    }

    /**
     * 获取类的全名称
     *
     * @param sourceCode 源码
     * @return 类的全名称
     */
    private static String getFullClassName(String sourceCode) {
        String className = "";

        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            className = matcher.group().replaceFirst("package", "").replace(";", "").trim() + ".";
        }

        matcher = classPattern.matcher(sourceCode);
        if (matcher.find()) {
            className += matcher.group().replaceFirst("class", "").replace("{", "").trim();
        }
        return className;
    }


    public static void main(String[] args) throws Exception {
        String code = "public class H {\n" +
                "\n" +
                "    public static Integer add(Integer i, Integer j) {\n" +
                "        return i + j;\n" +
                "    }\n" +
                "}";

        Object[] pars = new Object[2];
        pars[0] = 1;
        pars[1] = 2;

        JavaCompilerResult add = JavaCompilerUtil.getResult(code, "add", new String[]{Integer.class.getName(), Integer.class.getName()}, pars);
        System.out.println(add.getOutPrint());
        System.out.println(add.getCompilerTime());
        System.out.println(add.getRunTime());
        System.out.println(add.getResult());
    }

}
