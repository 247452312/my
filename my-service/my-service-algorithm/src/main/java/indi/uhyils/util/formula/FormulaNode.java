package indi.uhyils.util.formula;

import java.util.Map;

/**
 * 公式计算节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月22日 14时24分
 */
public interface FormulaNode {


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
}
