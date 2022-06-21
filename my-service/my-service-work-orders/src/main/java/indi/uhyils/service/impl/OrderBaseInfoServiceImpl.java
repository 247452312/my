package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderBaseInfoAssembler;
import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.OrderBaseInfo;
import indi.uhyils.repository.OrderBaseInfoRepository;
import indi.uhyils.repository.OrderBaseNodeFieldRepository;
import indi.uhyils.repository.OrderBaseNodeRepository;
import indi.uhyils.repository.OrderBaseNodeResultTypeRepository;
import indi.uhyils.repository.OrderBaseNodeRouteRepository;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.service.OrderBaseInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工单基础信息样例表(OrderBaseInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分54秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_base_info"})
public class OrderBaseInfoServiceImpl extends AbstractDoService<OrderBaseInfoDO, OrderBaseInfo, OrderBaseInfoDTO, OrderBaseInfoRepository, OrderBaseInfoAssembler> implements OrderBaseInfoService {

    @Autowired
    private OrderBaseNodeRepository nodeRepository;

    @Autowired
    private OrderBaseNodeFieldRepository fieldRepository;

    @Autowired
    private OrderBaseNodeRouteRepository routeRepository;

    @Autowired
    private OrderBaseNodeResultTypeRepository resultTypeRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    public OrderBaseInfoServiceImpl(OrderBaseInfoAssembler assembler, OrderBaseInfoRepository repository) {
        super(assembler, repository);
    }

    @Override
    public List<OrderBaseInfoDTO> getAllBaseOrderIdAndName(DefaultCQE request) {
        return rep.getAllBaseOrderIdAndName();
    }

    @Override
    public OrderBaseInfoDTO getOneOrder(IdQuery request) {
        OrderBaseInfo orderBaseInfo = new OrderBaseInfo(request.getId());
        // 填充本体信息
        orderBaseInfo.completion(rep);
        // 填充为隐藏的节点信息
        orderBaseInfo.fillNoHiddenNode(nodeRepository);
        // 内部节点填充详情信息
        orderBaseInfo.recursionFillNodeInfo(fieldRepository, routeRepository, resultTypeRepository);
        return assem.toDTO(orderBaseInfo);
    }


}
