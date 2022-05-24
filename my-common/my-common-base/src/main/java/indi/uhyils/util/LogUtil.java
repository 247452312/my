package indi.uhyils.util;


import com.alibaba.fastjson.JSON;
import indi.uhyils.context.MyTraceIdContext;
import indi.uhyils.enum_.LogDetailTypeEnum;
import indi.uhyils.enum_.LogLevelEnum;
import indi.uhyils.enum_.LogTypeEnum;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 08时43分
 */
public final class LogUtil {

    /**
     * 链路
     */
    private static final Logger LINK_LOG = LoggerFactory.getLogger("link_log");

    private static final Marker LINK_MARKER = MarkerFactory.getMarker("LINK");

    /**
     * sql
     */
    private static final Logger SQL_LOG = LoggerFactory.getLogger("sql_log");

    private static final Marker SQL_MARKER = MarkerFactory.getMarker("SQL");

    /**
     * mq
     */
    private static final Logger MQ_LOG = LoggerFactory.getLogger("mq_log");

    private static final Marker MQ_MARKER = MarkerFactory.getMarker("MQ");

    /**
     * mq
     */
    private static final Logger RPC_LOG = LoggerFactory.getLogger("rpc_log");

    private static final Marker RPC_MARKER = MarkerFactory.getMarker("RPC");

    /**
     * task
     */
    private static final Logger TASK_LOG = LoggerFactory.getLogger("task_log");

    private static final Marker TASK_MARKER = MarkerFactory.getMarker("TASK");

    /**
     * controller
     */
    private static final Logger CONTROLLER_LOG = LoggerFactory.getLogger("controller_log");

    private static final Marker CONTROLLER_MARKER = MarkerFactory.getMarker("CONTROLLER");

    /**
     * 日志文件缓存地
     */
    private static final Map<String, Logger> loggerMap = new HashMap<>();

    private LogUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isDebugEnabled(Object obj) {
        if (obj == null) {
            return Boolean.FALSE;
        }
        return isDebugEnabled(obj.getClass());
    }

    public static boolean isDebugEnabled(Class<?> clazz) {
        if (clazz == null) {
            return Boolean.FALSE;
        }
        String simpleName = clazz.getName();
        Logger logger = MapUtil.putIfAbsent(loggerMap, simpleName, () -> LoggerFactory.getLogger(clazz));
        if (logger != null) {
            return logger.isDebugEnabled();
        }
        return Boolean.TRUE;
    }

    public static void info(Class<?> cls, String msg) {
        writeLog(cls.getName(), msg, null, LogLevelEnum.INFO);
    }

