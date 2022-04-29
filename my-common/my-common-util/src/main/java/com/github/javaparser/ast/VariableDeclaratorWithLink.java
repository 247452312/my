package com.github.javaparser.ast;

import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.VariableDeclaratorMetaModel;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.utils.LineSeparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月25日 13时45分
 */
public class VariableDeclaratorWithLink extends VariableDeclarator {

    /**
     * 本体
     */
    private VariableDeclarator target;

    /**
     * 类型
     */
    private CompilationUnitWithLink targetType;

    public VariableDeclaratorWithLink(VariableDeclarator target) {
        this.target = target;
    }

    public CompilationUnitWithLink getTargetType() {
        return targetType;
    }

    public void setTargetType(CompilationUnitWithLink targetType) {
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
    public Optional<Expression> getInitializer() {
        return target.getInitializer();
    }

    @Override
    public SimpleName getName() {
        return target.getName();
    }

    @Override
    public VariableDeclarator setName(SimpleName name) {
        if (target != null) {
            return target.setName(name);
        }
        return null;
    }

    @Override
    public VariableDeclarator setInitializer(Expression initializer) {
        if (target != null) {
            return target.setInitializer(initializer);
        }
        return null;
    }

    @Override
    public VariableDeclarator setInitializer(String init) {
        if (target != null) {
            return target.setInitializer(init);
        }
        return null;
    }

    @Override
    public Type getType() {
        return target.getType();
    }

    @Override
    public VariableDeclarator setType(Type type) {
        if (target != null) {
            return target.setType(type);
        }
        return null;
    }

    @Override
    public boolean remove(Node node) {
        return target.remove(node);
    }

    @Override
    public VariableDeclarator removeInitializer() {
        return target.removeInitializer();
    }

    @Override
    public VariableDeclarator clone() {
        return target.clone();
    }

    @Override
    public VariableDeclaratorMetaModel getMetaModel() {
        return target.getMetaModel();
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        return target.replace(node, replacementNode);
    }

    @Override
    public ResolvedValueDeclaration resolve() {
        return target.resolve();
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
        if (target != null) {
            target.register(observer);
        }
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
    public VariableDeclarator setName(String name) {
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
    public VariableDeclarator setType(Class<?> typeClass) {
        return target.setType(typeClass);
    }

    @Override
    public VariableDeclarator setType(String typeString) {
        return target.setType(typeString);
    }

    @Override
    public String getTypeAsString() {
        return target.getTypeAsString();
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
