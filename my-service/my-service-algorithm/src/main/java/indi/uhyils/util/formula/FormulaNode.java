package indi.uhyils.util.formula;

import java.util.List;
import java.util.Map;

/**
 * 公式计算节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月22日 14时24分
 */
public interface FormulaNode {

    /**
     * 变量前缀
     */
    String VAR_NAME_PREX = "$";

    /**
     * 符号
     */
    String[] SPECIAL_OPERATOR = new String[]{"\\+", "-", "\\*", "/", "\\^"};

    /**
     * 方法列表
     */
    String[] FUNCTION_NAME = new String[]{"sin", "cos", "tan", "abs", "sqrt", "log", "ln"};

    /**
     * 0值
     */
    String ZERO = "0";

    /**
     * 获取下一层节点
     *
     * @return
     */
    Map<String, FormulaNode> lastNodes();

    /**
     * 获取这个公式的节点
     *
     * @return
     */
    String getNodeFormula();

    /**
     * 获取这个公式融合下层的公式的节点
     *
     * @return
     */
    String getFormula();

    /**
     * 求导
     *
     * @param varName 待求导的变量
     *
     * @return
     */
    String derivation(String varName);

    /**
     * 是否包含指定符号
     *
     * @param symbol 符号
     *
     * @return 是否包含
     */
    Boolean contains(String symbol);


    /**
     * 获取节点以及下层所有的变量名称(不包含自定义变量)
     *
     * @return
     */
    List<String> getAllVarName();
}
