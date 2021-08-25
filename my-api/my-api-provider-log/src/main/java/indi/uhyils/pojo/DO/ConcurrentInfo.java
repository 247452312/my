package indi.uhyils.pojo.DO;

import java.io.Serializable;

/**
 * 并发信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 06时31分
 */
public class ConcurrentInfo implements Serializable {

    /**
     * 网关 并发数/秒
     */
    private Long concurrentNumber;

    public static ConcurrentInfo build(Long concurrentNumber) {
        ConcurrentInfo build = new ConcurrentInfo();
        build.concurrentNumber = concurrentNumber;
        return build;

    }

    public Long getConcurrentNumber() {
        return concurrentNumber;
    }

    public void setConcurrentNumber(Long concurrentNumber) {
        this.concurrentNumber = concurrentNumber;
    }
}
