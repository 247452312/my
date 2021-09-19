package indi.uhyils.pojo.entity;

import indi.uhyils.BaseTest;
import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.response.info.MenuTreeDTO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.CollectionUtil;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 08时06分
 */
public class MenuTest extends BaseTest {

    @Autowired
    private MenuRepository repository;

    private Long m1Id;

    private Long m2Id;

    @Autowired
    private MenuAssembler assembler;

    private Long deptId;

    @Autowired
    private DeptRepository deptRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        MenuDO do1 = new MenuDO();
        do1.setFid(0L);
        do1.setIFrame(123);
        do1.setIcon("asd");
        do1.setName("asd");
        do1.setSort(1);
        do1.setTarget("asd");
        do1.setType(Boolean.TRUE);
        do1.setUrl("asd");

        Menu m1 = new Menu(do1);
        repository.save(m1);
        m1Id = m1.getId().getId();

        MenuDO do2 = new MenuDO();
        do2.setFid(m1.getId().getId());
        do2.setIFrame(123);
        do2.setIcon("asd");
        do2.setName("asd");
        do2.setSort(1);
        do2.setTarget("asd");
        do2.setType(Boolean.TRUE);
        do2.setUrl("asd");

        Menu m2 = new Menu(do2);
        repository.save(m2);
        m2Id = m2.getId().getId();

        // dept
        DeptDO deptDO = new DeptDO();
        deptDO.setName("test");
        Dept dept = new Dept(deptDO);
        deptRepository.save(dept);
        deptId = dept.getId().getId();


    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void removeSelf() {
        Menu menu = new Menu(m1Id);
        menu.completion(repository);
        menu.removeSelf(repository, assembler);

        Menu menu1 = new Menu(m1Id);
        AssertUtil.assertException(() -> menu1.completion(repository));

        Menu menu2 = new Menu(m2Id);
        AssertUtil.assertException(() -> menu2.completion(repository));
    }

    @Test
    public void findSelfNode() {
        Menu menu = new Menu(m1Id);
        menu.completion(repository);
        MenuTreeDTO selfNode = menu.findSelfNode(repository, assembler);
        List<MenuTreeDTO> child = selfNode.getChild();
        MenuDTO menuDTO = selfNode.getMenuDTO();
        AssertUtil.assertTrue(m1Id.equals(menuDTO.getId()));
        AssertUtil.assertTrue(CollectionUtil.isNotEmpty(child));
        AssertUtil.assertTrue(child.size() == 1);
    }

    @Test
    public void findSelfNode2() {
        Menu menu = new Menu(m1Id);
        menu.completion(repository);
        menu.toDo().setIFrame(123123123);
        MenuTreeDTO selfNode = menu.findSelfNode(repository, assembler);

        AssertUtil.assertTrue(selfNode == null);
    }

    @Test
    public void cleanDept() {
        Dept dept = new Dept(deptId);
        dept.completion(deptRepository);
        Menu menu = new Menu(m1Id);
        dept.addMenu(Arrays.asList(menu), deptRepository);
        dept.addMenu(Arrays.asList(menu), deptRepository);

        menu.cleanDept(repository);
        AssertUtil.assertTrue(menu.depts() == null);

        Dept dept1 = new Dept(deptId);
        dept1.initMenus(repository);
        List<Menu> menus = dept1.menus();

        AssertUtil.assertTrue(menus.size() == 0);
    }

    @Test
    public void cleanDept2() {
        Dept dept = new Dept(deptId);
        dept.completion(deptRepository);
        Menu menu = new Menu(new Identifier(m1Id));
        menu.addDepts(Arrays.asList(dept), repository);
        List<Dept> depts = menu.depts();
        AssertUtil.assertTrue(depts.size() == 1);

        menu.cleanDept(repository);
        AssertUtil.assertTrue(menu.depts() == null);

        Dept dept1 = new Dept(deptId);
        dept1.initMenus(repository);
        List<Menu> menus = dept1.menus();

        AssertUtil.assertTrue(menus.size() == 0);
    }

}
