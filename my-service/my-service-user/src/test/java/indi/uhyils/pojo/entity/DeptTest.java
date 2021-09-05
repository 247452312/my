package indi.uhyils.pojo.entity;

import indi.uhyils.BaseTest;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.util.AssertUtil;
import java.lang.reflect.Field;
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

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

    }

    @Test
    public void addPower() throws Exception {
        Dept dept = new Dept(deptId);
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
        dept.completion(deptRepository);
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
        List<Power> powerList3 = (List<Power>) powers.get(dept);
        AssertUtil.assertTrue(powerList3 != null);
        AssertUtil.assertTrue(powerList3.size() == 0);


    }

    @Test
    public void cleanMenu() {
    }

    @Test
    public void addMenu() {
    }

    @Test
    public void initPower() {
    }

    @Test
    public void initMenus() {
    }

    @Test
    public void menus() {
    }

    @Test
    public void removeMenuLink() {
    }

    @Test
    public void removePowerLink() {
    }

    @Test
    public void removeRoleLink() {
    }
}