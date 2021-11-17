package indi.uhyils.protocol.mysql.pojo.request.impl;

import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.mysql.MysqlExtension;
import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import indi.uhyils.protocol.mysql.enums.ClientPowerEnum;
import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.cqe.FindPasswordByNameQuery;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
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
        byte[] seed = getMysqlHandler().getPassword();
        ServiceResult<ConsumerInfoDTO> passwordByName = mysqlExtension.findPasswordByName(new FindPasswordByNameQuery(username));
        ConsumerInfoDTO consumerInfoDTO = passwordByName.validationAndGet();
        if (consumerInfoDTO == null) {
            return Collections.singletonList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "没有找到此用户"));
        }
        byte[] bytes = MysqlUtil.encodePassword(consumerInfoDTO.getSecretKey().getBytes(StandardCharsets.UTF_8), seed);
        boolean equals = Objects.equals(Base64.encodeBase64String(bytes), challenge);
        if (equals) {
            return Collections.singletonList(new OkResponse(getMysqlHandler(), SqlTypeEnum.NULL));
        } else {
            return Collections.singletonList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "密码错误,密码请使用secretKey"));
        }
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
