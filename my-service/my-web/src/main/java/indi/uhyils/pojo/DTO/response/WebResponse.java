package indi.uhyils.pojo.DTO.response;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.context.MyContext;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.util.DefaultRequestBuildUtil;
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
public class WebResponse<T extends Serializable> implements Serializable {


    private Integer code;

    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public static <T extends Serializable> WebResponse build(T data, String message, Integer code) {
        WebResponse<T> serializableWebResponse = new WebResponse();
        serializableWebResponse.setCode(code);
        serializableWebResponse.setData(data);
        serializableWebResponse.setMsg(message);
        return serializableWebResponse;
    }

    public static <T extends Serializable> WebResponse build(T data, ServiceCode code) {
        if (code == ServiceCode.SPIDER_VERIFICATION) {
            WebResponse<VerificationGetDTO> serializableWebResponse = new WebResponse<>();
            serializableWebResponse.setCode(code.getText());

            List args = new ArrayList();
            args.add(DefaultRequestBuildUtil.getAdminDefaultRequest());
            ServiceResult<JSONObject> serviceResult = (ServiceResult) RpcApiUtil.rpcApiTool(MyContext.VERIFICATION_CODE_INTERFACE, MyContext.GET_VERIFICATION_CODE_METHOD, args, new DefaultRequest());
            JSONObject verification = serviceResult.getData();
            VerificationGetDTO verificationGetResponse = verification.toJavaObject(VerificationGetDTO.class);
            serializableWebResponse.setData(verificationGetResponse);

            serializableWebResponse.setMsg(code.getMsg());
            return serializableWebResponse;
        }
        WebResponse<T> serializableWebResponse = new WebResponse();
        serializableWebResponse.setCode(code.getText());
        serializableWebResponse.setData(data);
        serializableWebResponse.setMsg(code.getMsg());
        return serializableWebResponse;
    }

    public static <T extends Serializable> WebResponse build(ServiceResult serviceResult) {
        return build(serviceResult.getData(), serviceResult.getServiceMessage(), serviceResult.getServiceCode());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
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