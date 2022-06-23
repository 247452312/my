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
package com.github.javaparser.ast;

import static com.github.javaparser.JavaToken.Kind.EOF;
import static com.github.javaparser.Providers.UTF8;
import static com.github.javaparser.Providers.provider;
import static com.github.javaparser.Range.range;
import static com.github.javaparser.StaticJavaParser.parseImport;
import static com.github.javaparser.StaticJavaParser.parseName;
import static com.github.javaparser.ast.Modifier.createModifierList;
import static com.github.javaparser.utils.CodeGenerationUtils.subtractPaths;
import static com.github.javaparser.utils.Utils.assertNotNull;

import com.github.javaparser.AstContext;
import com.github.javaparser.JavaParser;
import com.github.javaparser.JavaToken;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParseStart;
import com.github.javaparser.Position;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.CompilationUnitMetaModel;
import com.github.javaparser.metamodel.InternalProperty;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.utils.ClassUtils;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.Utils;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.annotation.Nullable;
import indi.uhyils.util.StringUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * This class represents the entire compilation unit. Each java file denotes a
 * compilation unit.
 * </p>
 * A compilation unit start with an optional package declaration,
 * followed by zero or more import declarations,
 * followed by zero or more type declarations.
 *
 * @author Julio Vilmar Gesser
 * @see PackageDeclaration
 * @see ImportDeclaration
 * @see TypeDeclaration
 * @see Storage
 */
public class CompilationUnit extends Node {

    private static final String JAVA_LANG = "java.lang";

    @OptionalProperty
    private PackageDeclaration packageDeclaration;

    private NodeList<ImportDeclaration> imports;

    private NodeList<TypeDeclaration<?>> types;

    @OptionalProperty
    private ModuleDeclaration module;

    @InternalProperty
    private Storage storage;

    public CompilationUnit() {
        this(null, null, new NodeList<>(), new NodeList<>(), null);
    }

    public CompilationUnit(String packageDeclaration) {
        this(null, new PackageDeclaration(new Name(packageDeclaration)), new NodeList<>(), new NodeList<>(), null);
    }

    @AllFieldsConstructor
    public CompilationUnit(PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration<?>> types, ModuleDeclaration module) {
        this(null, packageDeclaration, imports, types, module);
    }

    /**
     * This constructor is used by the parser and is considered private.
     */
    @Generated("com.github.javaparser.generator.core.node.MainConstructorGenerator")
    public CompilationUnit(TokenRange tokenRange, PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration<?>> types, ModuleDeclaration module) {
        super(tokenRange);
        setPackageDeclaration(packageDeclaration);
        setImports(imports);
        setTypes(types);
        setModule(module);
        customInitialization();
    }

    private static Optional<Name> getImportPackageName(ImportDeclaration importDeclaration) {
        return (importDeclaration.isAsterisk() ? new Name(importDeclaration.getName(), "*") : importDeclaration.getName()).getQualifier();
    }


