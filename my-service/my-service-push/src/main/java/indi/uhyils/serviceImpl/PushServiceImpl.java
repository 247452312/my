package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.ApiDao;
import indi.uhyils.dao.ApiGroupDao;
import indi.uhyils.dao.ApiSubscribeDao;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.model.ApiGroupEntity;
import indi.uhyils.pojo.model.ApiSubscribeEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.model.base.BaseIdEntity;
import indi.uhyils.pojo.request.CronRequest;
import indi.uhyils.pojo.request.PushMsgToSomeoneRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.rpc.spring.util.RpcApiUtil;
import indi.uhyils.service.PushService;
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
public class PushServiceImpl implements PushService {


    @Resource
    ApiSubscribeDao apiSubscribeDao;

    @Resource
    ApiDao apiDao;
    @Resource
    ApiGroupDao apiGroupDao;

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> push(CronRequest request) throws Exception {
        Boolean result = true;
        String cron = request.getCron();
        LogUtil.info(this, "定时推送任务启动: " + cron);
        /* 获取api群 */
        List<ApiGroupEntity> apiGroups = apiGroupDao.selectList(null);
        List<ApiEntity> apis = apiDao.selectList(null);
        HashMap<String, ApiGroupEntity> apiMaps = new HashMap<>(apiGroups.size());
        Map<Long, ApiGroupEntity> collect = apiGroups.stream().collect(Collectors.toMap(BaseIdEntity::getId, value -> value));
        for (ApiEntity api : apis) {
            if (collect.containsKey(api.getApiGroupId())) {
                apiMaps.get(api.getApiGroupId()).getApis().add(api);
            }
        }

        /* 获取订阅 */
        List<ApiSubscribeEntity> list = apiSubscribeDao.getByCron(cron);
        for (ApiSubscribeEntity apiSubscribeEntity : list) {
            Long apiGroupId = apiSubscribeEntity.getApiGroupId();
            // 获取对应的api,如果没有,则跳过(可能api下线了)
            ApiGroupEntity apiGroupEntity = apiMaps.get(apiGroupId);
            if (apiGroupEntity == null || apiGroupEntity.getApis().size() == 0) {
                continue;
            }

            Long userId = apiSubscribeEntity.getUserId();
            /* 获取user */
            IdRequest build = IdRequest.build(request, userId);
            ServiceResult serviceResult = RpcApiUtil.rpcApiTool("UserService", "getById", build);
            if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                return serviceResult;
            }
            Serializable data = serviceResult.getData();
            JSONObject jsonObject = (JSONObject) data;
            UserEntity userEntity = jsonObject.toJavaObject(UserEntity.class);
            String sendContent = PushUtils.getSendContent(userEntity, apiGroupEntity);
            switch (Objects.requireNonNull(PushTypeEnum.prase(apiSubscribeEntity.getType()))) {
                case PAGE:
                    result = !PushUtils.pagePush(userEntity, "my系统,订阅消息-" + apiGroupEntity.getName(), sendContent) ? false : result;
                    break;
                case EMAIL:
                    result = !PushUtils.emailPush(userEntity, "my系统,订阅邮件-" + apiGroupEntity.getName(), sendContent) ? false : result;
                    break;
                default:
                    break;
            }
        }
        LogUtil.info(this, "定时推送任务结束: " + cron);
        return ServiceResult.buildSuccessResult("定时任务执行成功", result, request);
    }

    @Override
    public ServiceResult<Boolean> pushMsgToSomeone(PushMsgToSomeoneRequest request) throws Exception {
        ServiceResult serviceResult = RpcApiUtil.rpcApiTool("UserService", "getById", IdRequest.build(request, request.getUserId()));
        if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            return serviceResult;
        }
        JSONObject jsonObject = (JSONObject) serviceResult.getData();
        UserEntity userEntity = jsonObject.toJavaObject(UserEntity.class);
        boolean result = true;
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
        return ServiceResult.buildSuccessResult("执行成功", result, request);
    }
}
