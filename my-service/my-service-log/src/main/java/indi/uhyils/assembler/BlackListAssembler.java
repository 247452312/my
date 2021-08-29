package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.BlackListDO;
import indi.uhyils.pojo.DTO.BlackListDTO;
import indi.uhyils.pojo.entity.BlackList;

/**
 * 黑名单(BlackList)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分52秒
 */
@Assembler
public class BlackListAssembler extends AbstractAssembler<BlackListDO, BlackList, BlackListDTO> {

    @Override
    public BlackList toEntity(BlackListDO dO) {
        return new BlackList(dO);
    }

    @Override
    public BlackList toEntity(BlackListDTO dto) {
        return new BlackList(toDo(dto));
    }

    @Override
    protected Class<BlackListDO> getDoClass() {
        return BlackListDO.class;
    }

    @Override
    protected Class<BlackListDTO> getDtoClass() {
        return BlackListDTO.class;
    }
}

