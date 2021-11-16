package indi.uhyils.protocol.mysql.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 08时24分
 */
public enum SqlTypeEnum {
    /**
     * 查询
     */
    QUERY(0),
    /**
     * 修改
     */
    UPDATE(1),
    /**
     * 插入
     */
    INSERT(2),
    /**
     * 删除
     */
    DELETE(3),
    /**
     * 显示
     */
    SHOW(4),
    /**
     * 详情
     */
    DESC(5),
    /**
     * 切换数据库
     */
    USE(6),
    /**
     * 刷新数据库
     */
    REFRESH(7),
    /**
     * 未知
     */
    NULL(8);

    private final Integer code;

    SqlTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
