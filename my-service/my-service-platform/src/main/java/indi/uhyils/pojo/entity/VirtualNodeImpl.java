package indi.uhyils.pojo.entity;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.entity.node.VirtualNode;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月21日 09时52分
 */
public class VirtualNodeImpl extends AbstractEntity<String> implements VirtualNode {

    private final String sql;


    public VirtualNodeImpl(String sql) {
        super(sql);
        this.sql = sql;

    }

    @Override
    public JSONArray invoke() {
        return null;
    }
}
