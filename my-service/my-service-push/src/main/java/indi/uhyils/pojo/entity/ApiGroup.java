package indi.uhyils.pojo.entity;

import indi.uhyils.context.UserContext;
import indi.uhyils.pojo.DO.ApiGroupDO;
import indi.uhyils.repository.ApiGroupRepository;
import indi.uhyils.repository.ApiRepository;
import indi.uhyils.util.ApiUtils;
import indi.uhyils.util.AssertUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * api组表(ApiGroup)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 19时46分49秒
 */
public class ApiGroup extends AbstractDoEntity<ApiGroupDO> {

    private List<Api> apis;

    public ApiGroup(ApiGroupDO dO) {
        super(dO);
    }

    public ApiGroup(Long id) {
        super(id, new ApiGroupDO());
    }

    public ApiGroup(Long id, ApiGroupRepository rep) {
        super(id, new ApiGroupDO());
        completion(rep);
    }


    public void fillApi(ApiRepository apiRepository) {
        if (apis != null) {
            return;
        }

        this.apis = apiRepository.findByGroupId(id);
    }

    public String callApi() {
        AssertUtil.assertTrue(this.apis != null, "api为空");
        Map<String, String> parameter = new HashMap<>(16);
        ApiUtils.callApi(apis, UserContext.doGet(), parameter);
        String resultFormat = toDo().getResultFormat();
        resultFormat = ApiUtils.replaceString(parameter, resultFormat);
        return resultFormat;
    }

    public void removeSelf(ApiGroupRepository rep) {
        rep.remove(id);
    }

    public Integer removeApis(ApiRepository rep) {
        return rep.removeApiByGroup(this);
    }
}
