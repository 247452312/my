package com.github.javaparser.ast;

import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.PackageDeclarationMetaModel;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.resolution.SymbolResolver;
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
 * @date 文件创建日期 2022年04月25日 08时33分
 */
public class PackageDeclarationWithLink extends PackageDeclaration {

    /**
     * 本体
     */
    private final PackageDeclaration target;

    /**
     * package中另外的类
     */
    private List<CompilationUnitWithLink> otherCompilationUnits;

    public PackageDeclarationWithLink(PackageDeclaration target) {
        this.target = target;
    }

    public PackageDeclaration getTarget() {
        return target;
    }

    public List<CompilationUnitWithLink> getOtherCompilationUnits() {
        return otherCompilationUnits;
    }

    public void setOtherCompilationUnits(List<CompilationUnitWithLink> otherCompilationUnits) {
        this.otherCompilationUnits = otherCompilationUnits;
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
    public NodeList<AnnotationExpr> getAnnotations() {
        return target.getAnnotations();
    }

    @Override
    public Name getName() {
        return target.getName();
    }

    @Override
    public PackageDeclaration setAnnotations(NodeList<AnnotationExpr> annotations) {
        if (target != null) {
            return target.setAnnotations(annotations);
        }
        return null;
    }

    @Override
    public PackageDeclaration setName(Name name) {
        if (target != null) {
            return target.setName(name);
        }
        return null;
    }

    @Override
    public boolean remove(Node node) {
        return target.remove(node);
    }

    @Override
    public PackageDeclaration clone() {
        return target.clone();
    }

    @Override
    public PackageDeclarationMetaModel getMetaModel() {
        return target.getMetaModel();
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        return target.replace(node, replacementNode);
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
    public PackageDeclaration setAnnotation(int i, AnnotationExpr element) {
        return target.setAnnotation(i, element);
    }

    @Override
    public PackageDeclaration addAnnotation(AnnotationExpr element) {
        return target.addAnnotation(element);
    }

    @Override
    public PackageDeclaration addAnnotation(String name) {
        return target.addAnnotation(name);
    }

    @Override
    public NormalAnnotationExpr addAndGetAnnotation(String name) {
        return target.addAndGetAnnotation(name);
    }

    @Override
    public PackageDeclaration addAnnotation(Class<? extends Annotation> clazz) {
        return target.addAnnotation(clazz);
    }

    @Override
    public NormalAnnotationExpr addAndGetAnnotation(Class<? extends Annotation> clazz) {
        return target.addAndGetAnnotation(clazz);
    }

    @Override
    public PackageDeclaration addMarkerAnnotation(String name) {
        return target.addMarkerAnnotation(name);
    }

    @Override
    public PackageDeclaration addMarkerAnnotation(Class<? extends Annotation> clazz) {
        return target.addMarkerAnnotation(clazz);
    }

    @Override
    public PackageDeclaration addSingleMemberAnnotation(String name, Expression expression) {
        return target.addSingleMemberAnnotation(name, expression);
    }

    @Override
    public PackageDeclaration addSingleMemberAnnotation(Class<? extends Annotation> clazz, Expression expression) {
        return target.addSingleMemberAnnotation(clazz, expression);
    }

    @Override
    public PackageDeclaration addSingleMemberAnnotation(String name, String value) {
        return target.addSingleMemberAnnotation(name, value);
    }

    @Override
    public PackageDeclaration addSingleMemberAnnotation(Class<? extends Annotation> clazz, String value) {
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
    public PackageDeclaration setName(String name) {
        return target.setName(name);
    }

    @Override
    public String getNameAsString() {
        return target.getNameAsString();
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
