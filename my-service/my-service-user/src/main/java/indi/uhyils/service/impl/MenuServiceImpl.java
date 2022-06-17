package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ContentAssembler;
import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.context.UserContext;
import indi.uhyils.enums.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.MenuTreeBuilder;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import indi.uhyils.pojo.DTO.response.IndexMenuTreeDTO;
import indi.uhyils.pojo.DTO.response.MenuHtmlTreeDTO;
import indi.uhyils.pojo.DTO.response.info.MenuHomeInfo;
import indi.uhyils.pojo.DTO.response.info.MenuLogoInfo;
import indi.uhyils.pojo.DTO.response.info.MenuTreeDTO;
import indi.uhyils.pojo.entity.Content;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Iframe;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单(Menu)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分48秒
 */
@Service
@ReadWriteMark(tables = {"sys_menu"})
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
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean putDeptsToMenu(Identifier menuId, List<Identifier> deptIds) {
        Menu menu = new Menu(menuId);
        menu.cleanDept(rep);
        menu.addDepts(deptIds.stream().map(Dept::new).collect(Collectors.toList()), rep);
        return true;
    }

    @Override
    @ReadWriteMark(tables = {"sys_menu", "sys_content", "sys_dept", "sys_role_dept"})
    public IndexMenuTreeDTO getIndexMenu() {
        // 初始化角色的权限
        User user = userAssembler.toEntity(UserContext.doGet());
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

        /* 5. 返回 */
        return IndexMenuTreeDTO.build(menuHomeInfo, menuLogoInfo, menuTree);
    }

    @Override
    @ReadWriteMark(tables = {"sys_menu", "sys_content", "sys_dept", "sys_role_dept"})
    public MenuHtmlTreeDTO getMenuTree(Iframe iframe) {
        // 初始化角色的权限
        User user = userAssembler.toEntity(UserContext.doGet());
        user.initRole(roleRepository, deptRepository, powerRepository, menuRepository);

        /* 1. 全取出来 */
        List<Menu> menus = rep.findByIframe(new MenuIframe(iframe.getIframe()));
        /* 2. 只取出来有用的 */
        menus = user.screenMenu(menus);
        List<MenuDTO> menuDTOS = menus.stream().map(assem::toDTO).collect(Collectors.toList());
        return MenuHtmlTreeDTO.build(menuDTOS);
    }

    @Override
    @ReadWriteMark(tables = {"sys_dept_menu", "sys_menu"})
    public List<GetAllMenuWithHaveMarkDTO> getAllMenuWithHaveMark(Identifier deptId) {
        return rep.findAllMenuWithHaveMark(deptId);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeMenu(Identifier menuId) {
        /* 注:开启了事务 即@Transactional 参数propagation->事务传播类型,其中Propagation.REQUIRED为如果事务不存在,则创建新事物,如果事务存在,则加入
           isolation事务隔离级别 Isolation.DEFAULT默认隔离级别 */

        Menu menu = new Menu(menuId);
        menu.completion(rep);
        // 清空对应的连接
        menu.cleanDept(rep);
        // 删除自己以及子节点
        menu.removeSelf(rep, assem);
        return true;
    }


    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu", "sys_dept"})
    public List<GetDeptsByMenuIdDTO> getDeptsByMenuId(Identifier menuId) {
        return deptRepository.findByMenuId(new Menu(menuId));
    }

}
