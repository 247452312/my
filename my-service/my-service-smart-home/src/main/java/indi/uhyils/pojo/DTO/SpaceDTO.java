package indi.uhyils.pojo.DTO;


/**
 * 空间坐标表(Space)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分56秒
 */
public class SpaceDTO extends IdDTO {

    private static final long serialVersionUID = 252719796214117936L;


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
