package com.github.javaparser.ast.expr;

import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.CompilationUnitWithLink;
import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.resolution.types.ResolvedType;
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
 * @date 文件创建日期 2022年05月13日 11时34分
 */
public class ExpressionWithLink extends Expression {


    /**
     * 表达式最终返回的类型
     */
    private CompilationUnitWithLink returnCompilationUnitWithLink;

    /**
     * 原始target
     */
    private Expression target;

    public ExpressionWithLink(Expression target) {
        this.target = target;
    }

    public CompilationUnitWithLink getReturnCompilationUnitWithLink() {
        return returnCompilationUnitWithLink;
    }

    public void setReturnCompilationUnitWithLink(CompilationUnitWithLink returnCompilationUnitWithLink) {
        this.returnCompilationUnitWithLink = returnCompilationUnitWithLink;
    }

    public Expression getTarget() {
        return target;
    }

    public void setTarget(Expression target) {
        this.target = target;
    }

    @Override
    public Expression clone() {
        return target.clone();
    }

    @Override
    public ExpressionMetaModel getMetaModel() {
        return target.getMetaModel();
    }

    @Override
    public boolean isAnnotationExpr() {
        return target.isAnnotationExpr();
    }

    @Override
    public AnnotationExpr asAnnotationExpr() {
        return target.asAnnotationExpr();
    }

    @Override
    public boolean isArrayAccessExpr() {
        return target.isArrayAccessExpr();
    }

    @Override
    public ArrayAccessExpr asArrayAccessExpr() {
        return target.asArrayAccessExpr();
    }

    @Override
    public boolean isArrayCreationExpr() {
        return target.isArrayCreationExpr();
    }

    @Override
    public ArrayCreationExpr asArrayCreationExpr() {
        return target.asArrayCreationExpr();
    }

    @Override
    public boolean isArrayInitializerExpr() {
        return target.isArrayInitializerExpr();
    }

    @Override
    public ArrayInitializerExpr asArrayInitializerExpr() {
        return target.asArrayInitializerExpr();
    }

    @Override
    public boolean isAssignExpr() {
        return target.isAssignExpr();
    }

    @Override
    public AssignExpr asAssignExpr() {
        return target.asAssignExpr();
    }

    @Override
    public boolean isBinaryExpr() {
        return target.isBinaryExpr();
    }

    @Override
    public BinaryExpr asBinaryExpr() {
        return target.asBinaryExpr();
    }

    @Override
    public boolean isBooleanLiteralExpr() {
        return target.isBooleanLiteralExpr();
    }

    @Override
    public BooleanLiteralExpr asBooleanLiteralExpr() {
        return target.asBooleanLiteralExpr();
    }

    @Override
    public boolean isCastExpr() {
        return target.isCastExpr();
    }

    @Override
    public CastExpr asCastExpr() {
        return target.asCastExpr();
    }

    @Override
    public boolean isCharLiteralExpr() {
        return target.isCharLiteralExpr();
    }

    @Override
    public CharLiteralExpr asCharLiteralExpr() {
        return target.asCharLiteralExpr();
    }

    @Override
    public boolean isClassExpr() {
        return target.isClassExpr();
    }

    @Override
    public ClassExpr asClassExpr() {
        return target.asClassExpr();
    }

    @Override
    public boolean isConditionalExpr() {
        return target.isConditionalExpr();
    }

    @Override
    public ConditionalExpr asConditionalExpr() {
        return target.asConditionalExpr();
    }

    @Override
    public boolean isDoubleLiteralExpr() {
        return target.isDoubleLiteralExpr();
    }

    @Override
    public DoubleLiteralExpr asDoubleLiteralExpr() {
        return target.asDoubleLiteralExpr();
    }

    @Override
    public boolean isEnclosedExpr() {
        return target.isEnclosedExpr();
    }

    @Override
    public EnclosedExpr asEnclosedExpr() {
        return target.asEnclosedExpr();
    }

    @Override
    public boolean isFieldAccessExpr() {
        return target.isFieldAccessExpr();
    }

