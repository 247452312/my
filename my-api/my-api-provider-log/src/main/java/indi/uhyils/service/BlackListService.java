package indi.uhyils.service;

import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.pojo.model.BlackListEntity;
import indi.uhyils.pojo.request.AddBlackIpRequest;
import indi.uhyils.pojo.request.GetLogIntervalByIpRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

import java.util.ArrayList;

/**
 * (BlackList)表 服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 06时40分45秒
 */
public interface BlackListService extends DefaultEntityService<BlackListEntity> {

    /**
     * 获取ip是否在爬虫黑名单中
     *
     * @param request ip
     * @return
     */
    ServiceResult<Boolean> getLogIntervalByIp(GetLogIntervalByIpRequest request);

    /**
     * 获取所有的ip黑名单
     *
     * @param request 默认
     * @return 所有的ip黑名单
     */
    ServiceResult<ArrayList<String>> getAllIpBlackList(DefaultRequest request);

    /**
     * 添加黑名单
     *
     * @param request ip
     * @return 是否成功
     * @throws IdGenerationException
     * @throws InterruptedException
     */
    ServiceResult<Boolean> addBlackIp(AddBlackIpRequest request) throws IdGenerationException, InterruptedException;
}