    /**
     * 根据compilationUnit 去获取可以在局部代码中直接使用的变量
     *
     *
     * @return
     */
    public Map<String, TypeDeclaration<?>> findCanUseVariable() {
        Map<String, TypeDeclaration<?>> result = new HashMap<>();

        // 1.同包中其他类中的非私有静态变量
        // 2.util包中静态变量
        // 3.同包中其他枚举类的枚举
        // 4.util包中的枚举

        // 静态变量
        List<VariableDeclarator> otherPackageVariables = new ArrayList<>();
        // 枚举
        List<EnumConstantDeclaration> otherPackageEnumConstantDeclaration = new ArrayList<>();

        for (TypeDeclaration<?> typeDeclaration : types) {
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
     * 替换 属性
     */
    public void dealFields() {
        for (TypeDeclaration<?> type : types) {
            for (FieldDeclaration member : type.getFields()) {
                NodeList<VariableDeclarator> variables = member.getVariables();

                for (VariableDeclarator variable : variables) {
                    Type variableType = variable.getType();
                    fillTypeTargetByAllCompilationUnit(variableType);
                }
            }
        }
    }


    /**
     * 替换 method
     */
    public void dealMethods() {
        for (TypeDeclaration<?> type : types) {
            List<MethodDeclaration> methods = type.getMethods();
            for (MethodDeclaration method : methods) {
                Type returnType = method.getType();
                fillTypeTargetByAllCompilationUnit(returnType);
                for (Parameter parameter : method.getParameters()) {
                    Type parameterType = parameter.getType();
                    fillTypeTargetByAllCompilationUnit(parameterType);
                }
            }
        }
    }


    /**
     * 替换方法中的每一行
     */
    public void dealMethodRow() {
        for (TypeDeclaration<?> type : types) {
            type.dealMethodRow(this);

        }
    }

    /**
     * 替换填充继承以及接口实现
     */
    public void dealExtend() {
        for (TypeDeclaration<?> type : types) {
            if (type.isClassOrInterfaceDeclaration()) {
                ClassOrInterfaceDeclaration classOrInterfaceDeclaration = type.asClassOrInterfaceDeclaration();
                // 接口允许多继承
                List<ClassOrInterfaceType> extendedTypes = classOrInterfaceDeclaration.getExtendedTypes();
                List<ClassOrInterfaceType> implementedTypes = classOrInterfaceDeclaration.getImplementedTypes();
                for (ClassOrInterfaceType extendedType : extendedTypes) {
                    Optional<TypeDeclaration<?>> typeDeclaration = extendedType.fillTargetByCompilationUnit(this);
                    typeDeclaration.ifPresent(t -> t.addChild(type));
                }
                for (ClassOrInterfaceType implementedType : implementedTypes) {
                    Optional<TypeDeclaration<?>> typeDeclaration = implementedType.fillTargetByCompilationUnit(this);
                    typeDeclaration.ifPresent(t -> t.addChild(type));
                }

            }
        }
    }

    /**
     * 处理类的import
     *
     * @param allCompilationUnit 所有扫描到的类
     */
    public void dealImport(List<CompilationUnit> allCompilationUnit) {
        List<String> importName = imports.stream().map(NodeWithName::getNameAsString).distinct().collect(Collectors.toList());

        // 预过滤, 只留下同一个package的 以及 import的类
        List<TypeDeclaration<?>> filterType = allCompilationUnit.stream()
                                                                .flatMap(t -> t.getTypes().stream())
                                                                .filter(t -> {
                                                                    Optional<CompilationUnit> typeCompilationUnit = t.findCompilationUnit();
                                                                    if (!typeCompilationUnit.isPresent()) {
                                                                        return false;
                                                                    }
                                                                    PackageDeclaration packageDeclaration = typeCompilationUnit.get().getPackageDeclaration().orElse(null);
                                                                    if (packageDeclaration != null) {
                                                                        String packageName = packageDeclaration.getName().asString();
                                                                        return importName.contains(packageName + "." + t.getName().asString());
                                                                    } else {
                                                                        return importName.contains(t.getName().asString());
                                                                    }
                                                                })
                                                                .collect(Collectors.toList());
        for (ImportDeclaration importItem : imports) {
            String importClassName = importItem.getName().asString();
            // 找到目标类型, 如果没有,就新建一个
            TypeDeclaration<?> typeDeclaration = filterType.stream()
                                                           .filter(
                                                               t -> {
                                                                   Optional<CompilationUnit> typeCompilationUnit = t.findCompilationUnit();
                                                                   if (!typeCompilationUnit.isPresent()) {
                                                                       return false;
                                                                   }
                                                                   PackageDeclaration packageDeclaration = typeCompilationUnit.get().getPackageDeclaration().orElse(null);
                                                                   if (packageDeclaration != null) {
                                                                       String packageName = packageDeclaration.getName().asString();
                                                                       return Objects.equals(packageName + "." + t.getName().asString(), importClassName);
                                                                   } else {
                                                                       return Objects.equals(t.getName().asString(), importClassName);
                                                                   }

                                                               }
                                                           )
                                                           .findFirst()
                                                           .orElse(null);
            if (typeDeclaration == null) {
                typeDeclaration = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(importItem.getName().getQualifier().map(Name::asString).orElse(null), importItem.getName().getIdentifier());
                AstContext.addCache(typeDeclaration);
            }
            importItem.setTargetType(typeDeclaration);
        }
    }

    /**
     * 处理类的package类
     *
     * @param allCompilationUnit 所有扫描到的类
     */
    public void dealPackage(List<CompilationUnit> allCompilationUnit) {
        Optional<PackageDeclaration> packageDeclarationOptional = this.getPackageDeclaration();
        if (!packageDeclarationOptional.isPresent()) {
            return;
        }

        PackageDeclaration packageDeclaration = packageDeclarationOptional.get();
        // 筛选出同一个package的
        List<CompilationUnit> collect = allCompilationUnit.stream()
                                                          .filter(t -> t.getPackageDeclaration().isPresent())
                                                          .filter(t -> Objects.equals(t.getPackageDeclaration().get().getName().asString(),
                                                                                      packageDeclaration.getName().asString()))
                                                          .collect(Collectors.toList());
        packageDeclaration.setOtherCompilationUnits(collect);
    }

    /**
     * 根据node获取对应的type
     *
     * @param node
     *
     * @return
     */
    @Nullable
    public TypeDeclaration<?> findTypeByNode(Node node) {
        NodeList<TypeDeclaration<?>> types = this.getTypes();
        if (types.size() == 1) {
            return types.get(0);
        }
        Optional<Node> parentNode = node.getParentNode();
        while (parentNode.isPresent()) {
            Optional<Node> parentNode1 = parentNode.get().getParentNode();
            if (parentNode1.isPresent() && parentNode1.get() == this) {
                return (TypeDeclaration<?>) parentNode1.get();
            }
            parentNode = parentNode1;
        }
        return null;
    }

    /**
     * 寻找 typeAllName
     *
     * @param typeAllName
     *
     * @return
     */
    @NotNull
    public Optional<TypeDeclaration<?>> findTypeDeclaration(String typeAllName) {
        typeAllName = StringUtil.removeGenericsFromClassNames(typeAllName);
        Map<String, TypeDeclaration<?>> allCompilationUnitMap = AstContext.getAllCompilationUnitMap();
        if (typeAllName.contains(".") && allCompilationUnitMap.containsKey(typeAllName)) {
            return Optional.ofNullable(allCompilationUnitMap.get(typeAllName));
        } else {
            List<CompilationUnit> compilationUnits = findCompilationUnit(Collections.singletonList(typeAllName));
            CompilationUnit compilationUnit = compilationUnits.get(0);
            String finalTypeAllName = typeAllName;
            return compilationUnit.getTypes().stream().filter(t -> Objects.equals(t.getNameAsString(), finalTypeAllName)).findFirst();
        }
    }

    /**
     * 寻找typeSimpleName
     *
     * @param type
     *
     * @return
     */
    @NotNull
    public Optional<TypeDeclaration<?>> findTypeDeclaration(Type type) {
        return findTypeDeclaration(type.asString());
    }

    public List<CompilationUnit> findCompilationUnit(List<String> typeSimpleNames) {
        List<CompilationUnit> links = new ArrayList<>();
        for (String typeSimpleName : typeSimpleNames) {
            CompilationUnit targetCompilationUnit = null;
            // 1.从import中寻找
            for (ImportDeclaration importItem : imports) {
                String identifier = importItem.getName().getIdentifier();
                if (Objects.equals(identifier, typeSimpleName)) {
                    targetCompilationUnit = importItem.getTargetType().flatMap(Node::findCompilationUnit).orElse(null);
                    break;
                }
            }
            // 2.从同package中寻找
            if (targetCompilationUnit == null) {
                if (packageDeclaration != null) {
                    List<CompilationUnit> otherCompilationUnits = packageDeclaration.getOtherCompilationUnits();
                    targetCompilationUnit = otherCompilationUnits.stream()
                                                                 .filter(
                                                                     t -> t.getTypes()
                                                                           .stream()
                                                                           // 同package中 sampleName相同即可 不需要package前缀
                                                                           .anyMatch(s -> Objects.equals(s.getName().asString(), typeSimpleName))
                                                                 )
                                                                 .findFirst()
                                                                 .orElse(null);

                }
            }
            if (targetCompilationUnit == null) {
                targetCompilationUnit = createNotScannedCompilationUnit("java.util", typeSimpleName);
            }
            links.add(targetCompilationUnit);
        }
        return links;
    }

    /**
     * 创建一个未被扫描到的文件
     *
     * @param name 类全名
     *
     * @return
     */
    public CompilationUnit createNotScannedCompilationUnit(String packageName, String name) {
        TypeDeclaration<?> notScannedTypeDeclaration = TypeDeclaration.createNotScannedTypeDeclarationAndAddCache(packageName, name);
        return notScannedTypeDeclaration.findCompilationUnit().orElse(null);
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

    /**
     * Declare a specific printer
     */
    public CompilationUnit printer(Printer printer) {
        setData(PRINTER_KEY, printer);
        return this;
    }

    /**
     * @deprecated getComments was a too generic name and it could be confused with getComment
     * or getAllContainedComments
     * Use {@link #getAllComments()} instead
     */
    @Deprecated
    public List<Comment> getComments() {
        List<Comment> comments = this.getAllContainedComments();
        this.getComment().ifPresent(comments::add);
        return comments;
    }

    /**
     * Return a list containing all comments declared in this compilation unit.
     * Including javadocs, line comments and block comments of all types,
     * inner-classes and other members.<br>
     * If there is no comment, an empty list is returned.
     *
     * @return list with all comments of this compilation unit.
     *
     * @see JavadocComment
     * @see com.github.javaparser.ast.comments.LineComment
     * @see com.github.javaparser.ast.comments.BlockComment
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = this.getAllContainedComments();
        this.getComment().ifPresent(comments::add);
        return comments;
    }

    /**
     * Retrieves the list of imports declared in this compilation unit or
     * {@code null} if there is no import.
     *
     * @return the list of imports or {@code none} if there is no import
     */
    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public NodeList<ImportDeclaration> getImports() {
        return imports;
    }

    /**
     * Sets the list of imports of this compilation unit. The list is initially
     * {@code null}.
     *
     * @param imports the list of imports
     */
    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public CompilationUnit setImports(final NodeList<ImportDeclaration> imports) {
        assertNotNull(imports);
        if (imports == this.imports) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.IMPORTS, this.imports, imports);
        if (this.imports != null) {
            this.imports.setParentNode(null);
        }
        this.imports = imports;
        setAsParentNodeOf(imports);
        return this;
    }

    public ImportDeclaration getImport(int i) {
        return getImports().get(i);
    }

    /**
     * Retrieves the package declaration of this compilation unit.<br>
     * If this compilation unit has no package declaration (default package),
     * {@code Optional.none()} is returned.
     *
     * @return the package declaration or {@code none}
     */
    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public Optional<PackageDeclaration> getPackageDeclaration() {
        return Optional.ofNullable(packageDeclaration);
    }

    /**
     * Sets or clear the package declarations of this compilation unit.
     *
     * @param packageDeclaration the packageDeclaration declaration to set or {@code null} to default package
     */
    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public CompilationUnit setPackageDeclaration(final PackageDeclaration packageDeclaration) {
        if (packageDeclaration == this.packageDeclaration) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.PACKAGE_DECLARATION, this.packageDeclaration, packageDeclaration);
        if (this.packageDeclaration != null) {
            this.packageDeclaration.setParentNode(null);
        }
        this.packageDeclaration = packageDeclaration;
        setAsParentNodeOf(packageDeclaration);
        return this;
    }

    /**
     * sets the package declaration of this compilation unit
     *
     * @param name the name of the package
     *
     * @return this, the {@link CompilationUnit}
     */
    public CompilationUnit setPackageDeclaration(String name) {
        setPackageDeclaration(new PackageDeclaration(parseName(name)));
        return this;
    }

    /**
     * Return the list of top level types declared in this compilation unit.<br>
     * If there are no types declared, {@code none} is returned.
     *
     * @return the list of types or {@code none} null if there is no type
     *
     * @see AnnotationDeclaration
     * @see ClassOrInterfaceDeclaration
     * @see EnumDeclaration
     */
    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public NodeList<TypeDeclaration<?>> getTypes() {
        return types;
    }

    /**
     * Sets the list of types declared in this compilation unit.
     */
    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public CompilationUnit setTypes(final NodeList<TypeDeclaration<?>> types) {
        assertNotNull(types);
        if (types == this.types) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.TYPES, this.types, types);
        if (this.types != null) {
            this.types.setParentNode(null);
        }
        this.types = types;
        setAsParentNodeOf(types);
        return this;
    }

