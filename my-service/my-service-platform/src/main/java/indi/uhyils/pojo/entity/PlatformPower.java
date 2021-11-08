package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.PlatformPowerDO;
import indi.uhyils.repository.PlatformPowerRepository;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.annotation.Default;

/**
 * 接口权限表(PlatformPower)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
public class PlatformPower extends AbstractDoEntity<PlatformPowerDO> {

    @Default
    public PlatformPower(PlatformPowerDO data) {
        super(data);
    }

    public PlatformPower(Long id) {
        super(id, new PlatformPowerDO());
    }

    public PlatformPower(Long id, PlatformPowerRepository rep) {
        super(id, new PlatformPowerDO());
        completion(rep);
    }

    public PlatformPower(Identifier id) {
        super(id, new PlatformPowerDO());
    }

}
