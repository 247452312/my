package indi.uhyils.pojo.entity.software;

import indi.uhyils.pojo.DTO.response.GetInfosResponse;
import indi.uhyils.repository.ServerRepository;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 10时30分
 */
public interface RedisSoftwareInterface {

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 连接
     */
    void link();

    /**
     * 关闭连接
     */
    void close();

    /**
     * 切换DB
     *
     * @param db
     */
    void select(Integer db);

    /**
     * 获取所有key(危险操作)
     *
     * @return
     */
    List<String> keys();

    /**
     * 初始化info
     */
    void initBaseInfo();

    /**
     * 填充服务器信息
     *
     * @param serverRepository
     */
    void findServer(ServerRepository serverRepository);

    /**
     * 获取redis的info
     */
    List<GetInfosResponse> findRedisInfo();

    /**
     * 获取redis数据库数量
     *
     * @return
     */
    Integer findRedisDb();

    /**
     * update
     *
     * @param key
     * @param value
     */
    void cas(String key, String value);

    /**
     * set
     *
     * @param key
     * @param value
     */
    void forcePut(String key, String value);

    /**
     * 更新
     *
     * @param key
     * @param value
     */
    void update(String key, String value);

    /**
     * 获取
     *
     * @param key
     *
     * @return
     */
    String get(String key);

    /**
     * 删除
     *
     * @param key
     */
    void del(String key);
}
