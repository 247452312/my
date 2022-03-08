package indi.uhyils.pojo.entity.interfaces;

import com.alibaba.fastjson.JSON;
import indi.uhyils.annotation.Default;
import indi.uhyils.enum_.InterfaceTypeEnum;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.DO.SourceInfoDO;
import indi.uhyils.pojo.entity.DbInfo;
import indi.uhyils.pojo.entity.HttpInfo;
import indi.uhyils.pojo.entity.MqInfo;
import indi.uhyils.pojo.entity.SourceInfo;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ConsumerFilterRepository;
import indi.uhyils.repository.DbInfoRepository;
import indi.uhyils.repository.HttpInfoRepository;
import indi.uhyils.repository.InterfaceInfoRepository;
import indi.uhyils.repository.MqInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 接口信息表(节点)(InterfaceInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
public class InterfaceInfo extends AbstractDoEntity<InterfaceInfoDO> implements InterfaceInterface {


    /**
     * 对应资源的信息,可以是http资源,可以是MQ资源
     */
    protected SourceInfo<? extends SourceInfoDO> sourceInfo;

    /**
     * 子类
     */
    private List<InterfaceInterface> child;

    /**
     * 碾平子类(只有根节点存在)
     */
    private List<InterfaceInterface> flattenChild;

    @Default
    public InterfaceInfo(InterfaceInfoDO data) {
        super(data);
    }

    public InterfaceInfo(Long id) {
        super(id, new InterfaceInfoDO());
    }

    public InterfaceInfo(Long id, InterfaceInfoRepository rep) {
        super(id, new InterfaceInfoDO());
        completion(rep);
    }

    public InterfaceInfo(Identifier id) {
        super(id, new InterfaceInfoDO());
    }

    @Override
    public void completionChild(InterfaceInfoRepository rep) {
        // 递归获取interface
        if (this.flattenChild == null) {
            List<InterfaceInterface> child = rep.findChildsInterface(this);
            this.flattenChild = child;
            makeTreeOfThisChild(child);
        }
    }


    protected void makeTreeOfThisChild(List<InterfaceInterface> childs) {
        // 本身是树的根节点,所以要加入进去组装树
        childs.add(this);

        // 遍历所有子类
        for (InterfaceInterface interfaceInterface : childs) {
            List<InterfaceInterface> child = interfaceInterface.toChild();
            // 遍历并查询子类的子类
            for (InterfaceInterface item : childs) {
                Long pid = item.toPid();
                // 如果子类id 等于 子类的子类的pid
                if (interfaceInterface.getUnique() != null && Objects.equals(interfaceInterface.getUnique().getId(), pid)) {
                    child.add(item);
                }
            }
        }
    }

    @Override
    public JSON invoke(Long consumerId, Map<String, Object> map, ConsumerFilterRepository consumerFilterRepository) throws SQLException, Exception {
        // todo invoke 对接平台 要动态执行sql
        return null;
    }

    @Override
    public List<InterfaceInterface> toChild() {
        if (child == null) {
            this.child = new ArrayList<>();
        }
        return child;
    }

    @Override
    public Long toPid() {
        InterfaceInfoDO interfaceInfoDO = toData();
        Asserts.assertTrue(interfaceInfoDO != null);
        return interfaceInfoDO.getPid();
    }

    @Override
    public InterfaceTypeEnum toType() {
        InterfaceInfoDO interfaceInfoDO = toData();
        Asserts.assertTrue(interfaceInfoDO != null);
        return InterfaceTypeEnum.parse(interfaceInfoDO.getType());
    }

    @Override
    public Long toMarkId() {
        return toData().getMarkId();
    }

    @Override
    public void initSourceInfo(SourceInfo<? extends SourceInfoDO> info) {
        // 加速用
        if (sourceInfo == null) {
            // 内层判断
            if (Objects.equals(toMarkId(), info.getUnique().getId()) && info.type() == this.toType()) {
                this.sourceInfo = info;
            } else {
                Asserts.assertTrue(false, "资源分配错误,接口资源id:{},资源id:{}, 接口类型:{}, 资源类型:{}", this.toMarkId(), info.getUnique().getId(), this.toType().getName(), info.type().getName());
            }
        }
    }

    /**
     * 获取子类资源并填充
     *
     * @param httpInfoRepository
     * @param mqInfoRepository
     * @param dbInfoRepository
     *
     * @return
     */
    public void findSourceAndFull(HttpInfoRepository httpInfoRepository, MqInfoRepository mqInfoRepository, DbInfoRepository dbInfoRepository) {
        // 如果本身就是叶子结点,就不需要批量查询数据库了,直接转换为子类型即可
        InterfaceInfoDO interfaceInfoDO = toData();
        InterfaceTypeEnum parse = InterfaceTypeEnum.parse(interfaceInfoDO.getType());
        Map<InterfaceTypeEnum, List<InterfaceInterface>> collect;
        if (parse != null) {
            // 代表自身是子节点
            collect = Stream.of(this).filter(t -> t.toType() != null).collect(Collectors.groupingBy(InterfaceInterface::toType));
        } else {
            collect = this.flattenChild.stream().filter(t -> t.toType() != null).collect(Collectors.groupingBy(InterfaceInterface::toType));
        }

        // 获取资源信息 key->资源类型, value->资源信息
        List<SourceInfo<? extends SourceInfoDO>> sourceInfos = findSourceInfo(httpInfoRepository, mqInfoRepository, dbInfoRepository, collect);
        // 遍历子类
        for (InterfaceInterface interfaceInterface : flattenChild) {
            InterfaceTypeEnum sourceTypeEnum = interfaceInterface.toType();
            // 资源类型为空,代表不是叶子结点
            if (sourceTypeEnum == null) {
                continue;
            }
            // 遍历资源
            for (SourceInfo<? extends SourceInfoDO> sourceInfo : sourceInfos) {
                Identifier sourceInfoId = sourceInfo.getUnique();
                if (sourceInfoId == null) {
                    continue;
                }
                // 接口资源id
                Long markId = interfaceInterface.toMarkId();
                // 子类的资源id 和 资源id相等, 类型也相等, 说明一致.
                if (Objects.equals(markId, sourceInfoId.getId()) && sourceInfo.type() == interfaceInterface.toType()) {
                    interfaceInterface.initSourceInfo(sourceInfo);
                }
            }
        }
    }

    /**
     * 根据各自的interface信息获取对接信息, 例如http接口获取http本身的信息
     *
     * @param httpInfoRepository
     * @param mqInfoRepository
     * @param dbInfoRepository
     * @param collect
     *
     * @return
     */
    private List<SourceInfo<? extends SourceInfoDO>> findSourceInfo(
        HttpInfoRepository httpInfoRepository, MqInfoRepository mqInfoRepository, DbInfoRepository dbInfoRepository, Map<InterfaceTypeEnum, List<InterfaceInterface>> collect) {
        List<SourceInfo<? extends SourceInfoDO>> result = new ArrayList<>();
        List<InterfaceInterface> dbInterface = collect.get(InterfaceTypeEnum.DB);
        if (CollectionUtil.isNotEmpty(dbInterface)) {
            List<DbInfo> dbInfos = dbInfoRepository.find(dbInterface.stream().map(InterfaceInterface::toMarkId).map(Identifier::new).collect(Collectors.toList()));
            result.addAll(dbInfos);
        }

        List<InterfaceInterface> mqInterface = collect.get(InterfaceTypeEnum.MQ);
        if (CollectionUtil.isNotEmpty(mqInterface)) {
            List<MqInfo> mqInfos = mqInfoRepository.find(mqInterface.stream().map(InterfaceInterface::toMarkId).map(Identifier::new).collect(Collectors.toList()));
            result.addAll(mqInfos);
        }

        List<InterfaceInterface> httpInterface = collect.get(InterfaceTypeEnum.HTTP);
        if (CollectionUtil.isNotEmpty(httpInterface)) {
            List<HttpInfo> httpInfos = httpInfoRepository.find(httpInterface.stream().map(InterfaceInterface::toMarkId).map(Identifier::new).collect(Collectors.toList()));
            result.addAll(httpInfos);
        }

        return result;
    }

    /**
     * 转换为自身正确的类型, 例如HTTP接口转换为 HttpInterfaceInfoInterface
     *
     * @return 本身节点的真实类型, 如果是叶子结点, 就会转换成httpInterface, MqInterface, DbInterface等, 如果不是. 就返回 InterfaceInfo
     */
    public InterfaceInterface transChildToMarkType() {
        /*1.判断自身是否是叶子结点*/
        if (this.toType() != null) {
            return transThisToReal();
        }
        /*2.递归转换子类*/
        this.child = this.child.stream().map(InterfaceInterface::transThisToReal).collect(Collectors.toList());
        // 本实例不是叶子结点, 直接返回本类即可
        return this;
    }

    /**
     * 叶子结点转换为真实类型
     *
     * @return
     */
    @Override
    public InterfaceInterface transThisToReal() {
        // 非叶子结点的情况下,直接返回自身原本类型
        if (this.toType() == null) {
            return this;
        }
        // 叶子结点必须要获取资源
        Asserts.assertTrue(this.sourceInfo != null, "资源未初始化");
        switch (this.toType()) {
            case MQ:
                MqInterfaceInfo mqInterfaceInfo = new MqInterfaceInfo(this.toData());
                mqInterfaceInfo.initSourceInfo(this.sourceInfo);
                return mqInterfaceInfo;
            case DB:
                DbInterfaceInfo dbInterfaceInfo = new DbInterfaceInfo(this.toData());
                dbInterfaceInfo.initSourceInfo(this.sourceInfo);
                return dbInterfaceInfo;
            case HTTP:
                HttpInterfaceInfo httpInterfaceInfo = new HttpInterfaceInfo(this.toData());
                httpInterfaceInfo.initSourceInfo(this.sourceInfo);
                return httpInterfaceInfo;
            default:
                Asserts.assertTrue(false, "接口类型不存在");
                return null;
        }
    }
}
