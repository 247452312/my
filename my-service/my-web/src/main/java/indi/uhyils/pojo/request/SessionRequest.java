package indi.uhyils.pojo.request;

import java.util.HashMap;

/**
 * session操作
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月23日 22时02分
 */
public class SessionRequest {

    private String attrName;

    private HashMap<String, Object> data;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
