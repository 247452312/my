package indi.uhyils.util.python;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月16日 09时22分
 */
public class PythonUtil {

    /**
     * 默认入参
     */
    private static final Map<String, Object> DEFAULT_PARAM = new HashMap<>(0);

    /**
     * 设置一个关键key，用于从python中获取结果
     */
    private static final String RESULT_KEY = "result";

    /**
     * 系统变量
     */
    static {
        Properties props = new Properties();
        String property = SpringUtil.getProperty("python.home", "C:\\Users\\Lenovo\\AppData\\Local\\Programs\\Python\\Python38");
        props.put("python.home", property);
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preProps = System.getProperties();
        PythonInterpreter.initialize(preProps, props, new String[0]);
    }

    /**
     * 执行python
     *
     * @param srcPaths 资源文件们
     * @param params   入参
     *
     * @return
     */
    public static Object executePython(URI[] srcPaths, String methodName, Object[] params) throws IOException {
        InputStream[] inputStreams = Arrays.stream(srcPaths).map(t -> {
            try {
                return t.toURL().openConnection().getInputStream();
            } catch (IOException e) {
                LogUtil.error(e);
                return null;
            }
        }).filter(Objects::nonNull).toArray(InputStream[]::new);
        return executePython(inputStreams, methodName, params);
    }

    /**
     * 执行python
     *
     * @param srcPaths 资源文件们
     * @param params   入参
     *
     * @return
     */
    public static Object executePython(InputStream[] srcPaths, String methodName, Object[] params) throws IOException {
        Asserts.assertTrue(params != null, "入参不能为空,请检查");
        PythonInterpreter interpreter = null;
        Object result;
        try {
            interpreter = new PythonInterpreter();
            for (InputStream uri : srcPaths) {
                try {
                    interpreter.execfile(uri);
                } finally {
                    if (uri != null) {
                        uri.close();
                    }
                }
            }
            PyFunction pyFunction = interpreter.get(methodName, PyFunction.class);
            PyObject[] pyParams = Arrays.stream(params).map(Py::java2py).toArray(PyObject[]::new);
            PyObject pyObject = pyFunction.__call__(pyParams);
            result = pyObject.__tojava__(Object.class);
        } finally {
            if (interpreter != null) {
                interpreter.close();
            }
        }
        return result;
    }

    /**
     * 执行写好的python内容
     *
     * @param pythonStr python内容
     *
     * @return
     */
    public static Object executePython(String pythonStr) {
        return executePython(pythonStr, DEFAULT_PARAM);
    }

    /**
     * 执行写好的python内容
     *
     * @param pythonStr python内容
     * @param params    入参
     *
     * @return
     */
    public static Object executePython(String pythonStr, Map<String, Object> params) {
        try (PythonInterpreter pythonInterpreter = new PythonInterpreter()) {
            params.forEach((key, value) -> {
                PyObject pyValue;
                if (value instanceof String) {
                    //中文乱码解决
                    pyValue = Py.newStringUTF8(value.toString());
                } else if (value instanceof Float || value instanceof Double) {
                    //python和java浮点精度不一致问题，都转为double
                    pyValue = Py.newFloat(Double.parseDouble(value.toString()));
                } else if (value instanceof Integer) {
                    pyValue = Py.newInteger((int) value);
                } else {
                    pyValue = Py.java2py(value);
                }
                pythonInterpreter.set(key, pyValue);
            });
            pythonStr = pythonStr.replace("return", RESULT_KEY + "=");

            PyString pyString = Py.newStringUTF8(pythonStr);
            pythonInterpreter.exec(pyString);
            return pythonInterpreter.get(RESULT_KEY).toString();
        } catch (Exception e) {
            LogUtil.error("python脚本出错", e);
        }
        return null;
    }

    /**
     * 执行写好的python内容
     *
     * @param pythonStr python内容
     *
     * @return
     */
    public static Object executePython(String pythonStr, String methodName, Object[] params) throws IOException {
        InputStream charSequenceInputStream = new CharSequenceInputStream(pythonStr, StandardCharsets.UTF_8);
        return executePython(new InputStream[]{charSequenceInputStream}, methodName, params);
    }


}
