//package indi.uhyils.rpc.exception;
//
//import indi.uhyils.rpc.util.ExceptionUtil;
//import indi.uhyils.util.Asserts;
//import org.junit.jupiter.api.Test;
//
///**
// * @author uhyils <247452312@qq.com>
// * @date 文件创建日期 2022年06月29日 11时17分
// */
//class TargetExceptionFactoryTest {
//
//    @Test
//    void createExceptionByExceptionStr() throws Throwable {
//String s = ExceptionUtil.parseException(new RuntimeException(new MyRpcProviderThrowException("hello")));
//MyRpcProviderThrowException exception = ProviderExceptionFactory.createExceptionByExceptionStr(s);
//        Asserts.assertException(() -> {
//            throw exception;
//        });
//    }
//}
