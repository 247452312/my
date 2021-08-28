package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.ContentDO;
import indi.uhyils.pojo.DTO.ContentDTO;
import indi.uhyils.pojo.entity.Content;

/**
 * 公共参数(Content)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分15秒
 */
@Assembler
public class ContentAssembler extends AbstractAssembler<ContentDO, Content, ContentDTO> {

    @Override
    public Content toEntity(ContentDO dO) {
        return new Content(dO);
    }

    @Override
    public Content toEntity(ContentDTO dto) {
        return new Content(toDo(dto));
    }

    @Override
    protected Class<ContentDO> getDoClass() {
        return ContentDO.class;
    }

    @Override
    protected Class<ContentDTO> getDtoClass() {
        return ContentDTO.class;
    }
}

