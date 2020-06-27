package indi.uhyils.pojo.response.welcome;

import java.io.Serializable;
import java.util.List;

/**
 * TODO 这个类记得转移到算法模块里去 这里不是他应该呆的地方
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 07时26分
 */
public class AlgorithmStatisticsResponse implements Serializable {
    /**
     * 占位 不至于报错
     */
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public static AlgorithmStatisticsResponse build(List<String> list) {
        AlgorithmStatisticsResponse build = new AlgorithmStatisticsResponse();
        build.setList(list);
        return build;

    }
}
