package indi.uhyils.druid.filter.limit;

import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月08日 13时34分
 */
public class DruidFilterConfiguration implements Serializable {

    public static final String ROWS_LIMIT_INTERRUPT = "druid.rows.limit.interrupt";

    public static final String ROWS_LIMIT_SIZE = "druid.rows.limit.size";

    public static final String ROWS_LIMIT_LOG_THRESHOLD_SIZE = "druid.rows.limit.log.threshold.size";

    /**
     * 是否开启
     */
    private Boolean rowsLimitInterrupt = true;

    /**
     * 默认限制的大小
     */
    private int rowsLimitSize = 5000;

    /**
     * 默认打印日志的大小
     */
    private int logThresholdSize = 1000;

    public DruidFilterConfiguration() {
    }

    public Boolean getRowsLimitInterrupt() {
        return this.rowsLimitInterrupt;
    }

    public void setRowsLimitInterrupt(Boolean rowsLimitInterrupt) {
        this.rowsLimitInterrupt = rowsLimitInterrupt;
    }

    public int getRowsLimitSize() {
        return this.rowsLimitSize;
    }

    public void setRowsLimitSize(int rowsLimitSize) {
        this.rowsLimitSize = rowsLimitSize;
    }

    public int getLogThresholdSize() {
        return this.logThresholdSize;
    }

    public void setLogThresholdSize(int logThresholdSize) {
        this.logThresholdSize = logThresholdSize;
    }
}
