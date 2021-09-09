package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.context.MyContext;
import indi.uhyils.enum_.MethodTypeEnum;
import indi.uhyils.pojo.DTO.MethodDisableDTO;
import indi.uhyils.pojo.entity.MethodDisable;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.redis.Redisable;
import indi.uhyils.repository.ServiceControlRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 数据字典(Dict)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分37秒
 */
@Repository
public class ServiceControlRepositoryImpl implements ServiceControlRepository {


    @Autowired
    private RedisPoolHandle redisPoolHandle;

    @Override
    public Boolean checkMethodDisable(String fullMethodName) {
        try (Redisable jedis = redisPoolHandle.getJedis()) {
            Boolean exists = jedis.exists(MyContext.SERVICE_USEABLE_SWITCH);
            // 如果不存在这个hash串,则全部放行
            if (!exists) {
                return Boolean.TRUE;
            }
            String methodPower = jedis.hget(MyContext.SERVICE_USEABLE_SWITCH, fullMethodName);
            if (methodPower != null) {
                //如果是0返回不禁用.如果不是0返回禁用
                return 0 == Integer.parseInt(methodPower);
            } else {
                return null;
            }
        }
    }

    @Override
    public Boolean checkClassDisable(String className, MethodTypeEnum methodType) {
        if (methodType == null) {
            return true;
        }
        try (Redisable jedis = redisPoolHandle.getJedis()) {
            String classPower = jedis.hget(MyContext.SERVICE_USEABLE_SWITCH, className);
            if (classPower != null) {
                int classPowerInt = Integer.parseInt(classPower);
                //3代表禁用全部
                if (classPowerInt == 3) {
                    return Boolean.FALSE;
                } else if (classPowerInt == 0) {
                    // 0代表全部启用
                    return Boolean.TRUE;
                } else {
                    // 如果禁用接口模式对应传入的模式,就返回禁用
                    if (MethodTypeEnum.parse(classPowerInt).equals(methodType)) {
                        return Boolean.FALSE;
                    }
                    return Boolean.TRUE;
                }
            }
        }
        // 如果没有配置
        return Boolean.TRUE;
    }

    @Override
    public List<MethodDisableDTO> findAll() {
        try (Redisable jedis = redisPoolHandle.getJedis()) {
            Boolean exists = jedis.exists(MyContext.SERVICE_USEABLE_SWITCH);
            // 如果不存在禁用对应的key
            if (!exists) {
                return new ArrayList<>();
            }
            Map<String, String> allMethodDisable = jedis.hgetAll(MyContext.SERVICE_USEABLE_SWITCH);
            List<MethodDisableDTO> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : allMethodDisable.entrySet()) {
                String key = entry.getKey();
                Integer value = Integer.parseInt(entry.getValue());
                // 如果包含连接符说明是方法
                if (key.contains(MethodDisable.METHOD_LINK_CLASS_SYMBOL)) {
                    String[] split = key.split(MethodDisable.METHOD_LINK_CLASS_SYMBOL);
                    list.add(MethodDisableDTO.build(split[0], split[1], value));
                } else {
                    list.add(MethodDisableDTO.build(key, null, value));
                }
            }
            return list;
        }
    }

    @Override
    public void saveMethodDisable(MethodDisable methodDisable) {
        try (Redisable jedis = redisPoolHandle.getJedis()) {
            MethodDisableDTO data = methodDisable.toDTO();
            String className = data.getClassName();
            if (className != null && !className.contains(MethodDisable.INTERFACE_NAME_PACKAGE_SEPARATOR)) {
                className = MyContext.SERVICE_PACKAGE_PREFIX + className;
            }
            String methodName = data.getMethodName();
            // 这是一个方法
            if (StringUtils.isNotBlank(methodName)) {
                String key = className + MethodDisable.METHOD_LINK_CLASS_SYMBOL + methodName;
                Integer type = data.getDisableType();
                type = type == null ? 0 : type;
                jedis.hset(MyContext.SERVICE_USEABLE_SWITCH, key, type.toString());
            } else {
                jedis.hset(MyContext.SERVICE_USEABLE_SWITCH, className, data.getDisableType().toString());
            }
        }
    }

    @Override
    public void delMethodDisable(MethodDisable methodDisable) {
        MethodDisableDTO methodDisableDTO = methodDisable.toDTO();
        try (Redisable jedis = redisPoolHandle.getJedis()) {
            Boolean exists = jedis.exists(MyContext.SERVICE_USEABLE_SWITCH);
            // 如果不存在禁用对应的key
            if (!exists) {
                return;
            }
            String key;
            String className = methodDisableDTO.getClassName();
            if (className != null && !className.contains(MethodDisable.INTERFACE_NAME_PACKAGE_SEPARATOR)) {
                key = MyContext.SERVICE_PACKAGE_PREFIX + className;
            } else {
                key = className;
            }
            String methodName = methodDisableDTO.getMethodName();
            if (methodName != null) {
                key = methodDisable.toInterfaceMethodName();
            }
            jedis.hdel(MyContext.SERVICE_USEABLE_SWITCH, key);
        }
    }
}
