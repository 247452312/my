package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 算法表(Algorithm)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分09秒
 */
public class AlgorithmDTO extends IdDTO {

    private static final long serialVersionUID = 839502688584316497L;


    /**
     * 算法名称
     */
    private String name;

    /**
     * 模型文件所在地
     */
    private String modelFilePath;

    /**
     * 是否需要文件
     */
    private Boolean needFile;

    /**
     * 代码语言类型
     */
    private Integer languageType;

    /**
     * 算法体
     */
    private String body;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    public Boolean getNeedFile() {
        return needFile;
    }

    public void setNeedFile(Boolean needFile) {
        this.needFile = needFile;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getLanguageType() {
        return languageType;
    }

    public void setLanguageType(Integer languageType) {
        this.languageType = languageType;
    }
}
