package indi.uhyils.util.network.core;

/**
 * 损失函数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 09时45分
 */
public interface Loss {

    /**
     * 计算损失函数后的值
     *
     * @param result 数据集
     * @param label  标签
     *
     * @return 损失值
     */
    Double[] getLossNum(Resultable result, Resultable label);
}
