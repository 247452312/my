package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.BaseDTO;

/**
 * 并发信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 06时31分
 */
public class ConcurrentInfoDTO implements BaseDTO {

    /**
     * 网关 并发数/秒
     */
    private Long concurrentNumber;

    public static ConcurrentInfoDTO build(Long concurrentNumber) {
        ConcurrentInfoDTO build = new ConcurrentInfoDTO();
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
