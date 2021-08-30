package indi.uhyils.util;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月30日 08时44分
 */
public final class MathUtil {

    private MathUtil() {
    }


    /**
     * 获取方差
     *
     * @param times
     *
     * @return
     */
    public static double getVariance(List<Long> times) {
        int sum = 0;
        double avg;
        for (Long time : times) {
            sum += time;
        }
        avg = (double) sum / times.size();
        double result = 0.0;

        for (Long time : times) {
            result += Math.pow(time - avg, 2);
        }
        result /= times.size();
        return result;
    }
}
