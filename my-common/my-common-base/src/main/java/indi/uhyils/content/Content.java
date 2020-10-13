package indi.uhyils.content;

/**
 * 程序中用到的常量
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月10日 07时25分
 */
public class Content {

    /**
     * 空字符串
     */
    public static final String BLACK = "";

    /**
     * 作者
     */
    public static final String PROJECT_AUTHOR = "uhyils";

    /**
     * 项目名称
     */
    public static final String PROJECT_NAME = "my";


    /**
     * 未知
     */
    public static final String UN_KNOW = "unknown";

    /**
     * 超级管理员的id
     */
    public static final String ADMIN_USER_ID = "49ba59abbe56e057";

    /**
     * 超级管理员的角色id
     */
    public static final String ADMIN_ROLE_ID = "8c77ce2fa1a0c960";

    /**
     * 登录过期分钟
     */
    public static final Integer LOGIN_TIME_OUT_MIN = 30;

    /**
     * 验证码验证接口名称
     */
    public static final String VERIFICATION_CODE_INTERFACE = "VerificationService";
    /**
     * 验证码验证方法名称
     */
    public static final String VERIFICATION_CODE_METHOD = "verification";
    /**
     * 获取验证码方法(此方法不计入爬虫)
     */
    public static final String GET_VERIFICATION_CODE_METHOD = "getVerification";

    /**
     * 并发数数据字典的code
     */
    public static final String CONCURRENT_NUM_DICT_CODE = "concurrent_num_dict_code";

    /**
     * 接口禁用redis中的hash-key
     */
    public static final String SERVICE_USEABLE_SWITCH = "service_useable_switch";
    /**
     * service包前缀
     */
    public static final String SERVICE_PACKAGE_PREFIX = "indi.uhyils.service.";
}
