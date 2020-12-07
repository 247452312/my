package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.*;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.*;
import indi.uhyils.pojo.request.GetByIFrameAndDeptsRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.GetDeptsByMenuIdResponse;
import indi.uhyils.pojo.response.IndexMenuTreeResponse;
import indi.uhyils.pojo.response.MenuHtmlTreeResponse;
import indi.uhyils.pojo.response.QuickStartResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.info.IndexMenuInfo;
import indi.uhyils.pojo.response.info.MenuHomeInfo;
import indi.uhyils.pojo.response.info.MenuLogoInfo;
import indi.uhyils.service.MenuService;
import indi.uhyils.util.ContentUtil;
import indi.uhyils.util.LogUtil;
import org.apache.dubbo.config.annotation.Service;
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
@Service(group = "${spring.profiles.active}")
@ReadWriteMark(tables = {"sys_menu"})
public class MenuServiceImpl extends BaseDefaultServiceImpl<MenuEntity> implements MenuService {

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


    public MenuDao getDao() {
        return dao;
    }

    public void setDao(MenuDao dao) {
        this.dao = dao;
    }

    @Override
    @ReadWriteMark(tables = {"sys_menu", "sys_content", "sys_dept", "sys_role_dept"})
    public ServiceResult<IndexMenuTreeResponse> getIndexMenu(DefaultRequest request) {
        ContentEntity indexIframe = contentDao.getByName("indexIframe");
        /* 1. 全取出来 */
        String indexIframe1 = ContentUtil.getContentVarByTitle(indexIframe, "indexIframe");
        assert indexIframe1 != null;
        List<MenuEntity> byIFrame = dao.getByIFrame(Integer.parseInt(indexIframe1));
        Map<Long, MenuEntity> map = byIFrame.stream().collect(Collectors.toMap(MenuEntity::getId, Function.identity(), (k1, k2) -> k1));
        Set<MenuEntity> set = new HashSet<>();
        UserEntity user = request.getUser();
        if (user.getRole() == null && user.getRoleId() == null) {
            return ServiceResult.buildFailedResult("查询成功,此账号没有角色,请添加", null, request);
        }
        List<DeptEntity> depts;
        if (user.getRole() == null) {
            depts = userDao.getUserDeptsByRoleId(user.getRoleId());
        } else {
            depts = user.getRole().getDepts();
        }
        List<Long> deptIds = depts.stream().map(DeptEntity::getId).collect(Collectors.toList());
        // 准备获取权限内的子节点
        List<Long> level;
        // 超级管理员 就是牛逼
        if (user.getUserName().equals(ADMIN_USER_NAME)) {
            level = byIFrame.stream().map(MenuEntity::getId).collect(Collectors.toList());
        } else {
            level = dao.getByDeptIds(deptIds);
        }

        /* 2. 只取出来有用的 */
        for (Long id : level) {
            if (map.containsKey(id)) {
                MenuEntity e = map.get(id);
                set.add(e);
                getParents(e, set, map);
            }
        }
        /* 4. tree */
        ArrayList<IndexMenuInfo> menuMenuInfoArrayList = buildMenuTree(set);
        ContentEntity honeInfo = contentDao.getByName("honeInfo");
        ContentEntity logoInfo = contentDao.getByName("logoInfo");

        IndexMenuTreeResponse tree = new IndexMenuTreeResponse();
        tree.setMenuInfo(menuMenuInfoArrayList);
        tree.setHomeInfo(MenuHomeInfo.build(honeInfo));
        tree.setLogoInfo(MenuLogoInfo.build(logoInfo));
        /* 5. 返回 */
        return ServiceResult.buildSuccessResult("菜单请求成功", tree, request);
    }

