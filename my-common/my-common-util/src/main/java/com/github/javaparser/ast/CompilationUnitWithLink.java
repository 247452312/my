package com.github.javaparser.ast;

import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.Modifier.Keyword;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.CompilationUnitMetaModel;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.utils.LineSeparator;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月25日 08时34分
 */
public class CompilationUnitWithLink extends CompilationUnit {

    /**
     * 本体
     */
    private CompilationUnit target;


    public CompilationUnitWithLink setTarget(CompilationUnit target) {
        this.target = target;
        return this;
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
    public CompilationUnit printer(Printer printer) {
        return target.printer(printer);
    }


    @Override
    public List<Comment> getComments() {
        return target.getComments();
    }

    @Override
    public List<Comment> getAllComments() {
        return target.getAllComments();
    }

    @Override
    public NodeList<ImportDeclaration> getImports() {
        return target.getImports();
    }

    @Override
    public ImportDeclaration getImport(int i) {
        return target.getImport(i);
    }

    @Override
    public Optional<PackageDeclaration> getPackageDeclaration() {
        return target.getPackageDeclaration();
    }

    @Override
    public NodeList<TypeDeclaration<?>> getTypes() {
        return target.getTypes();
    }

    @Override
    public TypeDeclaration<?> getType(int i) {
        return target.getType(i);
    }

    @Override
    public CompilationUnit setImports(NodeList<ImportDeclaration> imports) {
        if (target != null) {
            return target.setImports(imports);
        }
        return null;
    }

    @Override
    public CompilationUnit setImport(int i, ImportDeclaration imports) {
        return target.setImport(i, imports);
    }

    @Override
    public CompilationUnit addImport(ImportDeclaration importDeclaration) {
        return target.addImport(importDeclaration);
    }

    @Override
    public CompilationUnit setPackageDeclaration(PackageDeclaration packageDeclaration) {
        if (target != null) {
            return target.setPackageDeclaration(packageDeclaration);
        }
        return null;
    }

    @Override
    public CompilationUnit setTypes(NodeList<TypeDeclaration<?>> types) {
        if (target != null) {
            return target.setTypes(types);
        }
        return null;
    }

    @Override
    public CompilationUnit setType(int i, TypeDeclaration<?> type) {
        return target.setType(i, type);
    }

    @Override
    public CompilationUnit addType(TypeDeclaration<?> type) {
        return target.addType(type);
    }

    @Override
    public CompilationUnit setPackageDeclaration(String name) {
        return target.setPackageDeclaration(name);
    }

    @Override
    public CompilationUnit addImport(String name) {
        return target.addImport(name);
    }

    @Override
    public CompilationUnit addImport(Class<?> clazz) {
        return target.addImport(clazz);
    }

    @Override
    public CompilationUnit addImport(String name, boolean isStatic, boolean isAsterisk) {
        return target.addImport(name, isStatic, isAsterisk);
    }

    @Override
    public ClassOrInterfaceDeclaration addClass(String name) {
        return target.addClass(name);
    }

    @Override
    public ClassOrInterfaceDeclaration addClass(String name, Keyword... modifiers) {
        return target.addClass(name, modifiers);
    }

    @Override
    public ClassOrInterfaceDeclaration addInterface(String name) {
        return target.addInterface(name);
    }

    @Override
    public ClassOrInterfaceDeclaration addInterface(String name, Keyword... modifiers) {
        return target.addInterface(name, modifiers);
    }

    @Override
    public EnumDeclaration addEnum(String name) {
        return target.addEnum(name);
    }

    @Override
    public EnumDeclaration addEnum(String name, Keyword... modifiers) {
        return target.addEnum(name, modifiers);
    }

    @Override
    public AnnotationDeclaration addAnnotationDeclaration(String name) {
        return target.addAnnotationDeclaration(name);
    }

    @Override
    public AnnotationDeclaration addAnnotationDeclaration(String name, Keyword... modifiers) {
        return target.addAnnotationDeclaration(name, modifiers);
    }

    @Override
    public Optional<ClassOrInterfaceDeclaration> getClassByName(String className) {
        return target.getClassByName(className);
    }

    @Override
    public List<ClassOrInterfaceDeclaration> getLocalDeclarationFromClassname(String className) {
        return target.getLocalDeclarationFromClassname(className);
    }

    @Override
    public Optional<ClassOrInterfaceDeclaration> getInterfaceByName(String interfaceName) {
        return target.getInterfaceByName(interfaceName);
    }

    @Override
    public Optional<EnumDeclaration> getEnumByName(String enumName) {
        return target.getEnumByName(enumName);
    }

    @Override
    public Optional<String> getPrimaryTypeName() {
        return target.getPrimaryTypeName();
    }

    @Override
    public Optional<TypeDeclaration<?>> getPrimaryType() {
        return target.getPrimaryType();
    }

    @Override
    public Optional<AnnotationDeclaration> getAnnotationDeclarationByName(String annotationName) {
        return target.getAnnotationDeclarationByName(annotationName);
    }

    @Override
    public boolean remove(Node node) {
        return target.remove(node);
    }

    @Override
    public CompilationUnit removePackageDeclaration() {
        return target.removePackageDeclaration();
    }

    @Override
    public Optional<ModuleDeclaration> getModule() {
        return target.getModule();
    }

    @Override
    public CompilationUnit setModule(ModuleDeclaration module) {
        if (target != null) {
            return target.setModule(module);
        }
        return null;
    }

    @Override
    public CompilationUnit removeModule() {
        return target.removeModule();
    }

    @Override
    public Optional<Storage> getStorage() {
        return target.getStorage();
    }

    @Override
    public CompilationUnit setStorage(Path path) {
        return target.setStorage(path);
    }

    @Override
    public CompilationUnit setStorage(Path path, Charset charset) {
        return target.setStorage(path, charset);
    }

    @Override
    public ModuleDeclaration setModule(String name) {
        return target.setModule(name);
    }

    @Override
    public void recalculatePositions() {
        target.recalculatePositions();
    }

    @Override
    public CompilationUnit clone() {
        return target.clone();
    }

    @Override
    public CompilationUnitMetaModel getMetaModel() {
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
    protected Printer getPrinter() {
        return target.getPrinter();
    }

    @Override
    protected Printer getPrinter(PrinterConfiguration config) {
        return target.getPrinter(config);
    }

    @Override
    protected void customInitialization() {
        if (target != null) {
            target.customInitialization();
        }
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
