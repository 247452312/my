package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.TraceDetailDTO;
import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月06日 21时37分
 */
public class GetDetailByHashAndLogTypeResponse implements Serializable {

    private TraceDetailDTO data;

    public static GetDetailByHashAndLogTypeResponse build(TraceDetailDTO data) {
        GetDetailByHashAndLogTypeResponse build = new GetDetailByHashAndLogTypeResponse();
        build.data = data;
        return build;
    }

    public TraceDetailDTO getData() {
        return data;
    }

    public void setData(TraceDetailDTO data) {
        this.data = data;
    }
}
