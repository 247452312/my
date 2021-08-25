package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.PowerConvert;
import indi.uhyils.dao.PowerDao;
import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.repository.PowerRepository;
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
public class PowerRepositoryImpl extends AbstractRepository<Power, PowerDO, PowerDao> implements PowerRepository {


    protected PowerRepositoryImpl(PowerConvert convert, PowerDao dao) {
        super(convert, dao);
    }

    @Override
    public List<Power> findByDeptId(Identifier deptId) {
        ArrayList<PowerDO> powerDos = getDao().getByArgsNoPage(Arrays.asList(new Arg("dept_id", "=", deptId.getId())));
        return powerDos.stream().map(Power::new).collect(Collectors.toList());
    }
}
