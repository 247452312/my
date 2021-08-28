package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.command.AbstractCommand;

/**
 * 验证码验证request
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时47分
 */
public class VerificationCommand extends AbstractCommand {

    /**
     * 验证码
     */
    private String code;

    /**
     * redis中存储验证码的key
     */
    private String key;

    public static VerificationCommand build(String code, String key) {
        VerificationCommand build = new VerificationCommand();
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
