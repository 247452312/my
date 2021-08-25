package indi.uhyils.pojo.DTO.response;

import java.io.Serializable;

/**
 * 对软件做出操作的回应
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月13日 07时27分
 */
public class OperateSoftwareResponse implements Serializable {

    /**
     * 不管成不成功,执行方法后的状态
     */
    private Integer status;

    /**
     * 执行方法需要说明的事项
     */
    private String msg;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
