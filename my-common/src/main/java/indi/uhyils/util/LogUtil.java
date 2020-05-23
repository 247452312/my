package indi.uhyils.util;


import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 08时43分
 */
public class LogUtil {

    /**
     * 日志文件缓存地
     */
    private static Map<Class, Logger> loggerMap = new HashMap<Class, Logger>();


    public static void info(Class cls, String msg) {
        writeLog(cls, msg, LogTypeEnum.INFO);
    }

    public static void info(Object obj, String msg) {
        info(obj.getClass(), msg);
    }


    public static void info(Class cls, Exception e) {
        info(cls, e.getMessage());
    }

    public static void info(Object obj, Exception e) {
        info(obj.getClass(), e.getMessage());
    }

    public static void debug(Class cls, String msg) {
        writeLog(cls, msg, LogTypeEnum.DEBUG);
    }

    public static void debug(Object obj, String msg) {
        debug(obj.getClass(), msg);
    }


    public static void debug(Class cls, Exception e) {
        debug(cls, e.getMessage());
    }

    public static void debug(Object obj, Exception e) {
        debug(obj.getClass(), e.getMessage());
    }


    public static void warn(Class cls, String msg) {
        writeLog(cls, msg, LogTypeEnum.WARN);
    }

    public static void warn(Object obj, String msg) {
        warn(obj.getClass(), msg);
    }


    public static void warn(Class cls, Exception e) {
        warn(cls, e.getMessage());
    }

    public static void warn(Object obj, Exception e) {
        warn(obj.getClass(), e.getMessage());
    }


    public static void error(Class cls, String msg) {
        writeLog(cls, msg, LogTypeEnum.ERROR);
    }

    public static void error(Object obj, String msg) {
        error(obj.getClass(), msg);
    }

    public static void error(Object obj, Exception e) {
        error(obj.getClass(), e.getMessage());
    }


    public static void error(Class cls, Exception e) {
        error(cls, e.getMessage());
    }


    /**
     * 根据类型输出不同级别的对应类的日志
     *
     * @param cls
     * @param msg
     * @param logTypeEnum
     */
    private static void writeLog(Class cls, String msg, LogTypeEnum logTypeEnum) {
        if (loggerMap.keySet().contains(cls)) {
            Logger logger = loggerMap.get(cls);
            choiseLogType(msg, logTypeEnum, logger);
            return;
        }
        Logger logger = LoggerFactory.getLogger(cls);
        loggerMap.put(cls, logger);
        choiseLogType(msg, logTypeEnum, logger);
    }

    private static void choiseLogType(String msg, LogTypeEnum logTypeEnum, Logger logger) {
        switch (logTypeEnum) {
            case INFO:
                logger.info(msg);
                break;
            case WARN:
                logger.warn(msg);
                break;
            case DEBUG:
                logger.debug(msg);
                break;
            case ERROR:
                logger.error(msg);
                break;
            default:
                logger.error("no this LogType!");
                break;
        }
    }


    /**
     * 添加链路跟踪
     *
     * @param targetRequest
     * @param serviceResult
     */
    public static void addRequestLink(DefaultRequest targetRequest, ServiceResult serviceResult) {
        LinkNode<String> serviceResultRequestLink = serviceResult.getRequestLink();
        targetRequest.setRequestLink(serviceResultRequestLink);
    }
}
