package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Response;
import indi.uhyils.pojo.DO.ResponseDO;

/**
 * 设备指令回应表(Response)表 转换类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分47秒
 */
@Convert
public class ResponseConvert extends AbstractDoConvert<Response, ResponseDO> {

    @Override
    public Response doToEntity(ResponseDO dO) {
        return new Response(dO);
    }
}
