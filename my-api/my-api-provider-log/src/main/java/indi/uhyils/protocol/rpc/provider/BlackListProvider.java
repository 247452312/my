package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.BlackListDO;
import indi.uhyils.pojo.DTO.request.AddBlackIpRequest;
import indi.uhyils.pojo.DTO.request.GetLogIntervalByIpRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DefaultDTOProvider;
import java.util.ArrayList;

/**
 * (BlackList)表 服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 06时40分45秒
 */
public interface BlackListProvider extends DefaultDTOProvider<BlackListDO> {

    /**
     * 获取ip是否在爬虫黑名单中
     *
     * @param request ip
     *
     * @return
     */
    ServiceResult<Boolean> getLogIntervalByIp(GetLogIntervalByIpRequest request);

    /**
     * 获取所有的ip黑名单
     *
     * @param request 默认
     *
     * @return 所有的ip黑名单
     */
    ServiceResult<ArrayList<String>> getAllIpBlackList(DefaultRequest request);

    /**
     * 添加黑名单
     *
     * @param request ip
     *
     * @return 是否成功
     *
     * @throws InterruptedException
     */
    ServiceResult<Boolean> addBlackIp(AddBlackIpRequest request);
}
