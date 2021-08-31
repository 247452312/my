package indi.uhyils.pojo.DTO;

import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 11时28分
 */
public class DealOrderNodeDTO implements BaseDTO {

    /**
     * 判断节点值是否允许的最终结果
     */
    private Boolean allow;

    /**
     * 判断节点值是否允许的结果
     */
    private Map<OrderNodeFieldDTO, Boolean> detailResult;

    /**
     * 出错时返回
     *
     * @param detailResult
     *
     * @return
     */
    public static DealOrderNodeDTO buildCheckFaild(Boolean allow, Map<OrderNodeFieldDTO, Boolean> detailResult) {
        DealOrderNodeDTO build = new DealOrderNodeDTO();
        build.setAllow(allow);
        build.setDetailResult(detailResult);
        return build;
    }

    /**
     * 成功时返回
     *
     * @return
     */
    public static DealOrderNodeDTO buildSuccess() {
        DealOrderNodeDTO build = new DealOrderNodeDTO();
        build.setAllow(Boolean.TRUE);
        return build;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public Map<OrderNodeFieldDTO, Boolean> getDetailResult() {
        return detailResult;
    }

    public void setDetailResult(Map<OrderNodeFieldDTO, Boolean> detailResult) {
        this.detailResult = detailResult;
    }
}
