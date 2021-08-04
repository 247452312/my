package indi.uhyils.pojo.model;

import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.pojo.model.base.BaseDbSaveable;
import indi.uhyils.pojo.request.base.DefaultRequest;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月04日 09时16分
 */
public class TraceDetailStatisticsView implements BaseDbSaveable {

    /**
     * 时间
     */
    private String time;

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 日志类型中文名称
     */
    private String typeName;

    /**
     * hash
     */
    private String hashCode;

    /**
     * 这一分钟的总调用次数
     */
    private Integer count;

    /**
     * 总用时
     */
    private Long sum;

    /**
     * 平均用时
     */
    private Long avg;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getAvg() {
        return avg;
    }

    public void setAvg(Long avg) {
        this.avg = avg;
    }

    @Override
    public void preInsert(DefaultRequest request) throws IdGenerationException, InterruptedException {
        throw new RuntimeException("视图不能插入");
    }

    @Override
    public void preInsert() throws IdGenerationException, InterruptedException {
        throw new RuntimeException("视图不能插入");
    }

    @Override
    public void preUpdate(DefaultRequest request) {
        throw new RuntimeException("视图不能修改");
    }
}
