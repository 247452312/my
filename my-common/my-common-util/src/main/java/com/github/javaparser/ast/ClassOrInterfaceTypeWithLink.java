package com.github.javaparser.ast;

import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ClassOrInterfaceTypeMetaModel;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.types.ResolvedType;
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
 * @date 文件创建日期 2022年04月29日 15时19分
 */
public class ClassOrInterfaceTypeWithLink extends ClassOrInterfaceType {

    private ClassOrInterfaceType target;

    /**
     * 是否是数组, 默认false代表不是数组
     */
    private Boolean arrayFlag = Boolean.FALSE;

    /**
     * 是否是基本类型,默认false代表不是基本类型
     */
    private Boolean primitiveFlag = Boolean.FALSE;

    private CompilationUnitWithLink typeTarget;

    public ClassOrInterfaceTypeWithLink(ClassOrInterfaceType target) {
        this.target = target;
    }

    public Boolean getPrimitiveFlag() {
        return primitiveFlag;
    }

    public void setPrimitiveFlag(Boolean primitiveFlag) {
        this.primitiveFlag = primitiveFlag;
    }

    public Boolean getArrayFlag() {
        return arrayFlag;
    }

    public void setArrayFlag(Boolean arrayFlag) {
        this.arrayFlag = arrayFlag;
    }

    public CompilationUnitWithLink getTypeTarget() {
        return typeTarget;
    }

