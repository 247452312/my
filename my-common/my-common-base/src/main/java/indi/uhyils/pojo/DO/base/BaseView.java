package indi.uhyils.pojo.DO.base;

import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.AssertUtil;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 17时49分
 */
public class BaseView implements BaseDbSaveable {

    @Override
    public void preInsert(DefaultCQE request) {
        AssertUtil.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preInsert(UserDTO userDO) {
        AssertUtil.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preInsert() {
        AssertUtil.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preUpdate(DefaultCQE request) {
        AssertUtil.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preUpdate(UserDTO userDO) {
        AssertUtil.assertTrue(false, "视图不能修改");
    }

    @Override
    public void preUpdate() {
        AssertUtil.assertTrue(false, "视图不能修改");
    }
}
