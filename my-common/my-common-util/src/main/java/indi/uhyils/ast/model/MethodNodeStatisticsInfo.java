package indi.uhyils.ast.model;

import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 12时21分
 */
public class MethodNodeStatisticsInfo implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 方法的总复杂度
     */
    private Integer sumComplexity;

    /**
     * 方法的平均复杂度
     */
    private Double avgComplexity;

    /**
     * 最深
     */
    private Integer maxComplexity;


    public static MethodNodeStatisticsInfo build(Integer sumComplexity, Double avgComplexity, Integer maxComplexity) {
        MethodNodeStatisticsInfo build = new MethodNodeStatisticsInfo();
        build.sumComplexity = sumComplexity;
        build.avgComplexity = avgComplexity;
        build.maxComplexity = maxComplexity;
        return build;
    }

    public static MethodNodeStatisticsInfo build(Integer maxComplexity) {
        MethodNodeStatisticsInfo build = new MethodNodeStatisticsInfo();
        build.sumComplexity = 1;
        build.avgComplexity = 1.0;
        build.maxComplexity = maxComplexity;
        return build;
    }

    public Integer getMaxComplexity() {
        return maxComplexity;
    }

    public void setMaxComplexity(Integer maxComplexity) {
        this.maxComplexity = maxComplexity;
    }

    public Integer getSumComplexity() {
        return sumComplexity;
    }

    public void setSumComplexity(Integer sumComplexity) {
        this.sumComplexity = sumComplexity;
    }

    public Double getAvgComplexity() {
        return avgComplexity;
    }

    public void setAvgComplexity(Double avgComplexity) {
        this.avgComplexity = avgComplexity;
    }
}
