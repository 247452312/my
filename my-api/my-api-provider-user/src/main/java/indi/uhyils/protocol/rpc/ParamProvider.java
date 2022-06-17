package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.ParamDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.FlushAllSysEvent;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 系统参数表(Param)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年06月17日 15时53分
 */
public interface ParamProvider extends DTOProvider<ParamDTO> {

    /**
     * 刷新所有系统参数
     *
     * @param event
     *
     * @return
     */
    ServiceResult<Boolean> flushAllSys(FlushAllSysEvent event);
}
