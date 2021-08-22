package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.DictItemDO;
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
    List<DictItemDO> infos;

    public static VersionInfoResponse build(List<DictItemDO> infos) {
        VersionInfoResponse versionInfoResponse = new VersionInfoResponse();
        versionInfoResponse.setInfos(infos);
        return versionInfoResponse;

    }

    public List<DictItemDO> getInfos() {
        return infos;
    }

    public void setInfos(List<DictItemDO> infos) {
        this.infos = infos;
    }
}
