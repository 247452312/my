package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.BlackListDTO;
import indi.uhyils.pojo.DTO.request.AddBlackIpRequest;
import indi.uhyils.pojo.DTO.request.GetLogIntervalByIpQuery;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

/**
 * (BlackList)表 服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 06时40分45秒
 */
public interface BlackListProvider extends DTOProvider<BlackListDTO> {

    /**
     * 获取ip是否在爬虫黑名单中
     *
     * @param request ip
     *
     * @return
     */
    Boolean getLogIntervalByIp(GetLogIntervalByIpQuery request);

    /**
     * 获取所有的ip黑名单
     *
     * @param request 默认
     *
     * @return 所有的ip黑名单
     */
    List<String> getAllIpBlackList(DefaultCQE request);

    /**
     * 添加黑名单
     *
     * @param request ip
     *
     * @return 是否成功
     *
     * @throws InterruptedException
     */
    Boolean addBlackIp(AddBlackIpRequest request);
}
