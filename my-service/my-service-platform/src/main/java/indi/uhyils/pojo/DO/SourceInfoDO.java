package indi.uhyils.pojo.DO;

import indi.uhyils.enum_.InterfaceTypeEnum;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月17日 12时32分
 */
public abstract class SourceInfoDO extends BaseDO {

    /**
     * 获取资源类型
     *
     * @return
     */
    public abstract InterfaceTypeEnum getSourceType();
}
