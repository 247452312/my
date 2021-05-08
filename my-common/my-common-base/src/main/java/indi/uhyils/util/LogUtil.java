package indi.uhyils.util;


import com.alibaba.fastjson.JSON;
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
    private static Map<String, Logger> loggerMap = new HashMap<>();

    public static Boolean isDebugEnabled(Object obj) {
        if (obj == null) {
            return false;
        }
        return isDebugEnabled(obj.getClass());
    }

    public static Boolean isDebugEnabled(Class obj) {
        if (obj == null) {
            return false;
        }
        String simpleName = obj.getName();
        if (!loggerMap.containsKey(simpleName)) {
            Logger logger = LoggerFactory.getLogger(simpleName);
            loggerMap.put(simpleName, logger);
        }
        return loggerMap.get(simpleName).isDebugEnabled();
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
     * 根据类型输出不同级别的对应类的日志
     *
     * @param className   名称,simpleName
     * @param msg         信息
     * @param logTypeEnum 类型
     */
    private static void writeLog(String className, String msg, Throwable throwable, LogTypeEnum logTypeEnum) {
        if (loggerMap.containsKey(className)) {
            Logger logger = loggerMap.get(className);
            choiseLogType(msg, throwable, logTypeEnum, logger);
            return;
        }
        Logger logger = LoggerFactory.getLogger(className);
        loggerMap.put(className, logger);
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
