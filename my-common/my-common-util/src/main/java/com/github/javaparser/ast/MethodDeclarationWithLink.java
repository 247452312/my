package com.github.javaparser.ast;

import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.Modifier.Keyword;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.CompactConstructorDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.metamodel.MethodDeclarationMetaModel;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.utils.LineSeparator;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月25日 09时59分
 */
public class MethodDeclarationWithLink extends MethodDeclaration {

    private final MethodDeclaration target;

    private CompilationUnitWithLink returnType;

    private List<CompilationUnitWithLink> paramTypes;

    public MethodDeclarationWithLink(MethodDeclaration target) {
        this.target = target;
    }

    public CompilationUnitWithLink getReturnType() {
        return returnType;
    }

    public void setReturnType(CompilationUnitWithLink returnType) {
        this.returnType = returnType;
    }

    public List<CompilationUnitWithLink> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<CompilationUnitWithLink> paramTypes) {
        this.paramTypes = paramTypes;
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return target.accept(v, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        target.accept(v, arg);
    }

    @Override
    public Optional<BlockStmt> getBody() {
        return target.getBody();
    }


    @Override
    public MethodDeclaration setBody(BlockStmt body) {
        if (target != null) {
            return target.setBody(body);
        }
        return null;
    }

    @Override
    public Type getType() {
        return target.getType();
    }

    @Override
    public MethodDeclaration setType(Type type) {
        if (target != null) {
            return target.setType(type);
        }
        return null;
    }

    @Override
    public MethodDeclaration setModifiers(NodeList<Modifier> modifiers) {

        if (target != null) {
            return target.setModifiers(modifiers);
        }
        return null;
    }

    @Override
    public MethodDeclaration setName(SimpleName name) {

        if (target != null) {
            return target.setName(name);
        }
        return null;
    }

    @Override
    public MethodDeclaration setParameters(NodeList<Parameter> parameters) {

        if (target != null) {
            return target.setParameters(parameters);
        }
        return null;
    }

    @Override
    public MethodDeclaration setThrownExceptions(NodeList<ReferenceType> thrownExceptions) {

        if (target != null) {
            return target.setThrownExceptions(thrownExceptions);
        }
        return null;
    }

    @Override
    public MethodDeclaration setTypeParameters(NodeList<TypeParameter> typeParameters) {

        if (target != null) {
            return target.setTypeParameters(typeParameters);
        }
        return null;
    }

    @Override
    public String getDeclarationAsString(boolean includingModifiers, boolean includingThrows, boolean includingParameterName) {
        return target.getDeclarationAsString(includingModifiers, includingThrows, includingParameterName);
    }

    @Override
    public String toDescriptor() {
        return target.toDescriptor();
    }

    @Override
    public boolean isNative() {
        return target.isNative();
    }

    @Override
    public boolean isSynchronized() {
        return target.isSynchronized();
    }

    @Override
    public boolean isDefault() {
        return target.isDefault();
    }

    @Override
    public MethodDeclaration setNative(boolean set) {
        return target.setNative(set);
    }

    @Override
    public MethodDeclaration setSynchronized(boolean set) {
        return target.setSynchronized(set);
    }

    @Override
    public MethodDeclaration setDefault(boolean set) {
        return target.setDefault(set);
    }

    @Override
    public boolean remove(Node node) {
        return target.remove(node);
    }

    @Override
    public MethodDeclaration removeBody() {
        return target.removeBody();
    }

    @Override
    public MethodDeclaration clone() {
        return target.clone();
    }

    @Override
    public MethodDeclarationMetaModel getMetaModel() {
        return target.getMetaModel();
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        return target.replace(node, replacementNode);
    }

    @Override
    public boolean isMethodDeclaration() {
        return target.isMethodDeclaration();
    }

    @Override
    public MethodDeclaration asMethodDeclaration() {
        return target.asMethodDeclaration();
    }

    @Override
    public void ifMethodDeclaration(Consumer<MethodDeclaration> action) {
        target.ifMethodDeclaration(action);
    }

    @Override
    public ResolvedMethodDeclaration resolve() {
        return target.resolve();
    }

    @Override
    public Optional<MethodDeclaration> toMethodDeclaration() {
        return target.toMethodDeclaration();
    }

    @Override
    public NodeList<Modifier> getModifiers() {
        return target.getModifiers();
    }

    @Override
    public SimpleName getName() {
        return target.getName();
    }

    @Override
    public NodeList<Parameter> getParameters() {
        return target.getParameters();
    }

    @Override
    public NodeList<ReferenceType> getThrownExceptions() {
        return target.getThrownExceptions();
    }

    @Override
    public NodeList<TypeParameter> getTypeParameters() {
        return target.getTypeParameters();
    }

    @Override
    public Signature getSignature() {
        return target.getSignature();
    }

    @Override
    public boolean isCallableDeclaration() {
        return target.isCallableDeclaration();
    }

    @Override
    public CallableDeclaration asCallableDeclaration() {
        return target.asCallableDeclaration();
    }

    @Override
    public void ifCallableDeclaration(Consumer<CallableDeclaration> action) {
        target.ifCallableDeclaration(action);
    }

    @Override
    public Optional<ReceiverParameter> getReceiverParameter() {
        return target.getReceiverParameter();
    }

    @Override
    public MethodDeclaration setReceiverParameter(ReceiverParameter receiverParameter) {

        if (target != null) {
            return target.setReceiverParameter(receiverParameter);
        }
        return null;
    }

    @Override
    public CallableDeclaration removeReceiverParameter() {
        return target.removeReceiverParameter();
    }

    @Override
    public Optional<CallableDeclaration> toCallableDeclaration() {
        return target.toCallableDeclaration();
    }

    @Override
    public boolean isVariableArityMethod() {
        return target.isVariableArityMethod();
    }

    @Override
    public boolean isFixedArityMethod() {
        return target.isFixedArityMethod();
    }

    @Override
    public NodeList<AnnotationExpr> getAnnotations() {
        return target.getAnnotations();
    }

    @Override
    public MethodDeclaration setAnnotations(NodeList<AnnotationExpr> annotations) {

        if (target != null) {
            return target.setAnnotations(annotations);
        }
        return null;
    }

    @Override
    public boolean isAnnotationDeclaration() {
        return target.isAnnotationDeclaration();
    }

    @Override
    public AnnotationDeclaration asAnnotationDeclaration() {
        return target.asAnnotationDeclaration();
    }

    @Override
    public boolean isAnnotationMemberDeclaration() {
        return target.isAnnotationMemberDeclaration();
    }

    @Override
    public AnnotationMemberDeclaration asAnnotationMemberDeclaration() {
        return target.asAnnotationMemberDeclaration();
    }

    @Override
    public boolean isClassOrInterfaceDeclaration() {
        return target.isClassOrInterfaceDeclaration();
    }

    @Override
    public ClassOrInterfaceDeclaration asClassOrInterfaceDeclaration() {
        return target.asClassOrInterfaceDeclaration();
    }

    @Override
    public boolean isConstructorDeclaration() {
        return target.isConstructorDeclaration();
    }

    @Override
    public ConstructorDeclaration asConstructorDeclaration() {
        return target.asConstructorDeclaration();
    }

    @Override
    public boolean isCompactConstructorDeclaration() {
        return target.isCompactConstructorDeclaration();
    }

    @Override
    public CompactConstructorDeclaration asCompactConstructorDeclaration() {
        return target.asCompactConstructorDeclaration();
    }

    @Override
    public boolean isEnumConstantDeclaration() {
        return target.isEnumConstantDeclaration();
    }

    @Override
    public EnumConstantDeclaration asEnumConstantDeclaration() {
        return target.asEnumConstantDeclaration();
    }

    @Override
    public boolean isEnumDeclaration() {
        return target.isEnumDeclaration();
    }

    @Override
    public EnumDeclaration asEnumDeclaration() {
        return target.asEnumDeclaration();
    }

    @Override
    public boolean isFieldDeclaration() {
        return target.isFieldDeclaration();
    }

    @Override
    public FieldDeclaration asFieldDeclaration() {
        return target.asFieldDeclaration();
    }

    @Override
    public boolean isInitializerDeclaration() {
        return target.isInitializerDeclaration();
    }

    @Override
    public InitializerDeclaration asInitializerDeclaration() {
        return target.asInitializerDeclaration();
    }

    @Override
    public boolean isTypeDeclaration() {
        return target.isTypeDeclaration();
    }

    @Override
    public TypeDeclaration asTypeDeclaration() {
        return target.asTypeDeclaration();
    }

    @Override
    public void ifAnnotationDeclaration(Consumer<AnnotationDeclaration> action) {
        target.ifAnnotationDeclaration(action);
    }

    @Override
    public void ifAnnotationMemberDeclaration(Consumer<AnnotationMemberDeclaration> action) {
        target.ifAnnotationMemberDeclaration(action);
    }

    @Override
    public void ifClassOrInterfaceDeclaration(Consumer<ClassOrInterfaceDeclaration> action) {
        target.ifClassOrInterfaceDeclaration(action);
    }

    @Override
    public void ifConstructorDeclaration(Consumer<ConstructorDeclaration> action) {
        target.ifConstructorDeclaration(action);
    }

    @Override
    public void ifEnumConstantDeclaration(Consumer<EnumConstantDeclaration> action) {
        target.ifEnumConstantDeclaration(action);
    }

    @Override
    public void ifEnumDeclaration(Consumer<EnumDeclaration> action) {
        target.ifEnumDeclaration(action);
    }

    @Override
    public void ifFieldDeclaration(Consumer<FieldDeclaration> action) {
        target.ifFieldDeclaration(action);
    }

    @Override
    public void ifInitializerDeclaration(Consumer<InitializerDeclaration> action) {
        target.ifInitializerDeclaration(action);
    }

    @Override
    public void ifTypeDeclaration(Consumer<TypeDeclaration> action) {
        target.ifTypeDeclaration(action);
    }

    @Override
    public void ifRecordDeclaration(Consumer<RecordDeclaration> action) {
        target.ifRecordDeclaration(action);
    }

    @Override
    public void ifCompactConstructorDeclaration(Consumer<CompactConstructorDeclaration> action) {
        target.ifCompactConstructorDeclaration(action);
    }

    @Override
    public Optional<AnnotationDeclaration> toAnnotationDeclaration() {
        return target.toAnnotationDeclaration();
    }

    @Override
    public Optional<AnnotationMemberDeclaration> toAnnotationMemberDeclaration() {
        return target.toAnnotationMemberDeclaration();
    }

    @Override
    public Optional<ClassOrInterfaceDeclaration> toClassOrInterfaceDeclaration() {
        return target.toClassOrInterfaceDeclaration();
    }

    @Override
    public Optional<ConstructorDeclaration> toConstructorDeclaration() {
        return target.toConstructorDeclaration();
    }

    @Override
    public Optional<EnumConstantDeclaration> toEnumConstantDeclaration() {
        return target.toEnumConstantDeclaration();
    }

    @Override
    public Optional<EnumDeclaration> toEnumDeclaration() {
        return target.toEnumDeclaration();
    }

    @Override
    public Optional<FieldDeclaration> toFieldDeclaration() {
        return target.toFieldDeclaration();
    }

    @Override
    public Optional<InitializerDeclaration> toInitializerDeclaration() {
        return target.toInitializerDeclaration();
    }

    @Override
    public Optional<TypeDeclaration> toTypeDeclaration() {
        return target.toTypeDeclaration();
    }

    @Override
    public boolean isRecordDeclaration() {
        return target.isRecordDeclaration();
    }

    @Override
    public RecordDeclaration asRecordDeclaration() {
        return target.asRecordDeclaration();
    }

    @Override
    public Optional<RecordDeclaration> toRecordDeclaration() {
        return target.toRecordDeclaration();
    }

    @Override
    public Optional<CompactConstructorDeclaration> toCompactConstructorDeclaration() {
        return target.toCompactConstructorDeclaration();
    }

    @Override
    public Optional<Comment> getComment() {
        return target.getComment();
    }

    @Override
    public Optional<Range> getRange() {
        return target.getRange();
    }

    @Override
    public Optional<TokenRange> getTokenRange() {
        return target.getTokenRange();
    }

    @Override
    public Node setTokenRange(TokenRange tokenRange) {
        if (target != null) {
            return target.setTokenRange(tokenRange);
        }
        return null;
    }

    @Override
    public Node setRange(Range range) {
        return target.setRange(range);
    }

    @Override
    public Node setComment(Comment comment) {
        return target.setComment(comment);
    }

    @Override
    public boolean equals(Object obj) {
        return target.equals(obj);
    }

    @Override
    public Optional<Node> getParentNode() {
        return target.getParentNode();
    }

    @Override
    public List<Node> getChildNodes() {
        return target.getChildNodes();
    }

    @Override
    public void addOrphanComment(Comment comment) {
        target.addOrphanComment(comment);
    }

    @Override
    public boolean removeOrphanComment(Comment comment) {
        return target.removeOrphanComment(comment);
    }

    @Override
    public List<Comment> getOrphanComments() {
        return target.getOrphanComments();
    }

    @Override
    public List<Comment> getAllContainedComments() {
        return target.getAllContainedComments();
    }

    @Override
    public Node setParentNode(Node newParentNode) {
        return target.setParentNode(newParentNode);
    }

    @Override
    public void tryAddImportToParentCompilationUnit(Class<?> clazz) {
        target.tryAddImportToParentCompilationUnit(clazz);
    }

    @Override
    public <N extends Node> List<N> getChildNodesByType(Class<N> clazz) {
        return target.getChildNodesByType(clazz);
    }

    @Override
    public <N extends Node> List<N> getNodesByType(Class<N> clazz) {
        return target.getNodesByType(clazz);
    }

    @Override
    public <M> M getData(DataKey<M> key) {
        return target.getData(key);
    }

    @Override
    public Set<DataKey<?>> getDataKeys() {
        return target.getDataKeys();
    }

    @Override
    public <M> void setData(DataKey<M> key, M object) {

        if (target != null) {
            target.setData(key, object);
        }
    }

    @Override
    public boolean containsData(DataKey<?> key) {
        return target.containsData(key);
    }

    @Override
    public void removeData(DataKey<?> key) {
        target.removeData(key);
    }

    @Override
    public boolean remove() {
        return target.remove();
    }

    @Override
    public boolean replace(Node node) {
        return target.replace(node);
    }

    @Override
    public void removeForced() {
        target.removeForced();
    }

    @Override
    public Node getParentNodeForChildren() {
        return target.getParentNodeForChildren();
    }

    @Override
    public <P> void notifyPropertyChange(ObservableProperty property, P oldValue, P newValue) {
        target.notifyPropertyChange(property, oldValue, newValue);
    }

    @Override
    public void unregister(AstObserver observer) {
        target.unregister(observer);
    }

    @Override
    public void register(AstObserver observer) {
        target.register(observer);
    }

    @Override
    public void register(AstObserver observer, ObserverRegistrationMode mode) {
        target.register(observer, mode);
    }

    @Override
    public void registerForSubtree(AstObserver observer) {
        target.registerForSubtree(observer);
    }

    @Override
    public boolean isRegistered(AstObserver observer) {
        return target.isRegistered(observer);
    }

    @Override
    public Node removeComment() {
        return target.removeComment();
    }

    @Override
    public Parsedness getParsed() {
        return target.getParsed();
    }

    @Override
    public Node setParsed(Parsedness parsed) {
        return target.setParsed(parsed);
    }

    @Override
    public Node findRootNode() {
        return target.findRootNode();
    }

    @Override
    public Optional<CompilationUnit> findCompilationUnit() {
        return target.findCompilationUnit();
    }

    @Override
    public LineSeparator getLineEndingStyleOrDefault(LineSeparator defaultLineSeparator) {
        return target.getLineEndingStyleOrDefault(defaultLineSeparator);
    }

    @Override
    public LineSeparator getLineEndingStyle() {
        return target.getLineEndingStyle();
    }

    @Override
    public Stream<Node> stream(TreeTraversal traversal) {
        return target.stream(traversal);
    }

    @Override
    public Stream<Node> stream() {
        return target.stream();
    }

    @Override
    public void walk(TreeTraversal traversal, Consumer<Node> consumer) {
        target.walk(traversal, consumer);
    }

    @Override
    public void walk(Consumer<Node> consumer) {
        target.walk(consumer);
    }

    @Override
    public <T extends Node> void walk(Class<T> nodeType, Consumer<T> consumer) {
        target.walk(nodeType, consumer);
    }

    @Override
    public <T extends Node> List<T> findAll(Class<T> nodeType) {
        return target.findAll(nodeType);
    }

    @Override
    public <T extends Node> List<T> findAll(Class<T> nodeType, TreeTraversal traversal) {
        return target.findAll(nodeType, traversal);
    }

    @Override
    public <T extends Node> List<T> findAll(Class<T> nodeType, Predicate<T> predicate) {
        return target.findAll(nodeType, predicate);
    }

    @Override
    public <T> Optional<T> findFirst(TreeTraversal traversal, Function<Node, Optional<T>> consumer) {
        return target.findFirst(traversal, consumer);
    }

    @Override
    public <N extends Node> Optional<N> findFirst(Class<N> nodeType) {
        return target.findFirst(nodeType);
    }

    @Override
    public <N extends Node> Optional<N> findFirst(Class<N> nodeType, Predicate<N> predicate) {
        return target.findFirst(nodeType, predicate);
    }

    @Override
    public boolean isAncestorOf(Node descendant) {
        return target.isAncestorOf(descendant);
    }

    @Override
    public boolean hasScope() {
        return target.hasScope();
    }

    @Override
    public boolean isPhantom() {
        return target.isPhantom();
    }

    @Override
    public boolean hasParentNode() {
        return target.hasParentNode();
    }

    @Override
    public <N> Optional<N> findAncestor(Class<N>... types) {
        return target.findAncestor(types);
    }

    @Override
    public <N> Optional<N> findAncestor(Class<N> type, Predicate<N> predicate) {
        return target.findAncestor(type, predicate);
    }

    @Override
    public <N> Optional<N> findAncestor(Predicate<N> predicate, Class<N>... types) {
        return target.findAncestor(predicate, types);
    }

    @Override
    public boolean isDescendantOf(Node ancestor) {
        return target.isDescendantOf(ancestor);
    }

    @Override
    public AnnotationExpr getAnnotation(int i) {
        return target.getAnnotation(i);
    }

    @Override
    public MethodDeclaration setAnnotation(int i, AnnotationExpr element) {
        return target.setAnnotation(i, element);
    }

    @Override
    public MethodDeclaration addAnnotation(AnnotationExpr element) {
        return target.addAnnotation(element);
    }

    @Override
    public MethodDeclaration addAnnotation(String name) {
        return target.addAnnotation(name);
    }

    @Override
    public NormalAnnotationExpr addAndGetAnnotation(String name) {
        return target.addAndGetAnnotation(name);
    }

    @Override
    public MethodDeclaration addAnnotation(Class<? extends Annotation> clazz) {
        return target.addAnnotation(clazz);
    }

    @Override
    public NormalAnnotationExpr addAndGetAnnotation(Class<? extends Annotation> clazz) {
        return target.addAndGetAnnotation(clazz);
    }

    @Override
    public MethodDeclaration addMarkerAnnotation(String name) {
        return target.addMarkerAnnotation(name);
    }

    @Override
    public MethodDeclaration addMarkerAnnotation(Class<? extends Annotation> clazz) {
        return target.addMarkerAnnotation(clazz);
    }

    @Override
    public MethodDeclaration addSingleMemberAnnotation(String name, Expression expression) {
        return target.addSingleMemberAnnotation(name, expression);
    }

    @Override
    public MethodDeclaration addSingleMemberAnnotation(Class<? extends Annotation> clazz, Expression expression) {
        return target.addSingleMemberAnnotation(clazz, expression);
    }

    @Override
    public MethodDeclaration addSingleMemberAnnotation(String name, String value) {
        return target.addSingleMemberAnnotation(name, value);
    }

    @Override
    public MethodDeclaration addSingleMemberAnnotation(Class<? extends Annotation> clazz, String value) {
        return target.addSingleMemberAnnotation(clazz, value);
    }

    @Override
    public boolean isAnnotationPresent(String annotationName) {
        return target.isAnnotationPresent(annotationName);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return target.isAnnotationPresent(annotationClass);
    }

    @Override
    public Optional<AnnotationExpr> getAnnotationByName(String annotationName) {
        return target.getAnnotationByName(annotationName);
    }

    @Override
    public Optional<AnnotationExpr> getAnnotationByClass(Class<? extends Annotation> annotationClass) {
        return target.getAnnotationByClass(annotationClass);
    }

    @Override
    public String getDeclarationAsString() {
        return target.getDeclarationAsString();
    }

    @Override
    public String getDeclarationAsString(boolean includingModifiers, boolean includingThrows) {
        return target.getDeclarationAsString(includingModifiers, includingThrows);
    }

    @Override
    public Optional<JavadocComment> getJavadocComment() {
        return target.getJavadocComment();
    }

    @Override
    public Optional<Javadoc> getJavadoc() {
        return target.getJavadoc();
    }

    @Override
    public MethodDeclaration setJavadocComment(String comment) {
        return target.setJavadocComment(comment);
    }

    @Override
    public MethodDeclaration setJavadocComment(JavadocComment comment) {
        return target.setJavadocComment(comment);
    }

    @Override
    public MethodDeclaration setJavadocComment(String indentation, Javadoc javadoc) {
        return target.setJavadocComment(indentation, javadoc);
    }

    @Override
    public MethodDeclaration setJavadocComment(Javadoc javadoc) {
        return target.setJavadocComment(javadoc);
    }

    @Override
    public boolean removeJavaDocComment() {
        return target.removeJavaDocComment();
    }

    @Override
    public boolean hasJavaDocComment() {
        return target.hasJavaDocComment();
    }

    @Override
    public MethodDeclaration addModifier(Keyword... newModifiers) {
        return target.addModifier(newModifiers);
    }

    @Override
    public MethodDeclaration removeModifier(Keyword... modifiersToRemove) {
        return target.removeModifier(modifiersToRemove);
    }

    @Override
    public MethodDeclaration setModifier(Keyword m, boolean set) {
        return target.setModifier(m, set);
    }

    @Override
    public boolean hasModifier(Keyword modifier) {
        return target.hasModifier(modifier);
    }

    @Override
    public MethodDeclaration setModifiers(Keyword... modifiers) {
        return target.setModifiers(modifiers);
    }

    @Override
    public AccessSpecifier getAccessSpecifier() {
        return target.getAccessSpecifier();
    }

    @Override
    public BlockStmt createBody() {
        return target.createBody();
    }

    @Override
    public Parameter getParameter(int i) {
        return target.getParameter(i);
    }

    @Override
    public MethodDeclaration setParameter(int i, Parameter parameter) {
        return target.setParameter(i, parameter);
    }

    @Override
    public MethodDeclaration addParameter(Type type, String name) {
        return target.addParameter(type, name);
    }

    @Override
    public MethodDeclaration addParameter(Class<?> paramClass, String name) {
        return target.addParameter(paramClass, name);
    }

    @Override
    public MethodDeclaration addParameter(String className, String name) {
        return target.addParameter(className, name);
    }

    @Override
    public MethodDeclaration addParameter(Parameter parameter) {
        return target.addParameter(parameter);
    }

    @Override
    public Parameter addAndGetParameter(Type type, String name) {
        return target.addAndGetParameter(type, name);
    }

    @Override
    public Parameter addAndGetParameter(Class<?> paramClass, String name) {
        return target.addAndGetParameter(paramClass, name);
    }

    @Override
    public Parameter addAndGetParameter(String className, String name) {
        return target.addAndGetParameter(className, name);
    }

    @Override
    public Parameter addAndGetParameter(Parameter parameter) {
        return target.addAndGetParameter(parameter);
    }

    @Override
    public Optional<Parameter> getParameterByName(String name) {
        return target.getParameterByName(name);
    }

    @Override
    public Optional<Parameter> getParameterByType(String type) {
        return target.getParameterByType(type);
    }

    @Override
    public Optional<Parameter> getParameterByType(Class<?> type) {
        return target.getParameterByType(type);
    }

    @Override
    public boolean hasParametersOfType(String... paramTypes) {
        return target.hasParametersOfType(paramTypes);
    }

    @Override
    public boolean hasParametersOfType(Class<?>... paramTypes) {
        return target.hasParametersOfType(paramTypes);
    }

    @Override
    public Optional<Position> getBegin() {
        return target.getBegin();
    }

    @Override
    public Optional<Position> getEnd() {
        return target.getEnd();
    }

    @Override
    public boolean containsWithin(Node other) {
        return target.containsWithin(other);
    }

    @Override
    public boolean containsWithinRange(Node other) {
        return target.containsWithinRange(other);
    }

    @Override
    public boolean hasRange() {
        return target.hasRange();
    }

    @Override
    public MethodDeclaration setName(String name) {
        return target.setName(name);
    }

    @Override
    public String getNameAsString() {
        return target.getNameAsString();
    }

    @Override
    public NameExpr getNameAsExpression() {
        return target.getNameAsExpression();
    }

    @Override
    public ReferenceType getThrownException(int i) {
        return target.getThrownException(i);
    }

    @Override
    public MethodDeclaration addThrownException(ReferenceType throwType) {
        return target.addThrownException(throwType);
    }

    @Override
    public MethodDeclaration addThrownException(Class<? extends Throwable> clazz) {
        return target.addThrownException(clazz);
    }

    @Override
    public boolean isThrown(Class<? extends Throwable> clazz) {
        return target.isThrown(clazz);
    }

    @Override
    public boolean isThrown(String throwableName) {
        return target.isThrown(throwableName);
    }

    @Override
    public MethodDeclaration setType(Class<?> typeClass) {
        return target.setType(typeClass);
    }

    @Override
    public MethodDeclaration setType(String typeString) {
        return target.setType(typeString);
    }

    @Override
    public String getTypeAsString() {
        return target.getTypeAsString();
    }

    @Override
    public TypeParameter getTypeParameter(int i) {
        return target.getTypeParameter(i);
    }

    @Override
    public MethodDeclaration setTypeParameter(int i, TypeParameter typeParameter) {
        return target.setTypeParameter(i, typeParameter);
    }

    @Override
    public MethodDeclaration addTypeParameter(TypeParameter typeParameter) {
        return target.addTypeParameter(typeParameter);
    }

    @Override
    public MethodDeclaration addTypeParameter(String typeParameter) {
        return target.addTypeParameter(typeParameter);
    }

    @Override
    public boolean isGeneric() {
        return target.isGeneric();
    }

    @Override
    public boolean isAbstract() {
        return target.isAbstract();
    }

    @Override
    public MethodDeclaration setAbstract(boolean set) {
        return target.setAbstract(set);
    }

    @Override
    public boolean isFinal() {
        return target.isFinal();
    }

    @Override
    public MethodDeclaration setFinal(boolean set) {
        return target.setFinal(set);
    }

    @Override
    public boolean isPrivate() {
        return target.isPrivate();
    }

    @Override
    public MethodDeclaration setPrivate(boolean set) {
        return target.setPrivate(set);
    }

    @Override
    public boolean isProtected() {
        return target.isProtected();
    }

    @Override
    public MethodDeclaration setProtected(boolean set) {
        return target.setProtected(set);
    }

    @Override
    public boolean isPublic() {
        return target.isPublic();
    }

    @Override
    public MethodDeclaration setPublic(boolean set) {
        return target.setPublic(set);
    }

    @Override
    public boolean isStatic() {
        return target.isStatic();
    }

    @Override
    public MethodDeclaration setStatic(boolean set) {
        return target.setStatic(set);
    }

    @Override
    public boolean isStrictfp() {
        return target.isStrictfp();
    }

    @Override
    public MethodDeclaration setStrictfp(boolean set) {
        return target.setStrictfp(set);
    }

    @Override
    protected void customInitialization() {

        if (target != null) {
            target.customInitialization();
        }
    }

    @Override
    protected Printer getPrinter() {
        return target.getPrinter();
    }

    @Override
    protected Printer getPrinter(PrinterConfiguration configuration) {
        return target.getPrinter(configuration);
    }

    @Override
    protected Printer createDefaultPrinter() {
        return target.createDefaultPrinter();
    }

    @Override
    protected Printer createDefaultPrinter(PrinterConfiguration configuration) {
        return target.createDefaultPrinter(configuration);
    }

    @Override
    protected PrinterConfiguration getDefaultPrinterConfiguration() {
        return target.getDefaultPrinterConfiguration();
    }

    @Override
    protected void setAsParentNodeOf(Node childNode) {
        target.setAsParentNodeOf(childNode);
    }

    @Override
    protected void setAsParentNodeOf(NodeList<? extends Node> list) {
        target.setAsParentNodeOf(list);
    }

    @Override
    protected SymbolResolver getSymbolResolver() {
        return target.getSymbolResolver();
    }
}
