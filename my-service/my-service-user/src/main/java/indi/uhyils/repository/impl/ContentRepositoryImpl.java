package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ContentAssembler;
import indi.uhyils.dao.ContentDao;
import indi.uhyils.pojo.DO.ContentDO;
import indi.uhyils.pojo.DTO.ContentDTO;
import indi.uhyils.pojo.entity.Content;
import indi.uhyils.repository.ContentRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 公共参数(Content)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分18秒
 */
@Repository
public class ContentRepositoryImpl extends AbstractRepository<Content, ContentDO, ContentDao, ContentDTO, ContentAssembler> implements ContentRepository {

    protected ContentRepositoryImpl(ContentAssembler convert, ContentDao dao) {
        super(convert, dao);
    }


    @Override
    public Content getByName(String honeInfo) {
        dao.getByName(honeInfo);
        return null;
    }
}
