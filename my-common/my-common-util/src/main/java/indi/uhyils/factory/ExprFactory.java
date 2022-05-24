package indi.uhyils.factory;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.UnaryExpr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public static TypeDeclaration<?> calculationBinaryType(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, BinaryExpr binaryExpr) {
        Operator operator = binaryExpr.getOperator();
        List<TypeDeclaration<?>> argsType;

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
                return compilationUnit.createNotScannedTypeDeclaration(Boolean.class.getSimpleName());

            case PLUS:
                argsType = arguments.stream().map(Expression::getReturnType).map(t -> t.orElse(null)).collect(Collectors.toList());
                TypeDeclaration<?> left = argsType.get(0);
                TypeDeclaration<?> right = argsType.get(1);
                if (left == null) {
                    return right;
                }
                if (right == null) {
                    return left;
                }
                if (Objects.equals(left.getName().asString(), "String")) {
                    return left;
                } else if (Objects.equals(right.getName().asString(), "String")) {
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
                return calculationBinaryType(compilationUnit, vars, expressions);
            case SIGNED_RIGHT_SHIFT:
            case UNSIGNED_RIGHT_SHIFT:
            case LEFT_SHIFT:
                return binaryExpr.getLeft().getReturnType().orElse(null);
            default:
        }
        return null;
    }

    /**
     * 推测一个表达式返回值的类型
     *
     * @param compilationUnit
     * @param vars
     * @param unary
     *
     * @return
     */
    public static TypeDeclaration<?> calculationUnaryType(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, UnaryExpr unary) {
        UnaryExpr.Operator operator = unary.getOperator();
        switch (operator) {
            case LOGICAL_COMPLEMENT:
                // 以上符号都是boolean类型
                return compilationUnit.createNotScannedTypeDeclaration(Boolean.class.getSimpleName());
            case PREFIX_DECREMENT:
            case PREFIX_INCREMENT:
            case POSTFIX_DECREMENT:
            case POSTFIX_INCREMENT:
            case BITWISE_COMPLEMENT:
            case MINUS:
            case PLUS:
                return unary.getExpression().getReturnType().orElse(null);
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
    private static TypeDeclaration<?> calculationBinaryType(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, List<Expression> expressions) {
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
        return compilationUnit.createNotScannedTypeDeclaration(privi);
    }
    /*
     *//**
     * 创建一个带有目标类型的表达式
     *
     * @param compilationUnitWithLink
     * @param vars
     * @param expression
     *
     * @return
     *//*
    @NotNull
    public Expression createExpressionWithLink(CompilationUnit compilationUnitWithLink, Map<String, CompilationUnit> vars, Expression expression) {
        CompilationUnit returnType = null;
        if (expression instanceof MethodCallExpr) {
            MethodCallExpr methodCallExprWithLink = (MethodCallExpr) expression;
            MethodDeclaration targetMethod = methodCallExprWithLink.getTargetMethod();
            returnType = targetMethod.getReturnType();
        } else if (expression.isNameExpr()) {
            String argumentVarName = expression.asNameExpr().getName().asString();
            returnType = InternalUtil.findScopeInVarPool(compilationUnitWithLink, vars, argumentVarName);

        } else if (expression.isMethodCallExpr()) {
            MethodCallExpr methodCallExpr1 = expression.asMethodCallExpr();
            if (methodCallExpr1 instanceof MethodCallExpr) {
                MethodCallExpr beforeMethodExpr = (MethodCallExpr) methodCallExpr1;
                MethodDeclaration targetMethod = beforeMethodExpr.getTargetMethod();
                returnType = targetMethod.getReturnType();
            } else {
                LogUtil.warn("未找到方法:{},的返回类型", expression.toString());
            }
        } else if (expression.isMethodReferenceExpr()) {
            MethodReferenceExpr methodReferenceExpr = expression.asMethodReferenceExpr();

            // 暂不支持methodReference 例如 objectSupplier::get
            returnType = null;
        } else if (expression.isLiteralExpr()) {
            LiteralExpr literalExpr = expression.asLiteralExpr();
            String simpleName = literalExpr.getClass().getSimpleName();
            int lastIndexOf = simpleName.lastIndexOf("LiteralExpr");
            if (lastIndexOf == -1) {
                LogUtil.error("未知的入参类型:{}", expression.toString());
            } else {
                String substring = simpleName.substring(0, lastIndexOf);
                returnType = new DeclarationFactory().createNotScannedCompilationUnit(substring);
            }

        } else if (expression.isFieldAccessExpr()) {
            FieldAccessExpr fieldAccessExpr = expression.asFieldAccessExpr();
            Expression scope = fieldAccessExpr.getScope();
            Expression expressionWithLink = createExpressionWithLink(compilationUnitWithLink, vars, scope);
            returnType = expressionWithLink.getReturnCompilationUnitWithLink();

        } else if (expression.isArrayAccessExpr()) {
            ArrayAccessExpr arrayAccessExpr = expression.asArrayAccessExpr();
            returnType = vars.get(arrayAccessExpr.getName().toString());
        } else if (expression.isBinaryExpr()) {
            BinaryExpr binaryExpr = expression.asBinaryExpr();
            returnType = calculationBinaryType(compilationUnitWithLink, vars, binaryExpr);
        } else if (expression.isCastExpr()) {
            Type type = expression.asCastExpr().getType();
            ClassOrInterfaceType classOrInterfaceTypeWithLink = InternalUtil.judgeTypeClassAndMakeWithLinkType(compilationUnitWithLink, type);
            returnType = classOrInterfaceTypeWithLink.getTypeTarget();
        } else if (expression.isClassExpr()) {
            returnType = new DeclarationFactory().createNotScannedCompilationUnitWithLink("Class");
        } else if (expression.isLambdaExpr()) {
            // todo lambda 暂时不进行处理
            returnType = null;
        } else if (expression.isThisExpr()) {
            returnType = compilationUnitWithLink;
        } else if (expression.isObjectCreationExpr()) {
            ObjectCreationExpr objectCreationExpr = expression.asObjectCreationExpr();
            String s = objectCreationExpr.getType().getName().asString();
            returnType = InternalUtil.findScopeInVarPool(compilationUnitWithLink, vars, s);
        } else if (expression.isArrayCreationExpr()) {
            ArrayCreationExpr arrayCreationExpr = expression.asArrayCreationExpr();
            Type elementType = arrayCreationExpr.getElementType();
            returnType = InternalUtil.findScopeInVarPool(compilationUnitWithLink, vars, elementType.asString());
        } else if (expression.isUnaryExpr()) {
            UnaryExpr unaryExpr = expression.asUnaryExpr();
            returnType = calculationUnaryType(compilationUnitWithLink, vars, unaryExpr);
        } else if (expression.isEnclosedExpr()) {
            EnclosedExpr enclosedExpr = expression.asEnclosedExpr();
            return createExpressionWithLink(compilationUnitWithLink, vars, enclosedExpr.getInner());
        } else if (expression.isInstanceOfExpr()) {
            returnType = new DeclarationFactory().createNotScannedCompilationUnitWithLink(Boolean.class.getSimpleName());
        } else if (expression.isSuperExpr()) {
            LogUtil.warn("暂不支持super解析:{}", expression);
        } else {
            InternalUtil.temp.add(expression.getClass().getName());
            LogUtil.error("未知的入参类型:{}", expression.toString());
        }
        Expression expressionWithLink = new Expression(expression);
        expressionWithLink.setReturnCompilationUnitWithLink(returnType);
        return expressionWithLink;
    }*/
}
