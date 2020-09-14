package indi.uhyils.pojo.response;

import java.io.Serializable;

/**
 * 请求验证码返回类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时29分
 */
public class VerificationGetResponse implements Serializable {
    /**
     * 验证码对应在redis中的key
     */
    private String key;
    /**
     * 图片的base64编码
     */
    private String picBase64;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPicBase64() {
        return picBase64;
    }

    public void setPicBase64(String picBase64) {
        this.picBase64 = picBase64;
    }

    public static VerificationGetResponse build(String base64, String key) {
        VerificationGetResponse build = new VerificationGetResponse();
        build.setPicBase64(base64);
        build.setKey(key);
        return build;

    }
}
