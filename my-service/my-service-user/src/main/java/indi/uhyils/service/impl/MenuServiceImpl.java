package indi.uhyils.service.impl;

import indi.uhyils.assembler.ContentAssembler;
import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.MenuTreeBuilder;
import indi.uhyils.pojo.DTO.request.GetByIFrameAndDeptsQuery;
import indi.uhyils.pojo.DTO.request.PutDeptsToMenuCommand;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import indi.uhyils.pojo.DTO.response.IndexMenuTreeResponse;
import indi.uhyils.pojo.DTO.response.MenuHtmlTreeResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.info.MenuHomeInfo;
import indi.uhyils.pojo.DTO.response.info.MenuLogoInfo;
import indi.uhyils.pojo.DTO.response.info.MenuTreeDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.Content;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.entity.MenuId;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.MenuIframe;
import indi.uhyils.repository.ContentRepository;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.service.MenuService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单(Menu)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分48秒
 */
@Service
public class MenuServiceImpl extends AbstractDoService<MenuDO, Menu, MenuDTO, MenuRepository, MenuAssembler> implements MenuService {

    /**
     * 主页显示菜单code
     */
    private static final Integer INDEX_IFRAME = 1;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DeptRepository deptRepository;


    @Autowired
    private PowerRepository powerRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentAssembler contentAssembler;


    public MenuServiceImpl(MenuAssembler assembler, MenuRepository repository) {
        super(assembler, repository);
    }


    @Override
    public ServiceResult<Boolean> putDeptsToMenu(PutDeptsToMenuCommand request) {
        MenuId menuId = new MenuId(request.getMenuId());
        menuId.cleanDept(rep);
        menuId.addDepts(request.getDeptIds(), rep);
        return ServiceResult.buildSuccessResult("赋权成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<IndexMenuTreeResponse> getIndexMenu(DefaultCQE request) {
        // 初始化角色的权限
        User user = userAssembler.toEntity(request.getUser());
        user.initRole(roleRepository, deptRepository, powerRepository, menuRepository);

        /* 1. 全取出来 */
        List<Menu> menus = rep.findByIframe(new MenuIframe(INDEX_IFRAME));
        /* 2. 只取出来有用的 */
        menus = user.screenMenu(menus);
        /* 4. tree */
        MenuTreeBuilder menuTreeBuilder = new MenuTreeBuilder();
        menuTreeBuilder.addMenu(menus.stream().map(assem::toDTO).collect(Collectors.toList()));
        List<MenuTreeDTO> menuTree = menuTreeBuilder.build();

        Content homeInfo = contentRepository.getByName("honeInfo");
        Content logoInfo = contentRepository.getByName("logoInfo");

        MenuHomeInfo menuHomeInfo = MenuHomeInfo.build(contentAssembler.toDTO(homeInfo));
        MenuLogoInfo menuLogoInfo = MenuLogoInfo.build(contentAssembler.toDTO(logoInfo));

        IndexMenuTreeResponse reuslt = IndexMenuTreeResponse.build(menuHomeInfo, menuLogoInfo, menuTree);
        /* 5. 返回 */
        return ServiceResult.buildSuccessResult("菜单请求成功", reuslt);
    }

    @Override
    public ServiceResult<MenuHtmlTreeResponse> getMenuTree(GetByIFrameAndDeptsQuery request) {
        // 初始化角色的权限
        User user = userAssembler.toEntity(request.getUser());
        user.initRole(roleRepository, deptRepository, powerRepository, menuRepository);

        /* 1. 全取出来 */
        List<Menu> menus = rep.findByIframe(new MenuIframe(INDEX_IFRAME));
        /* 2. 只取出来有用的 */
        menus = user.screenMenu(menus);
        List<MenuDTO> menuDTOS = menus.stream().map(assem::toDTO).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询树列表成功", MenuHtmlTreeResponse.build(menuDTOS));
    }

    @Override
    public ServiceResult<List<GetAllMenuWithHaveMarkDTO>> getAllMenuWithHaveMark(IdQuery request) {
        List<GetAllMenuWithHaveMarkDTO> result = rep.findAllMenuWithHaveMark(new Identifier(request));
        return ServiceResult.buildSuccessResult("查询菜单(包含羁绊)成功", result);
    }

    @Override
    public ServiceResult<Boolean> removeMenu(IdCommand request) {
        /* 注:开启了事务 即@Transactional 参数propagation->事务传播类型,其中Propagation.REQUIRED为如果事务不存在,则创建新事物,如果事务存在,则加入
           isolation事务隔离级别 Isolation.DEFAULT默认隔离级别 */

        MenuId menuId = new MenuId(request.getId());
        Menu menu = menuId.toMenu(rep);
        // 清空对应的连接
        menuId.cleanDept(rep);
        // 删除自己以及子节点
        menu.removeSelf(rep, assem);
        return ServiceResult.buildSuccessResult("删除成功", true);
    }


    @Override
    public ServiceResult<List<GetDeptsByMenuIdDTO>> getDeptsByMenuId(IdQuery request) {
        return ServiceResult.buildSuccessResult("查询成功", deptRepository.findByMenuId(new MenuId(request.getId())));
    }

}
