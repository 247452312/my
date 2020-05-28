package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.model.Arg;

import java.util.List;

/**
 * 查询参数 根据自定义参数获取数据
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时54分
 */
public class ArgsRequest extends DefaultPageRequest {

    /**
     * 查询参数
     */
    private List<Arg> args;


    public List<Arg> getArgs() {
        return args;
    }

    public void setArgs(List<Arg> args) {
        this.args = args;
    }
}
