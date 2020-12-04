package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderInfoDao extends DefaultDao<OrderInfoEntity> {


    /**
     * 获取一个工单的状态
     *
     * @param orderId
     * @return
     */
    Integer getOrderStatusById(String orderId);

    /**
     * 修改工单状态为等待撤回
     *
     * @param orderId
     * @param code
     */
    void changeOrderStatus(@Param("orderId") String orderId, @Param("code") Integer code);

    /**
     * 根据类型获取其他工单
     *
     * @param type
     * @return
     */
    ArrayList<OrderInfoEntity> getOrderByType(Integer type);
}
