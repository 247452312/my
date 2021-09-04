package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.PushMsgDO;
import indi.uhyils.repository.PushMsgRepository;

/**
 * 推送日志表(PushMsg)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 19时47分03秒
 */
public class PushMsg extends AbstractDoEntity<PushMsgDO> {

    public PushMsg(PushMsgDO dO) {
        super(dO);
    }

    public PushMsg(Long id) {
        super(id, new PushMsgDO());
    }

    public PushMsg(Long id, PushMsgRepository rep) {
        super(id, new PushMsgDO());
        completion(rep);
    }

}
