package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 菜单(Menu)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 08时53分18秒
 */
@TableName(value = "sys_menu")
public class MenuDO extends BaseDO {

    private static final long serialVersionUID = 266796507075887064L;


    @TableField
    private Long fid;

    @TableField
    private Integer iFrame;

    @TableField
    private String icon;

    @TableField
    private String name;

    @TableField
    private Integer sort;

    @TableField
    private String target;

    @TableField
    private Boolean type;

    @TableField
    private String url;


    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }


    public Integer getIFrame() {
        return iFrame;
    }

    public void setIFrame(Integer iFrame) {
        this.iFrame = iFrame;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }


    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
