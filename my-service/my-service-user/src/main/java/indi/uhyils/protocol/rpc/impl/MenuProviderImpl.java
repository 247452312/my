package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.*;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.*;
import indi.uhyils.pojo.DTO.request.GetByIFrameAndDeptsRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdResponse;
import indi.uhyils.pojo.DTO.response.IndexMenuTreeResponse;
import indi.uhyils.pojo.DTO.response.MenuHtmlTreeResponse;
import indi.uhyils.pojo.DTO.response.QuickStartResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.info.IndexMenuInfo;
import indi.uhyils.pojo.DTO.response.info.MenuHomeInfo;
import indi.uhyils.pojo.DTO.response.info.MenuLogoInfo;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.protocol.rpc.provider.MenuProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.util.ContentUtil;
import indi.uhyils.util.LogUtil;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 菜单接口实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时48分
 */
@RpcService
@ReadWriteMark(tables = {"sys_menu"})
public class MenuProviderImpl extends BaseDefaultProvider<MenuDO> implements MenuProvider {

    /**
     * 超级管理员的账号
     */
    private static final String ADMIN_USER_NAME = "admin";

    /**
     * 最高一级菜单的fid
     */
    private static final Long NONE = 0L;

    /**
     * 首页下一步计划展示 字典code
     */
    private static final String QUICK_START_CODE = "quickStart";

    @Resource
    private MenuDao dao;

    @Resource
    private ContentDao contentDao;

    @Resource
    private DeptDao deptDao;

    @Resource
    private UserDao userDao;

    @Resource
    private DictDao dictDao;
    @Resource
    private DictItemDao dictItemDao;


    @Override
    public MenuDao getDao() {
        return dao;
    }

    public void setDao(MenuDao dao) {
        this.dao = dao;
    }

    @Override
    @ReadWriteMark(tables = {"sys_menu", "sys_content", "sys_dept", "sys_role_dept"})
    public ServiceResult<IndexMenuTreeResponse> getIndexMenu(DefaultRequest request) {
        ContentDO indexIframe = contentDao.getByName("indexIframe");
        /* 1. 全取出来 */
        String indexIframe1 = ContentUtil.getContentVarByTitle(indexIframe, "indexIframe");
        assert indexIframe1 != null;
        List<MenuDO> byIFrame = dao.getByIFrame(Integer.parseInt(indexIframe1));
        Map<Long, MenuDO> map = byIFrame.stream().collect(Collectors.toMap(MenuDO::getId, Function.identity(), (k1, k2) -> k1));
        Set<MenuDO> set = new HashSet<>();
        UserDO user = request.getUser();
        if (user.getRole() == null && user.getRoleId() == null) {
            return ServiceResult.buildFailedResult("查询成功,此账号没有角色,请添加", null);
        }
        List<DeptDO> depts;
        if (user.getRole() == null) {
            depts = userDao.getUserDeptsByRoleId(user.getRoleId());
        } else {
            depts = user.getRole().getDepts();
        }
        List<Long> deptIds = depts.stream().map(DeptDO::getId).collect(Collectors.toList());
        // 准备获取权限内的子节点
        List<Long> level;
        // 超级管理员 就是牛逼
        if (user.getUserName().equals(ADMIN_USER_NAME)) {
            level = byIFrame.stream().map(MenuDO::getId).collect(Collectors.toList());
        } else {
            level = dao.getByDeptIds(deptIds);
        }

        /* 2. 只取出来有用的 */
        for (Long id : level) {
            if (map.containsKey(id)) {
                MenuDO e = map.get(id);
                set.add(e);
                getParents(e, set, map);
            }
        }
        /* 4. tree */
        ArrayList<IndexMenuInfo> menuMenuInfoArrayList = buildMenuTree(set);
        ContentDO honeInfo = contentDao.getByName("honeInfo");
        ContentDO logoInfo = contentDao.getByName("logoInfo");

        IndexMenuTreeResponse tree = new IndexMenuTreeResponse();
        tree.setMenuInfo(menuMenuInfoArrayList);
        tree.setHomeInfo(MenuHomeInfo.build(honeInfo));
        tree.setLogoInfo(MenuLogoInfo.build(logoInfo));
        /* 5. 返回 */
        return ServiceResult.buildSuccessResult("菜单请求成功", tree);
    }

