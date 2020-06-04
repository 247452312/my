package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.ContentDao;
import indi.uhyils.dao.MenuDao;
import indi.uhyils.pojo.model.ContentEntity;
import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.MenuEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.GetByIFrameAndDeptsRequest;
import indi.uhyils.pojo.response.IndexMenuTreeResponse;
import indi.uhyils.pojo.response.MenuHtmlTreeResponse;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.pojo.response.info.IndexMenuInfo;
import indi.uhyils.pojo.response.info.MenuHomeInfo;
import indi.uhyils.pojo.response.info.MenuLogoInfo;
import indi.uhyils.service.MenuService;
import indi.uhyils.util.ContentUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
public class MenuServiceImpl extends BaseDefaultServiceImpl<MenuEntity> implements MenuService {

    /**
     * 最高一级菜单的fid
     */
    private static final String NONE = "";

    @Autowired
    private MenuDao dao;

    @Autowired
    private ContentDao contentDao;

    public MenuDao getDao() {
        return dao;
    }

    public void setDao(MenuDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<IndexMenuTreeResponse> getIndexMenu(DefaultRequest request) {
        ContentEntity indexIframe = contentDao.getByName("indexIframe");
        /* 1. 全取出来 */
        String indexIframe1 = ContentUtil.getContentVarByTitle(indexIframe, "indexIframe");
        assert indexIframe1 != null;
        List<MenuEntity> byIFrame = dao.getByIFrame(Integer.parseInt(indexIframe1));
        Map<String, MenuEntity> map = byIFrame.stream().collect(Collectors.toMap(MenuEntity::getId, Function.identity(), (k1, k2) -> k1));
        Set<MenuEntity> set = new HashSet<>();
        UserEntity user = request.getUser();
        if (user.getRole() == null) {
            return ServiceResult.buildFailedResult("查询成功,此账号没有角色,请添加", null, request);
        }
        List<DeptEntity> depts = user.getRole().getDepts();
        List<String> deptIds = new ArrayList<>();
        for (DeptEntity dept : depts) {
            deptIds.add(dept.getId());
        }
        List<String> level = dao.getByDeptIds(deptIds);

        /* 2. 只取出来有用的 */
        for (String id : level) {
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
    public ServiceResult<ArrayList<MenuHtmlTreeResponse>> getMenuTree(GetByIFrameAndDeptsRequest request) {
        /* 1. 全取出来 */
        List<MenuEntity> byIFrame = dao.getByIFrame(1);
        Map<String, MenuEntity> map = byIFrame.stream().collect(Collectors.toMap(MenuEntity::getId, Function.identity(), (k1, k2) -> k1));
        Set<MenuEntity> set = new HashSet<>();
        UserEntity user = request.getUser();
        if (user.getRole() == null) {
            return ServiceResult.buildFailedResult("查询成功,此账号没有角色,请添加", null, request);
        }
        List<DeptEntity> depts = user.getRole().getDepts();
        List<String> deptIds = new ArrayList<>();
        for (DeptEntity dept : depts) {
            deptIds.add(dept.getId());
        }
        List<String> level = dao.getByDeptIds(deptIds);

        /* 2. 只取出来有用的 */
        for (String id : level) {
            if (map.containsKey(id)) {
                MenuEntity e = map.get(id);
                set.add(e);
                getParents(e, set, map);
            }
        }
        /* 4. tree */
        ArrayList<MenuHtmlTreeResponse> menuMenuInfoArrayList = buildMenuHtmlTree(set);

        return ServiceResult.buildSuccessResult("查询树列表成功", menuMenuInfoArrayList, request);
    }


    /**
     * 递归添加父节点
     *
     * @param e   子节点
     * @param set 已有节点集
     * @param map 全部节点
     */
    private void getParents(MenuEntity e, Set<MenuEntity> set, Map<String, MenuEntity> map) {
        String fid = e.getFid();
        if (StringUtils.isBlank(fid)) {
            return;
        }
        MenuEntity father = map.get(fid);
        set.add(father);
        getParents(father, set, map);

    }

    private ArrayList<MenuHtmlTreeResponse> buildMenuHtmlTree(Set<MenuEntity> byIFrame) {
        ArrayList<MenuHtmlTreeResponse> menuInfo = new ArrayList<>();

        // 父节点都找出来
        for (MenuEntity menuEntity : byIFrame) {
            if (NONE.equals(menuEntity.getFid())) {
                menuInfo.add(MenuHtmlTreeResponse.build(menuEntity));
            }
        }
        //每一个父节点都添加属于自己的树
        for (MenuHtmlTreeResponse treeResponse : menuInfo) {
            addMenuHtmlTreeChild(treeResponse, byIFrame);
        }
        return menuInfo;
    }

    /**
     * 递归添加子结点
     *
     * @param treeResponse 父节点
     * @param byIFrame     有用节点
     */
    private void addMenuHtmlTreeChild(MenuHtmlTreeResponse treeResponse, Set<MenuEntity> byIFrame) {
        for (MenuEntity menuEntity : byIFrame) {
            if (menuEntity.getFid().equals(treeResponse.getId())) {
                MenuHtmlTreeResponse build = MenuHtmlTreeResponse.build(menuEntity);
                addMenuHtmlTreeChild(build, byIFrame);
                treeResponse.getChildren().add(build);
            }
        }
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
