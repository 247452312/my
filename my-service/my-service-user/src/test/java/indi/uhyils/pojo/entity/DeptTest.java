package indi.uhyils.pojo.entity;

import indi.uhyils.BaseTest;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.LogUtil;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月05日 18时11分
 */
public class DeptTest extends BaseTest {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PowerRepository powerRepository;

    @Autowired
    private MenuRepository menuRepository;

    private Long roleId;

    private Long deptId;

    private Long powerId;

    private Long menuId;



    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        // role
        RoleDO roleDO = new RoleDO();
        roleDO.setLevel(1);
        roleDO.setName("testRole");
        Role role = new Role(roleDO);
        roleRepository.save(role);

        // dept
        DeptDO deptDO = new DeptDO();
        deptDO.setName("test");
        Dept dept = new Dept(deptDO);
        deptRepository.save(dept);

        // menu
        MenuDO menuDO = new MenuDO();
        menuDO.setFid(0L);
        menuDO.setIFrame(1);
        menuDO.setIcon("test");
        menuDO.setName("testMenuName");
        menuDO.setSort(1);
        menuDO.setTarget("target");
        menuDO.setType(true);
        menuDO.setUrl("testMenuUrl");
        Menu menu = new Menu(menuDO);
        menuRepository.save(menu);

        // power
        PowerDO powerDO = new PowerDO();
        powerDO.setInterfaceName("interfaceName");
        powerDO.setMethodName("methodName");
        powerDO.setTest("Test");
        Power power = new Power(powerDO);
        powerRepository.save(power);

        roleId = role.id.getId();
        deptId = dept.id.getId();
        menuId = menu.id.getId();
        powerId = power.id.getId();

