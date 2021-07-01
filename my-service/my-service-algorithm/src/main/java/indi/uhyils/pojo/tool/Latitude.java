package indi.uhyils.pojo.tool;

import java.io.Serializable;

/**
 * 纬度
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 18时14分
 */
public class Latitude implements Serializable, Comparable<Latitude> {

    /**
     * 行
     */
    private Integer x;

    /**
     * 列
     */
    private Integer y;


    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public int compareTo(Latitude o) {
        if (this.x.equals(o.getX()) && this.y.equals(o.getY())) {
            return 0;
        }
        if (this.x + this.y > o.getX() + o.getY()) {
            return 1;
        }
        return -1;
    }
}