    public static void info(String msg) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.INFO);
    }

    public static void info(String msg, String... params) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.INFO, params);
    }

    public static void info(String msg, String params) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.INFO, params);
    }

    public static void info(Class<?> cls, Throwable e) {
        writeLog(cls.getName(), null, e, LogLevelEnum.INFO);
    }

    public static void info(Object obj, String msg, String... params) {
        writeLog(obj.getClass().getName(), msg, null, LogLevelEnum.INFO, params);
    }

    public static void info(Object obj, Throwable e) {
        info(obj.getClass(), e);
    }

    public static void debug(Class<?> cls, String msg) {
        writeLog(cls.getName(), msg, null, LogLevelEnum.DEBUG);
    }

    public static void debug(Class<?> cls, String msg, String... param) {
        debug(cls, String.format(msg, param));
    }

    public static void debug(Class<?> cls, String msg, Object... param) {
        String[] paramStrs = new String[param.length];
        for (int i = 0; i < param.length; i++) {
            paramStrs[i] = JSON.toJSONString(param[i]);
        }
        debug(cls, msg, paramStrs);
    }

    public static void debug(String msg) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.DEBUG);
    }

    public static void debug(String msg, String... params) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.DEBUG, params);
    }

    public static void debug(String msg, String params) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.DEBUG, params);
    }

    public static void debug(Class<?> cls, Throwable e) {
        writeLog(cls.getName(), null, e, LogLevelEnum.DEBUG);
    }

    public static void debug(Object obj, String msg) {
        debug(obj.getClass(), msg);
    }

    public static void debug(Object obj, Throwable e) {
        debug(obj.getClass(), e);
    }

    public static void warn(Class<?> cls, String msg) {
        writeLog(cls.getName(), msg, null, LogLevelEnum.WARN);
    }

    public static void warn(String msg) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.WARN);
    }

    public static void warn(String msg, String... params) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.WARN, params);
    }

    public static void warn(Class<?> cls, Throwable e) {
        writeLog(cls.getName(), null, e, LogLevelEnum.WARN);
    }

    public static void warn(Object obj, String msg) {
        warn(obj.getClass(), msg);
    }

    public static void warn(Object obj, Throwable e) {
        warn(obj.getClass(), e);
    }

    public static void warn(String msg, String params) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.WARN, params);
    }

    public static void warn(String msg, Object params) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.WARN, params.toString());
    }

    public static void warn(Throwable e, String msg, String... params) {
        writeLog(Thread.currentThread().getName(), msg, e, LogLevelEnum.WARN, params);
    }

    public static void error(Class<?> cls, String msg) {
        writeLog(cls.getName(), msg, null, LogLevelEnum.ERROR);
    }

    public static void error(String msg) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.ERROR);
    }

    public static void error(Throwable e) {
        writeLog(Thread.currentThread().getName(), null, e, LogLevelEnum.ERROR);
    }

    public static void error(Class<?> cls, Throwable e) {
        writeLog(cls.getName(), null, e, LogLevelEnum.ERROR);
    }

    public static void error(Class<?> cls, Throwable e, String msg) {
        writeLog(cls.getName(), msg, e, LogLevelEnum.ERROR);
    }

    public static void error(Object cls, Throwable e, String msg) {
        error(cls.getClass(), e, msg);
    }

    public static void error(Object obj, String msg) {
        error(obj.getClass(), msg);
    }

    public static void error(String msg, String params) {
        writeLog(Thread.currentThread().getName(), msg, null, LogLevelEnum.ERROR, params);
    }

    public static void error(Throwable e, String msg) {
        writeLog(Thread.currentThread().getName(), msg, e, LogLevelEnum.ERROR);
    }

    public static void error(Throwable e, String msg, String... params) {
        writeLog(Thread.currentThread().getName(), msg, e, LogLevelEnum.ERROR, params);
    }

    public static void error(Object obj, Throwable e) {
        error(obj.getClass(), e);
    }

    /**
     * 链路跟踪日志打印
     *
     * @param msg
     */
    public static void link(String msg) {
        LINK_LOG.info(LINK_MARKER, msg);
    }

    /**
     * sql语句打印
     *
     * @param hash
     * @param sql
     * @param useTime
     */
    public static void sql(Long traceId, String hash, long useTime, String sql) {
        sql = String.format(LogDetailTypeEnum.DETAIL.getCode() + "%d|%d|%s|%d|%d|%s", traceId, LogTypeEnum.DB.getCode(), hash, System.currentTimeMillis(), useTime, sql);
        SQL_LOG.info(SQL_MARKER, sql);
    }

    /**
     * mq打印
     *
     * @param hash
     * @param exchangeName
     * @param queueName
     */
    public static void mq(Long traceId, String hash, long useTime, String exchangeName, String queueName) {
        String msg = String
            .format(LogDetailTypeEnum.DETAIL.getCode() + "%d|%d|%s|%d|%d|%s|%s", traceId, LogTypeEnum.MQ.getCode(), hash, System.currentTimeMillis(), useTime, exchangeName, queueName);
        MQ_LOG.info(MQ_MARKER, msg);
    }

    /**
     * rpc打印
     *
     * @param hash
     * @param useTime
     * @param serviceName
     * @param methodName
     */
    public static void rpc(Long traceId, String hash, long useTime, String serviceName, String methodName) {
        String msg = String.format(LogDetailTypeEnum.DETAIL.getCode() + "%d|%d|%s|%d|%d|%s|%s", traceId, LogTypeEnum.RPC.getCode(), hash, System
            .currentTimeMillis(), useTime, serviceName, methodName);
        RPC_LOG.info(RPC_MARKER, msg);
    }

    /**
     * rpc打印
     *
     * @param hash
     * @param useTime
     * @param serviceName
     * @param methodName
     */
    public static void task(Long traceId, String hash, long useTime, String serviceName, String methodName) {
        String msg = String.format(LogDetailTypeEnum.DETAIL.getCode() + "%d|%d|%s|%d|%d|%s|%s", traceId, LogTypeEnum.TASK.getCode(), hash, System
            .currentTimeMillis(), useTime, serviceName, methodName);
        TASK_LOG.info(TASK_MARKER, msg);
    }

    /**
     * controller打印
     *
     * @param hash
     * @param useTime
     * @param url
     */
    public static void controller(Long traceId, String hash, long useTime, String url, String ip) {
        String msg = String
            .format(LogDetailTypeEnum.DETAIL.getCode() + "%d|%d|%s|%d|%d|%s|%s", traceId, LogTypeEnum.CONTROLLER.getCode(), hash, System.currentTimeMillis(), useTime, url, ip);
        CONTROLLER_LOG.info(CONTROLLER_MARKER, msg);
    }

    /**
     * 根据类型输出不同级别的对应类的日志
     *
     * @param className   名称,simpleName
     * @param msg         信息
     * @param logTypeEnum 类型
     */
    private static void writeLog(String className, String msg, Throwable throwable, LogLevelEnum logTypeEnum, String... params) {
        msg = MessageFormatter.arrayFormat(msg, params).getMessage();
        if (msg != null && logTypeEnum != LogLevelEnum.DEBUG) {
            msg = String.format(LogDetailTypeEnum.LOG.getCode() + "%s|%s|%d|%s", MyTraceIdContext.getThraceId(), MyTraceIdContext.getAndAddRpcIdStr(), System.currentTimeMillis(), msg);
        }
        if (loggerMap.containsKey(className)) {
            Logger logger = loggerMap.get(className);
            choiceLogType(msg, throwable, logTypeEnum, logger);
            return;
        }
        Logger logger = LoggerFactory.getLogger(className);
        loggerMap.put(className, logger);
        choiceLogType(msg, throwable, logTypeEnum, logger);
    }


    private static void choiceLogType(String msg, Throwable throwable, LogLevelEnum logTypeEnum, Logger logger) {
        switch (logTypeEnum) {
            case INFO:
                logger.info(msg, throwable);
                break;
            case WARN:
                logger.warn(msg, throwable);
                break;
            case DEBUG:
                logger.debug(msg, throwable);
                break;
            case ERROR:
                logger.error(msg, throwable);
                break;
            default:
                logger.error("no this LogType!");
                break;
        }
    }

}
