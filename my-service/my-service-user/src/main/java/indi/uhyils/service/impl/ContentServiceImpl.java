package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ContentAssembler;
import indi.uhyils.pojo.DO.ContentDO;
import indi.uhyils.pojo.DTO.ContentDTO;
import indi.uhyils.pojo.entity.Content;
import indi.uhyils.repository.ContentRepository;
import indi.uhyils.service.ContentService;
import org.springframework.stereotype.Service;

/**
 * 公共参数(Content)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分19秒
 */
@Service
@ReadWriteMark(tables = {"sys_content"})
public class ContentServiceImpl extends AbstractDoService<ContentDO, Content, ContentDTO, ContentRepository, ContentAssembler> implements ContentService {

    public ContentServiceImpl(ContentAssembler assembler, ContentRepository repository) {
        super(assembler, repository);
    }


}
