package indi.uhyils.pojo.trace;

import indi.uhyils.pojo.DTO.IdDTO;

/**
 * 处理traceDeal
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时33分
 */
public interface TraceDealInterface<T extends IdDTO> {

    /**
     * 处理
     *
     * @param traceMsg
     */
    T doDeal(String traceMsg);

}
