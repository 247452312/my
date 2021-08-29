package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.BaseDTO;
import java.util.List;
import java.util.Map;

/**
 * JVM内存信息日志 --> 只有正在运行的微服务才能统计到
 * 这里返回的信息通过Echart展示为曲线
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 07时11分
 */
public class JvmInfoLogDTO implements BaseDTO {

    /**
     * y轴最大值
     */
    private Integer maxY;

    /**
     * 线数据 list(0)是x轴 list(1)是非堆 list(2)是堆
     */
    private Map<String, List> data;

    /**
     * 默认展示第一个
     */
    private String defaultData;

    /**
     * 创建JVM显示信息
     *
     * @param data 展示信息 -> (size=3 [0]->x轴 [1]->非堆内存 [2]->堆内存)
     *
     * @return
     */
    public static JvmInfoLogDTO build(Map<String, List> data) {

        Double max = 0.0;
        JvmInfoLogDTO build = new JvmInfoLogDTO();
        // 先进行验证
        for (Map.Entry<String, List> stringListEntry : data.entrySet()) {
            if (build.getDefaultData() == null) {
                build.setDefaultData(stringListEntry.getKey());
            }
            List<String> xAxis = data.get(0);
            List value = stringListEntry.getValue();
            assert value.get(1) instanceof List;
            assert value.get(2) instanceof List;
            List<Double> list1 = (List<Double>) value.get(1);
            List<Double> list2 = (List<Double>) value.get(2);
            assert list1.size() == xAxis.size();
            assert list2.size() == xAxis.size();
            for (Double number : list1) {
                if (number > max) {
                    max = number;
                }
            }
            for (Double number : list2) {
                if (number > max) {
                    max = number;
                }
            }
            double element = list1.get(list1.size() - 1) + list2.get(list2.size() - 1);
            if (value.size() > 3) {
                value.set(3, element);
            } else {
                value.add(element);
            }
        }
        build.setData(data);
        // 比最大值还大1 保证不顶格
        Double v = max + 1;
        build.setMaxY(v.intValue());

        return build;

    }

    public Integer getMaxY() {
        return maxY;
    }

    public void setMaxY(Integer maxY) {
        this.maxY = maxY;
    }

    public Map<String, List> getData() {
        return data;
    }

    public void setData(Map<String, List> data) {
        this.data = data;
    }

    public String getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(String defaultData) {
        this.defaultData = defaultData;
    }
}
