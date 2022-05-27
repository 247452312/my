/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2021 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */
package com.github.javaparser.ast.expr;

import static com.github.javaparser.utils.Utils.assertNotNull;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Generated;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalScope;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.MethodCallExprMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import indi.uhyils.util.CollectionUtil;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * A method call on an object or a class. <br>{@code circle.circumference()} <br>In {@code a.<String>bb(15);}, a
 * is the scope, String is a type argument, bb is the name and 15 is an argument.
 *
 * @author Julio Vilmar Gesser
 */
public class MethodCallExpr extends Expression implements NodeWithTypeArguments<MethodCallExpr>, NodeWithArguments<MethodCallExpr>, NodeWithSimpleName<MethodCallExpr>, NodeWithOptionalScope<MethodCallExpr>, Resolvable<ResolvedMethodDeclaration> {

    @OptionalProperty
    private Expression scope;

    @OptionalProperty
    private NodeList<Type> typeArguments;

    private SimpleName name;

    private NodeList<Expression> arguments;

    private MethodDeclaration methodLink;

    public MethodCallExpr() {
        this(null, null, null, new SimpleName(), new NodeList<>());
    }

    public MethodCallExpr(String name, Expression... arguments) {
        this(null, null, null, new SimpleName(name), new NodeList<>(arguments));
    }

    public MethodCallExpr(final Expression scope, final String name) {
        this(null, scope, null, new SimpleName(name), new NodeList<>());
    }

    public MethodCallExpr(final Expression scope, final SimpleName name) {
        this(null, scope, null, name, new NodeList<>());
    }

    public MethodCallExpr(final Expression scope, final String name, final NodeList<Expression> arguments) {
        this(null, scope, null, new SimpleName(name), arguments);
    }

    public MethodCallExpr(final Expression scope, final NodeList<Type> typeArguments, final String name, final NodeList<Expression> arguments) {
        this(null, scope, typeArguments, new SimpleName(name), arguments);
    }

    public MethodCallExpr(final Expression scope, final SimpleName name, final NodeList<Expression> arguments) {
        this(null, scope, null, name, arguments);
    }

    @AllFieldsConstructor
    public MethodCallExpr(final Expression scope, final NodeList<Type> typeArguments, final SimpleName name, final NodeList<Expression> arguments) {
        this(null, scope, typeArguments, name, arguments);
    }

    /**
     * This constructor is used by the parser and is considered private.
     */
    @Generated("com.github.javaparser.generator.core.node.MainConstructorGenerator")
    public MethodCallExpr(TokenRange tokenRange, Expression scope, NodeList<Type> typeArguments, SimpleName name, NodeList<Expression> arguments) {
        super(tokenRange);
        setScope(scope);
        setTypeArguments(typeArguments);
        setName(name);
        setArguments(arguments);
        customInitialization();
    }

    public Optional<MethodDeclaration> getMethodLink() {
        return Optional.ofNullable(methodLink);
    }

