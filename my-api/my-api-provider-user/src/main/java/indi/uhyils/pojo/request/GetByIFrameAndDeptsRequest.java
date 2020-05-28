package indi.uhyils.pojo.request;

import java.util.List;

/**
 * 获取权限菜单的请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 13时53分
 */
public class GetByIFrameAndDeptsRequest extends DefaultRequest {


    private List<String> deptIds;

    private Integer iFrame;


    public List<String> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }

    public Integer getiFrame() {
        return iFrame;
    }

    public void setiFrame(Integer iFrame) {
        this.iFrame = iFrame;
    }
}
