package indi.uhyils.pojo.DTO.response;

import java.io.Serializable;

/**
 * 调用算法返回值
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 17时15分
 */
public class CellAlgorithmResponse implements Serializable {

    /**
     * 算法执行结果
     */
    private Object result;


    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    public static CellAlgorithmResponse build(Object result){
        CellAlgorithmResponse build = new CellAlgorithmResponse();
        build.result = result;
        return build;

    }
}
