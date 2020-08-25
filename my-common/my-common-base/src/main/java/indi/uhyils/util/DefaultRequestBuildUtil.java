package indi.uhyils.util;

import indi.uhyils.content.Content;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 07时25分
 */
public class DefaultRequestBuildUtil {

    /**
     * 获取管理员的默认请求, 内部用
     *
     * @return 管理员的默认请求
     */
    public static DefaultRequest getAdminDefaultRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        UserEntity user = new UserEntity();
        user.setId(Content.ADMIN_USER_ID);
        user.setUserName("admin");
        defaultRequest.setUser(user);
        defaultRequest.setRequestLink(new LinkNode<>());
        return defaultRequest;
    }

}
