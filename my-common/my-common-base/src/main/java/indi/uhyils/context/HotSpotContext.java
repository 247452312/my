package indi.uhyils.context;

/**
 * 热点缓存的值
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月11日 14时45分
 */
public interface HotSpotContext {

    /**
     * 热点缓存中的tables_hash_key
     */
    String TABLES_HASH_KEY = "TABLES_HASH_KEY";

    /**
     * 初始化时的表
     */
    String INIT_TABLE_NAME = "sys_dict";

    /**
     * 热点缓存中的热点hashkey规则, 接口名称:方法名称:参数MD5
     */
    String HOTSPOT_HASH_KEY_ROLE = "%s:%s:%s";


    /**
     * 热点中的真实值
     */
    String HOTSPOT_HASH_DATA_KEY = "data";

    /**
     * 缓存中的类型标记
     */
    String CACHE_TYPE_MARK = "cache_type_mark";
}
