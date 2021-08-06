package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.TraceDetailEntity;
import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月06日 21时37分
 */
public class GetDetailByHashAndLogTypeResponse implements Serializable {

    private TraceDetailEntity data;

    public static GetDetailByHashAndLogTypeResponse build(TraceDetailEntity data) {
        GetDetailByHashAndLogTypeResponse build = new GetDetailByHashAndLogTypeResponse();
        build.data = data;
        return build;
    }

    public TraceDetailEntity getData() {
        return data;
    }

    public void setData(TraceDetailEntity data) {
        this.data = data;
    }
}
