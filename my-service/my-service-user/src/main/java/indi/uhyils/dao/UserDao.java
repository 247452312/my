package indi.uhyils.dao;

import indi.uhyils.model.UserEntity;
import indi.uhyils.request.model.Arg;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface UserDao extends DefaultDao<UserEntity> {


    ArrayList<UserEntity> getByArgsNoPage(List<Arg> map);

    ArrayList<UserEntity> getByArgs(List<Arg> map, Integer page, Integer size);

    ArrayList<UserEntity> getByClassId(String id);
}
