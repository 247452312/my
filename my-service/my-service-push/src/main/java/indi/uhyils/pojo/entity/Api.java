package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.repository.ApiRepository;

/**
 * api表(Api)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 19时46分45秒
 */
public class Api extends AbstractDoEntity<ApiDO> {


    public Api(ApiDO dO) {
        super(dO);
    }

    public Api(Long id) {
        super(id, new ApiDO());
    }

    public Api(Long id, ApiRepository rep) {
        super(id, new ApiDO());
        completion(rep);
    }


}
