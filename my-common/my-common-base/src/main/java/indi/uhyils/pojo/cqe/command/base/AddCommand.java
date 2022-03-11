package indi.uhyils.pojo.cqe.command.base;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 21时02分
 */
public class AddCommand<E extends Serializable> extends AbstractCommand {

    private E dto;

    public E getDto() {
        return dto;
    }

    public void setDto(E dto) {
        this.dto = dto;
    }
}
