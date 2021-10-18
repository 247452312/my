package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.ConsumerInfoDO;
import indi.uhyils.repository.ConsumerInfoRepository;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.annotation.Default;

/**
 * 服务消费方信息表(ConsumerInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
public class ConsumerInfo extends AbstractDoEntity<ConsumerInfoDO> {

    @Default
    public ConsumerInfo(ConsumerInfoDO data) {
        super(data);
    }

    public ConsumerInfo(Long id) {
        super(id, new ConsumerInfoDO());
    }

    public ConsumerInfo(Long id, ConsumerInfoRepository rep) {
        super(id, new ConsumerInfoDO());
        completion(rep);
    }

    public ConsumerInfo(Identifier id) {
        super(id, new ConsumerInfoDO());
    }

}
