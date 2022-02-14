package indi.uhyils.context;

/**
 * 动态代码 常量
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月11日 19时29分
 */
public class DynamicContext {

    /**
     * 应用mark 在header中的key
     */
    public static final String APP_MARK_KEY = "dynamic_app_mark";

    /**
     * 临时 在header中的key
     */
    public static final String TEMP_KEY = "dynamic_temp";

    /**
     * 临时在header中的value
     */
    public static final String TEMP_VALUE_Y = "1";

    /**
     * 永久在header中的value
     */
    public static final String TEMP_VALUE_N = "0";


    /**
     * 动态代码组id
     */
    public static final String DYNAMIC_GROUP_CODE = "dynamic_group_code";

    /**
     * 动态代码MQ exchange
     */
    public static final String DYNAMIC_MQ_EXCHANGE_NAME = "dynamic_exchange";

    /**
     * 记录现在程序应该变成的classLoader
     */
    public static volatile ClassLoader nowClassLoader = DynamicContext.class.getClassLoader();

}
