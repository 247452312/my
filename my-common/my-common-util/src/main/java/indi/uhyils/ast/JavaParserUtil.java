package indi.uhyils.ast;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import indi.uhyils.ast.model.MethodNodeInfo;
import indi.uhyils.ast.model.ParseMethodNodeExcludeModel;
import indi.uhyils.util.CollectionUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<CompilationUnit> compilationUnits = parseProject(Arrays.asList("D:\\share\\ideaSrc\\my"));
        Map<String, List<CompilationUnit>> collect = compilationUnits.stream()
                                                                     .filter(t -> t.getPackageDeclaration().isPresent())
                                                                     .filter(JavaParserUtil::filterInterface)
                                                                     .collect(Collectors.groupingBy(JavaParserUtil::namedUnit));

        //方法总复杂度
        long sum = 0L;
        //方法个数
        int count = 0;

        Map<String, Integer> methodDeeps = new HashMap<>();

        Function<String, Boolean> testFunction = s -> s.endsWith("Test");
        Function<String, Boolean> criteriaFunction = s -> s.endsWith("Criteria");
        Function<String, Boolean> exampleFunction = s -> s.endsWith("Example");
        ParseMethodNodeExcludeModel build = ParseMethodNodeExcludeModel.build(Arrays.asList(testFunction, criteriaFunction,exampleFunction), Arrays.asList("equals"::equals, "hashCode"::equals, "toString"::equals));

        for (Entry<String, List<CompilationUnit>> entry : collect.entrySet()) {
            List<CompilationUnit> values = entry.getValue();
            for (CompilationUnit value : values) {
                List<Node> childNodes = value.getChildNodes();
                for (Node childNode : childNodes) {
                    MethodNodeInfo methodNodeInfo = parseMethodNode(childNode, build);
                    if (methodNodeInfo == null) {
                        continue;
                    }
                    sum += methodNodeInfo.getSum();
                    count += methodNodeInfo.getCount();
                    methodDeeps.putAll(methodNodeInfo.getMethodDeep());
                }
            }

        }
        List<Entry<String, Integer>> list = new ArrayList<>(methodDeeps.entrySet());
        list.sort(Entry.comparingByValue());
        int middleIndex = list.size() / 2;
        Entry<String, Integer> middle = list.get(middleIndex);

        double avg = sum * 1.0 / count;
        System.out.println("方法总复杂度为:" + sum + "方法个数:" + count + " 平均复杂度为:" + avg);
        Integer middleValue = middle.getValue();
        System.out.println("方法复杂度中位数为: " + middleValue);
        System.out.println("偏态: " + (avg - middleValue) * 1.0 / middleValue);

        System.out.println("方法复杂度排名:");
        int maxSize = 25;
        List<Entry<String, Integer>> lastList = null;
        List<Entry<String, Integer>> nextList = null;
        if (list.size() > maxSize) {
            lastList = list.subList(0, maxSize);
            nextList = list.subList(list.size() - maxSize - 1, list.size() - 1);
        } else {
            lastList = list;
            nextList = list;
        }
        System.out.println("小--------------------------------------------------------------------");
        for (Entry<String, Integer> entry : lastList) {
            System.out.printf("名称: " + entry.getKey());
            System.out.println(" 复杂度: " + entry.getValue());
        }
        System.out.println("大--------------------------------------------------------------------");
        for (int i = nextList.size() - 1; i >= 0; i--) {
            Entry<String, Integer> entry = nextList.get(i);
            System.out.printf("名称: " + entry.getKey());
            System.out.println(" 复杂度: " + entry.getValue());
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

    private static MethodNodeInfo parseMethodNode(Node node, ParseMethodNodeExcludeModel excludeModel) {
        if (node instanceof MethodDeclaration) {
            MethodDeclaration methodDeclaration = (MethodDeclaration) node;
            if (!methodDeclaration.getBody().isPresent()) {
                return null;
            }
            String methodName = methodDeclaration.getName().toString();
            Node parentNode = methodDeclaration.getParentNode().get();
            String className = null;
            if (parentNode instanceof ClassOrInterfaceDeclaration) {
                ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) parentNode;
                className = classOrInterfaceDeclaration.getName().toString();
            } else if (parentNode instanceof EnumDeclaration) {
                EnumDeclaration enumDeclaration = (EnumDeclaration) parentNode;
                className = enumDeclaration.getName().toString();
            } else {
                className = "None";
            }
            String finalMethodName = methodName;
            String finalClassName = className;
            if (excludeModel.getExcludeMethodNames().stream().anyMatch(t -> t.apply(finalMethodName)) || excludeModel.getExcludeClassNames().stream().anyMatch(t -> t.apply(finalClassName))) {
                return null;
            }
            String allMethodName = className + "." + methodName;
            Integer deep = getDeep(node);

            if ((methodName.startsWith("get") || methodName.startsWith("set") || methodName.startsWith("is")) && deep < 15) {
                return null;
            }
            MethodNodeInfo build = MethodNodeInfo.build(Long.valueOf(deep), 1, new HashMap<>());
            build.getMethodDeep().put(allMethodName, deep);
            return build;
        }
        List<Node> childNodes = node.getChildNodes();
        if (CollectionUtil.isEmpty(childNodes)) {
            return null;
        }

        long sum = 0L;
        int count = 0;
        int index = 0;
        Map<String, Integer> methodDeep = new HashMap<>();
        for (Node childNode : childNodes) {
            MethodNodeInfo methodNodeInfo = parseMethodNode(childNode, excludeModel);
            if (methodNodeInfo == null) {
                continue;
            }
            sum += methodNodeInfo.getSum();
            count += methodNodeInfo.getCount();
            for (Entry<String, Integer> entry : methodNodeInfo.getMethodDeep().entrySet()) {
                if (methodDeep.containsKey(entry.getKey())) {
                    methodDeep.put(entry.getKey() + "_" + (index++), entry.getValue());
                } else {
                    methodDeep.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return MethodNodeInfo.build(sum, count, methodDeep);
    }

    private static Integer getDeep(Node node) {
        List<Node> childNodes = node.getChildNodes();
        if (CollectionUtil.isEmpty(childNodes)) {
            return 1;
        }

        int sum = 0;
        for (Node childNode : childNodes) {
            sum += getDeep(childNode);
        }

        return sum + 1;

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
