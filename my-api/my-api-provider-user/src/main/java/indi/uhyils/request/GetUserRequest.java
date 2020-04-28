package indi.uhyils.request;

import indi.uhyils.enum_.UserTypeEnum;

/**
 * 用来获取用户的Request
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月28日 16时41分
 */
public class GetUserRequest extends DefaultRequest {

    private String id;
    private UserTypeEnum userType;

    public static GetUserRequest build(String touristUserId, UserTypeEnum userType) {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setId(touristUserId);
        getUserRequest.setUserType(userType);
        return getUserRequest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}
