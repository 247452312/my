package indi.uhyils.pojo.DTO.response;

import java.io.Serializable;

/**
 * 根据菜单id获取全部的权限集以及所属的权限集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月06日 14时50分
 */
public class GetDeptsByMenuIdResponse implements Serializable {

    /**
     * 权限集id
     */
    private Long deptId;

    /**
     * 权限集名称
     */
    private String deptName;

    /**
     * 这个权限集属不属于指定的id
     */
    private Boolean mark;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }
}
