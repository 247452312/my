package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeDao;
import indi.uhyils.dao.OrderNodeFieldDao;
import indi.uhyils.dao.OrderNodeResultTypeDao;
import indi.uhyils.dao.OrderNodeRouteDao;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DTO.request.base.IdsRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderNodeProvider extends BaseDefaultProvider<OrderNodeDO> implements indi.uhyils.protocol.rpc.provider.OrderNodeProvider {

    @Resource
    private OrderNodeDao dao;
    @Resource
    private OrderNodeFieldDao orderNodeFieldDao;
    @Resource
    private OrderNodeResultTypeDao orderNodeResultTypeDao;
    @Resource
    private OrderNodeRouteDao orderNodeRouteDao;

    @Override
    public OrderNodeDao getDao() {
        return dao;
    }

    public void setDao(OrderNodeDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<Boolean> deleteByIds(IdsRequest request) {
        Integer updateDate = new Long(System.currentTimeMillis() / 1000).intValue();
        Long updateUser = request.getUser().getId();

        /*删除属性*/
        boolean deleteFieldByNodeIds = orderNodeFieldDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除结果类型*/
        boolean deleteResultTypeByNodeIds = orderNodeResultTypeDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除路由*/
        boolean deleteRouteByNodeIds = orderNodeRouteDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除本体*/
        boolean deleteByIds = dao.deleteByIds(request.getIds(), updateUser, updateDate) != 0;

        return ServiceResult.buildSuccessResult("删除执行成功", deleteFieldByNodeIds & deleteResultTypeByNodeIds & deleteRouteByNodeIds & deleteByIds);
    }
}
