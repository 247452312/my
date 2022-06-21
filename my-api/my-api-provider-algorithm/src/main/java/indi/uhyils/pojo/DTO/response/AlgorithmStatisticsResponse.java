package indi.uhyils.pojo.DTO.response;

import java.io.Serializable;
import java.util.List;

/**
 * 算法训练统计数据
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 07时26分
 */
public class AlgorithmStatisticsResponse implements Serializable {

    /**
     * 占位 不至于报错
     */
    private List<String> list;

    public static AlgorithmStatisticsResponse build(List<String> list) {
        AlgorithmStatisticsResponse build = new AlgorithmStatisticsResponse();
        build.setList(list);
        return build;

    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
