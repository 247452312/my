package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.InterfaceInfoAssembler;
import indi.uhyils.dao.InterfaceInfoDao;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInterface;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.InterfaceInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 接口信息表(节点)(InterfaceInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Repository
public class InterfaceInfoRepositoryImpl extends AbstractRepository<InterfaceInfo, InterfaceInfoDO, InterfaceInfoDao, InterfaceInfoDTO, InterfaceInfoAssembler> implements InterfaceInfoRepository {

    protected InterfaceInfoRepositoryImpl(InterfaceInfoAssembler convert, InterfaceInfoDao dao) {
        super(convert, dao);
    }


    @Override
    public List<InterfaceInterface> findChildsInterface(InterfaceInfo interfaceInfo) {
        Identifier unique = interfaceInfo.getUnique();
        return assembler.listDoToEntityInterface(findChildsInterface(Collections.singletonList(unique.getId())));
    }

    /**
     * 递归获取数据库中的interface
     *
     * @param rootId
     *
     * @return
     */
    private List<InterfaceInfoDO> findChildsInterface(List<Long> rootId) {
        LambdaQueryWrapper<InterfaceInfoDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(InterfaceInfoDO::getPid, rootId);
        List<InterfaceInfoDO> interfaceInfoDOS = dao.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(interfaceInfoDOS)) {
            return null;
        }
        List<InterfaceInfoDO> result = new ArrayList<>(interfaceInfoDOS);
        List<Long> childIds = interfaceInfoDOS.stream().map(BaseIdDO::getId).collect(Collectors.toList());
        List<InterfaceInfoDO> childsInterface = findChildsInterface(childIds);
        if (CollectionUtil.isNotEmpty(childsInterface)) {
            result.addAll(childsInterface);
        }
        return result;
    }
}
