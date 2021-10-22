package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.InterfaceInfoRepository;

/**
 * 接口信息表(InterfaceInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
public class InterfaceInfo extends AbstractDoEntity<InterfaceInfoDO> {

    @Default
    public InterfaceInfo(InterfaceInfoDO data) {
        super(data);
    }

    public InterfaceInfo(Long id) {
        super(id, new InterfaceInfoDO());
    }

    public InterfaceInfo(Long id, InterfaceInfoRepository rep) {
        super(id, new InterfaceInfoDO());
        completion(rep);
    }

    public InterfaceInfo(Identifier id) {
        super(id, new InterfaceInfoDO());
    }

}
