package indi.uhyils.repository;

import indi.uhyils.pojo.DO.SoftwareDO;
import indi.uhyils.pojo.entity.Software;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 中间件表(Software)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分21秒
 */
public interface SoftwareRepository extends BaseEntityRepository<SoftwareDO, Software> {


}
