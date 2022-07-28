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

        // 解析线程池包装异常
        if (th instanceof ExecutionException) {
            return onException(th.getCause());
        }
        // 解析rpc包装异常
        if (th instanceof MyRpcProviderThrowException) {
            return onException(th.getCause());
        }
        // 断言异常 返回前端
        if (th instanceof AssertException) {
            return onAssertException((AssertException) th);
        }
        // 未登录异常返回前端
        if (th instanceof NoLoginException) {
            return onNoLoginException((NoLoginException) th);
        }
        // rpc网络异常返回前端
        if (th instanceof RpcNetException) {
            return onRpcNetException((Exception) th);
        }
        // 其他异常(不暴露异常信息)
        return onOtherException(th);

    }

    /**
     * 返回前端其他异常
     *
     * @param th
     *
     * @return
     */
    private static WebResponse onOtherException(Throwable th) {
        LogUtil.error(th);
        return WebResponse.buildWithError("系统异常,请联系管理员!", ServiceCode.ERROR.getText());
    }

    /**
     * 服务器错误
     *
     * @param th
     *
     * @return
     */
    private static WebResponse onRpcNetException(Exception th) {
        return WebResponse.buildWithError("网络异常:" + th.getMessage(), ServiceCode.ERROR.getText());
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
