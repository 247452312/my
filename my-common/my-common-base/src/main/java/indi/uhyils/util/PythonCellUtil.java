package indi.uhyils.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;

/**
 * python文件调用接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月22日 06时44分
 */
public final class PythonCellUtil {

    /**
     * 寻找python源文件在哪里
     */
    private static final String PYTHON_PATH;

    /**
     * python文件的名称
     */
    private static final String PYTHON_FILE_NAME = "python.exe";

    static {
        PYTHON_PATH = getPythonPath(System.getenv("PYTHON_HOME"), System.getenv("path"));
    }

    private PythonCellUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * python调用
     *
     * @param pythonExePath python路径
     * @param pythonPath    要执行的文件的路径
     * @param args          参数
     *
     * @return 执行结果
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static String cellPython(String pythonExePath, String pythonPath, List<String> args) throws Exception {
        if (pythonExePath == null || !pythonExePath.endsWith(PYTHON_FILE_NAME)) {
            throw new Exception("python.exe路径错误");
        }
        StringBuilder sb = new StringBuilder(pythonExePath);
        sb.append(" ");
        sb.append(pythonPath);
        sb.append(" ");
        for (String arg : args) {
            sb.append(arg);
            sb.append(" ");
        }
        Process exec = Runtime.getRuntime().exec(sb.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        StringBuilder result = new StringBuilder();
        String get;
        while (!StringUtils.isEmpty(get = br.readLine())) {
            result.append(get);
        }
        br.close();
        exec.waitFor();
        return result.toString();
    }

    /**
     * 执行python文件
     *
     * @param pythonPath pythonPath
     * @param args       参数
     *
     * @return 执行结果
     *
     * @throws Exception
     */
    public static String cellPython(String pythonPath, List<String> args) throws Exception {
        return cellPython(PYTHON_PATH, pythonPath, args);
    }

    private static String getPythonPath(String... pythonHome) {
        List<String> paths = new ArrayList<>();
        for (String s : pythonHome) {
            if (s == null) {
                continue;
            }
            String[] split = s.split(";");
            paths.addAll(Arrays.asList(split));
        }
        for (String path : paths) {
            if (!StringUtils.isEmpty(path)) {
                File file = new File(path);
                if (file.exists()) {
                    if (file.isFile() && file.getName().equalsIgnoreCase(PYTHON_FILE_NAME)) {
                        return file.getPath();
                    } else if (file.isDirectory()) {
                        File[] files = file.listFiles();
                        assert files != null;
                        List<File> collect = Arrays.stream(files).filter(f -> f.isFile() && f.getName().equalsIgnoreCase(PYTHON_FILE_NAME)).collect(Collectors.toList());
                        if (collect.size() == 1) {
                            return collect.get(0).getPath();
                        }
                    }
                }
            }
        }
        return null;
    }
}
