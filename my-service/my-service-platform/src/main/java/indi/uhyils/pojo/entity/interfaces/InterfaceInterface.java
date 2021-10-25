package indi.uhyils.pojo.entity.interfaces;

import com.alibaba.fastjson.JSON;
import indi.uhyils.enum_.SourceTypeEnum;
import indi.uhyils.pojo.DO.SourceInfoDO;
import indi.uhyils.pojo.entity.SourceInfo;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.InterfaceInfoRepository;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月22日 15时07分
 */
public interface InterfaceInterface {

    /**
     * 填充子类
     *
     * @param rep
     */
    void completionChild(InterfaceInfoRepository rep);

    /**
     * 执行
     *
     * @param map
     *
     * @return
     */
    JSON invoke(Map<String, Object> map);

    /**
     * 获取子类
     *
     * @return
     */
    List<InterfaceInterface> toChild();


    /**
     * 获取父类id
     *
     * @return
     */
    Long toPid();

    /**
     * 获取唯一标示
     *
     * @return
     */
    Identifier getUnique();


    /**
     * 获取类型
     *
     * @return
     */
    SourceTypeEnum toType();

    /**
     * 获取类型对应的id
     *
     * @return
     */
    Long toMarkId();

    /**
     * 转换本身为真实类型
     *
     * @return
     */
    InterfaceInterface transThisToReal();

    /**
     * 初始化资源
     *
     * @param info
     */
    void initSourceInfo(SourceInfo<? extends SourceInfoDO> info);
}
