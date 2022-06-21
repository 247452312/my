package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetByIFrameAndDeptsQuery;
import indi.uhyils.pojo.DTO.request.PutDeptsToMenuCommand;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import indi.uhyils.pojo.DTO.response.IndexMenuTreeDTO;
import indi.uhyils.pojo.DTO.response.MenuHtmlTreeDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Iframe;
import indi.uhyils.protocol.rpc.MenuProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.MenuService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 菜单接口实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时48分
 */
@RpcService
public class MenuProviderImpl extends BaseDefaultProvider<MenuDTO> implements MenuProvider {

    @Autowired
    private MenuService service;

    @Override
    public ServiceResult<Boolean> putDeptsToMenu(PutDeptsToMenuCommand request) {
        Identifier menuId = new Identifier(request.getMenuId());
        List<Identifier> deptIds = request.getDeptIds().stream().map(Identifier::new).collect(Collectors.toList());
        Boolean result = service.putDeptsToMenu(menuId, deptIds);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<IndexMenuTreeDTO> getIndexMenu(DefaultCQE request) {
        IndexMenuTreeDTO result = service.getIndexMenu();
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<MenuHtmlTreeDTO> getMenuTree(GetByIFrameAndDeptsQuery request) {
        Iframe iframe = new Iframe(request.getiFrame());
        MenuHtmlTreeDTO result = service.getMenuTree(iframe);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> removeMenu(IdCommand request) {
        Identifier menuId = new Identifier(request.getId());
        Boolean result = service.removeMenu(menuId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<GetDeptsByMenuIdDTO>> getDeptsByMenuId(IdQuery request) {
        Identifier menuId = new Identifier(request.getId());
        List<GetDeptsByMenuIdDTO> result = service.getDeptsByMenuId(menuId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<GetAllMenuWithHaveMarkDTO>> getAllMenuWithHaveMark(IdQuery request) {
        Identifier deptId = new Identifier(request.getId());
        List<GetAllMenuWithHaveMarkDTO> result = service.getAllMenuWithHaveMark(deptId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    protected BaseDoService<MenuDTO> getService() {
        return service;
    }
}
