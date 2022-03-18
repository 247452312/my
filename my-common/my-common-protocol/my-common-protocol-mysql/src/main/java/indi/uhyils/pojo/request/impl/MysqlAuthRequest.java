package indi.uhyils.pojo.request.impl;

import indi.uhyils.MysqlUtil;
import indi.uhyils.context.UserContext;
import indi.uhyils.decoder.Proto;
import indi.uhyils.enums.ClientPowerEnum;
import indi.uhyils.enums.MysqlErrCodeEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.enums.SqlTypeEnum;
import indi.uhyils.extension.MysqlExtension;
import indi.uhyils.handler.MysqlHandler;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.request.AbstractMysqlRequest;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.ErrResponse;
import indi.uhyils.pojo.response.impl.OkResponse;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.SpringUtil;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.commons.codec.binary.Base64;


/**
 * 客户端登录请求
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 15时03分
 */
public class MysqlAuthRequest extends AbstractMysqlRequest {


    /**
     * 客户端权能标志
     */
    private long abilityFlags;


    /**
     * 最大消息长度
     */
    private long maxMsgSize;

    /**
     * 字符编码
     */
    private long charset;

    /**
     * 用户名
     */
    private String username;

    /**
     * 挑战认证数据
     */
    private long challengeLength;

    /**
     * 挑战认证数据
     */
    private String challenge;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 序列id
     */
    private long sequenceId;

    /**
     * 插件名称 todo 未有效
     */
    private String pluginName;

    /**
     * mysql扩展点
     */
    private MysqlExtension mysqlExtension;

    public MysqlAuthRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
        this.mysqlExtension = SpringUtil.getBean(MysqlExtension.class);
    }

    @Override
    public List<MysqlResponse> invoke() {
        // 1.判断密码是否正确
        byte[] seed = getMysqlHandler().getPassword();
        ServiceResult<UserDTO> passwordByName = mysqlExtension.findPasswordByName(FindUserByNameQuery.build(username));
        UserDTO userDTO = passwordByName.validationAndGet();
        if (userDTO == null) {
            return Collections.singletonList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "没有找到此用户"));
        }
        // 1.1 密码解密
        Password password = new Password(userDTO.getPassword());
        // 1.2 密码再次加密为mysql SHA-1
        String decodePassword = password.decode();
        byte[] bytes = MysqlUtil.encodePassword(decodePassword.getBytes(StandardCharsets.UTF_8), seed);
        String userPassword = Base64.encodeBase64String(bytes);
        boolean equals = Objects.equals(userPassword, challenge);
        // 2. 密码正确则登录
        if (equals) {
            // 调用系统权限登录
            LoginCommand loginCommand = LoginCommand.build(userDTO.getUsername(), userDTO.getPassword(), UserContext.MYSQL_ROLE_ID);
            ServiceResult<LoginDTO> loginResponse = mysqlExtension.login(loginCommand);
            LoginDTO loginDTO = loginResponse.validationAndGet();
            if (loginDTO != null) {
                // 生成的token缓存在mysqlHandler中
                String token = loginDTO.getToken();
                userDTO.setToken(token);
                getMysqlHandler().setUserDTO(userDTO);
                return Collections.singletonList(new OkResponse(getMysqlHandler(), SqlTypeEnum.NULL));
            }
        }
        return Collections.singletonList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "密码错误,密码请使用secretKey"));
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 3);
        this.sequenceId = proto.getFixedInt(1);
        this.abilityFlags = proto.getFixedInt(4);
        this.maxMsgSize = proto.getFixedInt(4);
        this.charset = proto.getFixedInt(1);
        // 32位填充值
        proto.get_filler(23);
        Asserts.assertTrue(proto.has_remaining_data(), "暂不支持mysql的SSL连接,请在连接串后添加<useSSL=false>");
        this.username = proto.get_null_str();

        this.challengeLength = proto.get_lenenc_int();
        this.challenge = proto.get_fixed_str(this.challengeLength, true);

        if (MysqlUtil.hasAbility(this.abilityFlags, ClientPowerEnum.CLIENT_CONNECT_WITH_DB.getCode())) {
            this.dbName = proto.get_null_str();
        }

        if (MysqlUtil.hasAbility(this.abilityFlags, ClientPowerEnum.CLIENT_SECURE_CONNECTION.getCode())) {
            this.pluginName = proto.get_null_str();
        }
    }

    public long getAbilityFlags() {
        return abilityFlags;
    }


    public long getMaxMsgSize() {
        return maxMsgSize;
    }

    public long getCharset() {
        return charset;
    }

    public String getUsername() {
        return username;
    }

    public String getChallenge() {
        return challenge;
    }

    public String getDbName() {
        return dbName;
    }

    public long getChallengeLength() {
        return challengeLength;
    }

    public long getSequenceId() {
        return sequenceId;
    }

    public String getPluginName() {
        return pluginName;
    }
}