    /**
     * Convenience method that wraps {@code getTypes()}.<br>
     * If {@code i} is out of bounds, throws <code>IndexOutOfBoundsException.</code>
     *
     * @param i the index of the type declaration to retrieve
     */
    public TypeDeclaration<?> getType(int i) {
        return getTypes().get(i);
    }

    public CompilationUnit setImport(int i, ImportDeclaration imports) {
        getImports().set(i, imports);
        return this;
    }

    /**
     * adds an import if not implicitly imported by java (i.e. java.lang) or
     * added before. Asterisk imports overrule the other imports within the same package.
     *
     * @param importDeclaration
     *
     * @return {@code this}
     */
    public CompilationUnit addImport(ImportDeclaration importDeclaration) {
        if (importDeclaration.isAsterisk()) {
            getImports().removeIf(im -> Objects.equals(getImportPackageName(im).get(), getImportPackageName(importDeclaration).orElse(null)));
        }
        if (!isImplicitImport(importDeclaration) && getImports().stream().noneMatch(im -> im.equals(importDeclaration) || (im.isAsterisk() && Objects.equals(getImportPackageName(im).get(), getImportPackageName(importDeclaration).orElse(null))))) {
            getImports().add(importDeclaration);
        }
        return this;
    }

    public CompilationUnit setType(int i, TypeDeclaration<?> type) {
        NodeList<TypeDeclaration<?>> copy = new NodeList<>();
        copy.addAll(getTypes());
        getTypes().set(i, type);
        notifyPropertyChange(ObservableProperty.TYPES, copy, types);
        return this;
    }

