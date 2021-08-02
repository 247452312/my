package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.DictItemEntity;
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
    List<DictItemEntity> infos;

    public static VersionInfoResponse build(List<DictItemEntity> infos) {
        VersionInfoResponse versionInfoResponse = new VersionInfoResponse();
        versionInfoResponse.setInfos(infos);
        return versionInfoResponse;

    }

    public List<DictItemEntity> getInfos() {
        return infos;
    }

    public void setInfos(List<DictItemEntity> infos) {
        this.infos = infos;
    }
}
