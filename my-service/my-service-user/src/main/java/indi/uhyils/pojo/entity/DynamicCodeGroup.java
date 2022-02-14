package indi.uhyils.pojo.entity;

import indi.uhyils.assembler.DynamicCodeAssembler;
import indi.uhyils.enum_.Symbol;
import indi.uhyils.pojo.DO.DynamicCodeDO;
import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.repository.DynamicCodeRepository;
import indi.uhyils.util.Asserts;
import java.util.Arrays;
import java.util.List;

/**
 * 动态代码组id
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月14日 19时24分
 */
public class DynamicCodeGroup extends AbstractEntity<Integer> {

    /**
     * 动态代码详情
     */
    private List<DynamicCode> codes;

    public DynamicCodeGroup(Integer unique) {
        super(unique);
    }


    public void fillByGroup(DynamicCodeRepository rep) {
        Asserts.assertTrue(getUnique() != null, "动态代码组id没有初始化");
        this.codes = rep.findNoPage(Arrays.asList(Arg.as(DynamicCodeDO::getGroupId, Symbol.EQ, getUnique())), null);
    }

    /**
     * 获取代码详情
     *
     * @param assem
     *
     * @return
     */
    public List<DynamicCodeDTO> codes(DynamicCodeAssembler assem) {
        return assem.listEntityToDTO(codes);
    }
}
