package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.BaseDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import java.util.List;

/**
 * 下一步计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 07时31分
 */
public class LastPlanDTO implements BaseDTO {

    /**
     * 下一步计划 -> 数据字典中
     */
    List<DictItemDTO> infos;

    public static LastPlanDTO build(List<DictItemDTO> infos) {
        LastPlanDTO lastPlanResponse = new LastPlanDTO();
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
