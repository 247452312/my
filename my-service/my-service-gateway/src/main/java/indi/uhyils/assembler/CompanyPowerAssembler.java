package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.CompanyPowerDO;
import indi.uhyils.pojo.DTO.CompanyPowerDTO;
import indi.uhyils.pojo.entity.CompanyPower;
import org.mapstruct.Mapper;

/**
 * 厂商接口调用权限表(CompanyPower)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Mapper(componentModel = "spring")
public abstract class CompanyPowerAssembler extends AbstractAssembler<CompanyPowerDO, CompanyPower, CompanyPowerDTO> {

}
