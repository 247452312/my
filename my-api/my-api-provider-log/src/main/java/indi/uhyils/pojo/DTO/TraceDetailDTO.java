package indi.uhyils.pojo.DTO;


/**
 * (TraceDetail)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分54秒
 */
public class TraceDetailDTO extends IdDTO {

    private static final long serialVersionUID = -20452659290526017L;


    private Long traceId;

    private String hashCode;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 用时
     */
    private Long useTime;

    /**
     * 类型
     */
    private Integer type;

    private String otherOne;

    private String otherTwo;


    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }


    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }


    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }


    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getOtherOne() {
        return otherOne;
    }

    public void setOtherOne(String otherOne) {
        this.otherOne = otherOne;
    }


    public String getOtherTwo() {
        return otherTwo;
    }

    public void setOtherTwo(String otherTwo) {
        this.otherTwo = otherTwo;
    }

}
