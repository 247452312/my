package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.pojo.DO.ProviderInterfaceParamDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.util.Asserts;

/**
 * 接口参数表(ProviderInterfaceParam)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceParam extends AbstractDoEntity<ProviderInterfaceParamDO> {

    @Default
    public ProviderInterfaceParam(ProviderInterfaceParamDO data) {
        super(data);
    }

    public ProviderInterfaceParam(Long id) {
        super(id, new ProviderInterfaceParamDO());
    }

    public String name() {
        return toDataAndValidate().getName();
    }

    public FieldTypeEnum type() {
        Integer type = toDataAndValidate().getType();
        switch (type) {
            case 1:
                return FieldTypeEnum.FIELD_TYPE_VARCHAR;
            case 2:
                return FieldTypeEnum.FIELD_TYPE_INT24;
            default:
                Asserts.throwException("未找到的参数类型");
                return null;
        }

    }

}
