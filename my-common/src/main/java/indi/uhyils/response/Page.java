package indi.uhyils.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时41分
 */
public class Page<T extends Serializable> implements Serializable {

    /**
     * 类
     */
    private List<T> data;


    /**
     * 页面大小
     */
    private Integer size;

    /**
     * 页码
     */
    private Integer pageNum;


    /**
     * 总条数
     */
    private Integer count;

    /**
     * 总页数.
     */
    private Integer totalPage;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
