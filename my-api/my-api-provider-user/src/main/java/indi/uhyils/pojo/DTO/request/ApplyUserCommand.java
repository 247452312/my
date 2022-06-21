package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月08日 19时46分
 */
public class ApplyUserCommand extends AbstractCommand {

    /**
     * 要申请的用户
     */
    private UserDTO userDTO;


    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
