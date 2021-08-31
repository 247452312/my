package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.DTO.BaseDbDTO;
import indi.uhyils.pojo.cqe.query.BaseArgQuery;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 21时11分
 */
public class ChangeCommand<T extends BaseDbDTO> extends AbstractCommand {

    /**
     * 要改成的东西
     */
    private T dto;

    /**
     * 要改的东西
     */
    private BaseArgQuery order;


    public T getDto() {
        return dto;
    }

    public void setDto(T dto) {
        this.dto = dto;
    }

    public BaseArgQuery getOrder() {
        return order;
    }

    public void setOrder(BaseArgQuery order) {
        this.order = order;
    }
}
