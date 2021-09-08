package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * api组表(ApiGroup)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分48秒
 */
public class ApiGroupDO extends BaseDO {

    private static final long serialVersionUID = -65481279979947245L;


    private String name;

    private String resultFormat;

    private Boolean subscribe;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getResultFormat() {
        return resultFormat;
    }

    public void setResultFormat(String resultFormat) {
        this.resultFormat = resultFormat;
    }


    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

}