package indi.uhyils.pojo.cqe.query.demo;

import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 18时08分
 */
public class Limit implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 页码
     */
    private Integer number;

    /**
     * 大小
     */
    private Integer size;

    /**
     * 分页
     */
    private Boolean page;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getPage() {
        return page;
    }

    public void setPage(Boolean page) {
        this.page = page;
    }
}