    public void setTypeTarget(CompilationUnitWithLink typeTarget) {
        this.typeTarget = typeTarget;
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
    public SimpleName getName() {
        return target.getName();
    }

    @Override
    public String getNameWithScope() {
        return target.getNameWithScope();
    }

    @Override
    public Optional<ClassOrInterfaceType> getScope() {
        return target.getScope();
    }

    @Override
    public boolean isBoxedType() {
        return target.isBoxedType();
    }

    @Override
    public PrimitiveType toUnboxedType() throws UnsupportedOperationException {
        return target.toUnboxedType();
    }

    @Override
    public ClassOrInterfaceType setName(SimpleName name) {
        if (target != null) {
            return target.setName(name);
        }
        return null;
    }

    @Override
    public ClassOrInterfaceType setScope(ClassOrInterfaceType scope) {
        if (target != null) {
            return target.setScope(scope);
        }
        return null;
    }

    @Override
    public Optional<NodeList<Type>> getTypeArguments() {
        return target.getTypeArguments();
    }

    @Override
    public ClassOrInterfaceType setTypeArguments(NodeList<Type> typeArguments) {
        if (target != null) {
            return target.setTypeArguments(typeArguments);
        }
        return null;
    }

    @Override
    public ClassOrInterfaceType setAnnotations(NodeList<AnnotationExpr> annotations) {
        if (target != null) {
            return target.setAnnotations(annotations);
        }
        return null;
    }

    @Override
    public boolean remove(Node node) {
        return target.remove(node);
    }

    @Override
    public String asString() {
        return target.asString();
    }

    @Override
    public String toDescriptor() {
        return target.toDescriptor();
    }

    @Override
    public ClassOrInterfaceType removeScope() {
        return target.removeScope();
    }

    @Override
    public ClassOrInterfaceType clone() {
        return target.clone();
    }

    @Override
    public ClassOrInterfaceTypeMetaModel getMetaModel() {
        return target.getMetaModel();
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        return target.replace(node, replacementNode);
    }

    @Override
    public boolean isClassOrInterfaceType() {
        return target.isClassOrInterfaceType();
    }

    @Override
    public ClassOrInterfaceType asClassOrInterfaceType() {
        return target.asClassOrInterfaceType();
    }

    @Override
    public void ifClassOrInterfaceType(Consumer<ClassOrInterfaceType> action) {
        target.ifClassOrInterfaceType(action);
    }

    @Override
    public ResolvedType resolve() {
        return target.resolve();
    }

    @Override
    public Optional<ClassOrInterfaceType> toClassOrInterfaceType() {
        return target.toClassOrInterfaceType();
    }

    @Override
    public ClassOrInterfaceType setAnnotation(int i, AnnotationExpr element) {
        if (target != null) {
            return target.setAnnotation(i, element);
        }
        return null;
    }

    @Override
    public ClassOrInterfaceType addAnnotation(AnnotationExpr element) {
        return target.addAnnotation(element);
    }

    @Override
    public ClassOrInterfaceType addAnnotation(String name) {
        return target.addAnnotation(name);
    }

    @Override
    public NormalAnnotationExpr addAndGetAnnotation(String name) {
        return target.addAndGetAnnotation(name);
    }

    @Override
    public ClassOrInterfaceType addAnnotation(Class<? extends Annotation> clazz) {
        return target.addAnnotation(clazz);
    }

    @Override
    public NormalAnnotationExpr addAndGetAnnotation(Class<? extends Annotation> clazz) {
        return target.addAndGetAnnotation(clazz);
    }

    @Override
    public ClassOrInterfaceType addMarkerAnnotation(String name) {
        return target.addMarkerAnnotation(name);
    }

    @Override
    public ClassOrInterfaceType addMarkerAnnotation(Class<? extends Annotation> clazz) {
        return target.addMarkerAnnotation(clazz);
    }

    @Override
    public ClassOrInterfaceType addSingleMemberAnnotation(String name, Expression expression) {
        return target.addSingleMemberAnnotation(name, expression);
    }

    @Override
    public ClassOrInterfaceType addSingleMemberAnnotation(Class<? extends Annotation> clazz, Expression expression) {
        return target.addSingleMemberAnnotation(clazz, expression);
    }

    @Override
    public ClassOrInterfaceType addSingleMemberAnnotation(String name, String value) {
        return target.addSingleMemberAnnotation(name, value);
    }

    @Override
    public ClassOrInterfaceType addSingleMemberAnnotation(Class<? extends Annotation> clazz, String value) {
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
    public ClassOrInterfaceType setName(String name) {
        if (target != null) {
            return target.setName(name);
        }
        return null;
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
    public boolean isUsingDiamondOperator() {
        return target.isUsingDiamondOperator();
    }

    @Override
    public ClassOrInterfaceType setDiamondOperator() {
        if (target != null) {
            return target.setDiamondOperator();
        }
        return null;
    }

    @Override
    public ClassOrInterfaceType removeTypeArguments() {
        return target.removeTypeArguments();
    }

    @Override
    public ClassOrInterfaceType setTypeArguments(Type... typeArguments) {
        if (target != null) {
            return target.setTypeArguments(typeArguments);
        }
        return null;
    }

    @Override
    public boolean isReferenceType() {
        return target.isReferenceType();
    }

    @Override
    public ReferenceType asReferenceType() {
        return target.asReferenceType();
    }

    @Override
    public void ifReferenceType(Consumer<ReferenceType> action) {
        target.ifReferenceType(action);
    }

    @Override
    public Optional<ReferenceType> toReferenceType() {
        return target.toReferenceType();
    }

    @Override
    public NodeList<AnnotationExpr> getAnnotations() {
        return target.getAnnotations();
    }

    @Override
    public AnnotationExpr getAnnotation(int i) {
        return target.getAnnotation(i);
    }

    @Override
    public Type getElementType() {
        return target.getElementType();
    }

    @Override
    public int getArrayLevel() {
        return target.getArrayLevel();
    }

    @Override
    public boolean isArrayType() {
        return target.isArrayType();
    }

    @Override
    public ArrayType asArrayType() {
        return target.asArrayType();
    }

    @Override
    public boolean isIntersectionType() {
        return target.isIntersectionType();
    }

    @Override
    public IntersectionType asIntersectionType() {
        return target.asIntersectionType();
    }

    @Override
    public boolean isPrimitiveType() {
        return target.isPrimitiveType();
    }

    @Override
    public PrimitiveType asPrimitiveType() {
        return target.asPrimitiveType();
    }

    @Override
    public boolean isTypeParameter() {
        return target.isTypeParameter();
    }

    @Override
    public TypeParameter asTypeParameter() {
        return target.asTypeParameter();
    }

    @Override
    public boolean isUnionType() {
        return target.isUnionType();
    }

    @Override
    public UnionType asUnionType() {
        return target.asUnionType();
    }

    @Override
    public boolean isUnknownType() {
        return target.isUnknownType();
    }

    @Override
    public UnknownType asUnknownType() {
        return target.asUnknownType();
    }

    @Override
    public boolean isVoidType() {
        return target.isVoidType();
    }

    @Override
    public VoidType asVoidType() {
        return target.asVoidType();
    }

    @Override
    public boolean isWildcardType() {
        return target.isWildcardType();
    }

    @Override
    public WildcardType asWildcardType() {
        return target.asWildcardType();
    }

    @Override
    public void ifArrayType(Consumer<ArrayType> action) {
        target.ifArrayType(action);
    }

    @Override
    public void ifIntersectionType(Consumer<IntersectionType> action) {
        target.ifIntersectionType(action);
    }

    @Override
    public void ifPrimitiveType(Consumer<PrimitiveType> action) {
        target.ifPrimitiveType(action);
    }

    @Override
    public void ifTypeParameter(Consumer<TypeParameter> action) {
        target.ifTypeParameter(action);
    }

    @Override
    public void ifUnionType(Consumer<UnionType> action) {
        target.ifUnionType(action);
    }

    @Override
    public void ifUnknownType(Consumer<UnknownType> action) {
        target.ifUnknownType(action);
    }

    @Override
    public void ifVoidType(Consumer<VoidType> action) {
        target.ifVoidType(action);
    }

    @Override
    public void ifWildcardType(Consumer<WildcardType> action) {
        target.ifWildcardType(action);
    }

    @Override
    public Optional<ArrayType> toArrayType() {
        return target.toArrayType();
    }

    @Override
    public Optional<IntersectionType> toIntersectionType() {
        return target.toIntersectionType();
    }

    @Override
    public Optional<PrimitiveType> toPrimitiveType() {
        return target.toPrimitiveType();
    }

    @Override
    public Optional<TypeParameter> toTypeParameter() {
        return target.toTypeParameter();
    }

    @Override
    public Optional<UnionType> toUnionType() {
        return target.toUnionType();
    }

    @Override
    public Optional<UnknownType> toUnknownType() {
        return target.toUnknownType();
    }

    @Override
    public Optional<VoidType> toVoidType() {
        return target.toVoidType();
    }

    @Override
    public Optional<WildcardType> toWildcardType() {
        return target.toWildcardType();
    }

    @Override
    public boolean isVarType() {
        return target.isVarType();
    }

    @Override
    public VarType asVarType() {
        return target.asVarType();
    }

    @Override
    public Optional<VarType> toVarType() {
        return target.toVarType();
    }

    @Override
    public void ifVarType(Consumer<VarType> action) {
        target.ifVarType(action);
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
        if (target != null) {
            return target.getDataKeys();
        }
        return null;
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
        if (target != null) {
            return target.setParsed(parsed);
        }
        return null;
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
