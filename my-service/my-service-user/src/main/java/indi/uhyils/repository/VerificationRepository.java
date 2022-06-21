package indi.uhyils.repository;

import indi.uhyils.pojo.entity.Verification;
import indi.uhyils.repository.base.BaseRepository;

/**
 * 角色仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时46分
 */
public interface VerificationRepository extends BaseRepository {


    /**
     * 保存验证码到缓存
     *
     * @param key
     * @param code
     * @param time
     */
    void saveVerificationToCache(String key, String code, Integer time);

    /**
     * 验证是否正确
     *
     * @param verification
     *
     * @return
     */
    Boolean verification(Verification verification);

    /**
     * 情况此缓存
     *
     * @param verification
     */
    void cleanCache(Verification verification);
}
