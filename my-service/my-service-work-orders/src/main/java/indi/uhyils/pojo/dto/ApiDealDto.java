package indi.uhyils.pojo.dto;

import indi.uhyils.enum_.ApiCodeEnum;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月25日 07时08分
 */
public class ApiDealDto implements Serializable {

    /**
     * 处理状态
     */
    private ApiCodeEnum code;

    /**
     * 下一节点需要的信息
     */
    private Object result;

    public static ApiDealDto build(ApiCodeEnum code, Object result) {
        ApiDealDto build = new ApiDealDto();
        build.code = code;
        build.result = result;
        return build;
    }

    public static ApiDealDto build(ApiCodeEnum code) {
        ApiDealDto build = new ApiDealDto();
        build.code = code;
        return build;
    }

    public ApiCodeEnum getCode() {
        return code;
    }

    public void setCode(ApiCodeEnum code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


}
