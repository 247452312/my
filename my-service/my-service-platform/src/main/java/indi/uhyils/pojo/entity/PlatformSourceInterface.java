package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PlatformSourceInterfaceDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.repository.PlatformSourceInterfaceRepository;
import java.util.List;

/**
 * 接口资源表(PlatformSourceInterface)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public class PlatformSourceInterface extends AbstractDoEntity<PlatformSourceInterfaceDO> {

    @Default
    public PlatformSourceInterface(PlatformSourceInterfaceDO data) {
        super(data);
    }

    public PlatformSourceInterface(Long id) {
        super(id, new PlatformSourceInterfaceDO());
    }

    /**
     * 检查此interface有没有可以并行的group
     *
     * @param rep
     */
    public void checkAndInitGroup(PlatformSourceInterfaceRepository rep) {
        // 查询此资源之前有没有可以分配到同一组的资源
        List<PlatformSourceInterface> groups = rep.findSeamGroup(this);

    }
}
