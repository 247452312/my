package indi.uhyils.pojo.model;

import java.io.Serializable;

/**
 * 解密token后的信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月26日 14时25分
 */
public class TokenInfo implements Serializable {


    /**
     * 日
     */
    private Integer day;

    /**
     * 小时
     */
    private Integer hour;
    /**
     * 分钟
     */
    private Integer min;
    /**
     * 秒
     */
    private Integer sec;

    /**
     * 随机数
     */
    private Integer random;


    /**
     * 用户id
     */
    private String userId;

    /**
     * 是否超时
     */
    private Boolean timeOut;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }

    public Integer getRandom() {
        return random;
    }

    public void setRandom(Integer random) {
        this.random = random;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Boolean timeOut) {
        this.timeOut = timeOut;
    }
}
