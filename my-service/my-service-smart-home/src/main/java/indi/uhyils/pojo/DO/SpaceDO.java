package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;

/**
 * 空间坐标表(Space)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分32秒
 */
public class SpaceDO extends BaseDoDO {

    private static final long serialVersionUID = 296876313580278733L;


    /**
     * 父空间id
     */
    private Long fid;

    /**
     * 空间坐标集 json point集合形式
     */
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
