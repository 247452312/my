package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 日志{@db sys_log}
 * 在这个数据库中应该保存一些正常操作等等 用来进行错误时的排查以及数据库回退的依据
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时36分
 */
public class LogEntity extends BaseVoEntity {

    /**
     * 错误信息 {@ps 不一定有, 正确的操作的错误信息默认为空}
     */
    private String exceptionDetail;

    /**
     * 代表了日志的类型
     * 详情见{@link indi.uhyils.enum_.ServiceCode}
     */
    private Integer logType;

    /**
     * 前端或外界进行api调用时的接口名称 也是dubbo泛化调用的依据 另一个需要关注的应该是{@link this#methodName}
     */
    private String interfaceName;

    /**
     * 前端或外界进行api调用时的方法名称 也是dubbo泛化调用的依据 另一个需要关注的应该是{@link this#interfaceName}
     * 商业互吹?
     */
    private String methodName;

    /**
     * 参数的json形式 这里保存着这个日志代表的参数的真实调用的属性
     */
    private String params;

    /**
     * 链路跟踪 保存了此条日志代表的操作经过的后台的操作
     */
    private String link;

    /**
     * 执行时间 此条操作花费的时间 (请求进入后台时 -> 请求从后台返回前台时)
     */
    private Long time;

    /**
     * ip 用户的ip
     */
    private String ip;

    /**
     * 此处为用户操作的token,可以使用AES {@ps 这里的AESUtil类没有在这个包中引入, 所以没有办法link }算法反向计算出用户的信息{@link indi.uhyils.pojo.model.base.TokenInfo}
     */
    private String userId;

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
