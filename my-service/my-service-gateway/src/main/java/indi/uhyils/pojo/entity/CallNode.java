package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.pojo.DTO.DatabaseInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.GatewayUtil;
import indi.uhyils.util.Pair;
import java.util.Map;
import java.util.Optional;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class CallNode extends AbstractDataNode<CallNodeDO> {

    /**
     * 中间节点
     */
    private Node node;

    @Default
    public CallNode(CallNodeDO data) {
        super(data);
    }

    public CallNode(Long id) {
        super(id, new CallNodeDO());
    }

    @NotNull
    @Override
    public String databaseName() {
        CallNodeDO node = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        String url = node.getUrl();
        int firstIndex = url.indexOf("/");
        return url.substring(0, firstIndex);
    }

    @NotNull
    @Override
    public String tableName() {
        CallNodeDO node = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        String url = node.getUrl();
        int firstIndex = url.indexOf("/");
        return url.substring(firstIndex + 1);
    }

    /**
     * 转换为数据库格式
     *
     * @return
     */
    public DatabaseInfo changeToDatabaseInfo() {
        CallNodeDO callNodeDO = toData().orElseThrow(() -> Asserts.makeException("未填充"));
        String url = callNodeDO.getUrl();
        Pair<String, String> databaseAndTable = GatewayUtil.splitDataBaseUrl(url);
        DatabaseInfo databaseInfo = new DatabaseInfo();
        databaseInfo.setCatalogName(MysqlContent.CATALOG_NAME);
        String key = databaseAndTable.getKey();
        if (key == null) {
            return null;
        }
        databaseInfo.setSchemaName(key);
        databaseInfo.setDefaultCharacterSetName(MysqlContent.DEFAULT_CHARACTER_SET_NAME);
        databaseInfo.setDefaultCollationName(MysqlContent.DEFAULT_COLLATION_NAME);
        databaseInfo.setSqlPath(null);
        databaseInfo.setDefaultEncryption("NO");
        return databaseInfo;
    }

    /**
     * 向下填充
     *
     * @param nodeRepository
     */
    @Override
    public void fill(NodeRepository nodeRepository, ProviderInterfaceRepository providerInterfaceRepository) {
        Optional<CallNodeDO> callNodeOptional = toData();
        callNodeOptional.ifPresent(callNodeDO -> {
            this.node = nodeRepository.find(new CallNode(callNodeDO.getNodeId()));
            this.node.fill(nodeRepository, providerInterfaceRepository);
        });
    }

    @Override
    public NodeInvokeResult getResult(Map<String, String> header, Map<String, Object> params) {
        Asserts.assertTrue(node != null, "callNode未初始化");
        return node.getResult(header, params);
    }
}
