package indi.uhyils.exception;

/**
 * 无链路跟踪
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月23日 06时58分
 */
public class NoRequestLinkException extends Exception {

    public NoRequestLinkException() {
        super("请求无链路跟踪,请检查参数");
    }

}
