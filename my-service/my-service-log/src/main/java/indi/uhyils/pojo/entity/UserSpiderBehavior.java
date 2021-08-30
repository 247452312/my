package indi.uhyils.pojo.entity;

import indi.uhyils.repository.TraceDetailRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.MathUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户的爬虫行为判断
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月30日 08时15分
 */
public class UserSpiderBehavior extends AbstractEntity {

    /**
     * 最大时间跨度 1秒
     */
    private static final Long MAX_TIME_SPAN = 1000L;

    /**
     * 很久很久以前 (3分钟)
     */
    private static final Long LONG_TIME_AGO = 3 * 60 * 1000L;

    private String ip;

    /**
     * 最近size次执行的时间
     */
    private List<Long> times;

    private Integer size;

    /**
     * 是否足够指定的次数
     */
    private Boolean enoughTarget;

    public UserSpiderBehavior(String ip) {
        this.ip = ip;
    }

    public void fillLastTime(Integer size, TraceDetailRepository repository) {
        this.size = size;
        this.times = repository.findLastTime(this);
        this.enoughTarget = this.times.size() >= size;
    }

    public Integer size() {
        return size;
    }

    public String ip() {
        return ip;
    }

    /**
     * 判断是否是爬虫
     *
     * @param frequency 方差阈值
     *
     * @return
     */
    public Boolean judgeSpider(Integer frequency) {
        AssertUtil.assertTrue(times != null, "没有填充lastTime");
        // 如果次数不够,代表不能判断,返回不是爬虫的判断
        if (!enoughTarget) {
            return false;
        }
        Long first = times.get(0);
        Long end = times.get(times.size() - 1);
        // 时间跨度
        long timeSpan = first - end;
        // 如果时间已经过去很久了(指3分钟) 则返回不是爬虫
        if (System.currentTimeMillis() - first > LONG_TIME_AGO) {
            return false;
        }
        List<Long> timeIntervals = new ArrayList<>(times.size() - 1);

        // 获取所有的时间间隔
        for (int i = 1; i < times.size(); i++) {
            timeIntervals.add(times.get(i) - times.get(i - 1));
        }
        /* 求方差 */
        double result = MathUtil.getVariance(timeIntervals);
        // 求出来的方差小于阈值并且时间跨度小(每秒n个) 返回此ip可能是爬虫
        if (result <= frequency && timeSpan < MAX_TIME_SPAN) {
            LogUtil.info("ip:" + ip + " 最近12次请求的时间跨度为:" + timeSpan + " 方差为:" + result);
            return true;
        }
        return false;
    }


}
