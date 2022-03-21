package indi.uhyils.pojo.cqe.command;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月21日 10时01分
 */
public class HttpInvokeCommand extends AbstractCommand {

    private String url;

    private String requestType;

    private JSONArray param;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public JSONArray getParam() {
        return param;
    }

    public void setParam(JSONArray param) {
        this.param = param;
    }
}
