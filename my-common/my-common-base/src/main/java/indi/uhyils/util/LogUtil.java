package indi.uhyils.util;


import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.base.ServiceResult;
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
    private static Map<Class<?>, Logger> loggerMap = new HashMap<>();


    public static void info(Class<?> cls, String msg) {
        writeLog(cls, msg, null, LogTypeEnum.INFO);
    }

    public static void info(Class<?> cls, Throwable e) {
        writeLog(cls, null, e, LogTypeEnum.INFO);
    }

    public static void info(Object obj, String msg) {
        info(obj.getClass(), msg);
    }

    public static void info(Object obj, Throwable e) {
        info(obj.getClass(), e);
    }


    public static void debug(Class<?> cls, String msg) {
        writeLog(cls, msg, null, LogTypeEnum.DEBUG);
    }

    public static void debug(Class<?> cls, Throwable e) {
        writeLog(cls, null, e, LogTypeEnum.DEBUG);
    }

    public static void debug(Object obj, String msg) {
        debug(obj.getClass(), msg);
    }

    public static void debug(Object obj, Throwable e) {
        debug(obj.getClass(), e);
    }


    public static void warn(Class<?> cls, String msg) {
        writeLog(cls, msg, null, LogTypeEnum.WARN);
    }

    public static void warn(Class<?> cls, Throwable e) {
        writeLog(cls, null, e, LogTypeEnum.WARN);
    }

    public static void warn(Object obj, String msg) {
        warn(obj.getClass(), msg);
    }

    public static void warn(Object obj, Throwable e) {
        warn(obj.getClass(), e);
    }


    public static void error(Class<?> cls, String msg) {
        writeLog(cls, msg, null, LogTypeEnum.ERROR);
    }

    public static void error(Class<?> cls, Throwable e) {
        writeLog(cls, null, e, LogTypeEnum.ERROR);
    }

    public static void error(Object obj, String msg) {
        error(obj.getClass(), msg);
    }

    public static void error(Object obj, Throwable e) {
        error(obj.getClass(), e);
    }


    /**
     * 根据类型输出不同级别的对应类的日志
     *
     * @param cls         class
     * @param msg         信息
     * @param logTypeEnum 类型
     */
    private static void writeLog(Class<?> cls, String msg, Throwable throwable, LogTypeEnum logTypeEnum) {
        if (loggerMap.containsKey(cls)) {
            Logger logger = loggerMap.get(cls);
            choiseLogType(msg, throwable, logTypeEnum, logger);
            return;
        }
        Logger logger = LoggerFactory.getLogger(cls);
        loggerMap.put(cls, logger);
        choiseLogType(msg, throwable, logTypeEnum, logger);
    }


    private static void choiseLogType(String msg, Throwable throwable, LogTypeEnum logTypeEnum, Logger logger) {
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


    /**
     * 添加链路跟踪
     *
     * @param targetRequest 目标请求
     * @param serviceResult 请求返回
     */
    public static void addRequestLink(DefaultRequest targetRequest, ServiceResult<?> serviceResult) {
        LinkNode<String> serviceResultRequestLink = serviceResult.getRequestLink();
        targetRequest.setRequestLink(serviceResultRequestLink);
    }


    /**
     * 控制台输出链路跟踪
     *
     * @param requestLink 链
     */
    public static void linkPrint(LinkNode<String> requestLink) {
        if (requestLink == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        LinkNode<String> p = requestLink;
        do {
            sb.append(p.getData());
            sb.append(" --> ");
            p = p.getLinkNode();
        } while (p != null);
        LogUtil.info(LogUtil.class, String.format("链路跟踪: %s  结束!", sb.toString()));
    }
}
