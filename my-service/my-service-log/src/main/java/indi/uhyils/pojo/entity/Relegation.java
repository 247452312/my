package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.facade.ServiceControlFacade;
import indi.uhyils.pojo.DO.RelegationDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.RelegationRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.StringUtil;

/**
 * 接口降级策略(Relegation)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月27日 09时33分23秒
 */
public class Relegation extends AbstractDoEntity<RelegationDO> {

    /**
     * 默认入参个数
     */
    private static final Integer DEFAULT_PARAM_LENGTH = 1;

    /**
     * 日志类型
     */
    private LogTypeEnum logTypeEnum;

    @Default
    public Relegation(RelegationDO data) {
        super(data);
    }

    public Relegation(String serviceName, String methodName) {
        super(new RelegationDO());
        RelegationDO dO = toData();
        dO.setServiceName(serviceName);
        dO.setMethodName(methodName);
    }

    public Relegation(Long id) {
        super(id, new RelegationDO());
    }

    public Relegation(Long id, RelegationRepository rep) {
        super(id, new RelegationDO());
        completion(rep);
    }

    public Relegation(Identifier id) {
        super(id, new RelegationDO());
    }

    public Relegation(Integer type, String serviceName, String methodName) {
        super(new RelegationDO());
        RelegationDO dO = toData();
        this.logTypeEnum = LogTypeEnum.parse(type);
        dO.setServiceName(serviceName);
        dO.setMethodName(methodName);
    }

    /**
     * 检查是否合理
     *
     * @return
     */
    public void validate() {
        Asserts.assertTrue(logTypeEnum == LogTypeEnum.RPC, "非RPC,不是接口");
        Asserts.assertTrue(StringUtil.isNotEmpty(toData().getServiceName()) && StringUtil.isNotEmpty(toData().getMethodName()));
    }

    /**
     * 检查是否重复
     *
     * @param rep
     */
    public boolean checkRepeat(RelegationRepository rep) {
        return rep.checkRepeat(this);
    }

    /**
     * 设置默认值
     */
    public void initDefault() {
        RelegationDO relegationDO = toData();
        Asserts.assertTrue(relegationDO != null);
        Integer paramLength = relegationDO.getParamLength();
        if (paramLength == null) {
            relegationDO.setParamLength(DEFAULT_PARAM_LENGTH);
        }
    }

    /**
     * 降级
     *
     * @param facade
     *
     * @return
     */
    public boolean demotion(ServiceControlFacade facade) {
        RelegationDO dO = this.toData();
        return facade.demotion(dO.getServiceName(), dO.getMethodName());
    }

    /**
     * 服务恢复
     *
     * @param facade
     *
     * @return
     */
    public boolean recover(ServiceControlFacade facade) {
        RelegationDO dO = this.toData();
        return facade.recover(dO.getServiceName(), dO.getMethodName());
    }
}
