package indi.uhyils.ast.model;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月21日 13时35分
 */
public class ParseMethodNodeExcludeModel implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 排除class名称
     */
    private List<Function<String, Boolean>> excludeClassNames;

    /**
     * 排除的method名称
     */
    private List<Function<String, Boolean>> excludeMethodNames;

    public static ParseMethodNodeExcludeModel build(List<Function<String, Boolean>> excludeClassNames, List<Function<String, Boolean>> excludeMethodNames) {
        ParseMethodNodeExcludeModel build = new ParseMethodNodeExcludeModel();
        build.excludeClassNames = excludeClassNames;
        build.excludeMethodNames = excludeMethodNames;
        return build;
    }

    public List<Function<String, Boolean>> getExcludeClassNames() {
        return excludeClassNames;
    }

    public void setExcludeClassNames(List<Function<String, Boolean>> excludeClassNames) {
        this.excludeClassNames = excludeClassNames;
    }

    public List<Function<String, Boolean>> getExcludeMethodNames() {
        return excludeMethodNames;
    }

    public void setExcludeMethodNames(List<Function<String, Boolean>> excludeMethodNames) {
        this.excludeMethodNames = excludeMethodNames;
    }
}
