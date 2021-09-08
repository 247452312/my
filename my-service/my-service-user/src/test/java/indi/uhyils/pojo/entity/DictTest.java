package indi.uhyils.pojo.entity;

import indi.uhyils.BaseTest;
import indi.uhyils.enum_.DictTypeEnum;
import indi.uhyils.pojo.DO.DictDO;
import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.repository.DictItemRepository;
import indi.uhyils.repository.DictRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.CollectionUtil;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 21时01分
 */
public class DictTest extends BaseTest {

    @Autowired
    private DictRepository dictRepository;

    @Autowired
    private DictItemRepository dictItemRepository;

    private Long dictId;

    private Long dictItemId;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        DictDO dO = new DictDO();
        dO.setCode("testCode");
        dO.setDescription("testDesc");
        dO.setName("testName");
        dO.setType(DictTypeEnum.INTEGER.getCode());
        Dict dict = new Dict(dO);
        dictRepository.save(dict);
        this.dictId = dict.id.getId();

        DictItemDO item = new DictItemDO();
        item.setDescription("itemDesc");
        item.setDictId(dict.id.getId());
        item.setSortOrder(1);
        item.setText("test");
        item.setValue("1");
        item.setStatus(1);
        DictItem dictItem = new DictItem(item);
        dictItemRepository.save(dictItem);
        dictItemId = dictItem.id.getId();
    }

    @Test
    public void findItem() {
        Dict dict = new Dict("testCode");
        List<DictItem> item = dict.findItemByCode(dictItemRepository);
        AssertUtil.assertTrue(CollectionUtil.isNotEmpty(item));
        AssertUtil.assertTrue(item.size() == 1);
        AssertUtil.assertTrue(item.get(0).id.getId().equals(dictItemId));
    }

    @Test
    public void fillItem() {
        Dict dict = new Dict(dictId);
        dict.completion(dictRepository);
        dict.fillItem(dictItemRepository);
        List<DictItem> dictItems = dict.toItem();
        AssertUtil.assertTrue(CollectionUtil.isNotEmpty(dictItems));
        AssertUtil.assertTrue(dictItems.size() == 1);
        AssertUtil.assertTrue(dictItems.get(0).id.getId().equals(dictItemId));
    }


    @Test
    public void cleanItem() {
        Dict dict = new Dict(dictId);
        dict.fillItem(dictItemRepository);
        dict.cleanItem(dictItemRepository);
        dict.cleanItem(dictItemRepository);
        Dict dict2 = new Dict(dictId);
        dict2.fillItem(dictItemRepository);
        List<DictItem> dictItems = dict2.toItem();
        AssertUtil.assertTrue(dictItems != null);
        AssertUtil.assertTrue(dictItems.size() == 0);

    }
}
