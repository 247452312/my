package indi.uhyils.genetic.core;

/**
 * 食物,训练集
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时48分
 */
public interface Food {

    /**
     * 获取入参
     *
     * @return
     */
    double[] getIn();


    /**
     * 获取出参(目标)
     *
     * @return
     */
    double[] getShould();
}
