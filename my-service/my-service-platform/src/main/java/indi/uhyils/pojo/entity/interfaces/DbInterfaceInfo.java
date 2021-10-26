package indi.uhyils.pojo.entity.interfaces;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.enum_.SourceTypeEnum;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.entity.ConsumerFilter;
import indi.uhyils.pojo.entity.DbInfo;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ConsumerFilterRepository;
import indi.uhyils.repository.InterfaceInfoRepository;
import indi.uhyils.util.Asserts;
import java.sql.Connection;
import java.util.List;
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
    public JSON invoke(Long consumerId, Map<String, Object> map, ConsumerFilterRepository consumerFilterRepository) throws Exception {
        InterfaceInfoDO interfaceInfoDO = toData();
        Asserts.assertTrue(sourceInfo != null);
        Asserts.assertTrue(sourceInfo.type() == SourceTypeEnum.DB);
        DbInfo sourceInfo = (DbInfo) this.sourceInfo;
        Connection conn = sourceInfo.getConnect();
        List<ConsumerFilter> consumerFilters = consumerFilterRepository.findByConsumerAndInterface(consumerId, this);
        List<Map<String, Object>> result = sourceInfo.execute(conn, map, consumerFilters);
        return JSON.parseArray(JSONArray.toJSONString(result));
    }
}