    public CompilationUnit addType(TypeDeclaration<?> type) {
        NodeList<TypeDeclaration<?>> copy = new NodeList<>();
        copy.addAll(getTypes());
        getTypes().add(type);
        notifyPropertyChange(ObservableProperty.TYPES, copy, types);
        return this;
    }

    /**
     * Add an import to the list of {@link ImportDeclaration} of this compilation unit<br>
     * shorthand for {@link #addImport(String, boolean, boolean)} with name,false,false
     *
     * @param name the import name
     *
     * @return this, the {@link CompilationUnit}
     */
    public CompilationUnit addImport(String name) {
        return addImport(name, false, false);
    }

    /**
     * Add an import to the list of {@link ImportDeclaration} of this compilation unit<br>
     * shorthand for {@link #addImport(String)} with clazz.getName()
     *
     * @param clazz the class to import
     *
     * @return this, the {@link CompilationUnit}
     *
     * @throws IllegalArgumentException if clazz is an anonymous or local class
     */
    public CompilationUnit addImport(Class<?> clazz) {
        if (clazz.isArray()) {
            return addImport(clazz.getComponentType());
        }
        if (ClassUtils.isPrimitiveOrWrapper(clazz) || JAVA_LANG.equals(clazz.getPackage().getName())) {
            return this;
        } else if (clazz.isAnonymousClass() || clazz.isLocalClass()) {
            throw new IllegalArgumentException(clazz.getName() + " is an anonymous or local class therefore it can't be added with addImport");
        }
        return addImport(clazz.getCanonicalName());
    }

