package indi.uhyils.pojo.DTO.response.base;

import indi.uhyils.pojo.cqe.query.BaseQuery;
import indi.uhyils.pojo.cqe.query.Limit;
import indi.uhyils.pojo.DTO.request.base.DefaultPageRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时41分
 */
public class Page<T> implements Serializable {

    /**
     * 类
     */
    private List<T> list;


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

    public Page(List<T> list, Integer size, Integer pageNum, Integer count, Integer totalPage) {
        this.list = list;
        this.size = size;
        this.pageNum = pageNum;
        this.count = count;
        this.totalPage = totalPage;
    }

    public Page() {
    }

    public static <T extends Serializable> Page<T> build(List<T> list, Integer size, Integer pageNum, Integer count, Integer totalPage) {
        return new Page(list, size, pageNum, count, totalPage);
    }

    public static <T extends Serializable> Page<T> build(List<T> list, Integer size, Integer pageNum, Integer count) {
        int remainder = count % size == 0 ? 0 : 1;
        return build(list, size, pageNum, count, count / size + remainder);
    }

    public static <T extends Serializable> Page<T> build(List<T> list, BaseQuery order, Integer count) {
        return build(list, order.limit(), count);
    }

    public static <T extends Serializable> Page<T> build(List<T> list, Limit limit, Integer count) {
        return build(list, limit.getSize(), limit.getNumber(), count);
    }

    /**
     * 创建一个page
     *
     * @param pageRequest 分页请求
     * @param list        查询结果
     * @param count       查询数量
     * @param totalPage   总页数
     * @param <T>         查询结果类
     *
     * @return 包含分页信息的返回集
     */
    public static <T extends Serializable> Page<T> build(DefaultPageRequest pageRequest, List<T> list, Integer count, Integer totalPage) {
        //代表分页
        if (Boolean.TRUE.equals(pageRequest.getPaging())) {
            return build(list, pageRequest.getSize(), pageRequest.getPage(), count, totalPage);
        } else {
            return build(list, count, 1, count, 1);
        }
    }

    /**
     * 创建一个page
     *
     * @param pageRequest 分页请求
     * @param list        查询结果
     * @param count       查询数量
     * @param <T>         查询结果类
     *
     * @return 包含分页信息的返回集
     */
    public static <T extends Serializable> Page<T> build(DefaultPageRequest pageRequest, List<T> list, Integer count) {
        Integer totalPage = count / pageRequest.getSize();
        if (count % pageRequest.getSize() != 0) {
            totalPage++;
        }

        //代表分页
        if (Boolean.TRUE.equals(pageRequest.getPaging())) {
            return build(list, pageRequest.getSize(), pageRequest.getPage(), count, totalPage);
        } else {
            return build(list, count, 1, count, 1);
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
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
