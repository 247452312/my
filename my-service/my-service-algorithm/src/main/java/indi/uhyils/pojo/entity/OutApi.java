package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.OutApiDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.OutApiRepository;

/**
 * 开放api(OutApi)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月09日 20时58分10秒
 */
public class OutApi extends AbstractDoEntity<OutApiDO> {

    @Default
    public OutApi(OutApiDO data) {
        super(data);
    }

    public OutApi(Long id) {
        super(id, new OutApiDO());
    }

    public OutApi(Long id, OutApiRepository rep) {
        super(id, new OutApiDO());
        completion(rep);
    }

    public OutApi(Identifier id) {
        super(id, new OutApiDO());
    }

}
