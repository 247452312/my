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
import indi.uhyils.protocol.rpc.MenuProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.MenuService;
import java.util.List;
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
        Boolean result = service.putDeptsToMenu(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<IndexMenuTreeDTO> getIndexMenu(DefaultCQE request) {
        IndexMenuTreeDTO result = service.getIndexMenu(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<MenuHtmlTreeDTO> getMenuTree(GetByIFrameAndDeptsQuery request) {
        MenuHtmlTreeDTO result = service.getMenuTree(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> removeMenu(IdCommand request) {
        Boolean result = service.removeMenu(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<GetDeptsByMenuIdDTO>> getDeptsByMenuId(IdQuery request) {
        List<GetDeptsByMenuIdDTO> result = service.getDeptsByMenuId(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<GetAllMenuWithHaveMarkDTO>> getAllMenuWithHaveMark(IdQuery request) {
        List<GetAllMenuWithHaveMarkDTO> result = service.getAllMenuWithHaveMark(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    protected BaseDoService<MenuDTO> getService() {
        return service;
    }
}
