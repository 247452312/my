package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.DTO.request.base.DefaultRequest;

/**
 * 验证码验证request
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时47分
 */
public class VerificationRequest extends DefaultRequest {

    /**
     * 验证码
     */
    private String code;

    /**
     * redis中存储验证码的key
     */
    private String key;

    public static VerificationRequest build(String code, String key) {
        VerificationRequest build = new VerificationRequest();
        build.setCode(code);
        build.setKey(key);
        return build;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
