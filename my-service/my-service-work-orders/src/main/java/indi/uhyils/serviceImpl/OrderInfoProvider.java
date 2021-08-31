package indi.uhyils.serviceImpl;

import indi.uhyils.dao.*;
import indi.uhyils.pojo.DO.*;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderInfoProvider extends BaseDefaultProvider<OrderInfoDO> implements indi.uhyils.protocol.rpc.OrderInfoProvider {

    @Resource
    private OrderInfoDao dao;

    @Resource
    private OrderNodeDao orderNodeDao;

    @Resource
    private OrderNodeFieldDao orderNodeFieldDao;

    @Resource
    private OrderNodeResultTypeDao orderNodeResultTypeDao;

    @Resource
    private OrderNodeRouteDao orderNodeRouteDao;


    @Resource
    private OrderInfoDao orderInfoDao;


    @Resource
    private OrderNodeFieldValueDao orderNodeFieldValueDao;


    @Override
    public OrderInfoDao getDao() {
        return dao;
    }

    public void setDao(OrderInfoDao dao) {
        this.dao = dao;
    }


}
