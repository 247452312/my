package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;
import java.util.ArrayList;
import java.util.List;

/**
 * api群组
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月30日 07时54分
 */
public class ApiGroupDO extends BaseDoDO {

    /**
     * 名称
     */
    private String name;

    /**
     * 是否可订阅
     */
    private Boolean subscribe;


    /**
     * 输出格式
     */
    private String resultFormat;

    /**
     * 下属api
     */
    private List<ApiDO> apis = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getResultFormat() {
        return resultFormat;
    }

    public void setResultFormat(String resultFormat) {
        this.resultFormat = resultFormat;
    }

    public List<ApiDO> getApis() {
        return apis;
    }

    public void setApis(List<ApiDO> apis) {
        this.apis = apis;
    }
}
