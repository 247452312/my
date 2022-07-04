package indi.uhyils.util.formula.enums;

import com.google.common.collect.Maps.EntryTransformer;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.formula.FormulaNode;
import indi.uhyils.util.formula.FormulaNodeFactory;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月24日 09时40分
 */
public enum FunctionEnum {
    /**
     *
     */
    SIN("sin", (formula, varName) -> {
        formula = removeFunctionName(formula, "sin");
        FormulaNode node = FormulaNodeFactory.create(formula);
        String derivation = node.derivation(varName);
        if (Objects.equals(derivation, FormulaNode.ZERO)) {
            return FormulaNode.ZERO;
        }
        return "(" + derivation + ") * cos(" + formula + ")";
    }),
    COS("cos", (formula, varName) -> {
        formula = removeFunctionName(formula, "cos");
        FormulaNode node = FormulaNodeFactory.create(formula);
        String derivation = node.derivation(varName);
        if (Objects.equals(derivation, FormulaNode.ZERO)) {
            return FormulaNode.ZERO;
        }
        return "(" + derivation + ") * sin(" + formula + ")";
    }),
    TAN("tan", (formula, varName) -> {
        formula = removeFunctionName(formula, "tan");
        FormulaNode node = FormulaNodeFactory.create(formula);
        String derivation = node.derivation(varName);
        if (Objects.equals(derivation, FormulaNode.ZERO)) {
            return FormulaNode.ZERO;
        }
        return "(" + derivation + ") * (1-(tan(" + formula + ")^2))";
    }),
    ABS("abs", (formula, varName) -> {
        Asserts.throwException("绝对值暂不支持求导");
        return formula;
    }),
    SQRT("sqrt", (formula, varName) -> {
        formula = removeFunctionName(formula, "sqrt");
        FormulaNode node = FormulaNodeFactory.create(formula);
        String derivation = node.derivation(varName);
        if (Objects.equals(derivation, FormulaNode.ZERO)) {
            return FormulaNode.ZERO;
        }
        return "(" + derivation + ") * (1/2*" + formula + "^(-1/2))";
    }),
    LOG("log", (formula, varName) -> {
        formula = removeFunctionName(formula, "sqrt");
        String[] split = formula.split(",");
        Asserts.assertTrue(split.length == 2, "log需要两个参数, 第一个参数为底数,第二个参数为真数,例:log(a,x) 代表以a为底x的对数");
        // 底数
        String fx = split[0];
        // 真数
        String gx = split[1];
        // 底数求导
        String fpx = FormulaNodeFactory.create(fx).derivation(varName);
        // 真数求导
        String gpx = FormulaNodeFactory.create(gx).derivation(varName);
        return String.format("((log(%s) * %s)/%s - (%s * log(%s))/%s)/(log(%s)^2)", fx, gpx, gx, fpx, gx, fx, fx);
    }),
    LN("ln", (formula, varName) -> {
        formula = removeFunctionName(formula, "ln");
        FormulaNode node = FormulaNodeFactory.create(formula);
        String derivation = node.derivation(varName);
        if (Objects.equals(derivation, FormulaNode.ZERO)) {
            return FormulaNode.ZERO;
        }
        return "(" + derivation + ") * (1/(" + formula + "))";
    });

    /**
     * 名称
     */
    private final String name;

    /**
     * 求导方法
     */
    private final EntryTransformer<String, String, String> derivationFunction;


    FunctionEnum(String name, EntryTransformer<String, String, String> derivationFunction) {
        this.name = name;
        this.derivationFunction = derivationFunction;
    }

    /**
     * 解析
     *
     * @param functionName
     *
     * @return
     */
    public static FunctionEnum parse(String functionName) {
        for (FunctionEnum value : values()) {
            if (Objects.equals(value.getName(), functionName)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 去除方法体
     *
     * @param formula
     *
     * @return
     */
    public static String removeFunctionName(String formula, String functionName) {
        Asserts.assertTrue(formula != null, "公式内容为null:{}", functionName);
        if (!formula.contains(functionName)) {
            return formula;
        }
        int first = formula.indexOf("(", formula.indexOf(functionName));
        int end = formula.lastIndexOf(")");
        return formula.substring(first + 1, end);
    }

    /**
     * 求导
     *
     * @param formula
     * @param varName
     *
     * @return
     */
    public String derivation(String formula, String varName) {
        return derivationFunction.transformEntry(formula, varName);
    }

    public String getName() {
        return name;
    }
}
