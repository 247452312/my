package indi.uhyils.util.network.layer.core;

import indi.uhyils.util.network.core.Datable;

/**
 * 神经网络输出层
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 10时21分
 */
public interface InputLayer {

    /**
     * 将输入修改为程序可以识别的格式
     *
     * @param obj 输入
     *
     * @return 输入层输出
     */
    Datable changeToData(Object obj);

}
