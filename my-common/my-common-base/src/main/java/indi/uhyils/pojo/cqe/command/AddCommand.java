package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.DTO.BaseDbDTO;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 21时02分
 */
public class AddCommand<E extends BaseDbDTO> extends AbstractCommand {

    private E dto;

    public E getDto() {
        return dto;
    }

    public void setDto(E dto) {
        this.dto = dto;
    }
}
