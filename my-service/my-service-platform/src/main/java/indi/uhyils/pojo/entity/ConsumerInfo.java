package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.enum_.ConsumerStatusEnum;
import indi.uhyils.pojo.DO.ConsumerInfoDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ConsumerInfoRepository;
import indi.uhyils.util.Asserts;

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

    /**
     * 检查名称是否重复
     *
     * @param rep
     */
    public void checkNameRepeat(ConsumerInfoRepository rep) {
        ConsumerInfoDO consumerInfoDO = this.toData();
        Asserts.assertTrue(consumerInfoDO != null);
        boolean repeat = rep.checkNameRepeat(consumerInfoDO.getName());
        Asserts.assertTrue(!repeat, "名称:{} 已存在", consumerInfoDO.getName());
    }

    public void injDefaultInfo() {
        ConsumerInfoDO consumerInfoDO = toData();
        Asserts.assertTrue(consumerInfoDO != null);

        // 默认状态信息
        consumerInfoDO.setStatus(ConsumerStatusEnum.REGISTTING.getCode());
    }
}
