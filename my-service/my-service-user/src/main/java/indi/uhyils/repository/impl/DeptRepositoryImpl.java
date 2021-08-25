package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.DeptConvert;
import indi.uhyils.dao.DeptDao;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class DeptRepositoryImpl extends AbstractRepository<Dept, DeptDO, DeptDao> implements DeptRepository {


    protected DeptRepositoryImpl(DeptConvert convert, DeptDao dao) {
        super(convert, dao);
    }

    @Override
    public List<Dept> findByRoleId(Identifier roleId) {
        Arg arg = new Arg("role_id", "=", roleId.getId());
        ArrayList<DeptDO> byArgsNoPage = getDao().getByArgsNoPage(Arrays.asList(arg));
        return byArgsNoPage.stream().map(Dept::new).collect(Collectors.toList());
    }
}
