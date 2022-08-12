package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ParamDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 系统参数表(Param)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月17日 15时53分
 */
public class Param extends AbstractDoEntity<ParamDO> {

    @Default
    public Param(ParamDO data) {
        super(data);
    }

    public Param(Long id) {
        super(id, new ParamDO());
    }

}
