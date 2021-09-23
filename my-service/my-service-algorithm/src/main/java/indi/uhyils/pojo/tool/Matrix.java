package indi.uhyils.pojo.tool;

import java.io.Serializable;

/**
 * 矩阵
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 17时40分
 */
public class Matrix implements Serializable {

    /**
     * 行
     */
    private Integer x;

    /**
     * 列
     */
    private Integer y;

    /**
     * 本体
     */
    private Double[][] data;

    public Integer getX() {
        return x;
    }

    public Matrix setX(Integer x) {
        this.x = x;
        return this;
    }

    public Integer getY() {
        return y;
    }

    public Matrix setY(Integer y) {
        this.y = y;
        return this;
    }

    public Double[][] getData() {
        return data;
    }

    public Matrix setData(Double[][] data) {
        this.data = data;
        this.x = data.length;
        this.y = data[0].length;
        return this;
    }
}
