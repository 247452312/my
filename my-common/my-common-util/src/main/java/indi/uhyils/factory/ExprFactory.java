package indi.uhyils.factory;

import com.github.javaparser.ast.ClassOrInterfaceTypeWithLink;
import com.github.javaparser.ast.CompilationUnitWithLink;
import com.github.javaparser.ast.MethodCallExprWithLink;
import com.github.javaparser.ast.MethodDeclarationWithLink;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ExpressionWithLink;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.type.Type;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.internal.InternalUtil;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月27日 09时31分
 */
public class ExprFactory extends AbstractFactory {

    /**
     * 基本类型排序
     */
    private static final String[] privis = new String[]{"byte", "short", "int", "long", "float", "double"};

    /**
     * 推测一个表达式返回值的类型
     *
     * @param compilationUnit
     * @param vars
     * @param binaryExpr
     *
     * @return
     */
    private static CompilationUnitWithLink calculationType(CompilationUnitWithLink compilationUnit, Map<String, CompilationUnitWithLink> vars, BinaryExpr binaryExpr) {
        Operator operator = binaryExpr.getOperator();
        List<CompilationUnitWithLink> argsType;

        //左右表达式
        List<Expression> arguments = Arrays.asList(binaryExpr.getLeft(), binaryExpr.getRight());
        switch (operator) {
            case OR:
            case AND:
            case EQUALS:
            case NOT_EQUALS:
            case LESS:
            case GREATER:
            case LESS_EQUALS:
            case GREATER_EQUALS:
            case XOR:
                // 以上符号都是boolean类型
                return new DeclarationFactory().createNotScannedCompilationUnitWithLink(Boolean.class.getSimpleName());

            case PLUS:
                argsType = InternalUtil.findArgsType(compilationUnit, vars, arguments);
                CompilationUnitWithLink left = argsType.get(0);
                CompilationUnitWithLink right = argsType.get(1);
                if (left == null) {
                    return right;
                }
                if (right == null) {
                    return left;
                }
                if (Objects.equals(left.getType(0).getName().asString(), "String")) {
                    return left;
                } else if (Objects.equals(right.getType(0).getName().asString(), "String")) {
                    return right;
                }
                /*这里没有return null 因为如果上面判断String不成立 则加入下方计算类型大军*/

            case BINARY_OR:
            case BINARY_AND:
            case MINUS:
            case DIVIDE:
            case MULTIPLY:
            case REMAINDER:
                // 递归获取所有的表达式
                List<Expression> expressions = splitExpression(binaryExpr);

                return calculationType(compilationUnit, vars, expressions);
            case SIGNED_RIGHT_SHIFT:
            case UNSIGNED_RIGHT_SHIFT:
            case LEFT_SHIFT:
                argsType = InternalUtil.findArgsType(compilationUnit, vars, Arrays.asList(binaryExpr.getLeft()));
                return argsType.get(0);
            default:
        }
        return null;
    }

    /**
     * 拆分一堆
     *
     * @param argument
     *
     * @return
     */
    private static List<Expression> splitExpression(Expression argument) {
        List<Expression> result = new ArrayList<>();
        if (argument.isBinaryExpr()) {
            BinaryExpr binaryExpr = argument.asBinaryExpr();
            result.addAll(splitExpression(binaryExpr.getRight()));
            result.addAll(splitExpression(binaryExpr.getLeft()));
        } else {
            result.add(argument);
        }
        return result;
    }

    /**
     * 推算计算类型
     *
     * @param vars        变量表
     * @param expressions 表达式们
     *
     * @return 推算后的计算类型
     */
    private static CompilationUnitWithLink calculationType(CompilationUnitWithLink compilationUnit, Map<String, CompilationUnitWithLink> vars, List<Expression> expressions) {
        // 如果是 "byte", "short", "int", "long", "float", "double" 其中一个,则判断是两个中较大的那个
        int maxIndex = 0;
        boolean haveLiteralExpr = false;
        for (Expression expression : expressions) {
            if (expression.isLiteralExpr()) {
                haveLiteralExpr = true;
                String expressionStr = expression.toString();
                expressionStr = expressionStr.replace("LiteralExpr", "");
                for (int j = 0; j < privis.length; j++) {
                    String privi = privis[j];
                    if (privi.equalsIgnoreCase(expressionStr)) {
                        maxIndex = j;
                    }
                }
            }
        }
        final String privi;
        if (!haveLiteralExpr) {
            // 如果没有,则默认是int
            privi = "int";
        } else {
            privi = privis[maxIndex];
        }
        return new DeclarationFactory().createNotScannedCompilationUnitWithLink(privi);
    }

