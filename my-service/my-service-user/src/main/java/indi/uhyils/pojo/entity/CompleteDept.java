package indi.uhyils.pojo.entity;

import java.util.List;

/**
 * 完备部门
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时09分
 */
public class CompleteDept extends AbstractEntity {

    private Dept dept;

    private List<Power> powers;

    private List<Menu> menus;

    public CompleteDept(Dept dept, List<Power> powers, List<Menu> menus) {
        this.dept = dept;
        this.powers = powers;
        this.menus = menus;
    }
}
