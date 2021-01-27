package indi.uhyils.pojo.request.base;

/**
 * 分页查询
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时07分
 */
public class DefaultPageRequest extends DefaultRequest {


    /**
     * 页码 下标从1开始 sql中会主动减去1
     */
    private Long page;

    /**
     * 每页大小
     */
    private Long size;

    /**
     * 是否分页
     */
    private Boolean paging;


    public Boolean getPaging() {
        return paging;
    }

    public void setPaging(Boolean paging) {
        this.paging = paging;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
