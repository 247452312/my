package indi.uhyils.dao;

import indi.uhyils.model.UserEntity;
import indi.uhyils.model.UserRightEntity;
import indi.uhyils.request.model.Arg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface UserDao extends DefaultDao<UserEntity> {


    ArrayList<UserEntity> getByArgsNoPage(List<Arg> map);

    ArrayList<UserEntity> getByArgs(@Param("args") List<Arg> args,@Param("page") Integer page,@Param("size") Integer size);

    List<UserRightEntity> getUserRightsByUserId(String userId);

    UserRightEntity getUserRightsByRightId(String userRightId);
}
