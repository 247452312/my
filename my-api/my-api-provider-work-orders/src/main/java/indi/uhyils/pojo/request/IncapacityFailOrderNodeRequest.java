package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 申请失败转交节点请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月25日 07时56分
 */
public class IncapacityFailOrderNodeRequest extends DefaultRequest {

    /**
     * 失败原因
     */
    private String reasons;

    /**
     * 推荐人id
     */
    private String recommendUserId;


    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getRecommendUserId() {
        return recommendUserId;
    }

    public void setRecommendUserId(String recommendUserId) {
        this.recommendUserId = recommendUserId;
    }
}