    /**
     * Add an import to the list of {@link ImportDeclaration} of this compilation unit<br>
     * <b>This method check if no import with the same name is already in the list</b>
     *
     * @param name       the import name
     * @param isStatic   is it an "import static"
     * @param isAsterisk does the import end with ".*"
     *
     * @return this, the {@link CompilationUnit}
     */
    public CompilationUnit addImport(String name, boolean isStatic, boolean isAsterisk) {
        if (name == null) {
            return this;
        }
        final StringBuilder i = new StringBuilder("import ");
        if (isStatic) {
            i.append("static ");
        }
        i.append(name);
        if (isAsterisk) {
            i.append(".*");
        }
        i.append(";");
        return addImport(parseImport(i.toString()));
    }

    /**
     * Add a public class to the types of this compilation unit
     *
     * @param name the class name
     *
     * @return the newly created class
     */
    public ClassOrInterfaceDeclaration addClass(String name) {
        return addClass(name, Modifier.Keyword.PUBLIC);
    }

    /**
     * Add a class to the types of this compilation unit
     *
     * @param name      the class name
     * @param modifiers the modifiers (like Modifier.PUBLIC)
     *
     * @return the newly created class
     */
    public ClassOrInterfaceDeclaration addClass(String name, Modifier.Keyword... modifiers) {
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = new ClassOrInterfaceDeclaration(createModifierList(modifiers), false, name);
        getTypes().add(classOrInterfaceDeclaration);
        return classOrInterfaceDeclaration;
    }

    /**
     * Add a public interface class to the types of this compilation unit
     *
     * @param name the interface name
     *
     * @return the newly created class
     */
    public ClassOrInterfaceDeclaration addInterface(String name) {
        return addInterface(name, Modifier.Keyword.PUBLIC);
    }

