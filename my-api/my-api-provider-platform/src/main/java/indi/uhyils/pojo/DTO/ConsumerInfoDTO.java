package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 服务消费方信息表(ConsumerInfo)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分39秒
 */
public class ConsumerInfoDTO extends IdDTO {

    private static final long serialVersionUID = -31479931518572026L;


    /**
     * 责任人电话
     */
    private String responsibilityTelPhone;

    /**
     * 责任人名称
     */
    private String responsibilityName;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * 消费者名称
     */
    private String name;

    /**
     * 状态(0->申请中 1->使用中 2->已停用)
     */
    private Integer status;

    /**
     * 登录后此消费者的token
     */
    private String token;

    /**
     * 登录的user
     */
    private UserDTO loginUser;


    public String getResponsibilityTelPhone() {
        return responsibilityTelPhone;
    }

    public void setResponsibilityTelPhone(String responsibilityTelPhone) {
        this.responsibilityTelPhone = responsibilityTelPhone;
    }


    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserDTO loginUser) {
        this.loginUser = loginUser;
    }
}
