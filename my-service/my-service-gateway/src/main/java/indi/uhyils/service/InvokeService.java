package indi.uhyils.service;

import indi.uhyils.pojo.cqe.InvokeCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 10时18分
 */
public interface InvokeService {

    /**
     * 执行某个方法
     *
     * @param command
     *
     * @return
     */
    Object invoke(InvokeCommand command);

}
