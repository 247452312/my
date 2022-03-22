package indi.uhyils.protocol.mysql.handler;

import indi.uhyils.enums.MysqlHandlerStatusEnum;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.util.mysql.pojo.entity.PrepareInfo;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mysql此次tcp请求的连接信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 19时24分
 */
public class MysqlTcpInfo implements Serializable {

    /**
     * 此次tcp预处理语句的本地缓存
     */
    private Map<Long, PrepareInfo> prepareCache = new ConcurrentHashMap<>();

    /**
     * 客户端连接地址
     */
    private InetSocketAddress localAddress;

    /**
     * 默认clientIndex为-1
     */
    private long index = -1;

    /**
     * 创建完成之后默认是初见状态
     */
    private MysqlHandlerStatusEnum status = MysqlHandlerStatusEnum.FIRST_SIGHT;

    /**
     * 此次登录的用户
     */
    private UserDTO userDTO;


    public PrepareInfo getPrepareCache(long key) {
        return prepareCache.get(key);
    }

    public void setPrepareCache(long key, PrepareInfo prepareInfo) {
        this.prepareCache.put(key, prepareInfo);
    }

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(InetSocketAddress localAddress) {
        this.localAddress = localAddress;
    }

    public long index() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public MysqlHandlerStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MysqlHandlerStatusEnum status) {
        this.status = status;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public MysqlHandlerStatusEnum getAndIncrementStatus() {
        switch (getStatus()) {
            case FIRST_SIGHT:
                setStatus(MysqlHandlerStatusEnum.PASSED);
                break;
            case OVER:
            case PASSED:
            default:
                break;
        }
        return status;
    }
}