    @Override
    public FieldAccessExpr asFieldAccessExpr() {
        return target.asFieldAccessExpr();
    }

    @Override
    public boolean isInstanceOfExpr() {
        return target.isInstanceOfExpr();
    }

    @Override
    public InstanceOfExpr asInstanceOfExpr() {
        return target.asInstanceOfExpr();
    }

    @Override
    public boolean isIntegerLiteralExpr() {
        return target.isIntegerLiteralExpr();
    }

    @Override
    public IntegerLiteralExpr asIntegerLiteralExpr() {
        return target.asIntegerLiteralExpr();
    }

    @Override
    public boolean isLambdaExpr() {
        return target.isLambdaExpr();
    }

    @Override
    public LambdaExpr asLambdaExpr() {
        return target.asLambdaExpr();
    }

    @Override
    public boolean isLiteralExpr() {
        return target.isLiteralExpr();
    }

    @Override
    public LiteralExpr asLiteralExpr() {
        return target.asLiteralExpr();
    }

    @Override
    public boolean isLiteralStringValueExpr() {
        return target.isLiteralStringValueExpr();
    }

    @Override
    public LiteralStringValueExpr asLiteralStringValueExpr() {
        return target.asLiteralStringValueExpr();
    }

    @Override
    public boolean isLongLiteralExpr() {
        return target.isLongLiteralExpr();
    }

    @Override
    public LongLiteralExpr asLongLiteralExpr() {
        return target.asLongLiteralExpr();
    }

    @Override
    public boolean isMarkerAnnotationExpr() {
        return target.isMarkerAnnotationExpr();
    }

    @Override
    public MarkerAnnotationExpr asMarkerAnnotationExpr() {
        return target.asMarkerAnnotationExpr();
    }

    @Override
    public boolean isMethodCallExpr() {
        return target.isMethodCallExpr();
    }

    @Override
    public MethodCallExpr asMethodCallExpr() {
        return target.asMethodCallExpr();
    }

    @Override
    public boolean isMethodReferenceExpr() {
        return target.isMethodReferenceExpr();
    }

    @Override
    public MethodReferenceExpr asMethodReferenceExpr() {
        return target.asMethodReferenceExpr();
    }

    @Override
    public boolean isNameExpr() {
        return target.isNameExpr();
    }

    @Override
    public NameExpr asNameExpr() {
        return target.asNameExpr();
    }

    @Override
    public boolean isNormalAnnotationExpr() {
        return target.isNormalAnnotationExpr();
    }

    @Override
    public NormalAnnotationExpr asNormalAnnotationExpr() {
        return target.asNormalAnnotationExpr();
    }

    @Override
    public boolean isNullLiteralExpr() {
        return target.isNullLiteralExpr();
    }

    @Override
    public NullLiteralExpr asNullLiteralExpr() {
        return target.asNullLiteralExpr();
    }

    @Override
    public boolean isObjectCreationExpr() {
        return target.isObjectCreationExpr();
    }

    @Override
    public ObjectCreationExpr asObjectCreationExpr() {
        return target.asObjectCreationExpr();
    }

    @Override
    public boolean isSingleMemberAnnotationExpr() {
        return target.isSingleMemberAnnotationExpr();
    }

    @Override
    public SingleMemberAnnotationExpr asSingleMemberAnnotationExpr() {
        return target.asSingleMemberAnnotationExpr();
    }

    @Override
    public boolean isStringLiteralExpr() {
        return target.isStringLiteralExpr();
    }

    @Override
    public StringLiteralExpr asStringLiteralExpr() {
        return target.asStringLiteralExpr();
    }


    @Override
    public boolean isThisExpr() {
        return target.isThisExpr();
    }

    @Override
    public ThisExpr asThisExpr() {
        return target.asThisExpr();
    }

    @Override
    public boolean isTypeExpr() {
        return target.isTypeExpr();
    }

    @Override
    public TypeExpr asTypeExpr() {
        return target.asTypeExpr();
    }

