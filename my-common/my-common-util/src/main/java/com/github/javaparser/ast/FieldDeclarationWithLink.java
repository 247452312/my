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
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.metamodel.FieldDeclarationMetaModel;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
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
 * @date 文件创建日期 2022年04月25日 11时09分
 */
public class FieldDeclarationWithLink extends FieldDeclaration {

    private final FieldDeclaration target;

    private List<CompilationUnitWithLink> targetType;

    public FieldDeclarationWithLink(FieldDeclaration target) {
        this.target = target;
    }

    public List<CompilationUnitWithLink> getTargetType() {
        return targetType;
    }

    public void setTargetType(List<CompilationUnitWithLink> targetType) {
        this.targetType = targetType;
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
    public NodeList<Modifier> getModifiers() {
        return target.getModifiers();
    }

    @Override
    public NodeList<VariableDeclarator> getVariables() {
        return target.getVariables();
    }

    @Override
    public FieldDeclaration setModifiers(NodeList<Modifier> modifiers) {

        if (target != null) {
            return target.setModifiers(modifiers);
        }
        return null;
    }

    @Override
    public FieldDeclaration setVariables(NodeList<VariableDeclarator> variables) {

        if (target != null) {
            return target.setVariables(variables);
        }
        return null;
    }

    @Override
    public MethodDeclaration createGetter() {
        return target.createGetter();
    }

    @Override
    public MethodDeclaration createSetter() {
        return target.createSetter();
    }

    @Override
    public boolean isTransient() {
        return target.isTransient();
    }

    @Override
    public boolean isVolatile() {
        return target.isVolatile();
    }

    @Override
    public FieldDeclaration setTransient(boolean set) {

        if (target != null) {
            return target.setTransient(set);
        }
        return null;
    }

    @Override
    public FieldDeclaration setVolatile(boolean set) {

        if (target != null) {
            return target.setVolatile(set);
        }
        return null;
    }

    @Override
    public boolean remove(Node node) {
        return target.remove(node);
    }

    @Override
    public FieldDeclaration clone() {
        return target.clone();
    }

    @Override
    public FieldDeclarationMetaModel getMetaModel() {
        return target.getMetaModel();
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        return target.replace(node, replacementNode);
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
    public void ifFieldDeclaration(Consumer<FieldDeclaration> action) {
        target.ifFieldDeclaration(action);
    }

    @Override
    public ResolvedFieldDeclaration resolve() {
        return target.resolve();
    }

    @Override
    public Optional<FieldDeclaration> toFieldDeclaration() {
        return target.toFieldDeclaration();
    }

    @Override
    public NodeList<AnnotationExpr> getAnnotations() {
        return target.getAnnotations();
    }

    @Override
    public FieldDeclaration setAnnotations(NodeList<AnnotationExpr> annotations) {

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
    public boolean isCallableDeclaration() {
        return target.isCallableDeclaration();
    }

    @Override
    public CallableDeclaration asCallableDeclaration() {
        return target.asCallableDeclaration();
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
    public boolean isInitializerDeclaration() {
        return target.isInitializerDeclaration();
    }

    @Override
    public InitializerDeclaration asInitializerDeclaration() {
        return target.asInitializerDeclaration();
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
    public void ifCallableDeclaration(Consumer<CallableDeclaration> action) {
        target.ifCallableDeclaration(action);
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
    public void ifInitializerDeclaration(Consumer<InitializerDeclaration> action) {
        target.ifInitializerDeclaration(action);
    }

    @Override
    public void ifMethodDeclaration(Consumer<MethodDeclaration> action) {
        target.ifMethodDeclaration(action);
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
    public Optional<CallableDeclaration> toCallableDeclaration() {
        return target.toCallableDeclaration();
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
    public Optional<InitializerDeclaration> toInitializerDeclaration() {
        return target.toInitializerDeclaration();
    }

    @Override
    public Optional<MethodDeclaration> toMethodDeclaration() {
        return target.toMethodDeclaration();
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

        if (target != null) {
            return target.setRange(range);
        }
        return null;
    }

    @Override
    public Node setComment(Comment comment) {

        if (target != null) {
            return target.setComment(comment);
        }
        return null;
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

        if (target != null) {
            return target.setParentNode(newParentNode);
        }
        return null;
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
        target.setData(key, object);
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
    public FieldDeclaration setAnnotation(int i, AnnotationExpr element) {
        return target.setAnnotation(i, element);
    }

    @Override
    public FieldDeclaration addAnnotation(AnnotationExpr element) {
        return target.addAnnotation(element);
    }

    @Override
    public FieldDeclaration addAnnotation(String name) {
        return target.addAnnotation(name);
    }

    @Override
    public NormalAnnotationExpr addAndGetAnnotation(String name) {
        return target.addAndGetAnnotation(name);
    }

    @Override
    public FieldDeclaration addAnnotation(Class<? extends Annotation> clazz) {
        return target.addAnnotation(clazz);
    }

    @Override
    public NormalAnnotationExpr addAndGetAnnotation(Class<? extends Annotation> clazz) {
        return target.addAndGetAnnotation(clazz);
    }

    @Override
    public FieldDeclaration addMarkerAnnotation(String name) {
        return target.addMarkerAnnotation(name);
    }

    @Override
    public FieldDeclaration addMarkerAnnotation(Class<? extends Annotation> clazz) {
        return target.addMarkerAnnotation(clazz);
    }

    @Override
    public FieldDeclaration addSingleMemberAnnotation(String name, Expression expression) {
        return target.addSingleMemberAnnotation(name, expression);
    }

    @Override
    public FieldDeclaration addSingleMemberAnnotation(Class<? extends Annotation> clazz, Expression expression) {
        return target.addSingleMemberAnnotation(clazz, expression);
    }

    @Override
    public FieldDeclaration addSingleMemberAnnotation(String name, String value) {
        return target.addSingleMemberAnnotation(name, value);
    }

    @Override
    public FieldDeclaration addSingleMemberAnnotation(Class<? extends Annotation> clazz, String value) {
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
    public Optional<JavadocComment> getJavadocComment() {
        return target.getJavadocComment();
    }

    @Override
    public Optional<Javadoc> getJavadoc() {
        return target.getJavadoc();
    }

    @Override
    public FieldDeclaration setJavadocComment(String comment) {
        return target.setJavadocComment(comment);
    }

    @Override
    public FieldDeclaration setJavadocComment(JavadocComment comment) {
        return target.setJavadocComment(comment);
    }

    @Override
    public FieldDeclaration setJavadocComment(String indentation, Javadoc javadoc) {
        return target.setJavadocComment(indentation, javadoc);
    }

    @Override
    public FieldDeclaration setJavadocComment(Javadoc javadoc) {
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
    public FieldDeclaration addModifier(Keyword... newModifiers) {
        return target.addModifier(newModifiers);
    }

    @Override
    public FieldDeclaration removeModifier(Keyword... modifiersToRemove) {
        return target.removeModifier(modifiersToRemove);
    }

    @Override
    public FieldDeclaration setModifier(Keyword m, boolean set) {
        return target.setModifier(m, set);
    }

    @Override
    public boolean hasModifier(Keyword modifier) {
        return target.hasModifier(modifier);
    }

    @Override
    public FieldDeclaration setModifiers(Keyword... modifiers) {
        return target.setModifiers(modifiers);
    }

    @Override
    public AccessSpecifier getAccessSpecifier() {
        return target.getAccessSpecifier();
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
    public VariableDeclarator getVariable(int i) {
        return target.getVariable(i);
    }

    @Override
    public FieldDeclaration setVariable(int i, VariableDeclarator variableDeclarator) {
        return target.setVariable(i, variableDeclarator);
    }

    @Override
    public FieldDeclaration addVariable(VariableDeclarator variableDeclarator) {
        return target.addVariable(variableDeclarator);
    }

    @Override
    public Type getCommonType() {
        return target.getCommonType();
    }

    @Override
    public Type getElementType() {
        return target.getElementType();
    }

    @Override
    public FieldDeclaration setAllTypes(Type newType) {
        return target.setAllTypes(newType);
    }

    @Override
    public Optional<Type> getMaximumCommonType() {
        return target.getMaximumCommonType();
    }

    @Override
    public boolean isFinal() {
        return target.isFinal();
    }

    @Override
    public FieldDeclaration setFinal(boolean set) {
        return target.setFinal(set);
    }

    @Override
    public boolean isPrivate() {
        return target.isPrivate();
    }

    @Override
    public FieldDeclaration setPrivate(boolean set) {
        return target.setPrivate(set);
    }

    @Override
    public boolean isProtected() {
        return target.isProtected();
    }

    @Override
    public FieldDeclaration setProtected(boolean set) {
        return target.setProtected(set);
    }

    @Override
    public boolean isPublic() {
        return target.isPublic();
    }

    @Override
    public FieldDeclaration setPublic(boolean set) {
        return target.setPublic(set);
    }

    @Override
    public boolean isStatic() {
        return target.isStatic();
    }

    @Override
    public FieldDeclaration setStatic(boolean set) {
        return target.setStatic(set);
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

        if (target != null) {
            target.setAsParentNodeOf(childNode);
        }
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
