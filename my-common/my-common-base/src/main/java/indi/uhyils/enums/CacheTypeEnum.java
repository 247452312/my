package indi.uhyils.enums;

/**
 * 缓存类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月12日 08时41分
 */
public enum CacheTypeEnum {
    /**
     * 同一角色可以使用缓存
     */
    ROLE_TYPE(1),
    /**
     * 所有人都可以使用缓存
     */
    ALL_TYPE(0),
    /**
     * 不可以使用缓存
     */
    NOT_TYPE(3);

    private final Integer cacheType;

    CacheTypeEnum(Integer cacheType) {
        this.cacheType = cacheType;
    }
}
