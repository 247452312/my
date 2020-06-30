package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.ArgsRequest;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月30日 09时16分
 */
public class GetByArgsAndGroupRequest extends ArgsRequest {

    /**
     * 组id
     */
    private String groupId;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
