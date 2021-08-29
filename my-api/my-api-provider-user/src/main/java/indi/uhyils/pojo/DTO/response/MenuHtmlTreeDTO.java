package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.BaseDTO;
import indi.uhyils.pojo.DTO.MenuDTO;
import java.io.Serializable;
import java.util.List;

/**
 * menu.html菜单页用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月05日 11时44分
 */
public class MenuHtmlTreeDTO implements BaseDTO {

    Integer code = 0;

    String msg = "";

    Integer count;

    List<MenuDTO> data;


    public static MenuHtmlTreeDTO build(List<MenuDTO> list) {
        assert list != null;
        MenuHtmlTreeDTO menuHtmlTreeResponse = new MenuHtmlTreeDTO();
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

    public List<MenuDTO> getData() {
        return data;
    }

    public void setData(List<MenuDTO> data) {
        this.data = data;
    }
}
