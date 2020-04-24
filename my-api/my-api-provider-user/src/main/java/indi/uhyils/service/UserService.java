package indi.uhyils.service;

import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.UserEntity;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;


/**
 * 学生接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时29分
 */
public interface UserService extends DefaultEntityService<UserEntity> {


    /**
     * 根据班级号获取所有此班级的学生
     *
     * @param idRequest 班级id
     * @return
     */
    ServiceResult<Page<UserEntity>> getByClassId(IdRequest idRequest);


    /**
     * 根据某几列获取数据
     *
     * @return
     */
    ServiceResult<Page<UserEntity>> getByArgs(ArgsRequest argsRequest);


}
