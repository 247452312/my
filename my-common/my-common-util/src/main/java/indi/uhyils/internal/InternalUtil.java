package indi.uhyils.internal;

import com.github.javaparser.ast.ClassOrInterfaceTypeWithLink;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.CompilationUnitWithLink;
import com.github.javaparser.ast.FieldDeclarationWithLink;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.ImportDeclarationWithLink;
import com.github.javaparser.ast.MethodCallExprWithLink;
import com.github.javaparser.ast.MethodDeclarationWithLink;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.PackageDeclarationWithLink;
import com.github.javaparser.ast.VariableDeclaratorWithLink;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ExpressionWithLink;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.annotation.Nullable;
import indi.uhyils.factory.DeclarationFactory;
import indi.uhyils.factory.ExprFactory;
import indi.uhyils.factory.TypeFactory;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
     * 处理类的package类
     *
     * @param compilationUnit
     * @param allCompilationUnit 所有扫描到的类
     */
    public static void dealCompilationUnitPackage(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> allCompilationUnit) {
        Optional<PackageDeclaration> packageDeclarationOptional = compilationUnit.getPackageDeclaration();
        if (!packageDeclarationOptional.isPresent()) {
            return;
        }

        PackageDeclaration packageDeclaration = packageDeclarationOptional.get();
        PackageDeclarationWithLink packageDeclarationWithLink = new PackageDeclarationWithLink(packageDeclaration);
        List<CompilationUnitWithLink> collect = allCompilationUnit.stream().filter(t -> t.getPackageDeclaration().isPresent()).filter(t -> Objects.equals(t.getPackageDeclaration().get().getName().asString(), packageDeclarationWithLink.getName().asString())).collect(Collectors.toList());
        packageDeclarationWithLink.setOtherCompilationUnits(collect);
        compilationUnit.setPackageDeclaration(packageDeclarationWithLink);
    }

    /**
     * 处理类的import
     *
     * @param compilationUnit
     * @param allCompilationUnit 所有扫描到的类
     */
    public static void dealCompilationUnitImport(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> allCompilationUnit) {
        NodeList<ImportDeclaration> imports = compilationUnit.getImports();
        NodeList<ImportDeclaration> targetList = new NodeList<>();
        for (ImportDeclaration importItem : imports) {
            ImportDeclarationWithLink importDeclarationWithLink = new ImportDeclarationWithLink(importItem);
            String importClassName = importDeclarationWithLink.getName().asString();
            CompilationUnitWithLink compilationUnitWithLink = allCompilationUnit.stream()
                                                                                .filter(
                                                                                    t -> {
                                                                                        PackageDeclaration packageDeclaration = t.getPackageDeclaration().orElse(null);
                                                                                        if (packageDeclaration != null) {
                                                                                            String packageName = packageDeclaration.getName().asString();
                                                                                            return t.getTypes()
                                                                                                    .stream()
                                                                                                    .anyMatch(
                                                                                                        item -> Objects.equals(packageName + "." + item.getName().asString(), importClassName)
                                                                                                    );
                                                                                        } else {
                                                                                            return t.getTypes()
                                                                                                    .stream()
                                                                                                    .anyMatch(
                                                                                                        item -> Objects.equals(item.getName().asString(), importClassName)
                                                                                                    );
                                                                                        }

                                                                                    }
                                                                                )
                                                                                .findFirst()
                                                                                .orElse(null);
            if (compilationUnitWithLink == null) {
                compilationUnitWithLink = new DeclarationFactory().createNotScannedCompilationUnitWithLink(importClassName);
            }
            importDeclarationWithLink.setTargetCompilationUnit(compilationUnitWithLink);
            targetList.add(importDeclarationWithLink);
        }
        compilationUnit.setImports(targetList);
    }

    /**
     * 替换 method
     *
     * @param compilationUnit
     * @param allCompilationUnit 所有扫描到的类
     */
    public static void dealCompilationUnitMethods(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> allCompilationUnit) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            NodeList<BodyDeclaration<?>> targetList = new NodeList<>();
            for (BodyDeclaration<?> member : members) {
                if (!(member instanceof MethodDeclaration)) {
                    targetList.add(member);
                    continue;
                }
                MethodDeclarationWithLink method = new DeclarationFactory().createMethod((MethodDeclaration) member, compilationUnit);
                targetList.add(method);

            }
            type.setMembers(targetList);
        }
    }

    /**
     * 替换 属性
     *
     * @param compilationUnit
     * @param allCompilationUnit 所有扫描到的类
     */
    public static void dealCompilationUnitFields(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> allCompilationUnit) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            NodeList<BodyDeclaration<?>> targetMembers = new NodeList<>();

            for (BodyDeclaration<?> member : members) {
                if (!(member instanceof FieldDeclaration)) {
                    targetMembers.add(member);
                    continue;
                }
                // 处理属性的每一个参数
                FieldDeclarationWithLink fieldDeclarationWithLink = new DeclarationFactory().createField((FieldDeclaration) member, compilationUnit);
                targetMembers.add(fieldDeclarationWithLink);
            }
            type.setMembers(targetMembers);
        }
    }

    /**
     * 替换方法中的每一行
     *
     * @param compilationUnit
     * @param allCompilationUnit 所有扫描到的类
     */
    public static void dealCompilationUnitMethodRow(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> allCompilationUnit) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            List<MethodDeclarationWithLink> methods = type.getMethods().stream().map(t -> (MethodDeclarationWithLink) t).collect(Collectors.toList());
            for (MethodDeclarationWithLink method : methods) {
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
    private static void dealMethodCallExprWithLink(MethodDeclarationWithLink method, CompilationUnitWithLink compilationUnit) {
        Optional<BlockStmt> body = method.getBody();
        if (!body.isPresent()) {
            return;
        }
        BlockStmt blockStmt = body.get();
        NodeList<Statement> statements = blockStmt.getStatements();
        //局部变量记录,用来判断局部变量的类型,用以之后推测方法所在地 <局部变量名称,局部变量>
        Map<String, CompilationUnitWithLink> vars = new HashMap<>();

        // 将入参放置进可用参数列表中
        List<CompilationUnitWithLink> paramTypes = method.getParamTypes();
        NodeList<Parameter> parameters = method.getParameters();
        List<String> parameterNames = parameters.stream().map(t -> t.getName().asString()).collect(Collectors.toList());
        for (int i = 0; i < parameterNames.size(); i++) {
            vars.put(parameterNames.get(i), paramTypes.get(i));
        }

        // 将类属性放入可用参数列表中
        TypeDeclaration<?> typeByNode = compilationUnit.findTypeByNode(method);
        List<FieldDeclarationWithLink> fields = typeByNode.getFields().stream().map(t -> (FieldDeclarationWithLink) t).collect(Collectors.toList());
        for (FieldDeclarationWithLink field : fields) {
            List<VariableDeclaratorWithLink> variables = field.getVariables().stream().map(t -> (VariableDeclaratorWithLink) t).collect(Collectors.toList());
            for (VariableDeclaratorWithLink variable : variables) {
                vars.put(variable.getNameAsString(), variable.getTargetType());
            }
        }

        // 将相同包中的共有静态变量加入变量中去
        Optional<PackageDeclaration> packageDeclarationOptional = compilationUnit.getPackageDeclaration();
        if (packageDeclarationOptional.isPresent()) {
            PackageDeclarationWithLink packageDeclarationWithLink = (PackageDeclarationWithLink) packageDeclarationOptional.get();
            List<CompilationUnitWithLink> otherCompilationUnits = packageDeclarationWithLink.getOtherCompilationUnits();
            for (CompilationUnitWithLink otherCompilationUnit : otherCompilationUnits) {
                Map<String, CompilationUnitWithLink> canUseVariable = findCanUseVariable(otherCompilationUnit);
                vars.putAll(canUseVariable);
            }

        }

        for (Statement statement : statements) {
            // 处理方法中的每一个代码块中的方法
            dealStatementWithLink(compilationUnit, vars, statement);
        }
    }

    /**
     * 根据compilationUnit 去获取可以在局部代码中直接使用的变量
     *
     * @param otherCompilationUnit
     *
     * @return
     */
    public static Map<String, CompilationUnitWithLink> findCanUseVariable(CompilationUnitWithLink otherCompilationUnit) {
        Map<String, CompilationUnitWithLink> vars = new HashMap<>();
        List<VariableDeclarator> otherPackageVariables = new ArrayList<>();
        List<EnumConstantDeclaration> otherPackageEnumConstantDeclaration = new ArrayList<>();
        for (TypeDeclaration<?> typeDeclaration : otherCompilationUnit.getTypes()) {
            if (typeDeclaration.isClassOrInterfaceDeclaration()) {
                ClassOrInterfaceDeclaration classOrInterfaceDeclaration = typeDeclaration.asClassOrInterfaceDeclaration();
                if (classOrInterfaceDeclaration.isInterface()) {
                    List<VariableDeclarator> collect = classOrInterfaceDeclaration.getFields().stream().flatMap(t -> t.getVariables().stream()).collect(Collectors.toList());
                    otherPackageVariables.addAll(collect);
                } else {
                    List<VariableDeclarator> collect = typeDeclaration.getFields().stream().filter(t -> t.isPublic() && t.isStatic()).flatMap(t -> t.getVariables().stream()).collect(Collectors.toList());
                    otherPackageVariables.addAll(collect);
                }
            } else if (typeDeclaration.isEnumDeclaration()) {
                EnumDeclaration enumDeclaration = typeDeclaration.asEnumDeclaration();
                List<EnumConstantDeclaration> entries = enumDeclaration.getEntries();
                otherPackageEnumConstantDeclaration.addAll(entries);
            } else {
                throw new RuntimeException("不支持的类类型:" + typeDeclaration.getClass().getName());
            }
        }
        for (VariableDeclarator variableDeclarator : otherPackageVariables) {
            if (variableDeclarator instanceof VariableDeclaratorWithLink) {
                VariableDeclaratorWithLink variable = (VariableDeclaratorWithLink) variableDeclarator;
                vars.put(variable.getNameAsString(), variable.getTargetType());
            } else {
                LogUtil.error("局部变量转换错误:{}", variableDeclarator.getClass().getName());
            }
        }
        for (EnumConstantDeclaration enumConstantDeclaration : otherPackageEnumConstantDeclaration) {
            EnumDeclaration enumDeclaration = (EnumDeclaration) enumConstantDeclaration.getParentNode().get();
            String classType = enumDeclaration.getName().asString();
            String fieldName = enumConstantDeclaration.getName().asString();
            String finalName = classType + "." + fieldName;
            Node node = enumDeclaration.getParentNode().get();
            if (node instanceof CompilationUnitWithLink) {
                vars.put(finalName, (CompilationUnitWithLink) node);
            } else {
                CompilationUnitWithLink value = new CompilationUnitWithLink();
                vars.put(fieldName, value.setTarget((CompilationUnit) node));
            }
        }
        return vars;
    }

    /**
     * 处理方法中的每一个代码块中的方法
     *
     * @param compilationUnit 所在类
     * @param vars            局部变量表
     * @param statement       代码块
     */
    private static void dealStatementWithLink(CompilationUnitWithLink compilationUnit, Map<String, CompilationUnitWithLink> vars, Statement statement) {
        if (statement.isExpressionStmt()) {
            ExpressionStmt expressionStmt = statement.asExpressionStmt();
            Expression expression = expressionStmt.getExpression();
            dealExpressionWithLink(compilationUnit, vars, expression);
        } else {
            if (statement.isSwitchStmt()) {
                SwitchStmt switchStmt = statement.asSwitchStmt();
                Expression selector = switchStmt.getSelector();
                dealExpressionWithLink(compilationUnit, vars, selector);
                NodeList<SwitchEntry> entries = switchStmt.getEntries();
                List<Statement> collect = entries.stream().flatMap(t -> t.getStatements().stream()).collect(Collectors.toList());
                Map<String, CompilationUnitWithLink> itemVars = new HashMap<>(vars);
                for (Statement itemStatement : collect) {
                    dealStatementWithLink(compilationUnit, itemVars, itemStatement);
                }
            } else if (statement.isIfStmt()) {
                IfStmt ifStmt = statement.asIfStmt();
                Expression condition = ifStmt.getCondition();
                dealExpressionWithLink(compilationUnit, vars, condition);
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
                dealExpressionWithLink(compilationUnit, vars, condition);
                Statement body = whileStmt.getBody();
                dealStatementWithLink(compilationUnit, vars, body);
            } else if (statement.isTryStmt()) {
                TryStmt tryStmt = statement.asTryStmt();
                Map<String, CompilationUnitWithLink> tempVars = new HashMap<>(vars);
                NodeList<Expression> resources = tryStmt.getResources();
                for (Expression resource : resources) {
                    dealExpressionWithLink(compilationUnit, tempVars, resource);
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
                Map<String, CompilationUnitWithLink> itemVars = new HashMap<>(vars);
                for (Statement item : statements) {
                    dealStatementWithLink(compilationUnit, itemVars, item);
                }
            } else if (statement.isForEachStmt()) {
                ForEachStmt forEachStmt = statement.asForEachStmt();
                Expression variable = forEachStmt.getVariable();
                dealExpressionWithLink(compilationUnit, vars, variable);
                Expression iterable = forEachStmt.getIterable();
                dealExpressionWithLink(compilationUnit, vars, iterable);
                Statement body = forEachStmt.getBody();
                dealStatementWithLink(compilationUnit, vars, body);
            } else if (statement.isAssertStmt()) {
                AssertStmt assertStmt = statement.asAssertStmt();
                Expression check = assertStmt.getCheck();
                dealExpressionWithLink(compilationUnit, vars, check);
            } else if (statement.isSynchronizedStmt()) {
                SynchronizedStmt synchronizedStmt = statement.asSynchronizedStmt();
                Expression expression = synchronizedStmt.getExpression();
                dealExpressionWithLink(compilationUnit, vars, expression);
                BlockStmt body = synchronizedStmt.getBody();
                dealStatementWithLink(compilationUnit, vars, body);
            } else if (statement.isReturnStmt()) {
                ReturnStmt returnStmt = statement.asReturnStmt();
                Optional<Expression> expression = returnStmt.getExpression();
                if (expression.isPresent()) {
                    dealExpressionWithLink(compilationUnit, vars, expression.get());
                }
            } else if (statement.isForStmt()) {
                ForStmt forStmt = statement.asForStmt();
                Map<String, CompilationUnitWithLink> tempVars = new HashMap<>(vars);
                NodeList<Expression> initialization = forStmt.getInitialization();
                for (Expression expression : initialization) {
                    dealExpressionWithLink(compilationUnit, tempVars, expression);
                }
                Optional<Expression> compare = forStmt.getCompare();
                if (compare.isPresent()) {
                    dealExpressionWithLink(compilationUnit, tempVars, compare.get());
                }
                NodeList<Expression> update = forStmt.getUpdate();
                for (Expression expression : update) {
                    dealExpressionWithLink(compilationUnit, tempVars, expression);
                }
                Statement body = forStmt.getBody();
                dealStatementWithLink(compilationUnit, tempVars, body);
            } else if (statement.isThrowStmt()) {
                ThrowStmt throwStmt = statement.asThrowStmt();
                Expression expression = throwStmt.getExpression();
                dealExpressionWithLink(compilationUnit, vars, expression);
            } else if (statement.isContinueStmt() || statement.isBreakStmt()) {
                // do nothing
            } else {
                LogUtil.error("类型不正确:{}", statement.getClass().getSimpleName());
            }
        }
    }

    /**
     * 处理各种表达式
     *
     * @param compilationUnit
     * @param vars
     * @param expression
     */
    private static Expression dealExpressionWithLink(CompilationUnitWithLink compilationUnit, Map<String, CompilationUnitWithLink> vars, Expression expression) {
        if (expression.isVariableDeclarationExpr()) {
            VariableDeclarationExpr variableDeclarationExpr = expression.asVariableDeclarationExpr();
            NodeList<VariableDeclarator> variables = variableDeclarationExpr.getVariables();
            for (VariableDeclarator variable : variables) {
                SimpleName name = variable.getName();
                Type type = variable.getType();
                // 判断类型是否是一个可以判断出来的局部变量
                ClassOrInterfaceTypeWithLink withLinkType = judgeTypeClassAndMakeWithLinkType(compilationUnit, type);
                if (withLinkType == null) {
                    continue;
                }
                variable.setType(withLinkType);
                vars.put(name.asString(), withLinkType.getTypeTarget());
            }
            return expression;
        } else {
            if (expression.isMethodCallExpr()) {
                return dealMethodCallExpression(compilationUnit, vars, expression.asMethodCallExpr());
            } else if (expression.isAssignExpr() || expression.isNameExpr() || expression.isNullLiteralExpr() || expression.isBinaryExpr() || expression.isInstanceOfExpr() || expression.isObjectCreationExpr() || expression.isUnaryExpr() || expression.isUnaryExpr() || expression.getClass().getSimpleName().contains("Expr")) {
                // do nothing
            } else {
                LogUtil.error("未知的表达式类型:{}", expression.getClass().getSimpleName());
            }
        }
        return expression;
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
    private static Expression dealMethodCallExpression(CompilationUnitWithLink compilationUnit, Map<String, CompilationUnitWithLink> vars, MethodCallExpr expression) {
        // 如果此方法已经被替换过,则不需要再次替换
        if (expression instanceof MethodCallExprWithLink) {
            return expression;
        }
        MethodCallExpr methodCallExpr = expression.asMethodCallExpr();
        // 方法调用方
        Optional<Expression> scope = methodCallExpr.getScope();
        // 方法名称
        SimpleName methodName = methodCallExpr.getName();
        // 方法入参变量
        NodeList<Expression> arguments = methodCallExpr.getArguments();

        // 1.通过方法调用方判断这个是哪个类的
        CompilationUnit clazz = findCompilationByExpression(compilationUnit, vars, scope);
        if (clazz == null) {
            return expression;
        }
        // 2.通过方法名称判断这个方法是哪些
        // 3.通过方法入参类型确定最终的方法是哪一个
        // 4.注入
        // 相似的方法的名称
        String methodNameStr = methodName.asString();

        // 筛选出来的名称一致的方法
        List<MethodDeclarationWithLink> nameSameMethods = clazz.getTypes()
                                                               .stream()
                                                               .flatMap(t -> t.getMethods().stream())
                                                               .filter(t -> Objects.equals(t.getName().asString(), methodNameStr))
                                                               .map(MethodDeclarationWithLink.class::cast)
                                                               .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(nameSameMethods)) {
            return expression;
        }
        // 寻找代码中表达式调用的方法入参的类型
        List<CompilationUnitWithLink> argumentsClazzList = null;
        try {
            argumentsClazzList = findArgsType(compilationUnit, vars, arguments);
        } catch (Exception e) {
            LogUtil.error(e);
        }

        // 通过方法入参类型确定最终的方法是哪一个
        final List<CompilationUnitWithLink> finalArgumentsClazzList = argumentsClazzList;
        Optional<MethodDeclarationWithLink> first = nameSameMethods.stream().filter(t -> matchingMethodParamsType(finalArgumentsClazzList, t)).findFirst();
        if (first.isPresent()) {
            MethodDeclarationWithLink methodDeclarationWithLink = first.get();
            MethodCallExprWithLink methodCallExprWithLink = new MethodCallExprWithLink(methodCallExpr);
            methodCallExprWithLink.setTargetMethod(methodDeclarationWithLink);
            return methodCallExprWithLink;
        } else {
            // 没有匹配到方法,直接返回了
            return expression;
        }
    }

    @Nullable
    public static CompilationUnitWithLink findCompilationByExpression(CompilationUnitWithLink compilationUnit, Map<String, CompilationUnitWithLink> vars, Optional<Expression> scope) {
        // 调用方的类型
        CompilationUnitWithLink clazz = null;

        if (!scope.isPresent()) {
            clazz = compilationUnit;
        } else {
            Expression scopeExpression = scope.get();
            if (scopeExpression.isNameExpr()) {
                NameExpr nameExpr = scopeExpression.asNameExpr();
                String scopeName = nameExpr.getName().asString();
                // 获取调用方的类型
                clazz = findScopeInVarPool(compilationUnit, vars, scopeName);
            } else if (scopeExpression.isMethodCallExpr()) {
                // 先处理前面的方法,然后获取返回值
                Expression beforeMethod = dealExpressionWithLink(compilationUnit, vars, scopeExpression);
                MethodCallExpr callExpr = beforeMethod.asMethodCallExpr();
                if (callExpr instanceof MethodCallExprWithLink) {
                    MethodCallExprWithLink beforeMethodExpr = (MethodCallExprWithLink) callExpr;
                    MethodDeclarationWithLink targetMethod = beforeMethodExpr.getTargetMethod();
                    clazz = targetMethod.getReturnType();
                }
            } else {
                LogUtil.error("未知的方法调用方:{}", scopeExpression.getClass().getSimpleName());
            }
        }
        return clazz;
    }

    /**
     * 获取代码段中方法调用入参的实际类型
     *
     * @param compilationUnit
     * @param vars
     * @param arguments
     *
     * @return
     */
    @NotNull
    public static List<CompilationUnitWithLink> findArgsType(CompilationUnitWithLink compilationUnit, Map<String, CompilationUnitWithLink> vars, List<Expression> arguments) {
        // 方法的实际入参
        List<CompilationUnitWithLink> argumentsClazzList = new ArrayList<>(arguments.size());
        // 替换入参类型
        for (Expression argument : arguments) {
            argument = dealExpressionWithLink(compilationUnit, vars, argument);
            ExpressionWithLink expressionWithLink = new ExprFactory().createExpressionWithLink(compilationUnit, vars, argument);
            argumentsClazzList.add(expressionWithLink.getReturnCompilationUnitWithLink());
        }
        return argumentsClazzList;
    }


    /**
     * 匹配方法参数类型
     *
     * @param argumentsClazzList
     * @param method
     *
     * @return
     */
    private static boolean matchingMethodParamsType(List<CompilationUnitWithLink> argumentsClazzList, MethodDeclarationWithLink method) {
        List<CompilationUnitWithLink> paramTypes = method.getParamTypes();
        // 其中一个为空,返回匹配不成功
        if (paramTypes == null || argumentsClazzList == null) {
            return false;
        }
        // 方法入参个数不一致,返回匹配不成功
        if (paramTypes.size() != argumentsClazzList.size()) {
            return false;
        }
        for (int i = 0; i < argumentsClazzList.size(); i++) {
            // 注意 这里是入参的类型, 是有可能为null的, 例: 入参是一个方法,并且这个方法不是在扫描类中,就找不到这个方法明确的返回值
            CompilationUnitWithLink compilationUnit1 = argumentsClazzList.get(i);
            CompilationUnitWithLink compilationUnitWithLink = paramTypes.get(i);
            if (compilationUnit1 == null) {
                return false;
            }
            if (!Objects.equals(compilationUnit1.getType(0).getName().asString(), compilationUnitWithLink.getType(0).getName().asString())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取指定名称对应的类
     *
     * @param compilationUnit
     * @param vars
     * @param scopeName
     *
     * @return
     */
    private static CompilationUnitWithLink findScopeInVarPool(CompilationUnitWithLink compilationUnit, Map<String, CompilationUnitWithLink> vars, String scopeName) {

        CompilationUnitWithLink compilationUnitWithLink = vars.get(scopeName);
        if (compilationUnitWithLink != null) {
            return compilationUnitWithLink;
        }

        List<ImportDeclarationWithLink> importDeclarationWithLinks = compilationUnit.getImports().stream().map(t -> (ImportDeclarationWithLink) t).collect(Collectors.toList());
        Map<String, ImportDeclarationWithLink> importDeclarationWithLinkNameMap = importDeclarationWithLinks.stream().collect(Collectors.toMap(t -> t.getName().getIdentifier(), t -> t));
        PackageDeclarationWithLink packageDeclarationWithLink = (PackageDeclarationWithLink) compilationUnit.getPackageDeclaration().orElse(null);
        ImportDeclarationWithLink importDeclarationWithLink = importDeclarationWithLinkNameMap.get(scopeName);
        if (importDeclarationWithLink != null) {
            return importDeclarationWithLink.getTargetCompilationUnit();
        } else {
            List<CompilationUnitWithLink> otherCompilationUnits = packageDeclarationWithLink.getOtherCompilationUnits();
            List<CompilationUnitWithLink> collect = otherCompilationUnits.stream().filter(t -> t.getTypes().stream().anyMatch(s -> Objects.equals(s.getName().asString(), scopeName))).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(collect)) {
                return collect.get(0);
            }
        }
        return null;
    }

    /**
     * 判断type的类型 并且依据这个type创建一个带有连接的type
     *
     * @param compilationUnit
     * @param type
     *
     * @return
     */
    @Nullable
    public static ClassOrInterfaceTypeWithLink judgeTypeClassAndMakeWithLinkType(CompilationUnitWithLink compilationUnit, Type type) {
        if (type instanceof ClassOrInterfaceType) {
            return new TypeFactory().createType((ClassOrInterfaceType) type, compilationUnit);
        } else if (type instanceof PrimitiveType) {
            return transPrimitiveTypeToClassOrInterfaceTypeWithLink(type.asPrimitiveType());
        } else if (type.isArrayType()) {
            ArrayType arrayType = type.asArrayType();
            Type componentType = arrayType.getComponentType();
            return judgeTypeClassAndMakeWithLinkType(compilationUnit, componentType);
        } else {
            LogUtil.error("局部变量类型未找到:{}", type.getClass().getName());
        }
        return null;
    }

    /**
     * 转换基本类型type为classOrInterfaceType
     *
     * @param primitiveType
     *
     * @return
     */
    private static ClassOrInterfaceTypeWithLink transPrimitiveTypeToClassOrInterfaceTypeWithLink(PrimitiveType primitiveType) {
        String primitiveTypeName = primitiveType.getType().asString();
        // 基本类型创建一个就好
        return new TypeFactory().createPrimitiveType(primitiveTypeName);
    }

    public static void print() {
        temp.stream().forEach(System.out::println);
    }
}
