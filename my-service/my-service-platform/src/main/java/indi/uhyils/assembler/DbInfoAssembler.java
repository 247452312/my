package indi.uhyils.assembler;


import org.mapstruct.Mapper;
import indi.uhyils.pojo.DO.DbInfoDO;
import indi.uhyils.pojo.DTO.DbInfoDTO;
import indi.uhyils.pojo.entity.DbInfo;

/**
 * 数据库连接表(DbInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
@Mapper(componentModel = "spring")
public abstract class DbInfoAssembler extends AbstractAssembler<DbInfoDO, DbInfo, DbInfoDTO> {

}