        Dept dept1 = new Dept(Arrays.asList(menu), deptDO);
        Dept dept2 = new Dept(deptDO, Arrays.asList(power));

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();


    }

    @Test
    public void addPower() throws Exception {
        Dept dept = new Dept(new Identifier(deptId));
        dept.completion(deptRepository);
        Power power = new Power(powerId);
        power.completion(powerRepository);
        dept.addPower(Collections.singletonList(power), deptRepository);

        Class<Dept> aClass = Dept.class;
        Field powers = aClass.getDeclaredField("powers");
        powers.setAccessible(true);

        List<Power> o2 = (List<Power>) powers.get(dept);
        AssertUtil.assertTrue(o2 != null);
        AssertUtil.assertTrue(o2.size() == 1);
        AssertUtil.assertTrue(o2.get(0).id.getId().equals(powerId));
        Dept deptTest = new Dept(deptId);
        deptTest.completion(deptRepository);
        deptTest.initPower(powerRepository);
        List<Power> o = (List<Power>) powers.get(deptTest);
        AssertUtil.assertTrue(o != null);
        AssertUtil.assertTrue(o.size() == 1);
        AssertUtil.assertTrue(o.get(0).id.getId().equals(powerId));

    }

    @Test
    public void addPowerToEntity() throws Exception {
        Class<Dept> aClass = Dept.class;
        Field powers = aClass.getDeclaredField("powers");
        powers.setAccessible(true);

        Dept dept = new Dept(deptId);
        dept.completion(deptRepository);
        Power power = new Power(powerId);
        power.completion(powerRepository);

        // 第一次添加
        dept.addPower(Collections.singletonList(power), deptRepository);

        List<Power> o2 = (List<Power>) powers.get(dept);
        AssertUtil.assertTrue(o2 != null);
        AssertUtil.assertTrue(o2.size() == 1);
        AssertUtil.assertTrue(o2.get(0).id.getId().equals(powerId));

        // 第二次添加. 数量应该相同
        dept.addPower(Collections.singletonList(power), deptRepository);

        List<Power> o = (List<Power>) powers.get(dept);
        AssertUtil.assertTrue(o != null);
        AssertUtil.assertTrue(o.size() == 1);
        AssertUtil.assertTrue(o.get(0).id.getId().equals(powerId));
    }

    @Test
    public void cleanPower() throws Exception {
        Class<Dept> aClass = Dept.class;
        Field powers = aClass.getDeclaredField("powers");
        powers.setAccessible(true);

        Dept dept = new Dept(deptId);

        Dept testDept2 = new Dept(deptId);

        Power power = new Power(powerId);
        testDept2.addPower(Collections.singletonList(power), deptRepository);

        dept.initPower(powerRepository);
        List<Power> powerList = (List<Power>) powers.get(dept);
        AssertUtil.assertTrue(powerList != null);
        AssertUtil.assertTrue(powerList.size() == 1);
        AssertUtil.assertTrue(powerList.get(0).id.getId().equals(powerId));

        dept.cleanPower(deptRepository);
        List<Power> powerList2 = (List<Power>) powers.get(dept);
        AssertUtil.assertTrue(powerList2 == null);

        Dept testDept = new Dept(deptId);
        testDept.initPower(powerRepository);
        List<Power> powerList3 = (List<Power>) powers.get(testDept);
        AssertUtil.assertTrue(powerList3 != null);
        AssertUtil.assertTrue(powerList3.size() == 0);
    }

    @Test
    public void cleanMenu() throws Exception {
        Class<Dept> aClass = Dept.class;
        Field menus = aClass.getDeclaredField("menus");
        menus.setAccessible(true);

        Dept dept = new Dept(deptId);
        dept.completion(deptRepository);
        Menu newMenu = new Menu(menuId);
        newMenu.completion(menuRepository);
        dept.addMenu(Arrays.asList(newMenu), deptRepository);

        List<Menu> menuList = (List<Menu>) menus.get(dept);
        AssertUtil.assertTrue(menuList != null);
        AssertUtil.assertTrue(menuList.size() == 1);
        AssertUtil.assertTrue(menuList.get(0).id.getId().equals(menuId));

        dept.cleanMenu(deptRepository);
        List<Menu> powerList2 = (List<Menu>) menus.get(dept);
        AssertUtil.assertTrue(powerList2 == null);

        Dept testDept = new Dept(deptId);
        testDept.initMenus(menuRepository);
        List<Menu> powerList3 = (List<Menu>) menus.get(testDept);
        AssertUtil.assertTrue(powerList3 != null);
        AssertUtil.assertTrue(powerList3.size() == 0);

    }

    @Test
    public void addMenu() throws Exception {
        Class<Dept> aClass = Dept.class;
        Field menus = aClass.getDeclaredField("menus");
        menus.setAccessible(true);

        Dept dept = new Dept(deptId);
        dept.completion(deptRepository);

        Menu menu = new Menu(menuId);
        menu.completion(menuRepository);

        dept.addMenu(Collections.singletonList(menu), deptRepository);
        dept.addMenu(Collections.singletonList(menu), deptRepository);
        List<Menu> powerList2 = (List<Menu>) menus.get(dept);
        AssertUtil.assertTrue(powerList2 != null);
        AssertUtil.assertTrue(powerList2.size() == 1);
        AssertUtil.assertTrue(powerList2.get(0).id.getId().equals(menuId));

        Dept dept2 = new Dept(deptId);
        dept2.completion(deptRepository);
        dept2.initMenus(menuRepository);
        List<Menu> powerList3 = (List<Menu>) menus.get(dept2);
        AssertUtil.assertTrue(powerList3 != null);
        AssertUtil.assertTrue(powerList3.size() == 1);
        AssertUtil.assertTrue(powerList3.get(0).id.getId().equals(menuId));


    }

    @Test
    public void initPower() throws Exception {
        Class<Dept> aClass = Dept.class;
        Field powerField = aClass.getDeclaredField("powers");
        powerField.setAccessible(true);

        Dept dept = new Dept(deptId);
        dept.addPower(Arrays.asList(new Power(powerId)), deptRepository);

        Dept dept1 = new Dept(deptId);
        dept1.initPower(powerRepository);
        dept1.initPower(powerRepository);

        List<Power> powerList = (List<Power>) powerField.get(dept1);
        AssertUtil.assertTrue(powerList != null);
        AssertUtil.assertTrue(powerList.size() == 1);
        AssertUtil.assertTrue(powerList.get(0).id.getId().equals(powerId));

    }

    @Test
    public void initMenus() throws Exception {
        Class<Dept> aClass = Dept.class;
        Field powerField = aClass.getDeclaredField("menus");
        powerField.setAccessible(true);

        Dept dept = new Dept(deptId);
        dept.addMenu(Arrays.asList(new Menu(menuId)), deptRepository);

        Dept dept1 = new Dept(deptId);
        dept1.initMenus(menuRepository);
        dept1.initMenus(menuRepository);

        List<Menu> powerList = (List<Menu>) powerField.get(dept1);
        AssertUtil.assertTrue(powerList != null);
        AssertUtil.assertTrue(powerList.size() == 1);
        AssertUtil.assertTrue(powerList.get(0).id.getId().equals(menuId));

    }

    @Test
    public void menus() {

        Dept dept = new Dept(deptId);
        dept.addMenu(Arrays.asList(new Menu(menuId)), deptRepository);

        Dept dept1 = new Dept(deptId);
        dept1.initMenus(menuRepository);
        List<Menu> menus = dept1.menus();
        AssertUtil.assertTrue(menus != null);
        AssertUtil.assertTrue(menus.size() == 1);
        AssertUtil.assertTrue(menus.get(0).id.getId().equals(menuId));
    }

    @Test
    public void removeMenuLink() {
        Dept dept = new Dept(deptId);
        dept.addMenu(Arrays.asList(new Menu(menuId)), deptRepository);
        List<Menu> menus = dept.menus();
        AssertUtil.assertTrue(menus != null);
        AssertUtil.assertTrue(menus.size() == 1);
        AssertUtil.assertTrue(menus.get(0).id.getId().equals(menuId));

        dept.removeMenuLink(deptRepository);
        dept.removeMenuLink(deptRepository);
        List<Menu> menus1 = dept.menus();
        AssertUtil.assertTrue(menus1 == null);

        Dept dept1 = new Dept(deptId);
        dept1.initMenus(menuRepository);
        List<Menu> menus2 = dept1.menus();
        AssertUtil.assertTrue(menus2 != null);
        AssertUtil.assertTrue(menus2.size() == 0);

    }


    @Test
    public void removePowerLink() {
        Dept dept = new Dept(deptId);
        dept.addPower(Arrays.asList(new Power(powerId)), deptRepository);
        List<Power> menus = dept.powers();
        AssertUtil.assertTrue(menus != null);
        AssertUtil.assertTrue(menus.size() == 1);
        AssertUtil.assertTrue(menus.get(0).id.getId().equals(powerId));

        dept.removePowerLink(deptRepository);
        dept.removePowerLink(deptRepository);
        List<Power> menus1 = dept.powers();
        AssertUtil.assertTrue(menus1 == null);

        Dept dept1 = new Dept(deptId);
        dept1.initPower(powerRepository);
        List<Power> menus2 = dept1.powers();
        AssertUtil.assertTrue(menus2 != null);
        AssertUtil.assertTrue(menus2.size() == 0);
    }

    @Test
    public void removeRoleLink() throws Exception {
        Role role = new Role(roleId);
        Dept dept = new Dept(deptId);
        role.addDept(Arrays.asList(dept), roleRepository);

        Role roleTest = new Role(roleId);
        roleTest.initDept(deptRepository, powerRepository, menuRepository);

        Class<Role> roleClass = Role.class;
        Field depts = roleClass.getDeclaredField("depts");
        depts.setAccessible(true);
        List<Dept> depts1 = (List<Dept>) depts.get(role);
        AssertUtil.assertTrue(depts1 != null);
        AssertUtil.assertTrue(depts1.size() == 1);
        AssertUtil.assertTrue(depts1.get(0).id.getId().equals(deptId));

        dept.removeRoleLink(deptRepository);

        Role role1 = new Role(roleId);

        role1.initDept(deptRepository, powerRepository, menuRepository);
        List<Dept> depts2 = (List<Dept>) depts.get(role1);
        AssertUtil.assertTrue(depts2 != null);
        AssertUtil.assertTrue(depts2.size() == 0);

    }
}
