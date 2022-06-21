package indi.uhyils.pojo.temp;

import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 09时51分
 */
public class CheckNodeFieldResultTemporary {

    /**
     * 是否通过
     */
    private Boolean allow;

    /**
     * 详细结果
     */
    private Map<OrderNodeFieldDO, Boolean> detailResult;

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public Map<OrderNodeFieldDO, Boolean> getDetailResult() {
        return detailResult;
    }

    public void setDetailResult(Map<OrderNodeFieldDO, Boolean> detailResult) {
        this.detailResult = detailResult;
    }

    public void put(OrderNodeFieldDO orderNodeFieldEntity, Boolean allow) {
        this.detailResult.put(orderNodeFieldEntity, allow);
        if (!allow) {
            this.allow = false;
        }
    }
}
