package indi.uhyils.pojo.entity.sys;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.util.MapUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月01日 10时14分
 */
public abstract class AbstractSysTable implements SysTable {

    /**
     * 入参
     */
    protected Map<String, Object> params;

    protected AbstractSysTable(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public NodeInvokeResult getResult() {
        NodeInvokeResult nodeInvokeResult = doGetResultNoParams();
        if (MapUtil.isEmpty(params)) {
            return nodeInvokeResult;
        }

        List<Map<String, Object>> result = nodeInvokeResult.getResult();
        Iterator<Map<String, Object>> iterator = result.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> next = iterator.next();
            boolean pass = true;
            // 遍历条件.筛选
            for (Entry<String, Object> param : params.entrySet()) {
                Object o = next.get(param.getKey().toUpperCase());
                if (!Objects.equals(o, param.getValue())) {
                    pass = false;
                    break;
                }
            }
            if (!pass) {
                iterator.remove();
            }
        }
        return nodeInvokeResult;
    }

    /**
     * 无参数获取指定结果
     *
     * @return
     */
    protected abstract NodeInvokeResult doGetResultNoParams();


}
