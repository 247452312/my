package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.DictItemDTO;
import java.io.Serializable;
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
    List<DictItemDTO> infos;

    public static LastPlanResponse build(List<DictItemDTO> infos) {
        LastPlanResponse lastPlanResponse = new LastPlanResponse();
        lastPlanResponse.setInfos(infos);
        return lastPlanResponse;
    }

    public List<DictItemDTO> getInfos() {
        return infos;
    }

    public void setInfos(List<DictItemDTO> infos) {
        this.infos = infos;
    }
}
