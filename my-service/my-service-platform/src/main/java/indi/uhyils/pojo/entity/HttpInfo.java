package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.enum_.HttpReuestEnum;
import indi.uhyils.pojo.DO.HttpInfoDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.HttpInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.HttpUtil;
import indi.uhyils.util.LogUtil;
import java.util.HashMap;

/**
 * http连接表(HttpInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
public class HttpInfo extends SourceInfo<HttpInfoDO> {

    @Default
    public HttpInfo(HttpInfoDO data) {
        super(data);
    }

    public HttpInfo(Long id) {
        super(id, new HttpInfoDO());
    }

    public HttpInfo(Long id, HttpInfoRepository rep) {
        super(id, new HttpInfoDO());
        completion(rep);
    }

    public HttpInfo(Identifier id) {
        super(id, new HttpInfoDO());
    }

    @Override
    public Boolean testConnect() {
        HttpInfoDO httpInfoDO = toData();
        Asserts.assertTrue(httpInfoDO != null);
        String requestType = httpInfoDO.getRequestType();
        HttpReuestEnum param = HttpReuestEnum.parse(requestType);
        switch (param) {
            case GET:
                try {
                    HttpUtil.sendHttpGet(httpInfoDO.getUrl(), new HashMap<>());
                } catch (Exception e) {
                    LogUtil.error(e, "尝试连接失败");
                    return false;
                }
                return true;
            case POST:
                try {
                    HttpUtil.sendHttpPost(httpInfoDO.getUrl(), new HashMap<>(), new HashMap<>());
                } catch (Exception e) {
                    LogUtil.error(e, "尝试连接失败");
                    return false;
                }
                return true;
            default:
                Asserts.assertTrue(false, "暂不支持的请求类型");
        }
        return false;
    }

}