    @Override
    @ReadWriteMark(tables = {"sys_menu", "sys_content", "sys_dept", "sys_role_dept"})
    public ServiceResult<MenuHtmlTreeResponse> getMenuTree(GetByIFrameAndDeptsRequest request) {
        /* 1. 全取出来 */
        List<MenuEntity> byIFrame = dao.getByIFrame(request.getiFrame());
        Map<Long, MenuEntity> map = byIFrame.stream().collect(Collectors.toMap(MenuEntity::getId, Function.identity(), (k1, k2) -> k1));
        HashSet<MenuEntity> set = new HashSet<>();
        UserEntity user = request.getUser();
        if (user.getRole() == null && user.getRoleId() == null) {
            return ServiceResult.buildFailedResult("查询成功,此账号没有角色,请添加", null, request);
        }
        List<DeptEntity> depts;

        // 准备获取权限内的子节点
        List<Long> level;
        // 超级管理员 就是牛逼
        if (user.getUserName().equals(ADMIN_USER_NAME)) {
            level = byIFrame.stream().map(MenuEntity::getId).collect(Collectors.toList());
        } else {
            if (user.getRole() == null) {
                depts = userDao.getUserDeptsByRoleId(user.getRoleId());
            } else {
                depts = user.getRole().getDepts();
            }
            List<Long> deptIds = depts.stream().map(DeptEntity::getId).collect(Collectors.toList());
            level = dao.getByDeptIds(deptIds);
        }

        /* 2. 只取出来有用的 */
        for (Long id : level) {
            if (map.containsKey(id)) {
                MenuEntity e = map.get(id);
                set.add(e);
                getParents(e, set, map);
            }
        }
        return ServiceResult.buildSuccessResult("查询树列表成功", MenuHtmlTreeResponse.build(set), request);
    }


    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ServiceResult<Boolean> deleteMenu(IdRequest req) {
        /* 注:开启了事务 即@Transactional 参数propagation->事务传播类型,其中Propagation.REQUIRED为如果事务不存在,则创建新事物,如果事务存在,则加入
           isolation事务隔离级别 Isolation.DEFAULT默认隔离级别 */

        /* 1.删除所有对应子节点 */
        Long menuId = req.getId();
        MenuEntity menuEntity = dao.getById(menuId);
        if (menuEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null, req);
        }
        Integer frame = menuEntity.getiFrame();
        List<MenuEntity> byIFrame = dao.getByIFrame(frame);
        HashSet<MenuEntity> menuEntityHashSet = new HashSet<>();
        menuEntityHashSet.add(menuEntity);
        addWillDeleteChild(menuEntity, menuEntityHashSet, byIFrame);
        List<Long> menuIds = menuEntityHashSet.stream().map(MenuEntity::getId).collect(Collectors.toList());
        dao.deleteByIds(menuIds);
        /* 2.删除连接表节点 */
        dao.deleteDeptMenuByMenuIds(menuIds);
        return ServiceResult.buildSuccessResult("删除成功", true, req);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu", "sys_dept"})
    public ServiceResult<ArrayList<GetDeptsByMenuIdResponse>> getDeptsByMenuId(IdRequest req) {
        ArrayList<GetDeptsByMenuIdResponse> list = deptDao.getByMenuId(req.getId());
        return ServiceResult.buildSuccessResult("查询成功", list, req);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict", "sys_dict_item"})
    public ServiceResult<QuickStartResponse> getQuickStartResponse(DefaultRequest request) {
        // TODO 快捷入口应该去缓存里
        Long idByCode = dictDao.getIdByCode(QUICK_START_CODE);
        ArrayList<DictItemEntity> byDictId = dictItemDao.getByDictId(idByCode);
        ArrayList<MenuEntity> collect = (ArrayList<MenuEntity>) byDictId.stream().map(t -> {
            Long menuId = (Long) t.getValue();
            MenuEntity byId = dao.getById(menuId);
            if (byId.getType() == false) {
                try {
                    throw new Exception("服务字典中快捷入口(" + byId.getName() + ") 不是叶子结点");
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }
            }
            return byId;
        }).collect(Collectors.toList());
        QuickStartResponse build = QuickStartResponse.build(collect);
        return ServiceResult.buildSuccessResult("查询快捷入口成功", build, request);
    }

    /**
     * 添加将要删除的子节点
     *
     * @param menuEntity
     * @param menuEntityHashSet
     * @param byIFrame
     */
    private void addWillDeleteChild(MenuEntity menuEntity, HashSet<MenuEntity> menuEntityHashSet, List<MenuEntity> byIFrame) {
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
    private void getParents(MenuEntity e, Set<MenuEntity> set, Map<Long, MenuEntity> map) {
        Long fid = e.getFid();
        if (fid == null || fid == 0L) {
            return;
        }
        MenuEntity father = map.get(fid);
        set.add(father);
        getParents(father, set, map);

    }


    private ArrayList<IndexMenuInfo> buildMenuTree(Set<MenuEntity> byIFrame) {
        ArrayList<IndexMenuInfo> menuInfo = new ArrayList<>();

        // 父节点都找出来
        for (MenuEntity menuEntity : byIFrame) {
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
    private void addChild(IndexMenuInfo treeResponse, Set<MenuEntity> byIFrame) {
        for (MenuEntity menuEntity : byIFrame) {
            if (menuEntity.getFid().equals(treeResponse.getId())) {
                IndexMenuInfo build = IndexMenuInfo.build(menuEntity);
                addChild(build, byIFrame);
                treeResponse.getChild().add(build);
            }
        }
    }

}
