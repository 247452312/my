package indi.uhyils.pojo.entity;

import java.util.List;

/**
 * 完备角色,包含角色本身,权限,等信息
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时09分
 */
public class CompleteRole extends AbstractEntity {


    private Role role;

    private List<CompleteDept> depts;

    public CompleteRole(Role role, List<CompleteDept> depts) {
        this.role = role;
        this.depts = depts;
    }
}
