package indi.uhyils.pojo.entity.user;

import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.commons.codec.binary.Base64;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月23日 16时35分
 */
public class MysqlUser extends AbstractUser {

    /**
     * mysql请求信息
     */
    private MysqlAuthCommand mysqlAuthCommand;

    public MysqlUser(MysqlAuthCommand mysqlAuthCommand) {
        super(mysqlAuthCommand.getUsername());
        this.mysqlAuthCommand = mysqlAuthCommand;
    }


    @Override
    public boolean checkPassword(UserFacade userFacade, String password) {
        byte[] seed = mysqlAuthCommand.getMysqlTcpInfo().getRandomByte();
        // 1.1 密码解密
        Password realPassword = new Password(userDTO.getPassword());
        // 1.2 密码再次加密为mysql SHA-1
        String decodePassword = realPassword.decode();
        byte[] bytes = MysqlUtil.encodePassword(decodePassword.getBytes(StandardCharsets.UTF_8), seed);
        String userPassword = Base64.encodeBase64String(bytes);
        return Objects.equals(userPassword, mysqlAuthCommand.getChallenge());
    }

    @Override
    protected void doAfterLogin(UserDTO userDTO) {
        MysqlTcpInfo mysqlTcpInfo = mysqlAuthCommand.getMysqlTcpInfo();
        mysqlTcpInfo.setUserDTO(userDTO);
    }
}
