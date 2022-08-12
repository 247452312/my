package indi.uhyils.pojo.cqe;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import java.util.Map;

/**
 * 对接中心执行方法command
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 10时19分
 */
public class InvokeCommand extends AbstractCommand {

    /**
     * 入参
     */
    private Map<String, Object> params;

    /**
     * 请求头
     */
    private Map<String, String> header;
}
