package indi.uhyils.rpc.config;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月16日 10时28分
 */
public class ConsumerConfig {

    /**
     * 是否需要检查(默认需要)
     */
    private Boolean check = Boolean.TRUE;


    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
