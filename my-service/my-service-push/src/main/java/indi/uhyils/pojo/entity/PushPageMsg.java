package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PushPageMsgDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.repository.PushPageMsgRepository;

/**
 * 推送日志信息表(PushPageMsg)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 19时47分08秒
 */
public class PushPageMsg extends AbstractDoEntity<PushPageMsgDO> {

    @Default
    public PushPageMsg(PushPageMsgDO data) {
        super(data);
    }

    public PushPageMsg(Long id) {
        super(id, new PushPageMsgDO());
    }

    public PushPageMsg(Long id, PushPageMsgRepository rep) {
        super(id, new PushPageMsgDO());
        completion(rep);
    }

}