    /**
     * 创建一个带有目标类型的表达式
     *
     * @param compilationUnitWithLink
     * @param vars
     * @param expression
     *
     * @return
     */
    @NotNull
    public ExpressionWithLink createExpressionWithLink(CompilationUnitWithLink compilationUnitWithLink, Map<String, CompilationUnitWithLink> vars, Expression expression) {
        CompilationUnitWithLink returnType = null;
        if (expression instanceof MethodCallExprWithLink) {
            MethodCallExprWithLink methodCallExprWithLink = (MethodCallExprWithLink) expression;
            MethodDeclarationWithLink targetMethod = methodCallExprWithLink.getTargetMethod();
            returnType = targetMethod.getReturnType();
        } else if (expression.isNameExpr()) {
            String argumentVarName = expression.asNameExpr().getName().asString();
            returnType = vars.get(argumentVarName);
            if (!vars.containsKey(argumentVarName)) {
                LogUtil.error("未找到临时变量:{},代码不正确", argumentVarName);
            }
        } else if (expression.isMethodCallExpr()) {
            MethodCallExpr methodCallExpr1 = expression.asMethodCallExpr();
            if (methodCallExpr1 instanceof MethodCallExprWithLink) {
                MethodCallExprWithLink beforeMethodExpr = (MethodCallExprWithLink) methodCallExpr1;
                MethodDeclarationWithLink targetMethod = beforeMethodExpr.getTargetMethod();
                returnType = targetMethod.getReturnType();
            } else {
                LogUtil.error("未找到方法:{},的返回类型", expression.toString());
            }
        } else if (expression.isLiteralExpr()) {
            LiteralExpr literalExpr = expression.asLiteralExpr();
            String simpleName = literalExpr.getClass().getSimpleName();
            int lastIndexOf = simpleName.lastIndexOf("LiteralExpr");
            if (lastIndexOf == -1) {
                LogUtil.error("未知的入参类型:{}", expression.toString());
            } else {
                String substring = simpleName.substring(0, lastIndexOf);
                returnType = new DeclarationFactory().createNotScannedCompilationUnitWithLink(substring);
            }

        } else if (expression.isFieldAccessExpr()) {
            FieldAccessExpr fieldAccessExpr = expression.asFieldAccessExpr();
            Expression scope = fieldAccessExpr.getScope();
            CompilationUnitWithLink compilationByName = InternalUtil.findCompilationByExpression(compilationUnitWithLink, vars, Optional.ofNullable(scope));
            if (compilationByName == null) {
                LogUtil.error("未找到静态变量:{}", expression.toString());
            } else {
                Map<String, CompilationUnitWithLink> canUseVariable = InternalUtil.findCanUseVariable(compilationByName);
                returnType = canUseVariable.get(expression.toString());
            }
        } else if (expression.isArrayAccessExpr()) {
            ArrayAccessExpr arrayAccessExpr = expression.asArrayAccessExpr();
            returnType = vars.get(arrayAccessExpr.getName().toString());
        } else if (expression.isBinaryExpr()) {
            BinaryExpr binaryExpr = expression.asBinaryExpr();
            returnType = calculationType(compilationUnitWithLink, vars, binaryExpr);
        } else if (expression.isCastExpr()) {
            Type type = expression.asCastExpr().getType();
            ClassOrInterfaceTypeWithLink classOrInterfaceTypeWithLink = InternalUtil.judgeTypeClassAndMakeWithLinkType(compilationUnitWithLink, type);
            returnType = classOrInterfaceTypeWithLink.getTypeTarget();
        } else {
            InternalUtil.temp.add(expression.getClass().getName());
            LogUtil.error("未知的入参类型:{}", expression.toString());
        }
        ExpressionWithLink expressionWithLink = new ExpressionWithLink(expression);
        expressionWithLink.setReturnCompilationUnitWithLink(returnType);
        return expressionWithLink;
    }
}
