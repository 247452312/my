package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.MenuDTO;
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
    public Boolean putDeptsToMenu(PutDeptsToMenuCommand request) {
        Identifier menuId = new Identifier(request.getMenuId());
        List<Identifier> deptIds = request.getDeptIds().stream().map(Identifier::new).collect(Collectors.toList());
        return service.putDeptsToMenu(menuId, deptIds);
    }

    @Override
    public IndexMenuTreeDTO getIndexMenu(DefaultCQE request) {
        return service.getIndexMenu();

    }

    @Override
    public MenuHtmlTreeDTO getMenuTree(GetByIFrameAndDeptsQuery request) {
        Iframe iframe = new Iframe(request.getiFrame());
        return service.getMenuTree(iframe);

    }

    @Override
    public Boolean removeMenu(IdCommand request) {
        Identifier menuId = new Identifier(request.getId());
        return service.removeMenu(menuId);

    }

    @Override
    public List<GetDeptsByMenuIdDTO> getDeptsByMenuId(IdQuery request) {
        Identifier menuId = new Identifier(request.getId());
        return service.getDeptsByMenuId(menuId);

    }

    @Override
    public List<GetAllMenuWithHaveMarkDTO> getAllMenuWithHaveMark(IdQuery request) {
        Identifier deptId = new Identifier(request.getId());
        return service.getAllMenuWithHaveMark(deptId);

    }

    @Override
    protected BaseDoService<MenuDTO> getService() {
        return service;
    }
}