    public void setMethodLink(MethodDeclaration methodLink) {
        this.methodLink = methodLink;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.AcceptGenerator")
    public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
        return v.visit(this, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.AcceptGenerator")
    public <A> void accept(final VoidVisitor<A> v, final A arg) {
        v.visit(this, arg);
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public NodeList<Expression> getArguments() {
        return arguments;
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public MethodCallExpr setArguments(final NodeList<Expression> arguments) {
        assertNotNull(arguments);
        if (arguments == this.arguments) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.ARGUMENTS, this.arguments, arguments);
        if (this.arguments != null) {
            this.arguments.setParentNode(null);
        }
        this.arguments = arguments;
        setAsParentNodeOf(arguments);
        return this;
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public SimpleName getName() {
        return name;
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public MethodCallExpr setName(final SimpleName name) {
        assertNotNull(name);
        if (name == this.name) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.NAME, this.name, name);
        if (this.name != null) {
            this.name.setParentNode(null);
        }
        this.name = name;
        setAsParentNodeOf(name);
        return this;
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public Optional<Expression> getScope() {
        return Optional.ofNullable(scope);
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public MethodCallExpr setScope(final Expression scope) {
        if (scope == this.scope) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.SCOPE, this.scope, scope);
        if (this.scope != null) {
            this.scope.setParentNode(null);
        }
        this.scope = scope;
        setAsParentNodeOf(scope);
        return this;
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public Optional<NodeList<Type>> getTypeArguments() {
        return Optional.ofNullable(typeArguments);
    }

    /**
     * Sets the typeArguments
     *
     * @param typeArguments the typeArguments, can be null
     *
     * @return this, the MethodCallExpr
     */
    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public MethodCallExpr setTypeArguments(final NodeList<Type> typeArguments) {
        if (typeArguments == this.typeArguments) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.TYPE_ARGUMENTS, this.typeArguments, typeArguments);
        if (this.typeArguments != null) {
            this.typeArguments.setParentNode(null);
        }
        this.typeArguments = typeArguments;
        setAsParentNodeOf(typeArguments);
        return this;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.RemoveMethodGenerator")
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i) == node) {
                arguments.remove(i);
                return true;
            }
        }
        if (scope != null) {
            if (node == scope) {
                removeScope();
                return true;
            }
        }
        if (typeArguments != null) {
            for (int i = 0; i < typeArguments.size(); i++) {
                if (typeArguments.get(i) == node) {
                    typeArguments.remove(i);
                    return true;
                }
            }
        }
        return super.remove(node);
    }

    @Generated("com.github.javaparser.generator.core.node.RemoveMethodGenerator")
    public MethodCallExpr removeScope() {
        return setScope((Expression) null);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.CloneGenerator")
    public MethodCallExpr clone() {
        return (MethodCallExpr) accept(new CloneVisitor(), null);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.GetMetaModelGenerator")
    public MethodCallExprMetaModel getMetaModel() {
        return JavaParserMetaModel.methodCallExprMetaModel;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.ReplaceMethodGenerator")
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i) == node) {
                arguments.set(i, (Expression) replacementNode);
                return true;
            }
        }
        if (node == name) {
            setName((SimpleName) replacementNode);
            return true;
        }
        if (scope != null) {
            if (node == scope) {
                setScope((Expression) replacementNode);
                return true;
            }
        }
        if (typeArguments != null) {
            for (int i = 0; i < typeArguments.size(); i++) {
                if (typeArguments.get(i) == node) {
                    typeArguments.set(i, (Type) replacementNode);
                    return true;
                }
            }
        }
        return super.replace(node, replacementNode);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public boolean isMethodCallExpr() {
        return true;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public MethodCallExpr asMethodCallExpr() {
        return this;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public void ifMethodCallExpr(Consumer<MethodCallExpr> action) {
        action.accept(this);
    }

    /**
     * Attempts to resolve the declaration corresponding to the invoked method. If successful, a
     * {@link ResolvedMethodDeclaration} representing the declaration of the constructor invoked by this
     * {@code MethodCallExpr} is returned. Otherwise, an {@link UnsolvedSymbolException} is thrown.
     *
     * @return a {@link ResolvedMethodDeclaration} representing the declaration of the invoked method.
     *
     * @throws UnsolvedSymbolException if the declaration corresponding to the method call expression could not be
     *                                 resolved.
     * @see NameExpr#resolve()
     * @see FieldAccessExpr#resolve()
     * @see ObjectCreationExpr#resolve()
     * @see ExplicitConstructorInvocationStmt#resolve()
     */
    @Override
    public ResolvedMethodDeclaration resolve() {
        return getSymbolResolver().resolveDeclaration(this, ResolvedMethodDeclaration.class);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public Optional<MethodCallExpr> toMethodCallExpr() {
        return Optional.of(this);
    }

    /*
     * A method invocation expression is a poly expression if all of the following are true:
     * 1. The invocation appears in an assignment context or an invocation context (§5.2, §5.3).
     * 2. If the invocation is qualified (that is, any form of MethodInvocation except for the first), then
     *    the invocation elides TypeArguments to the left of the Identifier.
     * 3. The method to be invoked, as determined by the following subsections, is generic (§8.4.4) and has a
     *    return type that mentions at least one of the method's type parameters.
     * Otherwise, the method invocation expression is a standalone expression.
     */
    @Override
    public boolean isPolyExpression() {
        // A method invocation expression is a poly expression if all of the following are true:
        //
        // 1. The invocation appears in an assignment context or an invocation context (§5.2, §5.3).
        if (!(appearsInAssignmentContext() || appearsInInvocationContext())) {
            return false;
        }
        // 2. If the invocation is qualified (that is, any form of MethodInvocation except for the form [MethodName (
        // [ArgumentList] )]), then the invocation elides TypeArguments to the left of the Identifier.
        if (isQualified() && !elidesTypeArguments()) {
            return false;
        }
        // 3. The method to be invoked, as determined by the following subsections, is generic (§8.4.4) and has a
        // return type that mentions at least one of the method's type parameters.
        // A method is generic if it declares one or more type variables (§4.4).
        if (isGenericMethod() && hasParameterwithSameTypeThanResultType(resolve().getReturnType())) {
            // it's a poly expression
            return true;
        }
        // Otherwise, the method invocation expression is a standalone expression.
        return false;
    }

    @Override
    public void dealSelf(CompilationUnit compilationUnit, Map<String, TypeDeclaration<?>> vars) {
        // 方法调用方
        Optional<Expression> scope = this.getScope();
        // 方法名称
        SimpleName methodName = this.getName();
        // 方法入参变量
        NodeList<Expression> arguments = this.getArguments();

        // 解析入参先
        for (Expression argument : arguments) {
            argument.dealSelf(compilationUnit, vars);
        }
        // 1.通过方法调用方判断这个是哪个类的
        TypeDeclaration<?> scopeReturnType;
        if (scope.isPresent()) {
            Expression scopeExpression = scope.get();
            scopeExpression.dealSelf(compilationUnit, vars);
            scopeReturnType = scopeExpression.getReturnType().orElse(null);
        } else {
            scopeReturnType = compilationUnit.findTypeByNode(this);
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
            this.setMethodLink(methodDeclarationWithLink);
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
    /*
     * Returns true if the expression is an invocation context.
     * https://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html#jls-5.3
     * 5.3. Invocation Contexts
     */
    @Override
    protected boolean isInvocationContext() {
        return true;
    }

    /*
     *  A method is generic if it declares one or more type variables (§4.4).
     *  Not sure it's enough to verify that the type arguments list is empty or not.
     */
    private boolean isGenericMethod() {
        return getTypeArguments().isPresent() && !getTypeArguments().get().isEmpty();
    }

    /*
     *  return true if at least one of the method's type parameters has the same type as the specified type .
     */
    private boolean hasParameterwithSameTypeThanResultType(ResolvedType resolvedReturnType) {
        return getTypeArguments().isPresent() && getTypeArguments().get().stream().anyMatch(argType -> argType.resolve().isAssignableBy(resolvedReturnType));
    }
}
