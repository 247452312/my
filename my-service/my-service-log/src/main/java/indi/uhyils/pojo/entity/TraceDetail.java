package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.TraceDetailDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * (TraceDetail)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月29日 16时58分54秒
 */
public class TraceDetail extends AbstractDoEntity<TraceDetailDO> {

    @Default
    public TraceDetail(TraceDetailDO data) {
        super(data);
    }

}
