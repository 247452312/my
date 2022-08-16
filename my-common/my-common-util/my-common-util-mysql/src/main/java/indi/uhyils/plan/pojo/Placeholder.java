package indi.uhyils.plan.pojo;

/**
 * 执行计划入参占位符
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 09时18分
 */
public class Placeholder {

    /**
     * 目标执行计划id
     */
    private long id;

    /**
     * 目标执行计划结果中字段名称
     */
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
