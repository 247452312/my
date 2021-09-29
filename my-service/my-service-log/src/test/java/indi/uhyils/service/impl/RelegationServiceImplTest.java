package indi.uhyils.service.impl;

import indi.uhyils.BaseTest;
import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.service.RelegationService;
import indi.uhyils.util.Asserts;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月28日 16时58分
 */
public class RelegationServiceImplTest extends BaseTest {

    @Autowired
    private RelegationService service;

    @Test
    public void demotion() {
        Boolean success = service.demotion("indi.uhyils.protocol.rpc.DictProvider", "getByCode");
        Asserts.assertTrue(success);
        RelegationDTO query = service.query(new Identifier(1712022700888686720L));
        Asserts.assertTrue(query.getDisable() == false);
        Boolean getByCode = service.recover("indi.uhyils.protocol.rpc.DictProvider", "getByCode");
        Asserts.assertTrue(getByCode);
        query = service.query(new Identifier(1712022700888686720L));
        Asserts.assertTrue(query.getDisable());

    }

    @Test
    public void recover() {
    }
}
