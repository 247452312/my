package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.ApiDao;
import indi.uhyils.dao.ApiGroupDao;
import indi.uhyils.dao.ApiSubscribeDao;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DO.ApiGroupDO;
import indi.uhyils.pojo.DO.ApiSubscribeDO;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.DTO.request.CronRequest;
import indi.uhyils.pojo.DTO.request.PushMsgToSomeoneRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.util.RpcApiUtil;
import indi.uhyils.protocol.rpc.provider.PushProvider;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.PushUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class PushProviderImpl implements PushProvider {


    @Resource
    ApiSubscribeDao apiSubscribeDao;

    @Resource
    ApiDao apiDao;
    @Resource
    ApiGroupDao apiGroupDao;

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> push(CronRequest request) throws Exception {
        Boolean result = Boolean.TRUE;
        String cron = request.getCron();
        LogUtil.info(this, "定时推送任务启动: " + cron);
        /* 获取api群 */
        List<ApiGroupDO> apiGroups = apiGroupDao.getAll();
        List<ApiDO> apis = apiDao.getAll();
        HashMap<String, ApiGroupDO> apiMaps = new HashMap<>(apiGroups.size());
        Map<Long, ApiGroupDO> collect = apiGroups.stream().collect(Collectors.toMap(BaseIdDO::getId, value -> value));
        for (ApiDO api : apis) {
            if (collect.containsKey(api.getApiGroupId())) {
                apiMaps.get(api.getApiGroupId()).getApis().add(api);
            }
        }

        /* 获取订阅 */
        List<ApiSubscribeDO> list = apiSubscribeDao.getByCron(cron);
        for (ApiSubscribeDO apiSubscribeEntity : list) {
            Long apiGroupId = apiSubscribeEntity.getApiGroupId();
            // 获取对应的api,如果没有,则跳过(可能api下线了)
            ApiGroupDO apiGroupEntity = apiMaps.get(apiGroupId);
            if (apiGroupEntity == null || apiGroupEntity.getApis().size() == 0) {
                continue;
            }

            Long userId = apiSubscribeEntity.getUserId();
            /* 获取user */
            IdRequest build = IdRequest.build(request, userId);
            ServiceResult serviceResult = (ServiceResult) RpcApiUtil.rpcApiTool("UserService", "getById", build);
            if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                return serviceResult;
            }
            Serializable data = serviceResult.getData();
            JSONObject jsonObject = (JSONObject) data;
            UserDO userEntity = jsonObject.toJavaObject(UserDO.class);
            String sendContent = PushUtils.getSendContent(userEntity, apiGroupEntity);
            switch (Objects.requireNonNull(PushTypeEnum.prase(apiSubscribeEntity.getType()))) {
                case PAGE:
                    result = !PushUtils.pagePush(userEntity, "my系统,订阅消息-" + apiGroupEntity.getName(), sendContent) ? Boolean.FALSE : result;
                    break;
                case EMAIL:
                    result = !PushUtils.emailPush(userEntity, "my系统,订阅邮件-" + apiGroupEntity.getName(), sendContent) ? Boolean.FALSE : result;
                    break;
                default:
                    break;
            }
        }
        LogUtil.info(this, "定时推送任务结束: " + cron);
        return ServiceResult.buildSuccessResult("定时任务执行成功", result);
    }

    @Override
    public ServiceResult<Boolean> pushMsgToSomeone(PushMsgToSomeoneRequest request) throws Exception {
        ServiceResult serviceResult = (ServiceResult) RpcApiUtil.rpcApiTool("UserService", "getById", IdRequest.build(request, request.getUserId()));
        if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            return serviceResult;
        }
        JSONObject jsonObject = (JSONObject) serviceResult.getData();
        UserDO userEntity = jsonObject.toJavaObject(UserDO.class);
        boolean result = Boolean.TRUE;
        switch (Objects.requireNonNull(PushTypeEnum.prase(request.getType()))) {
            case PAGE:
                result = PushUtils.pagePush(userEntity, request.getTitle(), request.getMsg());
                break;
            case EMAIL:
                result = PushUtils.emailPush(userEntity, request.getTitle(), request.getMsg());
                break;
            default:
                break;
        }
        return ServiceResult.buildSuccessResult("执行成功", result);
    }
}
