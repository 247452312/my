package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.ConsumerFilterDO;
import indi.uhyils.repository.ConsumerFilterRepository;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.annotation.Default;

/**
 * 消费过滤表(ConsumerFilter)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分03秒
 */
public class ConsumerFilter extends AbstractDoEntity<ConsumerFilterDO> {

    @Default
    public ConsumerFilter(ConsumerFilterDO data) {
        super(data);
    }

    public ConsumerFilter(Long id) {
        super(id, new ConsumerFilterDO());
    }

    public ConsumerFilter(Long id, ConsumerFilterRepository rep) {
        super(id, new ConsumerFilterDO());
        completion(rep);
    }

    public ConsumerFilter(Identifier id) {
        super(id, new ConsumerFilterDO());
    }

}
