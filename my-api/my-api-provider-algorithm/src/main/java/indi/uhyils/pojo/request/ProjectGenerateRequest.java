package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

import java.util.List;

/**
 * 项目生成请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 07时04分
 */
public class ProjectGenerateRequest extends DefaultRequest {

    /**
     * 多个数据库信息
     */
    private List<DbInformation> list;


    public List<DbInformation> getList() {
        return list;
    }

    public void setList(List<DbInformation> list) {
        this.list = list;
    }
}
