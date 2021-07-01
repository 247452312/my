package indi.uhyils.handler;

import indi.uhyils.pojo.temp.SaveToTransApiTemporary;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 13时56分
 */
public interface TransApiHandler {

    /**
     * 自动节点转移到下一个节点
     *
     * @param requestTemporary
     * @return
     */
    void trans(SaveToTransApiTemporary requestTemporary);

}
