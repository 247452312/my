package indi.uhyils.protocol.mysql.util;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.enums.MysqlHandlerStatusEnum;
import indi.uhyils.protocol.mysql.decode.Proto;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.mysql.pojo.request.MysqlRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComBinlogDumpRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComChangeUserRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComConnectOutRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComConnectRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComCreateDbRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComDebugRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComDelayedInsertRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComDropDbRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComFieldListRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComInitDbRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComPingRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComProcessInfoRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComProcessKillRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComQueryRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComQuitRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComRefreshRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComRegisterSlaveRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComSetOptionRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComShutdownRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComSleepRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComStatisticsRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComStmtCloseRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComStmtExecuteRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComStmtFetchRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComStmtPrepareRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComStmtResetRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComStmtSendLongDataRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComTableDumpRequest;
import indi.uhyils.util.mysql.pojo.request.impl.ComTimeRequest;
import indi.uhyils.util.mysql.pojo.request.impl.MysqlAuthRequest;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 09时22分
 */
public class RequestAnalysis {

    private RequestAnalysis() {
    }

    public static MysqlCommandTypeEnum load(byte[] mysqlBytes) {
        Proto proto = new Proto(mysqlBytes, 4);
        long type = proto.getFixedInt(1);
        return MysqlCommandTypeEnum.parse(type);
    }

    public static MysqlRequest load(MysqlTcpInfo mysqlTcpInfo, byte[] mysqlBytes) {
        mysqlTcpInfo.setIndex(mysqlBytes[3]);
        MysqlHandlerStatusEnum status = mysqlTcpInfo.getAndIncrementStatus();
        switch (status) {
            case FIRST_SIGHT:
                // 初见状态默认请求是登录信息
                MysqlAuthRequest mysqlAuthRequest = new MysqlAuthRequest(mysqlTcpInfo);
                mysqlAuthRequest.load(mysqlBytes);
                return mysqlAuthRequest;
            case PASSED:
                return loadCommand(mysqlTcpInfo, mysqlBytes);
            case OVER:
                Asserts.throwException("请求已经结束,请不要再次请求");
                return null;
            default:
                return null;
        }
    }

    private static MysqlRequest loadCommand(MysqlTcpInfo mysqlHandler, byte[] mysqlBytes) {
        Proto proto = new Proto(mysqlBytes, 4);
        long type = proto.getFixedInt(1);
        MysqlCommandTypeEnum parse = MysqlCommandTypeEnum.parse(type);
        MysqlRequest result = null;
        switch (parse) {
            case COM_QUERY:
                result = new ComQueryRequest(mysqlHandler);
                break;
            case COM_PING:
                result = new ComPingRequest(mysqlHandler);
                break;
            case COM_QUIT:
                result = new ComQuitRequest(mysqlHandler);
                mysqlHandler.setStatus(MysqlHandlerStatusEnum.OVER);
                break;
            case COM_TIME:
                result = new ComTimeRequest(mysqlHandler);
                break;
            case COM_DEBUG:
                result = new ComDebugRequest(mysqlHandler);
                break;
            case COM_SLEEP:
                result = new ComSleepRequest(mysqlHandler);
                break;
            case COM_CONNECT:
                result = new ComConnectRequest(mysqlHandler);
                break;
            case COM_DROP_DB:
                result = new ComDropDbRequest(mysqlHandler);
                break;
            case COM_INIT_DB:
                result = new ComInitDbRequest(mysqlHandler);
                break;
            case COM_REFRESH:
                result = new ComRefreshRequest(mysqlHandler);
                break;
            case COM_SHUTDOWN:
                result = new ComShutdownRequest(mysqlHandler);
                break;
            case COM_CREATE_DB:
                result = new ComCreateDbRequest(mysqlHandler);
                break;
            case COM_FIELD_LIST:
                result = new ComFieldListRequest(mysqlHandler);
                break;
            case COM_SET_OPTION:
                result = new ComSetOptionRequest(mysqlHandler);
                break;
            case COM_STATISTICS:
                result = new ComStatisticsRequest(mysqlHandler);
                break;
            case COM_STMT_CLOSE:
                result = new ComStmtCloseRequest(mysqlHandler);
                break;
            case COM_STMT_FETCH:
                result = new ComStmtFetchRequest(mysqlHandler);
                break;
            case COM_STMT_RESET:
                result = new ComStmtResetRequest(mysqlHandler);
                break;
            case COM_TABLE_DUMP:
                result = new ComTableDumpRequest(mysqlHandler);
                break;
            case COM_BINLOG_DUMP:
                result = new ComBinlogDumpRequest(mysqlHandler);
                break;
            case COM_CHANGE_USER:
                result = new ComChangeUserRequest(mysqlHandler);
                break;
            case COM_CONNECT_OUT:
                result = new ComConnectOutRequest(mysqlHandler);
                break;
            case COM_PROCESS_INFO:
                result = new ComProcessInfoRequest(mysqlHandler);
                break;
            case COM_PROCESS_KILL:
                result = new ComProcessKillRequest(mysqlHandler);
                break;
            case COM_STMT_EXECUTE:
                result = new ComStmtExecuteRequest(mysqlHandler);
                break;
            case COM_STMT_PREPARE:
                result = new ComStmtPrepareRequest(mysqlHandler);
                break;
            case COM_DELAYED_INSERT:
                result = new ComDelayedInsertRequest(mysqlHandler);
                break;
            case COM_REGISTER_SLAVE:
                result = new ComRegisterSlaveRequest(mysqlHandler);
                break;
            case COM_STMT_SEND_LONG_DATA:
                result = new ComStmtSendLongDataRequest(mysqlHandler);
                break;
            default:
                break;
        }
        result.load(mysqlBytes);
        return result;
    }
}
