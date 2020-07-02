package indi.uhyils.serviceImpl;

import indi.uhyils.mongo.MongoManager;
import indi.uhyils.pojo.model.MongoEntity;
import indi.uhyils.pojo.request.NameRequest;
import indi.uhyils.pojo.request.base.ObjsRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.MongoService;
import indi.uhyils.utils.MongoEntityBuild;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 07时08分
 */
@Service(group = "${spring.profiles.active}",protocol = "hessian")
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoManager mongoManager;

    @Override
    public ServiceResult<Boolean> add(ObjsRequest<MongoEntity> request) {
        List<MongoEntity> list = request.getList();
        boolean b = true;
        for (MongoEntity mongoEntity : list) {
            String fileName = mongoEntity.getFileName();
            byte[] bytes = mongoEntity.getBytes();
            boolean b1 = mongoManager.addFile(fileName, bytes);
            if (b1 == false) {
                b = false;
                break;
            }
        }
        if (b) {
            return ServiceResult.buildSuccessResult("插入执行完成", true, request);
        }
        return ServiceResult.buildFailedResult("插入出错", false, request);
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
