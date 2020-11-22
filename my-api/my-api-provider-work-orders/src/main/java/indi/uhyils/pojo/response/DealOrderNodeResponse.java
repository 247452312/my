package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.OrderNodeFieldEntity;

import java.io.Serializable;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 11时28分
 */
public class DealOrderNodeResponse implements Serializable {

    /**
     * 判断节点值是否允许的最终结果
     */
    private Boolean allow;
    /**
     * 判断节点值是否允许的结果
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

    /**
     * 出错时返回
     *
     * @param detailResult
     * @return
     */
    public static DealOrderNodeResponse buildCheckFaild(Boolean allow, Map<OrderNodeFieldEntity, Boolean> detailResult) {
        DealOrderNodeResponse build = new DealOrderNodeResponse();
        build.setAllow(allow);
        build.setDetailResult(detailResult);
        return build;
    }

    /**
     * 成功时返回
     *
     * @return
     */
    public static DealOrderNodeResponse buildSuccess() {
        DealOrderNodeResponse build = new DealOrderNodeResponse();
        build.setAllow(true);
        return build;
    }
}
