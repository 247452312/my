package indi.uhyils.mq.pojo.mqinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * jvm刚刚启动时发送给日志处理器的信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月18日 07时43分
 */
public class JvmStartInfo implements Serializable {

    /**
     * 收集信息,如果发送失败时也能记录之前的状态信息
     */
    private static volatile List<JvmStatusInfo> statusInfos = new ArrayList<>();

    /**
     * 分配给jvm的总内存
     */
    private Double jvmTotalMem;

    /**
     * 堆最大内存
     */
    private Double heapTotalMem;

    /**
     * 堆初始大小
     */
    private Double heapInitMem;

    /**
     * 非堆最大内存
     */
    private Double noHeapTotalMem;

    /**
     * 非堆初始大小
     */
    private Double noHeapInitMem;

    /**
     * 唯一标示
     */
    private JvmUniqueMark jvmUniqueMark;

    /**
     * 第一次发送携带此时的jvm信息
     */
    private List<JvmStatusInfo> jvmStatusInfos;

    public static List<JvmStatusInfo> getStatusInfos() {
        return statusInfos;
    }

    public static void setStatusInfos(List<JvmStatusInfo> statusInfos) {
        JvmStartInfo.statusInfos = statusInfos;
    }

    public Double getJvmTotalMem() {
        return jvmTotalMem;
    }

    public void setJvmTotalMem(Double jvmTotalMem) {
        this.jvmTotalMem = jvmTotalMem;
    }

    public Double getHeapTotalMem() {
        return heapTotalMem;
    }

    public void setHeapTotalMem(Double heapTotalMem) {
        this.heapTotalMem = heapTotalMem;
    }

    public Double getNoHeapTotalMem() {
        return noHeapTotalMem;
    }

    public void setNoHeapTotalMem(Double noHeapTotalMem) {
        this.noHeapTotalMem = noHeapTotalMem;
    }

    public Double getHeapInitMem() {
        return heapInitMem;
    }

    public void setHeapInitMem(Double heapInitMem) {
        this.heapInitMem = heapInitMem;
    }

    public Double getNoHeapInitMem() {
        return noHeapInitMem;
    }

    public void setNoHeapInitMem(Double noHeapInitMem) {
        this.noHeapInitMem = noHeapInitMem;
    }

    public JvmUniqueMark getJvmUniqueMark() {
        return jvmUniqueMark;
    }

    public void setJvmUniqueMark(JvmUniqueMark jvmUniqueMark) {
        this.jvmUniqueMark = jvmUniqueMark;
    }

    public List<JvmStatusInfo> getJvmStatusInfos() {
        return jvmStatusInfos;
    }

    public void setJvmStatusInfos(List<JvmStatusInfo> jvmStatusInfos) {
        this.jvmStatusInfos = jvmStatusInfos;
    }
}
