package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.DictItemDO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 下一步计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 07时31分
 */
public class LastPlanResponse implements Serializable {

    /**
     * 下一步计划 -> 数据字典中
     */
    List<DictItemDO> infos;

    public static LastPlanResponse build(ArrayList<DictItemDO> infos) {
        LastPlanResponse lastPlanResponse = new LastPlanResponse();
        lastPlanResponse.setInfos(infos);
        return lastPlanResponse;
    }

    public List<DictItemDO> getInfos() {
        return infos;
    }

    public void setInfos(List<DictItemDO> infos) {
        this.infos = infos;
    }
}
