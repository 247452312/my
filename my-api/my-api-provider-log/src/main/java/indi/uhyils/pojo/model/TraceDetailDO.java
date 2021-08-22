package indi.uhyils.pojo.model;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
public class TraceDetailDO extends TraceIdDoDO {


    /**
     * 用时
     */
    private Long useTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 类型
     */
    private Integer type;

    /**
     *
     */
    private String hashCode;

    /**
     *
     */
    private String otherTwo;

    /**
     *
     */
    private String otherOne;


    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getOtherTwo() {
        return otherTwo;
    }

    public void setOtherTwo(String otherTwo) {
        this.otherTwo = otherTwo;
    }

    public String getOtherOne() {
        return otherOne;
    }

    public void setOtherOne(String otherOne) {
        this.otherOne = otherOne;
    }


}