    @Override
    public boolean isUnaryExpr() {
        return target.isUnaryExpr();
    }

    @Override
    public UnaryExpr asUnaryExpr() {
        return target.asUnaryExpr();
    }

    @Override
    public boolean isVariableDeclarationExpr() {
        return target.isVariableDeclarationExpr();
    }

    @Override
    public VariableDeclarationExpr asVariableDeclarationExpr() {
        return target.asVariableDeclarationExpr();
    }

    @Override
    public void ifAnnotationExpr(Consumer<AnnotationExpr> action) {
        target.ifAnnotationExpr(action);
    }

    @Override
    public void ifArrayAccessExpr(Consumer<ArrayAccessExpr> action) {
        target.ifArrayAccessExpr(action);
    }

    @Override
    public void ifArrayCreationExpr(Consumer<ArrayCreationExpr> action) {
        target.ifArrayCreationExpr(action);
    }

    @Override
    public void ifArrayInitializerExpr(Consumer<ArrayInitializerExpr> action) {
        target.ifArrayInitializerExpr(action);
    }

    @Override
    public void ifAssignExpr(Consumer<AssignExpr> action) {
        target.ifAssignExpr(action);
    }

    @Override
    public void ifBinaryExpr(Consumer<BinaryExpr> action) {
        target.ifBinaryExpr(action);
    }

    @Override
    public void ifBooleanLiteralExpr(Consumer<BooleanLiteralExpr> action) {
        target.ifBooleanLiteralExpr(action);
    }

    @Override
    public void ifCastExpr(Consumer<CastExpr> action) {
        target.ifCastExpr(action);
    }

    @Override
    public void ifCharLiteralExpr(Consumer<CharLiteralExpr> action) {
        target.ifCharLiteralExpr(action);
    }

    @Override
    public void ifClassExpr(Consumer<ClassExpr> action) {
        target.ifClassExpr(action);
    }

    @Override
    public void ifConditionalExpr(Consumer<ConditionalExpr> action) {
        target.ifConditionalExpr(action);
    }

    @Override
    public void ifDoubleLiteralExpr(Consumer<DoubleLiteralExpr> action) {
        target.ifDoubleLiteralExpr(action);
    }

    @Override
    public void ifEnclosedExpr(Consumer<EnclosedExpr> action) {
        target.ifEnclosedExpr(action);
    }

    @Override
    public void ifFieldAccessExpr(Consumer<FieldAccessExpr> action) {
        target.ifFieldAccessExpr(action);
    }

    @Override
    public void ifInstanceOfExpr(Consumer<InstanceOfExpr> action) {
        target.ifInstanceOfExpr(action);
    }

    @Override
    public void ifIntegerLiteralExpr(Consumer<IntegerLiteralExpr> action) {
        target.ifIntegerLiteralExpr(action);
    }

    @Override
    public void ifLambdaExpr(Consumer<LambdaExpr> action) {
        target.ifLambdaExpr(action);
    }

    @Override
    public void ifLiteralExpr(Consumer<LiteralExpr> action) {
        target.ifLiteralExpr(action);
    }

    @Override
    public void ifLiteralStringValueExpr(Consumer<LiteralStringValueExpr> action) {
        target.ifLiteralStringValueExpr(action);
    }

    @Override
    public void ifLongLiteralExpr(Consumer<LongLiteralExpr> action) {
        target.ifLongLiteralExpr(action);
    }

    @Override
    public void ifMarkerAnnotationExpr(Consumer<MarkerAnnotationExpr> action) {
        target.ifMarkerAnnotationExpr(action);
    }

    @Override
    public void ifMethodCallExpr(Consumer<MethodCallExpr> action) {
        target.ifMethodCallExpr(action);
    }

    @Override
    public void ifMethodReferenceExpr(Consumer<MethodReferenceExpr> action) {
        target.ifMethodReferenceExpr(action);
    }

    @Override
    public void ifNameExpr(Consumer<NameExpr> action) {
        target.ifNameExpr(action);
    }

