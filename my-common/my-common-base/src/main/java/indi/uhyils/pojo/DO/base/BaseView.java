package indi.uhyils.pojo.DO.base;

import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.Asserts;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 17时49分
 */
public class BaseView implements BaseDbSaveable {

    @Override
    public void preInsert(DefaultCQE request) {
        Asserts.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preInsert(UserDTO userDO) {
        Asserts.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preInsert() {
        Asserts.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preUpdate(DefaultCQE request) {
        Asserts.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preUpdate(UserDTO userDO) {
        Asserts.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preUpdate() {
        Asserts.assertTrue(false, "视图不能修改");
    }
}
