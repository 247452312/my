package indi.uhyils.protocol.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;


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
     * 客户端权能标志(扩展)
     */
    private long abilityFlags2;

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
    private String challenge;

    /**
     * 数据库名称
     */
    private String dbName;

    public MysqlAuthRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    public MysqlResponse invoke() {
        return null;
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 3);
        this.abilityFlags = proto.getFixedInt(2);
        this.abilityFlags2 = proto.getFixedInt(2);
        this.maxMsgSize = proto.getFixedInt(4);
        this.charset = proto.getFixedInt(1);
        // 32位填充值
        proto.get_filler(23);
        this.username = proto.get_null_str();
        long fixedInt = proto.getFixedInt(1);
        long length = 0;
        if (fixedInt <= 250) {
            length = fixedInt;
        } else if (fixedInt == 251) {
            length = 0;
        } else if (fixedInt == 252) {
            length = proto.getFixedInt(2);
        } else if (fixedInt == 253) {
            length = proto.getFixedInt(3);
        } else if (fixedInt == 254) {
            length = proto.getFixedInt(8);
        }
        this.challenge = proto.get_fixed_str(length);
        if (proto.has_remaining_data()) {
            this.dbName = proto.get_null_str();
        }
    }

    public long getAbilityFlags() {
        return abilityFlags;
    }

    public long getAbilityFlags2() {
        return abilityFlags2;
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
}
