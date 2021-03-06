package indi.uhyils.pojo.temp;

import indi.uhyils.pojo.model.OrderNodeFieldEntity;

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
    private Map<OrderNodeFieldEntity, Boolean> detailResult;

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public Map<OrderNodeFieldEntity, Boolean> getDetailResult() {
        return detailResult;
    }

    public void setDetailResult(Map<OrderNodeFieldEntity, Boolean> detailResult) {
        this.detailResult = detailResult;
    }

    public void put(OrderNodeFieldEntity orderNodeFieldEntity, Boolean allow) {
        this.detailResult.put(orderNodeFieldEntity, allow);
        if (!allow) {
            this.allow = false;
        }
    }
}