    @Override
    public void ifNormalAnnotationExpr(Consumer<NormalAnnotationExpr> action) {
        target.ifNormalAnnotationExpr(action);
    }

    @Override
    public void ifNullLiteralExpr(Consumer<NullLiteralExpr> action) {
        target.ifNullLiteralExpr(action);
    }

    @Override
    public void ifObjectCreationExpr(Consumer<ObjectCreationExpr> action) {
        target.ifObjectCreationExpr(action);
    }

    @Override
    public void ifSingleMemberAnnotationExpr(Consumer<SingleMemberAnnotationExpr> action) {
        target.ifSingleMemberAnnotationExpr(action);
    }

    @Override
    public void ifStringLiteralExpr(Consumer<StringLiteralExpr> action) {
        target.ifStringLiteralExpr(action);
    }

    @Override
    public void ifThisExpr(Consumer<ThisExpr> action) {
        target.ifThisExpr(action);
    }

    @Override
    public void ifTypeExpr(Consumer<TypeExpr> action) {
        target.ifTypeExpr(action);
    }

    @Override
    public void ifUnaryExpr(Consumer<UnaryExpr> action) {
        target.ifUnaryExpr(action);
    }

    @Override
    public void ifVariableDeclarationExpr(Consumer<VariableDeclarationExpr> action) {
        target.ifVariableDeclarationExpr(action);
    }

    @Override
    public ResolvedType calculateResolvedType() {
        return target.calculateResolvedType();
    }

    @Override
    public Optional<AnnotationExpr> toAnnotationExpr() {
        return target.toAnnotationExpr();
    }

    @Override
    public Optional<ArrayAccessExpr> toArrayAccessExpr() {
        return target.toArrayAccessExpr();
    }

    @Override
    public Optional<ArrayCreationExpr> toArrayCreationExpr() {
        return target.toArrayCreationExpr();
    }

    @Override
    public Optional<ArrayInitializerExpr> toArrayInitializerExpr() {
        return target.toArrayInitializerExpr();
    }

    @Override
    public Optional<AssignExpr> toAssignExpr() {
        return target.toAssignExpr();
    }

    @Override
    public Optional<BinaryExpr> toBinaryExpr() {
        return target.toBinaryExpr();
    }

    @Override
    public Optional<BooleanLiteralExpr> toBooleanLiteralExpr() {
        return target.toBooleanLiteralExpr();
    }

    @Override
    public Optional<CastExpr> toCastExpr() {
        return target.toCastExpr();
    }

    @Override
    public Optional<CharLiteralExpr> toCharLiteralExpr() {
        return target.toCharLiteralExpr();
    }

    @Override
    public Optional<ClassExpr> toClassExpr() {
        return target.toClassExpr();
    }

    @Override
    public Optional<ConditionalExpr> toConditionalExpr() {
        return target.toConditionalExpr();
    }

    @Override
    public Optional<DoubleLiteralExpr> toDoubleLiteralExpr() {
        return target.toDoubleLiteralExpr();
    }

    @Override
    public Optional<EnclosedExpr> toEnclosedExpr() {
        return target.toEnclosedExpr();
    }

    @Override
    public Optional<FieldAccessExpr> toFieldAccessExpr() {
        return target.toFieldAccessExpr();
    }

    @Override
    public Optional<InstanceOfExpr> toInstanceOfExpr() {
        return target.toInstanceOfExpr();
    }

    @Override
    public Optional<IntegerLiteralExpr> toIntegerLiteralExpr() {
        return target.toIntegerLiteralExpr();
    }

    @Override
    public Optional<LambdaExpr> toLambdaExpr() {
        return target.toLambdaExpr();
    }

    @Override
    public Optional<LiteralExpr> toLiteralExpr() {
        return target.toLiteralExpr();
    }

    @Override
    public Optional<LiteralStringValueExpr> toLiteralStringValueExpr() {
        return target.toLiteralStringValueExpr();
    }

