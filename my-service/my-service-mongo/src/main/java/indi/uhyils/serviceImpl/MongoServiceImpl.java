package indi.uhyils.serviceImpl;

import indi.uhyils.mongo.MongoManager;
import indi.uhyils.pojo.model.MongoEntity;
import indi.uhyils.pojo.request.NameRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.MongoService;
import indi.uhyils.util.MD5Util;
import indi.uhyils.utils.MongoEntityBuild;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 07时08分
 */
@Service(group = "${spring.profiles.active}")
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoManager mongoManager;

    @Override
    public ServiceResult<String> add(ObjRequest<MongoEntity> request) {
        MongoEntity mongoEntity = request.getData();
        String fileName = mongoEntity.getFileName();
        String id = request.getUser().getId();
        String s = MD5Util.MD5Encode(fileName + id);
        byte[] bytes = mongoEntity.getBytes();
        boolean b1 = mongoManager.addFile(s, bytes);

        if (!b1) {
            return ServiceResult.buildSuccessResult("插入执行完成", s, request);
        }
        return ServiceResult.buildFailedResult("插入出错", s, request);
    }

    @Override
    public ServiceResult<Boolean> delete(NameRequest request) {
        String name = request.getName();
        boolean b = mongoManager.removeFile(name);
        return ServiceResult.buildSuccessResult("删除执行完成", b, request);
    }

    @Override
    public ServiceResult<MongoEntity> getByFileName(NameRequest request) {
        String name = request.getName();
        byte[] file = mongoManager.getFile(name);
        MongoEntity mongoEntity = MongoEntityBuild.build(name, file);
        return ServiceResult.buildSuccessResult("获取文件成功", mongoEntity, request);
    }


}
