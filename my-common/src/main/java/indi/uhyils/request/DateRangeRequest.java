package indi.uhyils.request;

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
    private Integer start;

    /**
     * 结束时间
     */
    private Integer end;


    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
