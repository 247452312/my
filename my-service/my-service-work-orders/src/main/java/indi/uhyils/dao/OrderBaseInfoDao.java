package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderBaseInfoDao extends DefaultDao<OrderBaseInfoDO> {


    /**
     * 获取全部的基础工单
     *
     * @return
     */
    ArrayList<OrderBaseInfoDO> getAllBaseOrderIdAndName();
}
