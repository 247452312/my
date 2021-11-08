package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 角色表(PlatformRole)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@TableName(value = "sys_platform_role")
public class PlatformRoleDO extends BaseDO {

    private static final long serialVersionUID = -97771698352014568L;


    /**
     * 角色名称
     */
    @TableField
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
