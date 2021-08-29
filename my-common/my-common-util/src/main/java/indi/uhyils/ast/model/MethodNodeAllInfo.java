package indi.uhyils.ast.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月21日 11时08分
 */
public class MethodNodeAllInfo implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 方法总深度
     */
    private Long sum = 0L;

    /**
     * 方法个数
     */
    private Integer count = 0;

    /**
     * 每个方法的复杂度
     */
    private Map<String, MethodNodeStatisticsInfo> methodDeep = new HashMap<>();

    public static MethodNodeAllInfo build(Long sum, Integer count, Map<String, MethodNodeStatisticsInfo> methodDeep) {
        MethodNodeAllInfo build = new MethodNodeAllInfo();
        build.sum = sum;
        build.count = count;
        build.methodDeep = methodDeep;
        return build;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map<String, MethodNodeStatisticsInfo> getMethodDeep() {
        return methodDeep;
    }

    public void setMethodDeep(Map<String, MethodNodeStatisticsInfo> methodDeep) {
        this.methodDeep = methodDeep;
    }
}