    /**
     * Add an interface to the types of this compilation unit
     *
     * @param name      the interface name
     * @param modifiers the modifiers (like Modifier.PUBLIC)
     *
     * @return the newly created class
     */
    public ClassOrInterfaceDeclaration addInterface(String name, Modifier.Keyword... modifiers) {
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = new ClassOrInterfaceDeclaration(createModifierList(modifiers), true, name);
        getTypes().add(classOrInterfaceDeclaration);
        return classOrInterfaceDeclaration;
    }

    /**
     * Add a public enum to the types of this compilation unit
     *
     * @param name the enum name
     *
     * @return the newly created class
     */
    public EnumDeclaration addEnum(String name) {
        return addEnum(name, Modifier.Keyword.PUBLIC);
    }

    /**
     * Add an enum to the types of this compilation unit
     *
     * @param name      the enum name
     * @param modifiers the modifiers (like Modifier.PUBLIC)
     *
     * @return the newly created class
     */
    public EnumDeclaration addEnum(String name, Modifier.Keyword... modifiers) {
        EnumDeclaration enumDeclaration = new EnumDeclaration(createModifierList(modifiers), name);
        getTypes().add(enumDeclaration);
        return enumDeclaration;
    }

    /**
     * Add a public annotation declaration to the types of this compilation unit
     *
     * @param name the annotation name
     *
     * @return the newly created class
     */
    public AnnotationDeclaration addAnnotationDeclaration(String name) {
        return addAnnotationDeclaration(name, Modifier.Keyword.PUBLIC);
    }

    /**
     * Add an annotation declaration to the types of this compilation unit
     *
     * @param name      the annotation name
     * @param modifiers the modifiers (like Modifier.PUBLIC)
     *
     * @return the newly created class
     */
    public AnnotationDeclaration addAnnotationDeclaration(String name, Modifier.Keyword... modifiers) {
        AnnotationDeclaration annotationDeclaration = new AnnotationDeclaration(createModifierList(modifiers), name);
        getTypes().add(annotationDeclaration);
        return annotationDeclaration;
    }

    /**
     * Try to get a top level class declaration by its name
     *
     * @param className the class name (case-sensitive)
     */
    public Optional<ClassOrInterfaceDeclaration> getClassByName(String className) {
        return getTypes().stream().filter(type -> type.getNameAsString().equals(className) && type instanceof ClassOrInterfaceDeclaration && !((ClassOrInterfaceDeclaration) type).isInterface()).findFirst().map(t -> (ClassOrInterfaceDeclaration) t);
    }

    /**
     * Try to get all local class declarations ending by its name (top level or inner class)
     *
     * @param className the class name (case-sensitive)
     */
    public List<ClassOrInterfaceDeclaration> getLocalDeclarationFromClassname(String className) {
        return findAll(ClassOrInterfaceDeclaration.class).stream().filter(cid -> cid.getFullyQualifiedName().get().endsWith(className)).collect(Collectors.toList());
    }

    /**
     * Try to get a top level interface declaration by its name
     *
     * @param interfaceName the interface name (case-sensitive)
     */
    public Optional<ClassOrInterfaceDeclaration> getInterfaceByName(String interfaceName) {
        return getTypes().stream().filter(type -> type.getNameAsString().equals(interfaceName) && type instanceof ClassOrInterfaceDeclaration && ((ClassOrInterfaceDeclaration) type).isInterface()).findFirst().map(t -> (ClassOrInterfaceDeclaration) t);
    }

    /**
     * Try to get a top level enum declaration by its name
     *
     * @param enumName the enum name (case-sensitive)
     */
    public Optional<EnumDeclaration> getEnumByName(String enumName) {
        return getTypes().stream().filter(type -> type.getNameAsString().equals(enumName) && type instanceof EnumDeclaration).findFirst().map(t -> (EnumDeclaration) t);
    }

    /**
     * @return the name that the primary type in this file should have, according to the filename in {@link Storage#getFileName()}.
     * Empty if no file information is present (when this compilation unit wasn't parsed from a file.)
     */
    public Optional<String> getPrimaryTypeName() {
        return getStorage().map(Storage::getFileName).map(Utils::removeFileExtension);
    }

    /**
     * @return the type whose name corresponds to the file name.
     * Empty if no file information is present (when this compilation unit wasn't parsed from a file.)
     * If for some strange reason there are multiple types of this name, the first one is returned.
     */
    public Optional<TypeDeclaration<?>> getPrimaryType() {
        return getPrimaryTypeName().flatMap(name -> getTypes().stream().filter(t -> t.getNameAsString().equals(name)).findFirst());
    }

