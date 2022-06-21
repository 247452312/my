package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.entity.OrderBaseInfo;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 工单基础信息样例表(OrderBaseInfo)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分53秒
 */
public interface OrderBaseInfoRepository extends BaseEntityRepository<OrderBaseInfoDO, OrderBaseInfo> {


    /**
     * 获取基础工单(只包含内容和名称)
     *
     * @return
     */
    List<OrderBaseInfoDTO> getAllBaseOrderIdAndName();

}
