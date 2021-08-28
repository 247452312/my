package indi.uhyils.util;

import indi.uhyils.context.MyContext;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * 创建一个默认的ADMIN用户的请求 此方法只用来作服务之间的调用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 07时25分
 */
public final class DefaultRequestBuildUtil {

    private DefaultRequestBuildUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取管理员的默认请求, 内部用
     *
     * @return 管理员的默认请求
     */
    public static DefaultCQE getAdminDefaultRequest() {
        DefaultCQE defaultRequest = new DefaultCQE();
        UserDTO user = new UserDTO();
        user.setId(MyContext.ADMIN_USER_ID);
        user.setUserName("admin");
        defaultRequest.setUser(user);
        return defaultRequest;
    }

    /**
     * 用管理员身份填充一个请求
     *
     * @param t
     * @param <T>
     */
    public static <T extends DefaultCQE> void fillRequestByAdminRequest(T t) {
        DefaultCQE adminDefaultRequest = getAdminDefaultRequest();
        t.setUnique(adminDefaultRequest.getUnique());
        t.setUser(adminDefaultRequest.getUser());
    }

}
