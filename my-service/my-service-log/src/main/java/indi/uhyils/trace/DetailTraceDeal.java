package indi.uhyils.trace;

import indi.uhyils.pojo.model.TraceDetailDO;


/**
 * 日志解析
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时41分
 */
public class DetailTraceDeal extends AbstractTraceDeal<TraceDetailDO> {

    @Override
    protected TraceDetailDO getTargetEntity(String[] split) {
        if (split.length <= 7) {
            throw new RuntimeException("错误");
        }

        String traceId = split[2];
        String type = split[3];
        String hashCode = split[4];
        String nowTime = split[5];
        String useTime = split[6];
        TraceDetailDO entity = new TraceDetailDO();
        entity.setTraceId(Long.valueOf(traceId));
        entity.setUseTime(Long.valueOf(useTime));
        entity.setEndTime(Long.valueOf(nowTime));
        entity.setType(Integer.valueOf(type));
        entity.setHashCode(hashCode);
        if (split.length == 8) {
            entity.setOtherOne(split[7]);
        } else if (split.length == 9) {
            entity.setOtherOne(split[7]);
            entity.setOtherTwo(split[8]);
        }
        return entity;
    }
}
