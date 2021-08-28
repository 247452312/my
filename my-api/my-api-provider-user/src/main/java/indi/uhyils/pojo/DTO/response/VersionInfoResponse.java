package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.DictItemDTO;
import java.io.Serializable;
import java.util.List;

/**
 * 版本信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 07时28分
 */
public class VersionInfoResponse implements Serializable {

    /**
     * 版本信息-> 数据字典中
     */
    List<DictItemDTO> infos;

    public static VersionInfoResponse build(List<DictItemDTO> infos) {
        VersionInfoResponse versionInfoResponse = new VersionInfoResponse();
        versionInfoResponse.setInfos(infos);
        return versionInfoResponse;

    }

    public List<DictItemDTO> getInfos() {
        return infos;
    }

    public void setInfos(List<DictItemDTO> infos) {
        this.infos = infos;
    }
}
