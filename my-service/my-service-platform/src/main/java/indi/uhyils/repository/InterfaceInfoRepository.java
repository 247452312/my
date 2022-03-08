package indi.uhyils.repository;

import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInterface;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 接口信息表(节点)(InterfaceInfo)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
public interface InterfaceInfoRepository extends BaseEntityRepository<InterfaceInfoDO, InterfaceInfo> {


    /**
     * 递归去获取树
     *
     * @param interfaceInfo
     *
     * @return
     */
    List<InterfaceInterface> findChildsInterface(InterfaceInfo interfaceInfo);
}
