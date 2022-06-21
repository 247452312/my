package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 空间坐标表(Space)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时15分38秒
 */
@TableName(value = "sys_space")
public class SpaceDO extends BaseDO {

    private static final long serialVersionUID = 203044128474060025L;


    /**
     * 父空间id
     */
    @TableField
    private Long fid;

    /**
     * 空间坐标集 json point集合形式
     */
    @TableField
    private String points;


    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }


    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}
