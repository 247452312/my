package indi.uhyils.dao;

import indi.uhyils.request.model.Arg;
import indi.uhyils.model.ClassEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 13时52分
 */
@Mapper
public interface ClassDao extends DefaultDao<ClassEntity> {


    ArrayList<ClassEntity> getByArgsNoPage(List<Arg> map);

    ArrayList<ClassEntity> getByArgs(List<Arg> map, Integer page, Integer size);


}
