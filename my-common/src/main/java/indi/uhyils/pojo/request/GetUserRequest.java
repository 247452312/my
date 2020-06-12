package indi.uhyils.pojo.request;


/**
 * 用来获取用户的Request
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月28日 16时41分
 */
public class GetUserRequest extends DefaultRequest {

    private String id;

    public static GetUserRequest build(String touristUserId) {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setId(touristUserId);
        return getUserRequest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
