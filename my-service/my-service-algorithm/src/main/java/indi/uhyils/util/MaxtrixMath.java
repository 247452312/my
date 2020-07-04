package indi.uhyils.util;

import indi.uhyils.pojo.tool.Latitude;
import indi.uhyils.pojo.tool.Matrix;

/**
 * 矩阵计算
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 17时40分
 */
public class MaxtrixMath {

    /**
     * 点乘
     *
     * @param a a
     * @param b b
     * @return 结果
     */
    Matrix dotMultiplication(Matrix a, Matrix b) {
        assert a.getY().equals(b.getX());
        Double[][] data = new Double[a.getX()][b.getY()];
        for (int i = 0; i < a.getX(); i++) {
            for (int j = 0; j < b.getY(); j++) {
                Double[] datum = a.getData()[i];
                Double sum = 0.0;
                for (int k = 0; k < datum.length; k++) {
                    sum += a.getData()[i][k] * b.getData()[k][j];
                }
                data[i][j] = sum;
            }
        }
        return new Matrix().setData(data);
    }

    /**
     * 对应乘
     *
     * @return 结果
     */
    Matrix multiplication(Matrix a, Matrix b) {
        assert a.getX().equals(b.getX()) && a.getY().equals(b.getY());
        Double[][] data = new Double[a.getX()][b.getY()];
        for (int i = 0; i < a.getX(); i++) {
            for (int j = 0; j < a.getY(); j++) {
                data[i][j] = a.getData()[i][j] * b.getData()[i][j];
            }
        }
        return new Matrix().setData(data);
    }

    /**
     * 获取纬度
     *
     * @param a 矩阵
     * @return 纬度
     */
    Latitude getLatitude(Matrix a) {
        Latitude latitude = new Latitude();
        latitude.setX(a.getX());
        latitude.setY(a.getY());
        return latitude;
    }
}
