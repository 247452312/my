package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.MenuDao;
import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.MenuEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.GetByIFrameAndDeptsRequest;
import indi.uhyils.pojo.response.MenuTreeResponse;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时48分
 */
@Service(group = "${spring.profiles.active}")
public class MenuServiceImpl extends DefaultServiceImpl<MenuEntity> implements MenuService {

    /**
     * 最高一级菜单的fid
     */
    private static final String NONE = "";
    @Autowired
    private MenuDao dao;

    public MenuDao getDao() {
        return dao;
    }

    public void setDao(MenuDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<ArrayList<MenuTreeResponse>> getByIFrameAndDepts(GetByIFrameAndDeptsRequest request) {
        /* 1.全取出来 */
        List<MenuEntity> byIFrame = dao.getByIFrame(request.getiFrame());
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

        for (String id : level) {
            MenuEntity e = map.get(id);
            set.add(e);
            getParents(e, set, map);
        }
        /* 4.tree */
        ArrayList<MenuTreeResponse> list = (ArrayList<MenuTreeResponse>) buildMenuTree(set);
        return ServiceResult.buildSuccessResult("树创建成功", list, request);
    }

    private void getParents(MenuEntity e, Set<MenuEntity> set, Map<String, MenuEntity> map) {
        String fid = e.getFid();
        if (StringUtils.isBlank(fid)) {
            return;
        }
        MenuEntity father = map.get(fid);
        set.add(father);
        getParents(father, set, map);

    }

    private List<MenuTreeResponse> buildMenuTree(Set<MenuEntity> byIFrame) {
        MenuTreeResponse menuTreeResponse = new MenuTreeResponse();
        // 父节点都找出来
        for (MenuEntity menuEntity : byIFrame) {
            if (NONE.equals(menuEntity.getFid())) {
                menuTreeResponse.getSubNode().add(MenuTreeResponse.build(menuTreeResponse, menuEntity));
            }
        }
        //每一个父节点都添加属于自己的树
        for (MenuTreeResponse treeResponse : menuTreeResponse.getSubNode()) {
            addSubType(treeResponse, byIFrame);
        }
        return menuTreeResponse.getSubNode();
    }

    /**
     * 递归添加子结点
     *
     * @param treeResponse
     * @param byIFrame
     */
    private void addSubType(MenuTreeResponse treeResponse, Set<MenuEntity> byIFrame) {
        for (MenuEntity menuEntity : byIFrame) {
            if (menuEntity.getFid().equals(treeResponse.getId())) {
                MenuTreeResponse build = MenuTreeResponse.build(treeResponse, menuEntity);
                treeResponse.getSubNode().add(build);
                addSubType(build, byIFrame);
            }
        }
    }

    /**
     * 删除没有子节点的父节点
     *
     * @param byIFrame
     */
    private void deleteNoChNodeNode(List<MenuEntity> byIFrame) {
        boolean haveRemove = true; // 记录是否有删除
        while (haveRemove) { // 如果上一次有删东西,就再来一遍 也是防止栈超出
            haveRemove = false;
            MenuEntity willRemove = null;
            for (MenuEntity menuEntity : byIFrame) { // 遍历整个菜单
                willRemove = null; // 将记录点置空
                if (menuEntity.getType()) { // 跳过叶子结点
                    continue;
                }
                boolean haveCh = false; // 记录此节点有没有子节点
                for (MenuEntity entity : byIFrame) {
                    if (entity.getFid().equals(menuEntity.getId())) { // 如果存在其他节点以此节点为父节点
                        haveCh = true;
                        break;
                    }
                }
                if (!haveCh) {
                    willRemove = menuEntity;
                    break;
                }
            }
            if (willRemove != null) {
                byIFrame.remove(willRemove);
                haveRemove = true;
            }
        }
    }

    /**
     * 删除没用的菜单
     *
     * @param deptIds
     * @param byIFrame
     */
    private void deleteUnUseableMenuNode(List<String> deptIds, List<MenuEntity> byIFrame) {
        List<String> useMenu = dao.getByDeptIds(deptIds);
        Iterator<MenuEntity> iterator = byIFrame.iterator();
        while (iterator.hasNext()) {
            MenuEntity next = iterator.next();
            if (!useMenu.contains(next.getId())) { // 如果不包含在有用的按钮中
                iterator.remove();
                continue;
            }
        }
    }



}
