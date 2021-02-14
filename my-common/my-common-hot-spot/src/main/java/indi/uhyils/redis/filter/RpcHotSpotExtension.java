package indi.uhyils.redis.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.response.HotSpotResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.redis.hotspot.HotSpotRedisPool;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.netty.extension.step.template.ConsumerResponseObjectExtension;
import indi.uhyils.util.ObjectByteUtil;
import indi.uhyils.util.SpringUtil;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;

/**
 * 如果返回值是缓存信息,那么应该获取真实的数据 然后返回
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月14日 12时21分
 */
@RpcSpi(order = Integer.MIN_VALUE)
public class RpcHotSpotExtension implements ConsumerResponseObjectExtension {

    /**
     * 如果返回值是缓存,那么应该获取真实的数据 然后返回
     *
     * @param serviceResult
     * @param json
     * @return
     */
    private static ServiceResult getRealServiceResult(ServiceResult serviceResult, String json) {
        // 如果返回值是缓存
        if (serviceResult.getServiceCode().equals(ServiceCode.SUCCESS_REDIS.getText())) {
            JSONObject jsonObject = JSON.parseObject(json);
            Object data = jsonObject.get("data");
            HotSpotResponse hotSpotResponse = JSON.parseObject(JSON.toJSONString(data), HotSpotResponse.class);
            String key = hotSpotResponse.getKey();
            String hkey = hotSpotResponse.getHkey();
            HotSpotRedisPool bean = SpringUtil.getBean(HotSpotRedisPool.class);
            Jedis jedis = bean.getJedis();
            byte[] hget = jedis.hget(key.getBytes(StandardCharsets.UTF_8), hkey.getBytes(StandardCharsets.UTF_8));
            serviceResult = ObjectByteUtil.toObject(hget, ServiceResult.class);
        }
        return serviceResult;
    }

    @Override
    public Object doFilter(Object obj, String json) {
        return getRealServiceResult((ServiceResult) obj, json);
    }
}
