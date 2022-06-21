package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 算法表(Algorithm)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时51分25秒
 */
@TableName(value = "sys_algorithm")
public class AlgorithmDO extends BaseDO {

    private static final long serialVersionUID = -49241463737845804L;


    /**
     * 算法名称
     */
    @TableField
    private String name;

    /**
     * 是否需要文件(如果为true,则需要填写模型所在地)
     */
    @TableField
    private Boolean needFile;

    /**
     * 模型所在地
     */
    @TableField
    private String modelFilePath;

    /**
     * 代码语言类型
     */
    @TableField
    private Integer languageType;

    /**
     * 算法体
     */
    @TableField
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
