package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.TraceDetailDTO;
import indi.uhyils.pojo.DTO.request.GetTraceDetailByHashCodeRequest;
import indi.uhyils.pojo.DTO.response.GetTraceDetailByHashCodeResponse;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
public interface TraceDetailProvider extends DTOProvider<TraceDetailDTO> {

    /**
     * 获取链路详情
     *
     * @param request
     *
     * @return
     */
    GetTraceDetailByHashCodeResponse getTraceDetailByHashCode(GetTraceDetailByHashCodeRequest request);


}
