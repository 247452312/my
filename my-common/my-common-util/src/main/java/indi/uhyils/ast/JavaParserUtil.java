package indi.uhyils.ast;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
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
import indi.uhyils.ast.link.MethodLineNode;
import indi.uhyils.ast.link.MethodLineTypeEnum;
import indi.uhyils.ast.link.NodeShape;
import indi.uhyils.ast.model.MethodNodeAllInfo;
import indi.uhyils.ast.model.MethodNodeStatisticsInfo;
import indi.uhyils.ast.model.ParseMethodNodeExcludeModel;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月21日 09时56分
 */
public class JavaParserUtil {

    /**
     * 解析器
     */
    private static final JavaParser JAVA_PARSER = new JavaParser();

    private static final Function<String, Boolean>[] skipMethodName = new Function[]{
        t -> ((String) t).startsWith("get"),
        t -> ((String) t).startsWith("set")
    };


    /**
     * main test
     *
     * @param args
     *
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        List<CompilationUnit> compilationUnits = parseProject(Arrays
                                                                  .asList(
                                                                      "D:\\share\\ideaSrc\\my"));
        Map<String, CompilationUnit> classNameUnitMap = compilationUnits.stream()
                                                                        .filter(t -> t.getPackageDeclaration().isPresent())
                                                                        .filter(JavaParserUtil::filterInterface)
                                                                        .collect(Collectors.toMap(JavaParserUtil::namedUnit, t -> t, (key1, key2) -> key1));

        //方法参数统计
        methodDeepAnalysis(classNameUnitMap);

        // 方法调用统计
        methodLink(classNameUnitMap, "indi.uhyils.core.message.MessageFactory#createMessage");

    }

    private static void methodLink(Map<String, CompilationUnit> collect, String methodName) {
        Map<String, MethodLineNode> methodLineNodeMap = makeMethodLineNode(collect);
        MethodLineNode methodLineNode = methodLineNodeMap.get(methodName);
        StringBuilder sb = new StringBuilder(";Example:\n");
        if (methodLineNode.getBlockNode()) {
            makeBlockMethodToDrawIo(methodLineNode, sb);
        } else {
            makeMethodToDrawIo(methodLineNode, sb);
        }
        System.out.println(sb.toString());
    }

    private static void makeMethodToDrawIo(MethodLineNode methodLineNode, StringBuilder sb) {
        for (int i = 0; i < methodLineNode.getChildNodes().size(); i++) {
            MethodLineNode childNode = methodLineNode.getChildNodes().get(i);
            String language = methodLineNode.getLanguageOnArrow().get(i);
            StringBuilder stringBuilder = makeDrawIoStr(methodLineNode, childNode, language);
            sb.append(stringBuilder);
        }
    }

    private static void makeBlockMethodToDrawIo(MethodLineNode methodLineNode, StringBuilder sb) {
        MethodLineNode last = methodLineNode;
        for (int i = 0; i < methodLineNode.getChildNodes().size(); i++) {
            MethodLineNode childNode = methodLineNode.getChildNodes().get(i);
            String language = methodLineNode.getLanguageOnArrow().get(i);
            StringBuilder stringBuilder = makeDrawIoStr(last, childNode, language);
            sb.append(stringBuilder);
            last = childNode;
        }
    }

    private static StringBuilder makeDrawIoStr(MethodLineNode last, MethodLineNode childNode, String language) {
        StringBuilder sb = new StringBuilder();
        String content = last.getContent();
        String childNodeContent = childNode.getContent();
        sb.append(content).append("->");
        if (!"".equals(language)) {
            sb.append(language);
            sb.append("->");
        }
        sb.append(childNodeContent).append("\n");
        if (childNode.getBlockNode()) {
            makeBlockMethodToDrawIo(childNode, sb);
        } else {
            makeMethodToDrawIo(childNode, sb);
        }
        return sb;
    }

    private static Map<String, MethodLineNode> makeMethodLineNode(Map<String, CompilationUnit> collect) {
        Map<String, MethodDeclaration> methodLink = getMethodLink(collect);
        Map<String, MethodLineNode> methodLineNodeMap = new HashMap<>(methodLink.size());
        // 遍历每一个方法
        for (Entry<String, MethodDeclaration> entry : methodLink.entrySet()) {
            // 方法全名 例如: A.B.C#getId
            String methodAllName = entry.getKey();
            MethodDeclaration value = entry.getValue();
            Optional<BlockStmt> body = value.getBody();
            if (!body.isPresent()) {
                continue;
            }
            BlockStmt blockStmt = body.get();
            MethodLineNode methodLineNode = new MethodLineNode();
            methodLineNode.setMethodLineType(MethodLineTypeEnum.START);
            methodLineNode.setNodeShape(NodeShape.circular);
            methodLineNode.setContent(value, "方法开始节点:" + methodAllName);
            parseBlockStmt(blockStmt, methodLineNode);
            methodLineNodeMap.put(methodAllName, methodLineNode);
        }
        return methodLineNodeMap;
    }

    private static void parseBlockStmt(BlockStmt blockStmt, MethodLineNode methodLineNode) {
        NodeList<Statement> statements = blockStmt.getStatements();
        methodLineNode.setBlockNode(true);
        parseStatement(statements, methodLineNode);
    }

    private static void parseStatement(NodeList<Statement> statements, MethodLineNode methodLineNode) {
        // 遍历方法中的每一行
        for (Statement statement : statements) {
            // 如果是一个简单语句
            if (statement.isExpressionStmt()) {
                ExpressionStmt expressionStmt = statement.asExpressionStmt();
                Expression expression = expressionStmt.getExpression();
                // 简单语句没有逻辑
                MethodLineNode methodNode = new MethodLineNode();
                methodNode.setContent(statement, expression.toString());
                methodNode.setNodeShape(NodeShape.square);
                methodNode.setMethodLineType(MethodLineTypeEnum.NORMAL);
                methodLineNode.addChildNode(methodNode, "");
            } else if (statement.isIfStmt()) {
                IfStmt ifStmt = statement.asIfStmt();
                parseIfStmt(ifStmt, methodLineNode);
            } else if (statement.isSwitchStmt()) {
                SwitchStmt switchStmt = statement.asSwitchStmt();
                MethodLineNode methodMode = parseSwitchStmt(switchStmt);
                methodLineNode.addChildNode(methodMode, "switch");
            } else if (statement.isReturnStmt()) {
                ReturnStmt returnStmt = statement.asReturnStmt();
                MethodLineNode methodNode = new MethodLineNode();
                methodNode.setContent(returnStmt, "return:" + returnStmt.toString());
                methodNode.setNodeShape(NodeShape.circular);
                methodNode.setMethodLineType(MethodLineTypeEnum.END);
                methodLineNode.addChildNode(methodNode, "return");
            } else if (statement.isTryStmt()) {
                TryStmt tryStmt = statement.asTryStmt();
                MethodLineNode methodNode = new MethodLineNode();
                methodNode.setContent(tryStmt, "try:" + tryStmt.getResources().toString());
                methodNode.setNodeShape(NodeShape.square);
                methodNode.setMethodLineType(MethodLineTypeEnum.TRY);
                BlockStmt tryBlock = tryStmt.getTryBlock();
                parseBlockStmt(tryBlock, methodNode);
                methodLineNode.addChildNode(methodNode, "try");
            } else if (statement.isForStmt()) {
                ForStmt forStmt = statement.asForStmt();
                MethodLineNode methodNode = parseForStmt(forStmt);
                methodLineNode.addChildNode(methodNode, "for");
            } else if (statement.isThrowStmt()) {
                ThrowStmt throwStmt = statement.asThrowStmt();
                MethodLineNode methodNode = new MethodLineNode();
                methodNode.setContent(throwStmt, "throw:" + throwStmt.toString());
                methodNode.setNodeShape(NodeShape.square);
                methodNode.setMethodLineType(MethodLineTypeEnum.THROW);
                methodLineNode.addChildNode(methodNode, "throw");
            } else if (statement.isForEachStmt()) {
                ForEachStmt forEachStmt = statement.asForEachStmt();
                MethodLineNode methodNode = parseForEachStmt(forEachStmt);
                methodLineNode.addChildNode(methodNode, "foreach");
            } else if (statement.isAssertStmt()) {
                AssertStmt assertStmt = statement.asAssertStmt();
                MethodLineNode methodNode = new MethodLineNode();
                methodNode.setContent(assertStmt, "assert:" + assertStmt.toString());
                methodNode.setNodeShape(NodeShape.square);
                methodNode.setMethodLineType(MethodLineTypeEnum.NORMAL);
                methodLineNode.addChildNode(methodNode, "assert");
            } else if (statement.isWhileStmt()) {
                WhileStmt whileStmt = statement.asWhileStmt();
                MethodLineNode methodNode = parseWhileStmt(whileStmt);
                methodLineNode.addChildNode(methodNode, "while");
            } else if (statement.isBlockStmt()) {
                parseBlockStmt(statement.asBlockStmt(), methodLineNode);
            } else if (statement.isSynchronizedStmt()) {
                SynchronizedStmt synchronizedStmt = statement.asSynchronizedStmt();
                MethodLineNode methodNode = parseSynchronizedStmt(synchronizedStmt);
                methodLineNode.addChildNode(methodNode, "sync");
            } else if (statement.isBreakStmt()) {
                BreakStmt breakStmt = statement.asBreakStmt();
                MethodLineNode methodNode = new MethodLineNode();
                methodNode.setContent(breakStmt, "break:" + breakStmt.toString());
                methodNode.setNodeShape(NodeShape.circular);
                methodNode.setMethodLineType(MethodLineTypeEnum.END);
                methodLineNode.addChildNode(methodNode, "break");
            } else if (statement.isContinueStmt()) {
                ContinueStmt continueStmt = statement.asContinueStmt();
                MethodLineNode methodNode = new MethodLineNode();
                methodNode.setContent(continueStmt, "continue:" + continueStmt.toString());
                methodNode.setNodeShape(NodeShape.circular);
                methodNode.setMethodLineType(MethodLineTypeEnum.END);
                methodLineNode.addChildNode(methodNode, "continue");
            } else if (statement.isDoStmt()) {
                DoStmt doStmt = statement.asDoStmt();
                MethodLineNode methodNode = parseDoStmt(doStmt);
                methodLineNode.addChildNode(methodNode, "do_while");
            } else {
                String name = statement.getClass().getName();
                LogUtil.error(name);
            }
        }
    }

    private static MethodLineNode parseDoStmt(DoStmt doStmt) {
        MethodLineNode methodNode = new MethodLineNode();
        methodNode.setContent(doStmt, "do_while:" + doStmt.getCondition().toString());
        methodNode.setNodeShape(NodeShape.diamond);
        methodNode.setMethodLineType(MethodLineTypeEnum.WHILE);
        Statement body = doStmt.getBody();
        parseBlockStmt((BlockStmt) body, methodNode);
        return methodNode;
    }

    private static MethodLineNode parseSynchronizedStmt(SynchronizedStmt synchronizedStmt) {
        MethodLineNode methodNode = new MethodLineNode();
        methodNode.setContent(synchronizedStmt, "synchronized:" + synchronizedStmt.getExpression().toString());
        methodNode.setNodeShape(NodeShape.diamond);
        methodNode.setMethodLineType(MethodLineTypeEnum.WHILE);
        Statement body = synchronizedStmt.getBody();
        parseBlockStmt((BlockStmt) body, methodNode);
        return methodNode;
    }

    private static MethodLineNode parseWhileStmt(WhileStmt whileStmt) {
        MethodLineNode methodNode = new MethodLineNode();
        methodNode.setContent(whileStmt, "while:" + whileStmt.getCondition().toString());
        methodNode.setNodeShape(NodeShape.diamond);
        methodNode.setMethodLineType(MethodLineTypeEnum.WHILE);
        Statement body = whileStmt.getBody();
        parseBlockStmt((BlockStmt) body, methodNode);
        return methodNode;
    }

    private static MethodLineNode parseForEachStmt(ForEachStmt forEachStmt) {
        MethodLineNode methodNode = new MethodLineNode();
        methodNode.setContent(forEachStmt, "foreach:" + forEachStmt.getVariable().toString() + ":" + forEachStmt.getIterable().toString());
        methodNode.setNodeShape(NodeShape.diamond);
        methodNode.setMethodLineType(MethodLineTypeEnum.FOR);
        Statement body = forEachStmt.getBody();
        parseBlockStmt((BlockStmt) body, methodNode);
        return methodNode;
    }

    private static MethodLineNode parseForStmt(ForStmt forStmt) {
        MethodLineNode methodNode = new MethodLineNode();
        methodNode.setContent(forStmt, "for:" + forStmt.getInitialization().toString() + ";" + forStmt.getCompare().get().toString() + ";" + forStmt.getUpdate().toString());
        methodNode.setNodeShape(NodeShape.diamond);
        methodNode.setMethodLineType(MethodLineTypeEnum.FOR);
        Statement body = forStmt.getBody();
        parseBlockStmt((BlockStmt) body, methodNode);
        return methodNode;
    }

    private static MethodLineNode parseSwitchStmt(SwitchStmt switchStmt) {
        MethodLineNode methodLineNode = new MethodLineNode();
        methodLineNode.setContent(switchStmt, "switch:" + switchStmt.getSelector().toString());
        methodLineNode.setNodeShape(NodeShape.diamond);
        methodLineNode.setMethodLineType(MethodLineTypeEnum.SWITCH);
        NodeList<SwitchEntry> entries = switchStmt.getEntries();
        for (SwitchEntry entry : entries) {
            MethodLineNode methodNode = new MethodLineNode();
            methodNode.setContent(entry, entry.getLabels().toString());
            NodeList<Statement> statements = entry.getStatements();
            parseStatement(statements, methodNode);
            methodLineNode.addChildNode(methodNode, "case: " + entry.getLabels().toString());
        }
        return methodLineNode;
    }

    private static void parseIfStmt(IfStmt ifStmt, MethodLineNode methodLineNode) {

        MethodLineNode methodNode = new MethodLineNode();
        methodNode.setMethodLineType(MethodLineTypeEnum.IF);
        methodNode.setNodeShape(NodeShape.diamond);
        methodNode.setContent(ifStmt, "if");
        NodeList<Statement> nodes = new NodeList<>();
        nodes.add(ifStmt.getThenStmt());
        parseStatement(nodes, methodNode);
        methodLineNode.addChildNode(methodNode, ifStmt.getCondition().toString());
        Optional<Statement> elseStmt = ifStmt.getElseStmt();
        if (elseStmt.isPresent()) {
            Statement statement = elseStmt.get();
            if (statement instanceof IfStmt) {
                IfStmt ifStmt1 = (IfStmt) statement;
                parseIfStmt(ifStmt1, methodLineNode);
            } else if (statement instanceof BlockStmt) {
                MethodLineNode elseNode = new MethodLineNode();
                elseNode.setMethodLineType(MethodLineTypeEnum.IF);
                elseNode.setNodeShape(NodeShape.diamond);
                elseNode.setContent(statement, ifStmt.getCondition().toString() + ":else");
                BlockStmt blockStmt = (BlockStmt) statement;
                parseBlockStmt(blockStmt, elseNode);
                methodLineNode.addChildNode(elseNode, "else");
            } else {
                int i = 1;
            }
        }
    }

    private static Map<String, MethodDeclaration> getMethodLink(Map<String, CompilationUnit> collect) {
        Map<String, MethodDeclaration> result = new HashMap<>();
        for (Entry<String, CompilationUnit> entry : collect.entrySet()) {
            String className = entry.getKey();
            CompilationUnit value = entry.getValue();
            Map<String, MethodDeclaration> methodResult = parseCompilationUnit(value, className);
            result.putAll(methodResult);
        }
        return result;
    }

    private static Map<String, MethodDeclaration> parseCompilationUnit(Node value, String className) {
        Map<String, MethodDeclaration> result = new HashMap<>();
        List<Node> childNodes = value.getChildNodes();
        if (CollectionUtil.isEmpty(childNodes)) {
            return result;
        }
        for (Node childNode : childNodes) {
            if (childNode instanceof MethodDeclaration) {
                MethodDeclaration methodNode = (MethodDeclaration) childNode;
                String methodName = methodNode.getName().toString();
                if (Arrays.stream(skipMethodName).noneMatch(t -> t.apply(methodName))) {
                    String classMethodName = className + "#" + methodName;
                    result.put(classMethodName, methodNode);
                }
            } else {
                result.putAll(parseCompilationUnit(childNode, className));
            }
        }
        return result;
    }

    private static void methodDeepAnalysis(Map<String, CompilationUnit> collect) {
        //方法总复杂度
        long sum = 0L;
        //方法个数
        int count = 0;

        Map<String, MethodNodeStatisticsInfo> methodDeeps = new HashMap<>();

        Function<String, Boolean> testFunction = s -> s.endsWith("Test");
        Function<String, Boolean> criteriaFunction = s -> s.endsWith("Criteria");
        Function<String, Boolean> exampleFunction = s -> s.endsWith("Example");
        ParseMethodNodeExcludeModel build = ParseMethodNodeExcludeModel
            .build(Arrays.asList(testFunction, criteriaFunction, exampleFunction), Arrays.asList("equals"::equals, "hashCode"::equals, "toString"::equals));

        for (Entry<String, CompilationUnit> entry : collect.entrySet()) {
            CompilationUnit value = entry.getValue();
            List<Node> childNodes = value.getChildNodes();
            for (Node childNode : childNodes) {
                MethodNodeAllInfo methodNodeInfo = parseMethodNode(childNode, build);
                if (methodNodeInfo == null) {
                    continue;
                }
                sum += methodNodeInfo.getSum();
                count += methodNodeInfo.getCount();
                methodDeeps.putAll(methodNodeInfo.getMethodDeep());
            }

        }
        printResult(sum, count, methodDeeps);
    }

    private static void printResult(long sum, int count, Map<String, MethodNodeStatisticsInfo> methodDeeps) {
        if (methodDeeps.size() == 0) {
            return;
        }
        List<Entry<String, MethodNodeStatisticsInfo>> list = new ArrayList<>(methodDeeps.entrySet());
        list.sort(Comparator.comparingInt(t -> t.getValue().getSumComplexity()));
        int middleIndex = list.size() / 2;
        Entry<String, MethodNodeStatisticsInfo> middle = list.get(middleIndex);
        double methodAvg = list.stream().mapToDouble(t -> t.getValue().getAvgComplexity()).average().getAsDouble();
        double methodMaxDeepAvg = list.stream().mapToDouble(t -> t.getValue().getMaxComplexity()).average().getAsDouble();

        List<MethodNodeStatisticsInfo> methodNodeStatisticsInfos = new ArrayList<>(methodDeeps.values());

        //均值
        double avgSumComplexity = methodNodeStatisticsInfos.stream().mapToDouble(MethodNodeStatisticsInfo::getSumComplexity).average().getAsDouble();
        double avgAvgComplexity = methodNodeStatisticsInfos.stream().mapToDouble(MethodNodeStatisticsInfo::getAvgComplexity).average().getAsDouble();
        double avgMaxComplexity = methodNodeStatisticsInfos.stream().mapToDouble(MethodNodeStatisticsInfo::getMaxComplexity).average().getAsDouble();

        //方差
        double varSumComplexity = methodNodeStatisticsInfos.stream().mapToDouble(t -> Math.pow(t.getSumComplexity() - avgSumComplexity, 2)).sum() / methodNodeStatisticsInfos.size();
        double varAvgComplexity = methodNodeStatisticsInfos.stream().mapToDouble(t -> Math.pow(t.getAvgComplexity() - avgSumComplexity, 2)).sum() / methodNodeStatisticsInfos.size();
        double varMaxComplexity = methodNodeStatisticsInfos.stream().mapToDouble(t -> Math.pow(t.getMaxComplexity() - avgSumComplexity, 2)).sum() / methodNodeStatisticsInfos.size();

        double avg = sum * 1.0 / count;
        System.out.println("方法总复杂度为:" + sum + "方法个数:" + count + " 平均方法复杂度为:" + avg + " 指令平均复杂度为:" + methodAvg + " 方法平均深度:" + methodMaxDeepAvg);
        MethodNodeStatisticsInfo middleValue = middle.getValue();
        Integer deep = middleValue.getSumComplexity();
        System.out.println("方法复杂度中位数为: " + deep);
        System.out.println("复杂度偏态: " + (avg - deep) * 1.0 / Math.sqrt(varSumComplexity));

        //相关系数
        double rSumComplexityMaxComplexity = methodNodeStatisticsInfos.stream().mapToDouble(t -> (t.getSumComplexity() - avgSumComplexity) * (t.getMaxComplexity()) - avgMaxComplexity)
                                                                      .sum() / methodNodeStatisticsInfos.size() / (Math.sqrt(varSumComplexity) * Math.sqrt(varMaxComplexity));
        double rSumComplexityAvgComplexity = methodNodeStatisticsInfos.stream().mapToDouble(t -> (t.getSumComplexity() - avgSumComplexity) * (t.getAvgComplexity()) - avgAvgComplexity)
                                                                      .sum() / methodNodeStatisticsInfos.size() / (Math.sqrt(varSumComplexity) * Math.sqrt(varAvgComplexity));
        double rMaxComplexityAvgComplexity = methodNodeStatisticsInfos.stream().mapToDouble(t -> (t.getMaxComplexity() - avgMaxComplexity) * (t.getAvgComplexity()) - avgAvgComplexity)
                                                                      .sum() / methodNodeStatisticsInfos.size() / (Math.sqrt(varMaxComplexity) * Math.sqrt(varAvgComplexity));
        System.out.println("相关系数: ");
        System.out.println("\t复杂度 and 最大深度:" + rSumComplexityMaxComplexity);
        System.out.println("\t复杂度 and 指令复杂度:" + rSumComplexityAvgComplexity);
        System.out.println("\t最大深度 and 指令复杂度:" + rMaxComplexityAvgComplexity);

        int maxSize = 25;
        List<Entry<String, MethodNodeStatisticsInfo>> nextList = null;
        if (list.size() > maxSize) {
            nextList = list.subList(list.size() - maxSize - 1, list.size() - 1);
        } else {
            nextList = list;
        }

        System.out.println();
        System.out.println("复杂度排名--------------------------------------------------------------------");
        for (int i = nextList.size() - 1; i >= 0; i--) {
            Entry<String, MethodNodeStatisticsInfo> entry = nextList.get(i);
            System.out.printf("名称: " + entry.getKey());
            System.out.printf(" 复杂度: " + entry.getValue().getSumComplexity());
            System.out.printf(" 指令平均复杂度为: " + entry.getValue().getAvgComplexity());
            System.out.println(" 方法深度: " + entry.getValue().getMaxComplexity());
        }
        list.sort(Comparator.comparingDouble(t -> t.getValue().getAvgComplexity()));
        if (list.size() > maxSize) {
            nextList = list.subList(list.size() - maxSize - 1, list.size() - 1);
        } else {
            nextList = list;
        }

        System.out.println();
        System.out.println("指令排名--------------------------------------------------------------------");
        for (int i = nextList.size() - 1; i >= 0; i--) {
            Entry<String, MethodNodeStatisticsInfo> entry = nextList.get(i);
            System.out.printf("名称: " + entry.getKey());
            System.out.printf(" 指令平均复杂度为: " + entry.getValue().getAvgComplexity());
            System.out.printf(" 总复杂度: " + entry.getValue().getSumComplexity());
            System.out.println(" 方法深度: " + entry.getValue().getMaxComplexity());
        }
        if (list.size() > maxSize) {
            nextList = list.subList(0, maxSize);
        } else {
            nextList = list;
        }
        System.out.println();
        System.out.println("指令倒排名--------------------------------------------------------------------");
        for (Entry<String, MethodNodeStatisticsInfo> entry : nextList) {
            System.out.printf("名称: " + entry.getKey());
            System.out.printf(" 指令平均复杂度为: " + entry.getValue().getAvgComplexity());
            System.out.printf(" 总复杂度: " + entry.getValue().getSumComplexity());
            System.out.println(" 方法深度: " + entry.getValue().getMaxComplexity());
        }

        list.sort(Comparator.comparingDouble(t -> t.getValue().getMaxComplexity()));
        if (list.size() > maxSize) {
            nextList = list.subList(list.size() - maxSize - 1, list.size() - 1);
        } else {
            nextList = list;
        }

        System.out.println();
        System.out.println("方法深度排名--------------------------------------------------------------------");
        for (int i = nextList.size() - 1; i >= 0; i--) {
            Entry<String, MethodNodeStatisticsInfo> entry = nextList.get(i);
            System.out.printf("名称: " + entry.getKey());
            System.out.printf(" 方法深度: " + entry.getValue().getMaxComplexity());
            System.out.printf(" 指令平均复杂度为: " + entry.getValue().getAvgComplexity());
            System.out.println(" 总复杂度: " + entry.getValue().getSumComplexity());
        }
    }

    private static String namedUnit(CompilationUnit unit) {
        Optional<PackageDeclaration> packageDeclaration = unit.getPackageDeclaration();
        return packageDeclaration.map(declaration -> declaration.getName() + "." + unit.getType(0).getName()).orElse("");
    }

    /**
     * 过滤接口
     *
     * @param unit 类本身
     *
     * @return
     */
    private static boolean filterInterface(CompilationUnit unit) {
        TypeDeclaration<?> type = unit.getType(0);
        if (type.isClassOrInterfaceDeclaration()) {
            ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) type;
            return !classOrInterfaceDeclaration.isInterface();
        }
        return false;
    }

    private static MethodNodeAllInfo parseMethodNode(Node node, ParseMethodNodeExcludeModel excludeModel) {
        if (node instanceof MethodDeclaration) {
            MethodDeclaration methodDeclaration = (MethodDeclaration) node;
            if (!methodDeclaration.getBody().isPresent()) {
                return null;
            }
            String methodName = methodDeclaration.getName().toString();
            Node parentNode = methodDeclaration.getParentNode().get();
            String className = null;
            if (parentNode instanceof NodeWithSimpleName) {
                NodeWithSimpleName typeDeclaration = (NodeWithSimpleName) parentNode;
                className = typeDeclaration.getName().toString();
            } else if (parentNode instanceof ObjectCreationExpr) {
                ObjectCreationExpr objectCreationExpr = (ObjectCreationExpr) parentNode;
                className = objectCreationExpr.getType() + "$";
            } else {
                className = "None";
            }
            String finalMethodName = methodName;
            String finalClassName = className;
            if (excludeModel.getExcludeMethodNames().stream().anyMatch(t -> t.apply(finalMethodName)) || excludeModel.getExcludeClassNames().stream().anyMatch(t -> t.apply(finalClassName))) {
                return null;
            }
            String allMethodName = className + "." + methodName;
            MethodNodeStatisticsInfo deepInfo = getDeep(node, 0);

            Integer deep = deepInfo.getSumComplexity();
            if ((methodName.startsWith("get") || methodName.startsWith("set") || methodName.startsWith("is")) && deep < 10) {
                return null;
            }
            MethodNodeAllInfo build = MethodNodeAllInfo.build(Long.valueOf(deep), 1, new HashMap<>());
            build.getMethodDeep().put(allMethodName, deepInfo);
            return build;
        }
        List<Node> childNodes = node.getChildNodes();
        if (CollectionUtil.isEmpty(childNodes)) {
            return null;
        }

        long sum = 0L;
        int count = 0;
        int index = 0;
        Map<String, MethodNodeStatisticsInfo> methodDeep = new HashMap<>();
        for (Node childNode : childNodes) {
            MethodNodeAllInfo methodNodeInfo = parseMethodNode(childNode, excludeModel);
            if (methodNodeInfo == null) {
                continue;
            }
            sum += methodNodeInfo.getSum();
            count += methodNodeInfo.getCount();
            for (Entry<String, MethodNodeStatisticsInfo> entry : methodNodeInfo.getMethodDeep().entrySet()) {
                if (methodDeep.containsKey(entry.getKey())) {
                    methodDeep.put(entry.getKey() + "_" + (index++), entry.getValue());
                } else {
                    methodDeep.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return MethodNodeAllInfo.build(sum, count, methodDeep);
    }

    private static MethodNodeStatisticsInfo getDeep(Node node, Integer maxDeep) {
        List<Node> childNodes = null;
        if (node instanceof MethodDeclaration) {
            MethodDeclaration methodDeclaration = (MethodDeclaration) node;
            BlockStmt blockStmt = methodDeclaration.getBody().get();
            childNodes = blockStmt.getChildNodes();
        } else {
            childNodes = node.getChildNodes();
        }
        if (CollectionUtil.isEmpty(childNodes)) {
            return MethodNodeStatisticsInfo.build(maxDeep + 1);
        }

        int sum = 1;
        int count = 1;
        int realDepp = 0;
        for (Node childNode : childNodes) {
            MethodNodeStatisticsInfo deep = getDeep(childNode, maxDeep + 1);
            if (deep == null) {
                continue;
            }
            realDepp = Math.max(realDepp, deep.getMaxComplexity());
            sum += deep.getSumComplexity();
            count++;
        }

        return MethodNodeStatisticsInfo.build(sum, sum * 1.0 / count, realDepp);

    }

    /**
     * 解析多个项目
     *
     * @param pathList 多个项目的跟路径
     *
     * @return
     */
    private static List<CompilationUnit> parseProject(List<String> pathList) throws FileNotFoundException {
        List<CompilationUnit> result = new ArrayList<>();

        for (String projectPath : pathList) {
            List<File> javaFiles = parseProjectToJavaFile(new File(projectPath));
            for (File javaFile : javaFiles) {
                ParseResult<CompilationUnit> parse = JAVA_PARSER.parse(javaFile);
                Optional<CompilationUnit> optional = parse.getResult();
                CompilationUnit compilationUnit = optional.get();
                result.add(compilationUnit);
            }
        }
        return result;
    }

    private static List<File> parseProjectToJavaFile(File projectFile) {
        List<File> result = new ArrayList<>();
        if (projectFile.isFile()) {
            if (projectFile.getName().endsWith(".java")) {
                result.add(projectFile);
            }
            return result;
        }

        File[] files = projectFile.listFiles();
        for (File file : files) {
            List<File> listFile = parseProjectToJavaFile(file);
            result.addAll(listFile);
        }
        return result;
    }
}