    @Override
    public Optional<LongLiteralExpr> toLongLiteralExpr() {
        return target.toLongLiteralExpr();
    }

    @Override
    public Optional<MarkerAnnotationExpr> toMarkerAnnotationExpr() {
        return target.toMarkerAnnotationExpr();
    }

    @Override
    public Optional<MethodCallExpr> toMethodCallExpr() {
        return target.toMethodCallExpr();
    }

    @Override
    public Optional<MethodReferenceExpr> toMethodReferenceExpr() {
        return target.toMethodReferenceExpr();
    }

    @Override
    public Optional<NameExpr> toNameExpr() {
        return target.toNameExpr();
    }

    @Override
    public Optional<NormalAnnotationExpr> toNormalAnnotationExpr() {
        return target.toNormalAnnotationExpr();
    }

    @Override
    public Optional<NullLiteralExpr> toNullLiteralExpr() {
        return target.toNullLiteralExpr();
    }

    @Override
    public Optional<ObjectCreationExpr> toObjectCreationExpr() {
        return target.toObjectCreationExpr();
    }

    @Override
    public Optional<SingleMemberAnnotationExpr> toSingleMemberAnnotationExpr() {
        return target.toSingleMemberAnnotationExpr();
    }

    @Override
    public Optional<StringLiteralExpr> toStringLiteralExpr() {
        return target.toStringLiteralExpr();
    }

    @Override
    public Optional<ThisExpr> toThisExpr() {
        return target.toThisExpr();
    }

    @Override
    public Optional<TypeExpr> toTypeExpr() {
        return target.toTypeExpr();
    }

    @Override
    public Optional<UnaryExpr> toUnaryExpr() {
        return target.toUnaryExpr();
    }

    @Override
    public Optional<VariableDeclarationExpr> toVariableDeclarationExpr() {
        return target.toVariableDeclarationExpr();
    }

    @Override
    public boolean isSwitchExpr() {
        return target.isSwitchExpr();
    }

    @Override
    public SwitchExpr asSwitchExpr() {
        return target.asSwitchExpr();
    }

    @Override
    public Optional<SwitchExpr> toSwitchExpr() {
        return target.toSwitchExpr();
    }

    @Override
    public void ifSwitchExpr(Consumer<SwitchExpr> action) {
        target.ifSwitchExpr(action);
    }

    @Override
    public boolean isTextBlockLiteralExpr() {
        return target.isTextBlockLiteralExpr();
    }

    @Override
    public TextBlockLiteralExpr asTextBlockLiteralExpr() {
        return target.asTextBlockLiteralExpr();
    }

    @Override
    public Optional<TextBlockLiteralExpr> toTextBlockLiteralExpr() {
        return target.toTextBlockLiteralExpr();
    }

    @Override
    public void ifTextBlockLiteralExpr(Consumer<TextBlockLiteralExpr> action) {
        target.ifTextBlockLiteralExpr(action);
    }

    @Override
    public boolean isPatternExpr() {
        return target.isPatternExpr();
    }

    @Override
    public PatternExpr asPatternExpr() {
        return target.asPatternExpr();
    }

    @Override
    public Optional<PatternExpr> toPatternExpr() {
        return target.toPatternExpr();
    }

    @Override
    public void ifPatternExpr(Consumer<PatternExpr> action) {
        target.ifPatternExpr(action);
    }

    @Override
    public boolean isStandaloneExpression() {
        return target.isStandaloneExpression();
    }

    @Override
    public boolean isPolyExpression() {
        return target.isPolyExpression();
    }

    @Override
    public boolean isQualified() {
        return target.isQualified();
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
    public boolean remove(Node node) {
        return target.remove(node);
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
    public boolean replace(Node node, Node replacementNode) {
        return target.replace(node, replacementNode);
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
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return null;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {

    }

    @Override
    protected boolean isAssignmentContext() {
        return target.isAssignmentContext();
    }

    @Override
    protected boolean isInvocationContext() {
        return target.isInvocationContext();
    }

}
