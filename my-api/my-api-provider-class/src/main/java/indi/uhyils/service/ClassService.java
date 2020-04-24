package indi.uhyils.service;

import indi.uhyils.request.ArgsRequest;
import indi.uhyils.model.ClassEntity;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;

/**
 * 班级接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时33分
 */
public interface ClassService extends DefaultEntityService<ClassEntity> {

    /**
     * 根据某几列获取数据
     *
     * @return
     */
    ServiceResult<Page<ClassEntity>> getByArgs(ArgsRequest argsRequest);

}
