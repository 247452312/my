package indi.uhyils.internal;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.factory.ExprFactory;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 内部的工具类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月25日 08时38分
 */
public class InternalUtil {


    public static final Set<String> temp = new HashSet<>();


    /**
     * 获取一个type的全名
     *
     * @param typeSimpleName
     *
     * @return
     */
    private static String parseTypeAllName(Optional<CompilationUnit> compilation, String typeSimpleName) {
        String packageName = null;
        if (compilation.isPresent()) {
            Optional<PackageDeclaration> typePackageDeclaration = compilation.get().getPackageDeclaration();
            if (typePackageDeclaration.isPresent()) {
                packageName = typePackageDeclaration.get().getName().asString();
            }
        }
        String typeAllName;
        if (StringUtil.isEmpty(packageName)) {
            typeAllName = typeSimpleName;
        } else {
            typeAllName = packageName + "." + typeSimpleName;
        }
        return typeAllName;
    }


    /**
     * 替换方法中的每一行
     *
     * @param compilationUnit
     */
    public static void dealCompilationUnitMethodRow(CompilationUnit compilationUnit) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            for (MethodDeclaration method : type.getMethods()) {
                dealMethodCallExprWithLink(method, compilationUnit);
            }
        }
    }

    /**
     * 处理一个节点下所有的方法调用
     *
     * @param method          方法本身
     * @param compilationUnit 方法所在的文件
     *
     * @return
     */
    private static void dealMethodCallExprWithLink(MethodDeclaration method, CompilationUnit compilationUnit) {
        Optional<BlockStmt> body = method.getBody();
        if (!body.isPresent()) {
            return;
        }
        BlockStmt blockStmt = body.get();
        NodeList<Statement> statements = blockStmt.getStatements();
        //局部变量记录,用来判断局部变量的类型,用以之后推测方法所在地 <局部变量名称,局部变量>
        Map<String, TypeDeclaration<?>> vars = new HashMap<>();

        // 将入参放置进可用参数列表中
        NodeList<Parameter> parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            Optional<TypeDeclaration<?>> targetOptional = parameter.getType().getTarget();
            targetOptional.ifPresent(typeDeclaration -> vars.put(parameter.getNameAsString(), typeDeclaration));
        }

        // 将类属性放入可用参数列表中
        TypeDeclaration<?> typeByNode = compilationUnit.findTypeByNode(method);

        List<FieldDeclaration> fields = typeByNode.getFields();
        for (FieldDeclaration field : fields) {
            List<VariableDeclarator> variables = field.getVariables();
            for (VariableDeclarator variable : variables) {
                Optional<TypeDeclaration<?>> targetOptional = variable.getType().getTarget();
                targetOptional.ifPresent(typeDeclaration -> vars.put(variable.getNameAsString(), typeDeclaration));
            }
        }

        // 将相同包中的共有静态变量加入变量中去
        Optional<PackageDeclaration> packageDeclarationOptional = compilationUnit.getPackageDeclaration();
        if (packageDeclarationOptional.isPresent()) {
            PackageDeclaration packageDeclarationWithLink = packageDeclarationOptional.get();
            List<CompilationUnit> otherCompilationUnits = packageDeclarationWithLink.getOtherCompilationUnits();
            for (CompilationUnit otherCompilationUnit : otherCompilationUnits) {
                Map<String, TypeDeclaration<?>> canUseVariable = findCanUseVariable(otherCompilationUnit);
                vars.putAll(canUseVariable);
            }

        }
        // 将 import中的类放到变量中去
        NodeList<ImportDeclaration> imports = compilationUnit.getImports();
        for (ImportDeclaration anImport : imports) {
            String className = anImport.getName().getIdentifier();
            anImport.getTargetType().ifPresent(t -> vars.put(className, t));

        }

        for (Statement statement : statements) {
            // 处理方法中的每一个代码块中的方法
            dealStatementWithLink(compilationUnit, vars, statement);
        }
    }

    /**
     * 根据compilationUnit 去获取可以在局部代码中直接使用的变量
     *
     * @param compilationUnit
     *
     * @return
     */
    public static Map<String, TypeDeclaration<?>> findCanUseVariable(CompilationUnit compilationUnit) {
        Map<String, TypeDeclaration<?>> result = new HashMap<>();

        // 1.同包中其他类中的非私有静态变量
        // 2.util包中静态变量
        // 3.同包中其他枚举类的枚举
        // 4.util包中的枚举

        // 静态变量
        List<VariableDeclarator> otherPackageVariables = new ArrayList<>();
        // 枚举
        List<EnumConstantDeclaration> otherPackageEnumConstantDeclaration = new ArrayList<>();

        for (TypeDeclaration<?> typeDeclaration : compilationUnit.getTypes()) {
            if (typeDeclaration.isClassOrInterfaceDeclaration()) {
                ClassOrInterfaceDeclaration classOrInterfaceDeclaration = typeDeclaration.asClassOrInterfaceDeclaration();
                if (classOrInterfaceDeclaration.isInterface()) {
                    // 接口中的全部
                    List<VariableDeclarator> collect = classOrInterfaceDeclaration.getFields().stream().flatMap(t -> t.getVariables().stream()).collect(Collectors.toList());
                    otherPackageVariables.addAll(collect);
                } else {
                    //非接口中的静态非私有变量
                    List<VariableDeclarator> collect = typeDeclaration.getFields().stream().filter(t -> !t.isPrivate() && t.isStatic()).flatMap(t -> t.getVariables().stream()).collect(Collectors.toList());
                    otherPackageVariables.addAll(collect);
                }
            } else if (typeDeclaration.isEnumDeclaration()) {
                // 枚举们
                EnumDeclaration enumDeclaration = typeDeclaration.asEnumDeclaration();
                List<EnumConstantDeclaration> entries = enumDeclaration.getEntries();
                otherPackageEnumConstantDeclaration.addAll(entries);
            } else if (typeDeclaration.isAnnotationDeclaration()) {
                // 注解跳过
            } else {
                throw new RuntimeException("不支持的类类型:" + typeDeclaration.getClass().getName());
            }
        }
        for (VariableDeclarator variableDeclarator : otherPackageVariables) {
            Optional<TypeDeclaration<?>> targetOptional = variableDeclarator.getType().getTarget();
            targetOptional.ifPresent(typeDeclaration -> result.put(variableDeclarator.getNameAsString(), typeDeclaration));
        }
        // 枚举
        for (EnumConstantDeclaration enumConstantDeclaration : otherPackageEnumConstantDeclaration) {
            EnumDeclaration enumDeclaration = (EnumDeclaration) enumConstantDeclaration.getParentNode().orElse(null);
            String classType = enumDeclaration.getName().asString();
            String fieldName = enumConstantDeclaration.getName().asString();
            String finalName = classType + "." + fieldName;
            result.put(finalName, enumDeclaration);
        }
        return result;
    }

    /**
     * 处理方法中的每一个代码块中的方法
     *
     * @param compilationUnit 所在类
     * @param vars            局部变量表
     * @param statement       代码块
     */
    private static void dealStatementWithLink(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, Statement statement) {
        if (statement.isExpressionStmt()) {
            ExpressionStmt expressionStmt = statement.asExpressionStmt();
            Expression expression = expressionStmt.getExpression();
            dealExpression(compilationUnit, vars, expression);
        } else if (statement.isSwitchStmt()) {
            SwitchStmt switchStmt = statement.asSwitchStmt();
            Expression selector = switchStmt.getSelector();
            dealExpression(compilationUnit, vars, selector);
            NodeList<SwitchEntry> entries = switchStmt.getEntries();
            List<Statement> collect = entries.stream().flatMap(t -> t.getStatements().stream()).collect(Collectors.toList());
            Map<String, TypeDeclaration<?>> itemVars = new HashMap<>(vars);
            for (Statement itemStatement : collect) {
                dealStatementWithLink(compilationUnit, itemVars, itemStatement);
            }
        } else if (statement.isIfStmt()) {
            IfStmt ifStmt = statement.asIfStmt();
            Expression condition = ifStmt.getCondition();
            dealExpression(compilationUnit, vars, condition);

            Statement thenStmt = ifStmt.getThenStmt();
            dealStatementWithLink(compilationUnit, vars, thenStmt);
            Optional<Statement> elseStmt = ifStmt.getElseStmt();
            if (elseStmt.isPresent()) {
                Statement statement1 = elseStmt.get();
                dealStatementWithLink(compilationUnit, vars, statement1);
            }
        } else if (statement.isWhileStmt()) {
            WhileStmt whileStmt = statement.asWhileStmt();
            Expression condition = whileStmt.getCondition();
            dealExpression(compilationUnit, vars, condition);
            Statement body = whileStmt.getBody();
            dealStatementWithLink(compilationUnit, vars, body);
        } else if (statement.isTryStmt()) {
            TryStmt tryStmt = statement.asTryStmt();
            Map<String, TypeDeclaration<?>> tempVars = new HashMap<>(vars);
            NodeList<Expression> resources = tryStmt.getResources();
            for (Expression resource : resources) {
                dealExpression(compilationUnit, tempVars, resource);
            }

            Statement tryBlock = tryStmt.getTryBlock();
            dealStatementWithLink(compilationUnit, tempVars, tryBlock);

            NodeList<CatchClause> catchClauses = tryStmt.getCatchClauses();
            // todo catch 暂时不处理, 不重要

            Optional<BlockStmt> finallyBlock = tryStmt.getFinallyBlock();
            // finally里访问不到try里的代码
            tempVars = new HashMap<>(vars);
            if (finallyBlock.isPresent()) {
                dealStatementWithLink(compilationUnit, tempVars, finallyBlock.get());
            }
        } else if (statement.isBlockStmt()) {
            BlockStmt blockStmt = statement.asBlockStmt();
            NodeList<Statement> statements = blockStmt.getStatements();
            Map<String, TypeDeclaration<?>> itemVars = new HashMap<>(vars);
            for (Statement item : statements) {
                dealStatementWithLink(compilationUnit, itemVars, item);
            }
        } else if (statement.isForEachStmt()) {
            Map<String, TypeDeclaration<?>> newVars = new HashMap<>(vars);
            ForEachStmt forEachStmt = statement.asForEachStmt();
            VariableDeclarationExpr variable = forEachStmt.getVariable();
            dealExpression(compilationUnit, newVars, variable);

            Expression iterable = forEachStmt.getIterable();
            dealExpression(compilationUnit, newVars, iterable);

            Statement body = forEachStmt.getBody();
            dealStatementWithLink(compilationUnit, newVars, body);
        } else if (statement.isAssertStmt()) {
            AssertStmt assertStmt = statement.asAssertStmt();
            Expression check = assertStmt.getCheck();
            dealExpression(compilationUnit, vars, check);
        } else if (statement.isSynchronizedStmt()) {
            SynchronizedStmt synchronizedStmt = statement.asSynchronizedStmt();
            Expression expression = synchronizedStmt.getExpression();
            dealExpression(compilationUnit, vars, expression);
            BlockStmt body = synchronizedStmt.getBody();
            dealStatementWithLink(compilationUnit, vars, body);
        } else if (statement.isReturnStmt()) {
            ReturnStmt returnStmt = statement.asReturnStmt();
            Optional<Expression> expression = returnStmt.getExpression();
            expression.ifPresent(value -> dealExpression(compilationUnit, vars, value));
        } else if (statement.isForStmt()) {
            ForStmt forStmt = statement.asForStmt();
            Map<String, TypeDeclaration<?>> tempVars = new HashMap<>(vars);
            NodeList<Expression> initialization = forStmt.getInitialization();
            for (Expression expression : initialization) {
                dealExpression(compilationUnit, tempVars, expression);
            }
            Optional<Expression> compare = forStmt.getCompare();
            compare.ifPresent(expression -> dealExpression(compilationUnit, tempVars, expression));
            NodeList<Expression> update = forStmt.getUpdate();
            for (Expression expression : update) {
                dealExpression(compilationUnit, tempVars, expression);
            }
            Statement body = forStmt.getBody();
            dealStatementWithLink(compilationUnit, tempVars, body);
        } else if (statement.isThrowStmt()) {
            ThrowStmt throwStmt = statement.asThrowStmt();
            Expression expression = throwStmt.getExpression();
            dealExpression(compilationUnit, vars, expression);
        } else if (statement.isContinueStmt() || statement.isBreakStmt()) {
            // do nothing
        } else if (statement.isLocalClassDeclarationStmt()) {
            // todo 暂不支持这种 方法内部定义class的行为
        } else if (statement.isEmptyStmt()) {
            // 空
        } else if (statement.isDoStmt()) {
            DoStmt doStmt = statement.asDoStmt();
            Statement body = doStmt.getBody();
            Map<String, TypeDeclaration<?>> newVars = new HashMap<>(vars);
            dealStatementWithLink(compilationUnit, newVars, body);
            Expression condition = doStmt.getCondition();
            dealExpression(compilationUnit, newVars, condition);
        } else if (statement.isLabeledStmt()) {
            LabeledStmt labeledStmt = statement.asLabeledStmt();
            dealStatementWithLink(compilationUnit, vars, labeledStmt.getStatement());
        } else {
            LogUtil.error("类型不正确:{}", statement.getClass().getSimpleName());
            temp.add(statement.getClass().getSimpleName());
        }
    }

    /**
     * 处理各种表达式
     *
     * @param compilationUnit
     * @param vars
     * @param expression
     */
    private static void dealExpression(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, Expression expression) {
        if (expression.isVariableDeclarationExpr()) {
            dealVariableDeclarationExpr(compilationUnit, vars, expression.asVariableDeclarationExpr());
        } else if (expression.isMethodCallExpr()) {
            dealMethodCallExpr(compilationUnit, vars, expression.asMethodCallExpr());
        } else if (expression.isNameExpr()) {
            dealNameExpr(compilationUnit, vars, expression.asNameExpr());
        } else if (expression.isBinaryExpr()) {
            dealBinaryExpr(compilationUnit, vars, expression.asBinaryExpr());
        } else if (expression.isAssignExpr()) {
            dealAssignExpr(compilationUnit, vars, expression.asAssignExpr());
        } else if (expression.isLiteralExpr()) {
            dealLiteralExpr(compilationUnit, vars, expression.asLiteralExpr());
        } else if (expression.isObjectCreationExpr()) {
            dealObjectCreationExpr(compilationUnit, vars, expression.asObjectCreationExpr());
        } else if (expression.isUnaryExpr()) {
            dealUnaryExpr(compilationUnit, vars, expression.asUnaryExpr());
        } else if (expression.isArrayAccessExpr()) {
            dealArrayAccessExpr(compilationUnit, vars, expression.asArrayAccessExpr());
        } else if (expression.isCastExpr()) {
            dealCastExpr(compilationUnit, vars, expression.asCastExpr());
        } else if (expression.isThisExpr()) {
            expression.setReturnType(compilationUnit.findTypeByNode(expression));
        } else if (expression.isFieldAccessExpr()) {
            dealFieldAccessExpr(compilationUnit, vars, expression.asFieldAccessExpr());
        } else if (expression.isInstanceOfExpr()) {
            dealInstanceOfExpr(compilationUnit, vars, expression.asInstanceOfExpr());
        } else if (expression.isArrayCreationExpr()) {
            dealArrayCreationExpr(compilationUnit, vars, expression.asArrayCreationExpr());
        } else if (expression.isClassExpr()) {
            dealClassExpr(compilationUnit, vars, expression.asClassExpr());
        } else if (expression.isConditionalExpr()) {
            dealConditionalExpr(compilationUnit, vars, expression.asConditionalExpr());
        } else if (expression.isEnclosedExpr()) {
            dealEnclosedExpr(compilationUnit, vars, expression.asEnclosedExpr());
        } else if (expression.isLambdaExpr()) {
            dealLambdaExpr(compilationUnit, vars, expression.asLambdaExpr());
        } else if (expression.isMethodReferenceExpr()) {
            dealMethodReferenceExpr(compilationUnit, vars, expression.asMethodReferenceExpr());
        } else if (expression.isTypeExpr()) {
            dealTypeExpr(compilationUnit, vars, expression.asTypeExpr());
        } else if (expression.isSuperExpr()) {
            // 暂时不处理super表达式
        } else {
            LogUtil.error("未知的表达式类型:{}", expression.getClass().getSimpleName());
        }
    }

    /**
     * 处理类型表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asTypeExpr
     */
    private static void dealTypeExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, TypeExpr asTypeExpr) {
        String typeString = asTypeExpr.getType().asString();
        if (vars.containsKey(typeString)) {
            asTypeExpr.setReturnType(vars.get(typeString));
            return;
        }

        Type type = asTypeExpr.getType();
        type.fillTargetByCompilationUnit(compilationUnit);
        Optional<TypeDeclaration<?>> target = type.getTarget();
        target.ifPresent(asTypeExpr::setReturnType);
    }

    /**
     * 处理方法引用调用
     *
     * @param compilationUnit
     * @param vars
     * @param asMethodReferenceExpr
     */
    private static void dealMethodReferenceExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, MethodReferenceExpr asMethodReferenceExpr) {
        // 方法调用方
        Expression scope = asMethodReferenceExpr.getScope();
        // 方法名称
        String methodName = asMethodReferenceExpr.getIdentifier();

        // 1.通过方法调用方判断这个是哪个类的
        TypeDeclaration<?> scopeReturnType;
        dealExpression(compilationUnit, vars, scope);
        scopeReturnType = scope.getReturnType().orElse(null);

        if (scopeReturnType == null) {
            return;
        }

        // 2.通过方法名称判断这个方法是哪些
        // 筛选出来的名称一致的方法
        List<MethodDeclaration> nameSameMethods = scopeReturnType.getMethods()
                                                                 .stream()
                                                                 .filter(t -> Objects.equals(t.getName().asString(), methodName))
                                                                 .map(MethodDeclaration.class::cast)
                                                                 .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(nameSameMethods)) {
            // 找到执行的类了 但是没找到对应方法
            return;
        }

        //        Asserts.assertTrue(nameSameMethods != null && nameSameMethods.size() == 1, "方法引用找到两个相同的方法名称");

        // todo 这里应该使用推理,判断方法引用引用的是哪个方法,暂时先不搞了

        // 4.注入
        MethodDeclaration methodDeclarationWithLink = nameSameMethods.get(0);
        asMethodReferenceExpr.setMethodLink(methodDeclarationWithLink);
    }

    /**
     * 处理lambda表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asLambdaExpr
     */
    private static void dealLambdaExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, LambdaExpr asLambdaExpr) {
        Statement body = asLambdaExpr.getBody();
        Map<String, TypeDeclaration<?>> newVars = new HashMap<>(vars);
        dealStatementWithLink(compilationUnit, newVars, body);
        ClassOrInterfaceDeclaration returnType = new ClassOrInterfaceDeclaration();
        returnType.setName(Function.class.getSimpleName());
        asLambdaExpr.setReturnType(returnType);
    }

    /**
     * 处理括号表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asEnclosedExpr
     */
    private static void dealEnclosedExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, EnclosedExpr asEnclosedExpr) {
        Expression inner = asEnclosedExpr.getInner();
        dealExpression(compilationUnit, vars, inner);
        inner.getReturnType().ifPresent(asEnclosedExpr::setReturnType);
    }

    /**
     * 处理三目表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asConditionalExpr
     */
    private static void dealConditionalExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, ConditionalExpr asConditionalExpr) {
        Expression condition = asConditionalExpr.getCondition();
        dealExpression(compilationUnit, vars, condition);
        Expression thenExpr = asConditionalExpr.getThenExpr();
        dealExpression(compilationUnit, vars, thenExpr);
        Expression elseExpr = asConditionalExpr.getElseExpr();
        dealExpression(compilationUnit, vars, elseExpr);

        Optional<TypeDeclaration<?>> returnType = thenExpr.getReturnType();
        Optional<TypeDeclaration<?>> elseReturnType = elseExpr.getReturnType();
        TypeDeclaration<?> exprReturnType = returnType.orElse(elseReturnType.orElse(null));
        asConditionalExpr.setReturnType(exprReturnType);
    }

    /**
     * 处理数组创建表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asArrayCreationExpr
     */
    private static void dealArrayCreationExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, ArrayCreationExpr asArrayCreationExpr) {
        Type elementType = asArrayCreationExpr.getElementType();
        elementType.fillTargetByCompilationUnit(compilationUnit);
        elementType.getTarget().ifPresent(asArrayCreationExpr::setReturnType);
    }

    /**
     * 处理class表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asClassExpr
     */
    private static void dealClassExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, ClassExpr asClassExpr) {
        Type type = asClassExpr.getType();
        type.fillTargetByCompilationUnit(compilationUnit);
        type.getTarget().ifPresent(asClassExpr::setReturnType);
    }

    /**
     * 处理 instanceOf
     *
     * @param compilationUnit
     * @param vars
     * @param asInstanceOfExpr
     */
    private static void dealInstanceOfExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, InstanceOfExpr asInstanceOfExpr) {
        Expression expression = asInstanceOfExpr.getExpression();
        dealExpression(compilationUnit, vars, expression);
        TypeDeclaration<?> booleanType = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(Boolean.class.getPackage().getName(), Boolean.class.getSimpleName());
        asInstanceOfExpr.setReturnType(booleanType);
    }

    /**
     * 处理类型强转语句
     *
     * @param compilationUnit
     * @param vars
     * @param asCastExpr
     */
    private static void dealCastExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, CastExpr asCastExpr) {
        Expression expression = asCastExpr.getExpression();
        dealExpression(compilationUnit, vars, expression);
        Type type = asCastExpr.getType();
        type.fillTargetByCompilationUnit(compilationUnit);
        type.getTarget().ifPresent(asCastExpr::setReturnType);
    }

    /**
     * 处理数组选择表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asArrayAccessExpr
     */
    private static void dealArrayAccessExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, ArrayAccessExpr asArrayAccessExpr) {
        Expression name = asArrayAccessExpr.getName();
        dealExpression(compilationUnit, vars, name);
        name.getReturnType().ifPresent(asArrayAccessExpr::setReturnType);
    }

    /**
     * 处理一元表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asUnaryExpr
     */
    private static void dealUnaryExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, UnaryExpr asUnaryExpr) {
        Expression expression = asUnaryExpr.getExpression();
        dealExpression(compilationUnit, vars, expression);
        TypeDeclaration<?> typeDeclaration = ExprFactory.calculationUnaryType(compilationUnit, vars, asUnaryExpr);
        asUnaryExpr.setReturnType(typeDeclaration);
    }

    /**
     * 处理new表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asObjectCreationExpr
     */
    private static void dealObjectCreationExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, final ObjectCreationExpr asObjectCreationExpr) {
        ClassOrInterfaceType type = asObjectCreationExpr.getType();
        Optional<TypeDeclaration<?>> typeDeclaration = compilationUnit.findTypeDeclaration(type);
        typeDeclaration.orElseGet(() -> {
            return null;
        });
        typeDeclaration.ifPresent(asObjectCreationExpr::setReturnType);
    }

    /**
     * 处理一些不是类的表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asLiteralExpr
     */
    private static void dealLiteralExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, LiteralExpr asLiteralExpr) {
        if (asLiteralExpr.isNullLiteralExpr()) {
            TypeDeclaration<?> type = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(null, "null");
            asLiteralExpr.setReturnType(type);
        } else if (asLiteralExpr.isBooleanLiteralExpr()) {
            TypeDeclaration<?> type = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(Boolean.class.getPackage().getName(), Boolean.class.getSimpleName());
            asLiteralExpr.setReturnType(type);
        } else if (asLiteralExpr.isCharLiteralExpr()) {
            TypeDeclaration<?> type = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(Character.class.getPackage().getName(), Character.class.getSimpleName());
            asLiteralExpr.setReturnType(type);
        } else if (asLiteralExpr.isDoubleLiteralExpr()) {
            TypeDeclaration<?> type = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(Double.class.getPackage().getName(), Double.class.getSimpleName());
            asLiteralExpr.setReturnType(type);
        } else if (asLiteralExpr.isLongLiteralExpr()) {
            TypeDeclaration<?> type = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(Long.class.getPackage().getName(), Long.class.getSimpleName());
            asLiteralExpr.setReturnType(type);
        } else if (asLiteralExpr.isStringLiteralExpr()) {
            TypeDeclaration<?> type = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(String.class.getPackage().getName(), String.class.getSimpleName());
            asLiteralExpr.setReturnType(type);
        } else if (asLiteralExpr.isIntegerLiteralExpr()) {
            TypeDeclaration<?> type = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(Integer.class.getPackage().getName(), Integer.class.getSimpleName());
            asLiteralExpr.setReturnType(type);
        }
    }

    /**
     * 处理无type赋值表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asAssignExpr
     */
    private static void dealAssignExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, AssignExpr asAssignExpr) {
        Expression target = asAssignExpr.getTarget();
        // 先处理符号右边的表达式
        Expression value = asAssignExpr.getValue();
        dealExpression(compilationUnit, vars, value);

        dealExpression(compilationUnit, vars, target);
        Optional<TypeDeclaration<?>> returnType = target.getReturnType();
        returnType.ifPresent(asAssignExpr::setReturnType);

    }

    private static void dealFieldAccessExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, FieldAccessExpr fieldAccessExpr) {
        Expression scope = fieldAccessExpr.getScope();
        String fieldName = fieldAccessExpr.getNameAsString();
        dealExpression(compilationUnit, vars, scope);

        Optional<TypeDeclaration<?>> returnType = scope.getReturnType();
        if (returnType.isPresent()) {
            TypeDeclaration<?> typeDeclaration = returnType.get();
            typeDeclaration.getFields().stream().flatMap(t -> t.getVariables().stream()).filter(t -> Objects.equals(t.getNameAsString(), fieldName)).findFirst().ifPresent(t -> {
                fieldAccessExpr.setFieldLink(t);
                Type type = t.getType();
                type.getTarget().ifPresent(fieldAccessExpr::setReturnType);
            });
        }
    }

    /**
     * 处理二元表达式
     *
     * @param compilationUnit
     * @param vars
     * @param asBinaryExpr
     */
    private static void dealBinaryExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, BinaryExpr asBinaryExpr) {
        TypeDeclaration<?> typeDeclaration = ExprFactory.calculationBinaryType(compilationUnit, vars, asBinaryExpr);
        asBinaryExpr.setReturnType(typeDeclaration);
    }

    /**
     * 处理名称表达式
     *
     * @param compilationUnit
     * @param vars
     * @param nameExpr
     */
    private static void dealNameExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, NameExpr nameExpr) {
        String name = nameExpr.getNameAsString();
        if (vars.containsKey(name)) {
            nameExpr.setReturnType(vars.get(name));
        }

    }

    /**
     * 处理赋值表达式
     *
     * @param compilationUnit
     * @param vars
     * @param variableDeclarationExpr
     */
    private static void dealVariableDeclarationExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, VariableDeclarationExpr variableDeclarationExpr) {
        NodeList<VariableDeclarator> variables = variableDeclarationExpr.getVariables();
        // 赋值表达式有可能有多个 例如 int i=2,j=3
        for (VariableDeclarator variable : variables) {
            SimpleName name = variable.getName();
            Type type = variable.getType();
            type.fillTargetByCompilationUnit(compilationUnit);

            Optional<Expression> initializer = variable.getInitializer();
            Optional<TypeDeclaration<?>> target = type.getTarget();
            if (initializer.isPresent()) {
                Expression initializerExpression = initializer.get();
                dealExpression(compilationUnit, vars, initializerExpression);
            }
            target.ifPresent(s -> vars.put(name.asString(), s));
        }
    }

    /**
     * 处理方法执行的表达式
     *
     * @param compilationUnit
     * @param vars
     * @param expression
     *
     * @return
     */
    @NotNull
    private static void dealMethodCallExpr(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars, MethodCallExpr expression) {
        // 方法调用方
        Optional<Expression> scope = expression.getScope();
        // 方法名称
        SimpleName methodName = expression.getName();
        // 方法入参变量
        NodeList<Expression> arguments = expression.getArguments();

        // 解析入参先
        for (Expression argument : arguments) {
            dealExpression(compilationUnit, vars, argument);
        }
        // 1.通过方法调用方判断这个是哪个类的
        TypeDeclaration<?> scopeReturnType;
        if (scope.isPresent()) {
            Expression scopeExpression = scope.get();
            dealExpression(compilationUnit, vars, scopeExpression);
            scopeReturnType = scopeExpression.getReturnType().orElse(null);
        } else {
            scopeReturnType = compilationUnit.findTypeByNode(expression);
        }
        if (scopeReturnType == null) {
            return;
        }

        // 2.通过方法名称判断这个方法是哪些
        // 相似的方法的名称
        String methodNameStr = methodName.asString();
        // 筛选出来的名称一致的方法
        List<MethodDeclaration> nameSameMethods = scopeReturnType.getMethods()
                                                                 .stream()
                                                                 .filter(t -> Objects.equals(t.getName().asString(), methodNameStr))
                                                                 .map(MethodDeclaration.class::cast)
                                                                 .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(nameSameMethods)) {
            // 找到执行的类了 但是没找到对应方法
            return;
        }

        // 3.通过方法入参类型确定最终的方法是哪一个
        // 寻找代码中表达式调用的方法入参的类型
        List<TypeDeclaration<?>> argumentsClazzList = null;

        argumentsClazzList = arguments.stream().map(Expression::getReturnType).map(t -> t.orElse(null)).collect(Collectors.toList());

        // 通过方法入参类型确定最终的方法是哪一个
        List<TypeDeclaration<?>> finalArgumentsClazzList = argumentsClazzList;
        Optional<MethodDeclaration> first = nameSameMethods.stream().filter(t -> matchingMethodParamsType(t, finalArgumentsClazzList)).findFirst();

        // 4.注入
        if (first.isPresent()) {
            MethodDeclaration methodDeclarationWithLink = first.get();
            expression.setMethodLink(methodDeclarationWithLink);
        }
    }


    /**
     * 匹配方法参数类型
     *
     * @param argumentsClazzList
     * @param method
     *
     * @return
     */
    private static boolean matchingMethodParamsType(MethodDeclaration method, List<TypeDeclaration<?>> argumentsClazzList) {
        List<TypeDeclaration<?>> methodParamTypes = method.getParameters().stream().map(t -> t.getType().getTarget()).map(t -> t.orElse(null)).collect(Collectors.toList());
        // 其中一个为空,返回匹配不成功
        if (argumentsClazzList == null) {
            return false;
        }
        // 方法入参个数不一致,返回匹配不成功
        if (methodParamTypes.size() != argumentsClazzList.size()) {
            return false;
        }
        for (int i = 0; i < argumentsClazzList.size(); i++) {
            // 注意 这里是入参的类型, 是有可能为null的, 例: 入参是一个方法,并且这个方法不是在扫描类中,就找不到这个方法明确的返回值
            TypeDeclaration<?> paramClazz = methodParamTypes.get(i);
            TypeDeclaration<?> argumentClazz = argumentsClazzList.get(i);
            if (argumentClazz == null || paramClazz == null) {
                return false;
            }
            if (!Objects.equals(paramClazz.getName().asString(), argumentClazz.getName().asString())) {
                return false;
            }
        }
        return true;
    }


    public static void print() {
        temp.forEach(System.out::println);
    }
}
