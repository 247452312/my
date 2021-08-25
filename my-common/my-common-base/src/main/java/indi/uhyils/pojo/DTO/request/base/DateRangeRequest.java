package indi.uhyils.pojo.DTO.request.base;

/**
 * 日期查询类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时11分
 */
public class DateRangeRequest extends DefaultRequest {

    /**
     * 开始时间
     */
    private Long start;

    /**
     * 结束时间
     */
    private Long end;

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
