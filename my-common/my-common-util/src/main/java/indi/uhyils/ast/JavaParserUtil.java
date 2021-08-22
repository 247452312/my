package indi.uhyils.ast;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import indi.uhyils.ast.model.MethodNodeAllInfo;
import indi.uhyils.ast.model.MethodNodeStatisticsInfo;
import indi.uhyils.ast.model.ParseMethodNodeExcludeModel;
import indi.uhyils.util.CollectionUtil;
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

    public static void main(String[] args) throws FileNotFoundException {
        List<CompilationUnit> compilationUnits = parseProject(Arrays
                                                                  .asList("D:\\share\\ideaSrc\\my\\my-common\\my-common-base\\src\\main\\java\\indi\\uhyils\\repository\\base\\AbstractRepository.java"));
        Map<String, List<CompilationUnit>> collect = compilationUnits.stream()
                                                                     .filter(t -> t.getPackageDeclaration().isPresent())
                                                                     .filter(JavaParserUtil::filterInterface)
                                                                     .collect(Collectors.groupingBy(JavaParserUtil::namedUnit));

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

        for (Entry<String, List<CompilationUnit>> entry : collect.entrySet()) {
            List<CompilationUnit> values = entry.getValue();
            for (CompilationUnit value : values) {
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

        }
        printResult(sum, count, methodDeeps);

    }

    private static void printResult(long sum, int count, Map<String, MethodNodeStatisticsInfo> methodDeeps) {
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
