package indi.uhyils.util.formula;

/**
 * 公式
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月22日 14时19分
 */
public interface Formula {


    /**
     * 获取公式
     *
     * @return
     */
    String getFormula();

    /**
     * 求导
     *
     * @return
     */
    Formula derivation();


}
