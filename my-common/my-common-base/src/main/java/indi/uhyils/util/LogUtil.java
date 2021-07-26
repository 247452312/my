package indi.uhyils.util;


import com.alibaba.fastjson.JSON;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.log.MyTraceIdContext;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 08时43分
 */
public class LogUtil {

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
     * 日志文件缓存地
     */
    private static Map<String, Logger> loggerMap = new HashMap<>();

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
        writeLog(cls.getName(), msg, null, LogTypeEnum.INFO);
    }

    public static void info(String msg) {
        writeLog(Thread.currentThread().getName(), msg, null, LogTypeEnum.INFO);
    }

    public static void info(Class<?> cls, Throwable e) {
        writeLog(cls.getName(), null, e, LogTypeEnum.INFO);
    }

    public static void info(Object obj, String msg) {
        info(obj.getClass(), msg);
    }

    public static void info(Object obj, Throwable e) {
        info(obj.getClass(), e);
    }

    public static void debug(Class<?> cls, String msg) {
        writeLog(cls.getName(), msg, null, LogTypeEnum.DEBUG);
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
        writeLog(Thread.currentThread().getName(), msg, null, LogTypeEnum.DEBUG);
    }

    public static void debug(Class<?> cls, Throwable e) {
        writeLog(cls.getName(), null, e, LogTypeEnum.DEBUG);
    }

    public static void debug(Object obj, String msg) {
        debug(obj.getClass(), msg);
    }

    public static void debug(Object obj, Throwable e) {
        debug(obj.getClass(), e);
    }

    public static void warn(Class<?> cls, String msg) {
        writeLog(cls.getName(), msg, null, LogTypeEnum.WARN);
    }

    public static void warn(String msg) {
        writeLog(Thread.currentThread().getName(), msg, null, LogTypeEnum.WARN);
    }

    public static void warn(Class<?> cls, Throwable e) {
        writeLog(cls.getName(), null, e, LogTypeEnum.WARN);
    }

    public static void warn(Object obj, String msg) {
        warn(obj.getClass(), msg);
    }

    public static void warn(Object obj, Throwable e) {
        warn(obj.getClass(), e);
    }

    public static void error(Class<?> cls, String msg) {
        writeLog(cls.getName(), msg, null, LogTypeEnum.ERROR);
    }

    public static void error(String msg) {
        writeLog(Thread.currentThread().getName(), msg, null, LogTypeEnum.ERROR);
    }

    public static void error(Throwable e) {
        writeLog(Thread.currentThread().getName(), null, e, LogTypeEnum.ERROR);
    }

    public static void error(Class<?> cls, Throwable e) {
        writeLog(cls.getName(), null, e, LogTypeEnum.ERROR);
    }

    public static void error(Class<?> cls, Throwable e, String msg) {
        writeLog(cls.getName(), msg, e, LogTypeEnum.ERROR);
    }

    public static void error(Object cls, Throwable e, String msg) {
        error(cls.getClass(), e, msg);
    }

    public static void error(Object obj, String msg) {
        error(obj.getClass(), msg);
    }

    public static void error(Throwable e, String msg) {
        writeLog(Thread.currentThread().getName(), msg, e, LogTypeEnum.ERROR);
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
        LINK_LOG.error(LINK_MARKER, msg);
    }

    /**
     * sql语句打印
     *
     * @param unique
     * @param sql
     * @param timeConsuming
     */
    public static void sql(String unique, String sql, long timeConsuming) {
        sql = String.format("%s|%d|%s", unique, timeConsuming, sql);
        SQL_LOG.error(SQL_MARKER, sql);
    }


    /**
     * 根据类型输出不同级别的对应类的日志
     *
     * @param className   名称,simpleName
     * @param msg         信息
     * @param logTypeEnum 类型
     */
    private static void writeLog(String className, String msg, Throwable throwable, LogTypeEnum logTypeEnum) {
        if (msg != null) {
            msg = String.format("%s|%s|%s", MyTraceIdContext.getThraceId(), MyTraceIdContext.getRpcIdStr(), msg);
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


    private static void choiceLogType(String msg, Throwable throwable, LogTypeEnum logTypeEnum, Logger logger) {
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
