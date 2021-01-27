package indi.uhyils.pojo.response.base;

import indi.uhyils.pojo.request.base.DefaultPageRequest;

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
    private List<T> list;


    /**
     * 页面大小
     */
    private Long size;

    /**
     * 页码
     */
    private Long pageNum;


    /**
     * 总条数
     */
    private Long count;

    /**
     * 总页数.
     */
    private Long totalPage;

    public Page(List<T> list, Long size, Long pageNum, Long count, Long totalPage) {
        this.list = list;
        this.size = size;
        this.pageNum = pageNum;
        this.count = count;
        this.totalPage = totalPage;
    }

    public Page() {
    }

    public static <T extends Serializable> Page<T> build(List<T> list, Long size, Long pageNum, Long count, Long totalPage) {
        return new Page(list, size, pageNum, count, totalPage);
    }

    /**
     * 创建一个page
     *
     * @param pageRequest 分页请求
     * @param list        查询结果
     * @param count       查询数量
     * @param totalPage   总页数
     * @param <T>         查询结果类
     * @return 包含分页信息的返回集
     */
    public static <T extends Serializable> Page<T> build(DefaultPageRequest pageRequest, List<T> list, Long count, Long totalPage) {
        //代表分页
        if (pageRequest.getPaging()) {
            return build(list, pageRequest.getSize(), pageRequest.getPage(), count, totalPage);
        } else {
            return build(list, count, 1L, count, 1L);
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}
