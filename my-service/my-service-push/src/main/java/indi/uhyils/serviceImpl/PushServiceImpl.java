package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.dao.ApiDao;
import indi.uhyils.dao.ApiGroupDao;
import indi.uhyils.dao.ApiSubscribeDao;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.model.ApiGroupEntity;
import indi.uhyils.pojo.model.ApiSubscribeEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.model.base.BaseIdEntity;
import indi.uhyils.pojo.request.CronRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.PushService;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.PushUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@Service(group = "${spring.profiles.active}")
public class PushServiceImpl implements PushService {


    @Autowired
    ApiSubscribeDao apiSubscribeDao;

    @Autowired
    ApiDao apiDao;
    @Autowired
    ApiGroupDao apiGroupDao;

    @Override
    public ServiceResult<Boolean> push(CronRequest request) {
        Boolean result = true;
        String cron = request.getCron();
        LogUtil.info(this, "定时推送任务启动: " + cron);
        /* 获取api群 */
        List<ApiGroupEntity> apiGroups = apiGroupDao.getAll();
        List<ApiEntity> apis = apiDao.getAll();
        HashMap<String, ApiGroupEntity> apiMaps = new HashMap<>(apiGroups.size());
        Map<String, ApiGroupEntity> collect = apiGroups.stream().collect(Collectors.toMap(BaseIdEntity::getId, value -> value));
        for (ApiEntity api : apis) {
            if (collect.containsKey(api.getApiGroupId())) {
                apiMaps.get(api.getApiGroupId()).getApis().add(api);
            }
        }

        /* 获取订阅 */
        List<ApiSubscribeEntity> list = apiSubscribeDao.getByCron(cron);
        for (ApiSubscribeEntity apiSubscribeEntity : list) {
            String apiGroupId = apiSubscribeEntity.getApiGroupId();
            // 获取对应的api,如果没有,则跳过(可能api下线了)
            ApiGroupEntity apiGroupEntity = apiMaps.get(apiGroupId);
            if (apiGroupEntity == null || apiGroupEntity.getApis().size() == 0) {
                continue;
            }

            String userId = apiSubscribeEntity.getUserId();
            /* 获取user */
            List args = new ArrayList();
            IdRequest build = IdRequest.build(userId);
            build.setUser(request.getUser());
            args.add(build);
            ServiceResult serviceResult = DubboApiUtil.dubboApiTool("UserService", "getById", args, request);
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
}
