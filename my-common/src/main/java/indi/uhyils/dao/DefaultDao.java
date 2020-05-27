package indi.uhyils.dao;

import indi.uhyils.pojo.request.model.Arg;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 15时22分
 */
public interface DefaultDao<T extends Serializable> {

    List<T> getById(String id);

    ArrayList<T> getByArgsNoPage(List<Arg> map);

    ArrayList<T> getByArgs(@Param("args") List<Arg> args, @Param("page") Integer page, @Param("size") Integer size);

    int insert(T t);

    int update(T t);

    int delete(String id);

    int count();

    int checkRepeat(@Param("columnName") String columnName, @Param("value") Object value);


}
