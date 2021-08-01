package indi.uhyils.pojo.response;

import indi.uhyils.pojo.response.trace.OneTraceLink;
import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月31日 21时04分
 */
public class GetLinkByTraceIdResponse implements Serializable {

    private OneTraceLink link;

    public static GetLinkByTraceIdResponse build(OneTraceLink link) {
        GetLinkByTraceIdResponse build = new GetLinkByTraceIdResponse();
        build.link = link;
        return build;
    }

    public OneTraceLink getLink() {
        return link;
    }

    public void setLink(OneTraceLink link) {
        this.link = link;
    }
}
