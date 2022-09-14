package indi.uhyils.mysql.pojo.DTO;

import java.io.Serializable;

/**
 * 引擎信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月14日 09时42分
 */
public class EnginesInfo implements Serializable {


    /**
     * 存储引擎名称
     */
    private String engine;

    /**
     * 存储引擎的支持级别
     * YES 引擎是被支持的且被激活
     * DEFAULT 同YES,并且是默认的
     * NO 不支持引擎
     * DISABLED 支持 但是禁用了
     */
    private String support;

    /**
     * 介绍
     */
    private String comment;

    /**
     * 是否支持事务
     */
    private String transactions;

    /**
     * 是否支持分布式事务
     */
    private String xa;

    /**
     * 是否支持保存点
     */
    private String savepoints;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getSavepoints() {
        return savepoints;
    }

    public void setSavepoints(String savepoints) {
        this.savepoints = savepoints;
    }
}
