package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ProviderInfoDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ProviderInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.StringUtil;

/**
 * 服务提供者表(ProviderInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
public class ProviderInfo extends AbstractDoEntity<ProviderInfoDO> {

    @Default
    public ProviderInfo(ProviderInfoDO data) {
        super(data);
    }

    public ProviderInfo(Long id) {
        super(id, new ProviderInfoDO());
    }

    public ProviderInfo(Long id, ProviderInfoRepository rep) {
        super(id, new ProviderInfoDO());
        completion(rep);
    }

    public ProviderInfo(Identifier id) {
        super(id, new ProviderInfoDO());
    }

    /**
     * 检查名称是否重复
     *
     * @param rep
     */
    public void checkNameRepeat(ProviderInfoRepository rep) {
        ProviderInfoDO providerInfoDO = toData();
        Asserts.assertTrue(providerInfoDO != null);
        Boolean repeat = rep.checkNameRepeat(providerInfoDO.getName());
        Asserts.assertTrue(!repeat, "名称不能重复");
    }

    /**
     * 初始化默认信息
     */
    public void injDefaultInfo() {
        ProviderInfoDO providerInfoDO = toData();
        Asserts.assertTrue(providerInfoDO != null);
        String uniqueKey = providerInfoDO.getUniqueKey();
        if (StringUtil.isNotEmpty(uniqueKey)) {
            return;
        }
        IdUtil bean = SpringUtil.getBean(IdUtil.class);
        providerInfoDO.setUniqueKey(Long.toString(bean.newId()));
    }
}
