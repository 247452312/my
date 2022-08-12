package indi.uhyils.pojo.DTO.response;

import indi.uhyils.context.MyContext;
import indi.uhyils.enums.ServiceCode;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.DefaultCQEBuildUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.RpcApiUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 从前台后端返回前台前端的返回
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 16时57分
 */
public class WebResponse implements Serializable {


    private Integer code;

    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 构建一个错误的返回
     *
     * @param message
     * @param code
     *
     * @return
     */
    public static WebResponse buildWithError(String message, Integer code) {
        WebResponse serializableWebResponse = new WebResponse();
        serializableWebResponse.setCode(code);
        serializableWebResponse.setMsg(message);
        return serializableWebResponse;
    }

    public static <T extends Serializable> WebResponse build(T data, ServiceCode code) {
        try {
            if (code == ServiceCode.SPIDER_VERIFICATION) {
                WebResponse serializableWebResponse = new WebResponse();
                serializableWebResponse.setCode(code.getText());

                List<DefaultCQE> args = new ArrayList<>();
                args.add(DefaultCQEBuildUtil.getAdminDefaultCQE());
                serializableWebResponse.setData(RpcApiUtil.rpcApiTool(MyContext.VERIFICATION_CODE_INTERFACE, MyContext.GET_VERIFICATION_CODE_METHOD, args, new DefaultCQE()));
                serializableWebResponse.setMsg(code.getMsg());
                return serializableWebResponse;
            }
        } catch (Throwable throwable) {
            LogUtil.error(throwable, "验证码验证异常,请联系管理员.");
        }
        WebResponse serializableWebResponse = new WebResponse();
        serializableWebResponse.setCode(code.getText());
        serializableWebResponse.setData(data);
        serializableWebResponse.setMsg(code.getMsg());
        return serializableWebResponse;
    }

    public static WebResponse build(ServiceResult serviceResult) {
        return build(serviceResult.getData(), serviceResult.getServiceMessage(), serviceResult.getServiceCode());
    }

    public static WebResponse build(Object data, String message, Integer code) {
        WebResponse serializableWebResponse = new WebResponse();
        serializableWebResponse.setCode(code);
        serializableWebResponse.setData(data);
        serializableWebResponse.setMsg(message);
        return serializableWebResponse;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
