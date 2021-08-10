package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.pojo.request.ExecuteCodeRequest;
import indi.uhyils.pojo.response.ExecuteCodeResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ParsingCodeService;
import indi.uhyils.util.compiler.JavaStringCompiler;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月10日 08时47分
 */
@RpcService
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
//
//    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
//        ParsingCodeServiceImpl parsingCodeService = new ParsingCodeServiceImpl();
//        ExecuteCodeRequest request = new ExecuteCodeRequest();
//        request.setClassValue(new StringBuilder().append("public class Test {\n  public String main() {\n        return \"asdasd\";\n    }  \n}")
//                                                 .toString());
//        ServiceResult<ExecuteCodeResponse> executeCodeResponseServiceResult = parsingCodeService.executeCode(request);
//        System.out.println(executeCodeResponseServiceResult.validationAndGet().getResponseJson());
//    }


    @Override
    public ServiceResult<ExecuteCodeResponse> executeCode(ExecuteCodeRequest request) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        String value = request.getClassValue();
        if (StringUtils.isEmpty(value)) {
            return ServiceResult.buildSuccessResult(new ExecuteCodeResponse());
        }
        JavaStringCompiler javaStringCompiler = new JavaStringCompiler();
        HashMap<String, String> fileSourceMap = new HashMap<>(1);
        fileSourceMap.put(FILE_NAME + SUFFIX, value);
        Map<String, byte[]> compile = javaStringCompiler.compile(fileSourceMap);
        Class<?> clazz = javaStringCompiler.loadClass(FILE_NAME, compile);
        Object obj = clazz.newInstance();
        Method main = clazz.getDeclaredMethod(EXECUTE_METHOD_NAME);
        main.setAccessible(Boolean.TRUE);
        Object invoke = main.invoke(obj);
        return ServiceResult.buildSuccessResult(ExecuteCodeResponse.build(JSON.toJSONString(invoke)));
    }
}
