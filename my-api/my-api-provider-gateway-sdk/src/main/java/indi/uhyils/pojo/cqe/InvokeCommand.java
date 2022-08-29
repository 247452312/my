package indi.uhyils.pojo.cqe;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import java.util.List;
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
    private List<Map<String, Object>> params;

    /**
     * 请求头
     */
    private Map<String, String> header;

    /**
     * 调用路径.
     * 如果是controller, 此值为url中{/invoke/}后面的东西
     * 如果是RPC,此值为{InterfaceName/MethodName}
     * 如果是mysql,此值为{库名/表名}
     */
    private String path;

    /**
     * 调用方式
     */
    private Integer invokeType;

    public List<Map<String, Object>> getParams() {
        return params;
    }

    public void setParams(List<Map<String, Object>> params) {
        this.params = params;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(Integer invokeType) {
        this.invokeType = invokeType;
    }
}
