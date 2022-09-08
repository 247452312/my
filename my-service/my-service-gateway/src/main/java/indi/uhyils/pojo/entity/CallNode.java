package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.pojo.DTO.DatabaseInfo;
import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.GatewayUtil;
import java.util.Optional;
import javafx.util.Pair;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时33分
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

    @Override
    public String databaseName() {
        final CallNodeDO node = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        final String url = node.getUrl();
        final int firstIndex = url.indexOf("/");
        return url.substring(0, firstIndex);
    }

    @Override
    public String tableName() {
        final CallNodeDO node = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        final String url = node.getUrl();
        final int firstIndex = url.indexOf("/");
        return url.substring(firstIndex + 1);
    }

    /**
     * 转换为数据库格式
     *
     * @return
     */
    public DatabaseInfo changeToDatabaseInfo() {
        final CallNodeDO callNodeDO = toData().orElseThrow(() -> Asserts.makeException("未填充"));
        final String url = callNodeDO.getUrl();
        final Pair<String, String> databaseAndTable = GatewayUtil.splitDataBaseUrl(url);
        final DatabaseInfo databaseInfo = new DatabaseInfo();
        databaseInfo.setCatalogName(MysqlContent.CATALOG_NAME);
        final String key = databaseAndTable.getKey();
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
     * @param providerInterfaceRepository
     */
    public void fillSubNode(NodeRepository nodeRepository, ProviderInterfaceRepository providerInterfaceRepository) {
        final Optional<CallNodeDO> callNodeOptional = toData();
        callNodeOptional.ifPresent(callNodeDO -> {
            this.node = nodeRepository.find(new CallNode(callNodeDO.getNodeId()));
            this.node.fillSubNode(nodeRepository, providerInterfaceRepository);
        });
    }
}
