package indi.uhyils.pojo.DTO.request;


import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * 定时任务表达式请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月29日 06时51分
 */
public class CronRequest extends DefaultCQE {

    /**
     * 定时表达式
     */
    private String cron;


    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
