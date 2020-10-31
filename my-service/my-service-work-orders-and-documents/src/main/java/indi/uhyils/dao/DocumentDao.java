package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.DocumentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
@Mapper
public interface DocumentDao extends DefaultDao<DocumentEntity> {


    /**
     * 获取根节点
     *
     * @param iframe
     * @return
     */
    List<DocumentEntity> getDocumentRootByIframe(Integer iframe);

    /**
     * 获取一个文档的子节点
     *
     * @param id 文档的id
     * @return
     */
    List<DocumentEntity> getChildensById(String id);
}
