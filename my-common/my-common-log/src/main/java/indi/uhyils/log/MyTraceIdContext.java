package indi.uhyils.log;

import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * traceId生成的地方
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月22日 13时05分
 */
public class MyTraceIdContext {


    /**
     * 保存traceId的地方
     */
    private static final ThreadLocal<Long> thraceId = new ThreadLocal<>();

    /**
     * 保存上一次调用链顺序的地方
     */
    private static final ThreadLocal<List<Integer>> rpcId = new ThreadLocal<>();

    /**
     * 这一次调用的RPCid
     */
    private static final ThreadLocal<AtomicInteger> thisRpcId = new ThreadLocal<>();

    /**
     * 分隔符
     */
    private static final String PIPE_SYMBOL = "|";

    /**
     * 项目名称
     */
    private static String projectName = SpringUtil.getProperty("spring.application.name");

    public static String getProjectName() {
        return projectName;
    }

    public static void setProjectName(String projectName) {
        MyTraceIdContext.projectName = projectName;
    }

    /**
     * 获取日志详情
     *
     * @param logTypeEnum
     * @param startTime
     * @param timeConsuming
     * @param otherInfo
     *
     * @return
     */
    public static String getLogInfo(LogTypeEnum logTypeEnum, long startTime, long timeConsuming, String... otherInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(getThraceId());
        sb.append(PIPE_SYMBOL);
        sb.append(startTime);
        sb.append(PIPE_SYMBOL);
        sb.append(logTypeEnum.getCode());
        sb.append(PIPE_SYMBOL);
        sb.append(getAndAddRpcId());
        sb.append(PIPE_SYMBOL);
        sb.append(projectName);
        sb.append(PIPE_SYMBOL);
        sb.append(timeConsuming);
        sb.append(PIPE_SYMBOL);
        for (String info : otherInfo) {
            sb.append(info);
            sb.append(PIPE_SYMBOL);
        }
        String resultNoHash = sb.toString();
        sb.append("^");
        int hash = resultNoHash.hashCode() & 0xffffff;
        sb.append(Integer.toString(hash, 16));
        return sb.toString();
    }

    /**
     * 打印链路跟踪日志
     *
     * @param logTypeEnum
     * @param startTime
     * @param timeConsuming
     * @param otherInfo
     *
     * @return
     */
    public static void printLogInfo(LogTypeEnum logTypeEnum, long startTime, long timeConsuming, String... otherInfo) {
        String logInfo = getLogInfo(logTypeEnum, startTime, timeConsuming, otherInfo);
        LogUtil.link(logInfo);
    }

    /**
     * 获取ThraceId
     *
     * @return
     */
    public static Long getThraceId() {
        if (thraceId.get() == null) {
            synchronized (MyTraceIdContext.class) {
                if (thraceId.get() == null) {
                    try {
                        IdUtil bean = SpringUtil.getBean(IdUtil.class);
                        if (bean == null) {
                            bean = new IdUtil();
                            bean.setCode(1L);
                        }
                        thraceId.set(bean.newId());
                    } catch (IdGenerationException | InterruptedException e) {
                        LogUtil.error(MyTraceIdContext.class, e);
                    }
                }
            }
        }

        return thraceId.get();
    }

    /**
     * 设置ThraceId
     *
     * @param thraceId
     */
    public static void setThraceId(Long thraceId) {
        MyTraceIdContext.thraceId.set(thraceId);
    }

    /**
     * 获取rpcId
     *
     * @return
     */
    public static String getAndAddRpcId() {
        if (rpcId.get() == null) {
            synchronized (MyTraceIdContext.class) {
                if (rpcId.get() == null) {
                    ArrayList<Integer> value = new ArrayList<>();
                    value.add(1);
                    rpcId.set(value);
                    thisRpcId.set(new AtomicInteger(1));
                }
            }
        }
        List<Integer> lastRpcIds = rpcId.get();
        int rpcId = thisRpcId.get().getAndAdd(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastRpcIds.size(); i++) {
            sb.append(lastRpcIds.get(i));
            sb.append(".");
        }
        sb.append(rpcId);
        return sb.toString();
    }

    /**
     * 设置rpcId
     *
     * @param lastRpcIds
     */
    public static void setRpcId(List<Integer> lastRpcIds) {
        rpcId.set(lastRpcIds);
        thisRpcId.set(new AtomicInteger(1));
    }

    public static void clean() {
        thraceId.remove();
        rpcId.remove();
        thisRpcId.remove();
    }
}