    @Override
    @ReadWriteMark(tables = {"sys_menu", "sys_content", "sys_dept", "sys_role_dept"})
    public ServiceResult<MenuHtmlTreeResponse> getMenuTree(GetByIFrameAndDeptsRequest request) {
        /* 1. 全取出来 */
        List<MenuDO> byIFrame = dao.getByIFrame(request.getiFrame());
        Map<Long, MenuDO> map = byIFrame.stream().collect(Collectors.toMap(MenuDO::getId, Function.identity(), (k1, k2) -> k1));
        HashSet<MenuDO> set = new HashSet<>();
        UserDO user = request.getUser();
        if (user.getRole() == null && user.getRoleId() == null) {
            return ServiceResult.buildFailedResult("查询成功,此账号没有角色,请添加", null);
        }
        List<DeptDO> depts;

        // 准备获取权限内的子节点
        List<Long> level;
        // 超级管理员 就是牛逼
        if (user.getUserName().equals(ADMIN_USER_NAME)) {
            level = byIFrame.stream().map(MenuDO::getId).collect(Collectors.toList());
        } else {
            if (user.getRole() == null) {
                depts = userDao.getUserDeptsByRoleId(user.getRoleId());
            } else {
                depts = user.getRole().getDepts();
            }
            List<Long> deptIds = depts.stream().map(DeptDO::getId).collect(Collectors.toList());
            level = dao.getByDeptIds(deptIds);
        }

        /* 2. 只取出来有用的 */
        for (Long id : level) {
            if (map.containsKey(id)) {
                MenuDO e = map.get(id);
                set.add(e);
                getParents(e, set, map);
            }
        }
        return ServiceResult.buildSuccessResult("查询树列表成功", MenuHtmlTreeResponse.build(set));
    }


    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ServiceResult<Boolean> deleteMenu(IdRequest req) {
        /* 注:开启了事务 即@Transactional 参数propagation->事务传播类型,其中Propagation.REQUIRED为如果事务不存在,则创建新事物,如果事务存在,则加入
           isolation事务隔离级别 Isolation.DEFAULT默认隔离级别 */

        /* 1.删除所有对应子节点 */
        Long menuId = req.getId();
        MenuDO menuEntity = dao.getById(menuId);
        if (menuEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        Integer frame = menuEntity.getiFrame();
        List<MenuDO> byIFrame = dao.getByIFrame(frame);
        HashSet<MenuDO> menuEntityHashSet = new HashSet<>();
        menuEntityHashSet.add(menuEntity);
        addWillDeleteChild(menuEntity, menuEntityHashSet, byIFrame);
        List<Long> menuIds = menuEntityHashSet.stream().map(MenuDO::getId).collect(Collectors.toList());
        dao.deleteByIds(menuIds);
        /* 2.删除连接表节点 */
        dao.deleteDeptMenuByMenuIds(menuIds);
        return ServiceResult.buildSuccessResult("删除成功", true);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu", "sys_dept"})
    public ServiceResult<ArrayList<GetDeptsByMenuIdResponse>> getDeptsByMenuId(IdRequest req) {
        ArrayList<GetDeptsByMenuIdResponse> list = deptDao.getByMenuId(req.getId());
        return ServiceResult.buildSuccessResult("查询成功", list);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict", "sys_dict_item"})
    public ServiceResult<QuickStartResponse> getQuickStartResponse(DefaultRequest request) {
        Long idByCode = dictDao.getIdByCode(QUICK_START_CODE);
        ArrayList<DictItemDO> byDictId = dictItemDao.getByDictId(idByCode);
        ArrayList<MenuDO> collect = (ArrayList<MenuDO>) byDictId.stream().map(t -> {
            Long menuId = Long.valueOf(t.getValue().toString());
            MenuDO byId = dao.getById(menuId);
            if (Boolean.FALSE.equals(byId.getType())) {
                try {
                    throw new Exception("服务字典中快捷入口(" + byId.getName() + ") 不是叶子结点");
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }
            }
            return byId;
        }).collect(Collectors.toList());
        QuickStartResponse build = QuickStartResponse.build(collect);
        return ServiceResult.buildSuccessResult("查询快捷入口成功", build);
    }

    /**
     * 添加将要删除的子节点
     *
     * @param menuEntity
     * @param menuEntityHashSet
     * @param byIFrame
     */
    private void addWillDeleteChild(MenuDO menuEntity, HashSet<MenuDO> menuEntityHashSet, List<MenuDO> byIFrame) {
        byIFrame.forEach(t -> {
            if (t.getFid().equals(menuEntity.getId())) {
                menuEntityHashSet.add(t);
                addWillDeleteChild(t, menuEntityHashSet, byIFrame);
            }
        });
    }


    /**
     * 递归添加父节点
     *
     * @param e   子节点
     * @param set 已有节点集
     * @param map 全部节点
     */
    private void getParents(MenuDO e, Set<MenuDO> set, Map<Long, MenuDO> map) {
        Long fid = e.getFid();
        if (fid == null || fid == 0L) {
            return;
        }
        MenuDO father = map.get(fid);
        set.add(father);
        getParents(father, set, map);

    }


    private ArrayList<IndexMenuInfo> buildMenuTree(Set<MenuDO> byIFrame) {
        ArrayList<IndexMenuInfo> menuInfo = new ArrayList<>();

        // 父节点都找出来
        for (MenuDO menuEntity : byIFrame) {
            if (NONE.equals(menuEntity.getFid())) {
                menuInfo.add(IndexMenuInfo.build(menuEntity));
            }
        }
        //每一个父节点都添加属于自己的树
        for (IndexMenuInfo treeResponse : menuInfo) {
            addChild(treeResponse, byIFrame);
        }
        return menuInfo;
    }


    /**
     * 递归添加子结点
     *
     * @param treeResponse 父节点
     * @param byIFrame     全部有用节点
     */
    private void addChild(IndexMenuInfo treeResponse, Set<MenuDO> byIFrame) {
        for (MenuDO menuEntity : byIFrame) {
            if (menuEntity.getFid().equals(treeResponse.getId())) {
                IndexMenuInfo build = IndexMenuInfo.build(menuEntity);
                addChild(build, byIFrame);
                treeResponse.getChild().add(build);
            }
        }
    }

}
