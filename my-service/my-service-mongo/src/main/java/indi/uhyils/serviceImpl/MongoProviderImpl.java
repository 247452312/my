package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enums.ReadWriteTypeEnum;
import indi.uhyils.mongo.MongoManager;
import indi.uhyils.pojo.DTO.request.NameRequest;
import indi.uhyils.pojo.cqe.command.base.AddCommand;
import indi.uhyils.protocol.rpc.MongoProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.MD5Util;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 07时08分
 */
@RpcService
public class MongoProviderImpl implements MongoProvider {

    @Autowired
    private MongoManager mongoManager;

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public String add(AddCommand<String> request) {
        String file = request.getDto();
        String uuid = UUID.randomUUID().toString();
        String md5 = MD5Util.MD5Encode(uuid);
        boolean b1 = mongoManager.addFile(md5, file);
        Asserts.assertTrue(b1, "mongoDB插入文件报错");
        return md5;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public Boolean delete(NameRequest request) {
        String name = request.getName();
        boolean b = mongoManager.removeFile(name);
        return b;
    }

    @Override
    public String getByFileName(NameRequest request) {
        String name = request.getName();
        return mongoManager.getFile(name);
    }


}