    /**
     * Try to get a top level annotation type declaration by its name
     *
     * @param annotationName the annotation name (case-sensitive)
     */
    public Optional<AnnotationDeclaration> getAnnotationDeclarationByName(String annotationName) {
        return getTypes().stream().filter(type -> type.getNameAsString().equals(annotationName) && type instanceof AnnotationDeclaration).findFirst().map(t -> (AnnotationDeclaration) t);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.RemoveMethodGenerator")
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < imports.size(); i++) {
            if (imports.get(i) == node) {
                imports.remove(i);
                return true;
            }
        }
        if (module != null) {
            if (node == module) {
                removeModule();
                return true;
            }
        }
        if (packageDeclaration != null) {
            if (node == packageDeclaration) {
                removePackageDeclaration();
                return true;
            }
        }
        for (int i = 0; i < types.size(); i++) {
            if (types.get(i) == node) {
                types.remove(i);
                return true;
            }
        }
        return super.remove(node);
    }

    @Generated("com.github.javaparser.generator.core.node.RemoveMethodGenerator")
    public CompilationUnit removePackageDeclaration() {
        return setPackageDeclaration((PackageDeclaration) null);
    }

    /**
     * @return the module declared in this compilation unit.
     */
    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public Optional<ModuleDeclaration> getModule() {
        return Optional.ofNullable(module);
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public CompilationUnit setModule(final ModuleDeclaration module) {
        if (module == this.module) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.MODULE, this.module, module);
        if (this.module != null) {
            this.module.setParentNode(null);
        }
        this.module = module;
        setAsParentNodeOf(module);
        return this;
    }

    @Generated("com.github.javaparser.generator.core.node.RemoveMethodGenerator")
    public CompilationUnit removeModule() {
        return setModule((ModuleDeclaration) null);
    }

    /**
     * @return information about where this compilation unit was loaded from, or empty if it wasn't loaded from a file.
     */
    public Optional<Storage> getStorage() {
        return Optional.ofNullable(storage);
    }

    public CompilationUnit setStorage(Path path) {
        this.storage = new Storage(this, path);
        return this;
    }

    public CompilationUnit setStorage(Path path, Charset charset) {
        this.storage = new Storage(this, path, charset);
        return this;
    }

    /**
     * Create (or overwrite) a module declaration in this compilation unit with name "name".
     *
     * @return the module
     */
    public ModuleDeclaration setModule(String name) {
        final ModuleDeclaration module = new ModuleDeclaration(parseName(name), false);
        setModule(module);
        return module;
    }

    /**
     * Recalculates the ranges of all nodes by looking at the sizes of the tokens.
     * This is useful when you have manually inserted or deleted tokens and still want to use the ranges.
     */
    public void recalculatePositions() {
        if (!getTokenRange().isPresent()) {
            throw new IllegalStateException("Can't recalculate positions without tokens.");
        }
        Position cursor = Position.HOME;
        for (JavaToken t : getTokenRange().get()) {
            int tokenLength = t.getKind() == EOF.getKind() ? 0 : t.getText().length() - 1;
            t.setRange(range(cursor, cursor.right(tokenLength)));
            if (t.getCategory().isEndOfLine()) {
                cursor = cursor.nextLine();
            } else {
                cursor = cursor.right(tokenLength + 1);
            }
        }
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.CloneGenerator")
    public CompilationUnit clone() {
        return (CompilationUnit) accept(new CloneVisitor(), null);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.GetMetaModelGenerator")
    public CompilationUnitMetaModel getMetaModel() {
        return JavaParserMetaModel.compilationUnitMetaModel;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.ReplaceMethodGenerator")
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < imports.size(); i++) {
            if (imports.get(i) == node) {
                imports.set(i, (ImportDeclaration) replacementNode);
                return true;
            }
        }
        if (module != null) {
            if (node == module) {
                setModule((ModuleDeclaration) replacementNode);
                return true;
            }
        }
        if (packageDeclaration != null) {
            if (node == packageDeclaration) {
                setPackageDeclaration((PackageDeclaration) replacementNode);
                return true;
            }
        }
        for (int i = 0; i < types.size(); i++) {
            if (types.get(i) == node) {
                types.set(i, (TypeDeclaration) replacementNode);
                return true;
            }
        }
        return super.replace(node, replacementNode);
    }

    /*
     * If there is no declared printer, returns a new default printer else returns a new printer with the current configuration
     */
    @Override
    protected Printer getPrinter() {
        if (!containsData(PRINTER_KEY)) {
            // create a default printer
            Printer printer = createDefaultPrinter();
            printer(printer);
        }
        return getData(PRINTER_KEY);
    }

    /*
     * Return the printer initialized with the specified configuration
     */
    @Override
    protected Printer getPrinter(PrinterConfiguration config) {
        Printer printer = getPrinter().setConfiguration(config);
        printer(printer);
        return printer;
    }

    /**
     * 根据所有扫描的文件填充type的类型
     *
     * @param variableType
     */
    private void fillTypeTargetByAllCompilationUnit(Type variableType) {
        if (variableType.isArrayType()) {
            // todo 这里不应该直接覆盖
            variableType = variableType.asArrayType().getComponentType();
        }
        Optional<TypeDeclaration<?>> typeTarget = this.findTypeDeclaration(variableType);
        Type finalVariableType = variableType;
        typeTarget.ifPresent(finalVariableType::setTarget);
    }

    /**
     * @param importDeclaration
     *
     * @return {@code true}, if the import is implicit
     */
    private boolean isImplicitImport(ImportDeclaration importDeclaration) {
        Optional<Name> importPackageName = getImportPackageName(importDeclaration);
        if (importPackageName.isPresent()) {
            if (parseName(JAVA_LANG).equals(importPackageName.get())) {
                // java.lang is implicitly imported
                return true;
            }
            if (packageDeclaration != null) {
                // the import is within the same package
                Name currentPackageName = packageDeclaration.getName();
                return currentPackageName.equals(importPackageName.get());
            }
            return false;
        } else {
            // imports of unnamed package are not allowed
            return true;
        }
    }

    /**
     * Information about where this compilation unit was loaded from.
     * This class only stores the absolute location.
     * For more flexibility use SourceRoot.
     */
    public static class Storage {

        private final CompilationUnit compilationUnit;

        private final Path path;

        private final Charset encoding;

        private Storage(CompilationUnit compilationUnit, Path path) {
            this(compilationUnit, path, UTF8);
        }

        private Storage(CompilationUnit compilationUnit, Path path, Charset encoding) {
            this.compilationUnit = compilationUnit;
            this.path = path.toAbsolutePath();
            this.encoding = encoding;
        }

        /**
         * @return the path to the source for this CompilationUnit
         */
        public Path getPath() {
            return path;
        }

        /**
         * @return the CompilationUnit this Storage is about.
         */
        public CompilationUnit getCompilationUnit() {
            return compilationUnit;
        }

        /**
         * @return the encoding used to read the file.
         */
        public Charset getEncoding() {
            return encoding;
        }

        /**
         * @return the source root directory, calculated from the path of this compiation unit, and the package
         * declaration of this compilation unit. If the package declaration is invalid (when it does not match the end
         * of the path) a RuntimeException is thrown.
         */
        public Path getSourceRoot() {
            final Optional<String> pkgAsString = compilationUnit.getPackageDeclaration().map(NodeWithName::getNameAsString);
            return pkgAsString.map(p -> Paths.get(CodeGenerationUtils.packageToPath(p))).map(pkg -> subtractPaths(getDirectory(), pkg)).orElse(getDirectory());
        }

        public String getFileName() {
            return path.getFileName().toString();
        }

        public Path getDirectory() {
            return path.getParent();
        }

        /**
         * Saves the compilation unit to its original location
         */
        public void save() {
            save(cu -> compilationUnit.getPrinter().print(cu));
        }

        /**
         * Saves a compilation unit to its original location with formatting according to the function passed as a
         * parameter.
         *
         * @param makeOutput a function that formats the compilation unit
         */
        public void save(Function<CompilationUnit, String> makeOutput) {
            save(makeOutput, encoding);
        }

        /**
         * Saves a compilation unit to its original location with formatting and encoding according to the function and
         * encoding passed as a parameter.
         *
         * @param makeOutput a function that formats the compilation unit
         * @param encoding   the encoding to use for the saved file
         */
        public void save(Function<CompilationUnit, String> makeOutput, Charset encoding) {
            try {
                Files.createDirectories(path.getParent());
                final String code = makeOutput.apply(getCompilationUnit());
                Files.write(path, code.getBytes(encoding));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public ParseResult<CompilationUnit> reparse(JavaParser javaParser) {
            try {
                return javaParser.parse(ParseStart.COMPILATION_UNIT, provider(getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
