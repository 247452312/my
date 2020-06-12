package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.MenuEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * menu.html菜单页用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月05日 11时44分
 */
public class MenuHtmlTreeResponse implements Serializable {

    Integer code = 0;

    String msg = "";

    Integer count;

    Collection<MenuEntity> data;


    public static MenuHtmlTreeResponse build(Collection<MenuEntity> list) {
        assert list != null;
        MenuHtmlTreeResponse menuHtmlTreeResponse = new MenuHtmlTreeResponse();
        menuHtmlTreeResponse.setCount(list.size());
        menuHtmlTreeResponse.setData(list);
        return menuHtmlTreeResponse;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Collection<MenuEntity> getData() {
        return data;
    }

    public void setData(Collection<MenuEntity> data) {
        this.data = data;
    }
}
