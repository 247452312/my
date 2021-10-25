package indi.uhyils.pojo.entity.interfaces;

import com.alibaba.fastjson.JSON;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.InterfaceInfoRepository;
import indi.uhyils.util.Asserts;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月22日 14时35分
 */
public class DbInterfaceInfo extends InterfaceInfo implements HttpInterfaceInfoInterface {


    public DbInterfaceInfo(InterfaceInfoDO data) {
        super(data);
    }

    public DbInterfaceInfo(Long id) {
        super(id);
    }

    public DbInterfaceInfo(Long id, InterfaceInfoRepository rep) {
        super(id, rep);
    }

    public DbInterfaceInfo(Identifier id) {
        super(id);
    }

    @Override
    public JSON invoke(Map<String, Object> map) {
        InterfaceInfoDO interfaceInfoDO = toData();
        // todo DB调用
        return null;
    }
}
