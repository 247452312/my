package indi.uhyils.util;

import indi.uhyils.enums.ServiceCode;
import indi.uhyils.exception.AssertException;
import indi.uhyils.exception.NoLoginException;
import indi.uhyils.pojo.DTO.response.WebResponse;
import indi.uhyils.rpc.exception.MyRpcProviderThrowException;
import indi.uhyils.rpc.exception.RpcNetException;
import java.util.concurrent.ExecutionException;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月27日 09时13分
 */
public class WebExceptionHandler {

    /**
     * 遇到要返回前台的错误时如何解析
     *
     * @return
     */
    public static WebResponse onException(Throwable th) {

        if (th instanceof ExecutionException) {
            return onException(th.getCause());
        }
        if (th instanceof MyRpcProviderThrowException) {
            return onException(th.getCause());
        }
        if (th instanceof AssertException) {
            return onAssertException((AssertException) th);
        }
        if (th instanceof NoLoginException) {
            return onNoLoginException((NoLoginException) th);
        }
        if (th instanceof RpcNetException) {
            return onOtherException((Exception) th);
        }
        return null;

    }

    /**
     * 服务器错误
     *
     * @param th
     *
     * @return
     */
    private static WebResponse onOtherException(Exception th) {
        return WebResponse.buildWithError(th.getMessage(), ServiceCode.ERROR.getText());
    }

    /**
     * 需要登录时的返回
     *
     * @param nle
     *
     * @return
     */
    private static WebResponse onNoLoginException(NoLoginException nle) {
        return WebResponse.buildWithError(nle.getMessage(), ServiceCode.NO_LOGIN__ERROR.getText());
    }

    /**
     * 断言异常
     *
     * @param ae
     *
     * @return
     */
    private static WebResponse onAssertException(AssertException ae) {
        return WebResponse.buildWithError(ae.getMessage(), ServiceCode.ASSERT_EXCEPTION.getText());
    }

}
