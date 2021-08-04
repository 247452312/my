package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.content.Content;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.AddOrEditMethodDisable;
import indi.uhyils.pojo.model.MethodDisableInfo;
import indi.uhyils.pojo.request.DelMethodDisableRequest;
import indi.uhyils.pojo.request.GetMethodDisableRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.redis.Redisable;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ServiceControlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
@ReadWriteMark
public class ServiceControlServiceImpl implements ServiceControlService {

    /**
     * 接口名称包分隔符
     */
    public static final String INTERFACE_NAME_PACKAGE_SEPARATOR = ".";
    /**
     * 接口连接方法的分隔符
     */
    private static final String METHOD_LINK_CLASS_SYMBOL = "#";
    @Autowired
    private RedisPoolHandle redisPoolHandle;

    @Override
    public ServiceResult<Boolean> getMethodDisable(GetMethodDisableRequest request) {

        String className = request.getClassName();
        if (!className.contains(INTERFACE_NAME_PACKAGE_SEPARATOR)) {
            className = Content.SERVICE_PACKAGE_PREFIX + className;
        }
        Boolean enable = redisPoolHandle.checkMethodDisable(className, className + METHOD_LINK_CLASS_SYMBOL + request.getMethodName(), request.getMethodType());
        return ServiceResult.buildSuccessResult(enable);
    }

    @Override
    public ServiceResult<ArrayList<MethodDisableInfo>> getAllMethodDisable(DefaultRequest request) {
        Redisable jedis = redisPoolHandle.getJedis();
        try {
            Boolean exists = jedis.exists(Content.SERVICE_USEABLE_SWITCH);
            // 如果不存在禁用对应的key
            if (!exists) {
                return ServiceResult.buildSuccessResult(new ArrayList<>());
            }
            Map<String, String> allMethodDisable = jedis.hgetAll(Content.SERVICE_USEABLE_SWITCH);
            ArrayList<MethodDisableInfo> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : allMethodDisable.entrySet()) {
                String key = entry.getKey();
                Integer value = Integer.parseInt(entry.getValue());
                // 如果包含连接符说明是方法
                if (key.contains(METHOD_LINK_CLASS_SYMBOL)) {
                    String[] split = key.split(METHOD_LINK_CLASS_SYMBOL);
                    list.add(MethodDisableInfo.build(split[0], split[1], value));
                } else {
                    list.add(MethodDisableInfo.build(key, null, value));
                }
            }
            return ServiceResult.buildSuccessResult(list);
        } finally {
            jedis.close();
        }
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> addOrEditMethodDisable(ObjRequest<AddOrEditMethodDisable> request) {
        Redisable jedis = redisPoolHandle.getJedis();
        AddOrEditMethodDisable data = request.getData();
        try {
            String className = data.getClassName();
            if (className != null && !className.contains(INTERFACE_NAME_PACKAGE_SEPARATOR)) {
                className = Content.SERVICE_PACKAGE_PREFIX + className;
            }
            String methodName = data.getMethodName();
            // 这是一个方法
            if (StringUtils.isNotBlank(methodName)) {
                String key = className + METHOD_LINK_CLASS_SYMBOL + methodName;
                Integer type = data.getType();
                jedis.hset(Content.SERVICE_USEABLE_SWITCH, key, type.toString());
            } else {
                jedis.hset(Content.SERVICE_USEABLE_SWITCH, className, data.getType().toString());
            }
            return ServiceResult.buildSuccessResult(true);
        } finally {
            jedis.close();
        }
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> delMethodDisable(DelMethodDisableRequest request) {
        Redisable jedis = redisPoolHandle.getJedis();
        try {
            String key;
            String className = request.getClassName();
            if (className != null && !className.contains(INTERFACE_NAME_PACKAGE_SEPARATOR)) {
                key = Content.SERVICE_PACKAGE_PREFIX + className;
            } else {
                key = className;
            }
            String methodName = request.getMethodName();

            if (methodName != null) {
                key = key + METHOD_LINK_CLASS_SYMBOL + methodName;
            }
            jedis.hdel(Content.SERVICE_USEABLE_SWITCH, key);
            return ServiceResult.buildSuccessResult(true);
        } finally {
            jedis.close();
        }
    }
}
