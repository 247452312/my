package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;

/**
 * (Space)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分54秒
 */
public class SpaceDO extends BaseDoDO {

    private static final long serialVersionUID = -14584589457118847L;

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
