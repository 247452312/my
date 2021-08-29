package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.BaseDTO;

/**
 * 请求验证码返回类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时29分
 */
public class VerificationGetDTO implements BaseDTO {

    /**
     * 验证码对应在redis中的key
     */
    private String key;

    /**
     * 图片的base64编码
     */
    private String picBase64;

    public static VerificationGetDTO build(String base64, String key) {
        VerificationGetDTO build = new VerificationGetDTO();
        build.setPicBase64(base64);
        build.setKey(key);
        return build;

    }

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
}
