package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeDao;
import indi.uhyils.dao.OrderBaseNodeFieldDao;
import indi.uhyils.dao.OrderBaseNodeResultTypeDao;
import indi.uhyils.dao.OrderBaseNodeRouteDao;
import indi.uhyils.pojo.model.OrderBaseNodeEntity;
import indi.uhyils.pojo.request.base.IdsRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderBaseNodeService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderBaseNodeServiceImpl extends BaseDefaultServiceImpl<OrderBaseNodeEntity> implements OrderBaseNodeService {

    @Resource
    private OrderBaseNodeDao dao;
    @Resource
    private OrderBaseNodeFieldDao orderBaseNodeFieldDao;
    @Resource
    private OrderBaseNodeResultTypeDao orderBaseNodeResultTypeDao;
    @Resource
    private OrderBaseNodeRouteDao orderBaseNodeRouteDao;


    @Override
    public OrderBaseNodeDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseNodeDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<Boolean> deleteByIds(IdsRequest request) {
        Integer updateDate = new Long(System.currentTimeMillis() / 1000).intValue();
        Long updateUser = request.getUser().getId();

        /*删除属性*/
        boolean deleteFieldByNodeIds = orderBaseNodeFieldDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除结果类型*/
        boolean deleteResultTypeByNodeIds = orderBaseNodeResultTypeDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除路由*/
        boolean deleteRouteByNodeIds = orderBaseNodeRouteDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除本体*/
        boolean deleteByIds = dao.deleteByIds(request.getIds(), updateUser, updateDate) != 0;

        return ServiceResult.buildSuccessResult("删除执行成功", deleteFieldByNodeIds & deleteResultTypeByNodeIds & deleteRouteByNodeIds & deleteByIds);
    }
}
