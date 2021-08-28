package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.request.GetByIFrameAndDeptsQuery;
import indi.uhyils.pojo.DTO.request.PutDeptsToMenuCommand;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import indi.uhyils.pojo.DTO.response.IndexMenuTreeResponse;
import indi.uhyils.pojo.DTO.response.MenuHtmlTreeResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.MenuProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单接口实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时48分
 */
@RpcService
@ReadWriteMark(tables = {"sys_menu"})
public class MenuProviderImpl extends BaseDefaultProvider<MenuDTO> implements MenuProvider {

    @Autowired
    private MenuService service;

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ServiceResult<Boolean> putDeptsToMenu(PutDeptsToMenuCommand request) {
        return service.putDeptsToMenu(request);
    }

    @Override
    @ReadWriteMark(tables = {"sys_menu", "sys_content", "sys_dept", "sys_role_dept"})
    public ServiceResult<IndexMenuTreeResponse> getIndexMenu(DefaultCQE request) {
        return service.getIndexMenu(request);

    }

    @Override
    @ReadWriteMark(tables = {"sys_menu", "sys_content", "sys_dept", "sys_role_dept"})
    public ServiceResult<MenuHtmlTreeResponse> getMenuTree(GetByIFrameAndDeptsQuery request) {
        return service.getMenuTree(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ServiceResult<Boolean> removeMenu(IdCommand request) {
        return service.removeMenu(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu", "sys_dept"})
    public ServiceResult<List<GetDeptsByMenuIdDTO>> getDeptsByMenuId(IdQuery request) {
        return service.getDeptsByMenuId(request);

    }

    @Override
    @ReadWriteMark(tables = {"sys_dept_menu", "sys_menu"})
    public ServiceResult<List<GetAllMenuWithHaveMarkDTO>> getAllMenuWithHaveMark(IdQuery request) {
        return service.getAllMenuWithHaveMark(request);

    }

    @Override
    protected BaseDoService<MenuDTO> getService() {
        return service;
    }
}
