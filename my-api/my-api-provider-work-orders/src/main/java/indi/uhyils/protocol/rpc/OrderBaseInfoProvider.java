package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.GetOneBaseOrderDTO;
import indi.uhyils.pojo.DTO.InitOrderDTO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public interface OrderBaseInfoProvider extends DTOProvider<OrderBaseInfoDTO> {

    /**
     * 获取全部的基础工单(id与名称)
     *
     * @param request
     *
     * @return
     */
    ServiceResult<List<OrderBaseInfoDTO>> getAllBaseOrderIdAndName(DefaultCQE request);

    /**
     * 获取一个工单的所有信息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetOneBaseOrderDTO> getOneOrder(IdQuery request);


    /**
     * 复制基础工单到工单
     *
     * @param request 工单本体
     *
     * @return 插入后的id
     */
    ServiceResult<InitOrderDTO> initOrder(IdCommand request);
}
