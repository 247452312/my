package indi.uhyils.handler;

import indi.uhyils.pojo.temp.InitApiRequestTemporary;
import indi.uhyils.pojo.temp.InitToRunApiTemporary;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 13时56分
 */
public interface InitApiHandler {

    /**
     * 自动节点初始化
     *
     * @param requestTemporary
     *
     * @return
     */
    InitToRunApiTemporary init(InitApiRequestTemporary requestTemporary);


}
