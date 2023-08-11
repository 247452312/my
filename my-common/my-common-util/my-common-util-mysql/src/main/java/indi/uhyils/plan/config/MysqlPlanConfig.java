package indi.uhyils.plan.config;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月01日 14时29分
 */
public class MysqlPlanConfig {

    /**
     * 是否容错
     */
    private Boolean allowFault;

    public Boolean getAllowFault() {
        return allowFault;
    }

    public void setAllowFault(Boolean allowFault) {
        this.allowFault = allowFault;
    }
}
