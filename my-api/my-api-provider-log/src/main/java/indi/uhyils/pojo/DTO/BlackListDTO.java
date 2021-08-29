package indi.uhyils.pojo.DTO;


/**
 * 黑名单(BlackList)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分52秒
 */
public class BlackListDTO extends IdDTO {

    private static final long serialVersionUID = -84101828857334223L;


    /**
     * 用户ip
     */
    private String ip;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
