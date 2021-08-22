package indi.uhyils.entity.query;

import indi.uhyils.pojo.request.model.Arg;
import java.util.List;

/**
 * 查询order
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 17时59分
 */
public interface BaseOrder {

    /**
     * 获取参数
     *
     * @return
     */
    List<Arg> args();


    /**
     * 获取排序
     *
     * @return
     */
    Order order();

    /**
     * 获取分页
     *
     * @return
     */
    Limit limit();
}
