package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ResponseDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 设备指令回应表(Response)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时04分27秒
 */
public class Response extends AbstractDoEntity<ResponseDO> {

    @Default
    public Response(ResponseDO data) {
        super(data);
    }

    public Response(Long id) {
        super(id, new ResponseDO());
    }

}
