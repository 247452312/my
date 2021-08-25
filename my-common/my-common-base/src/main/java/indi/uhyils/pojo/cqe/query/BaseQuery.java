package indi.uhyils.pojo.cqe.query;

import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.pojo.cqe.BaseCQE;
import java.util.List;

/**
 * 查询命令, 只是查询,不会对现有的系统产生影响,一定是只读操作
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 17时59分
 */
public interface BaseQuery extends BaseCQE {

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
