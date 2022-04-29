package indi.uhyils.internal;

import com.github.javaparser.ast.CompilationUnitWithLink;
import com.github.javaparser.ast.FieldDeclarationWithLink;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.ImportDeclarationWithLink;
import com.github.javaparser.ast.MethodDeclarationWithLink;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.PackageDeclarationWithLink;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import indi.uhyils.factory.DeclarationFactory;
import indi.uhyils.util.LogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 内部的工具类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月25日 08时38分
 */
public class InternalUtil {


    /**
     * 处理类的package类
     *
     * @param compilationUnit
     * @param compilationUnits
     */
    public static void dealCompilationUnitPackage(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> compilationUnits) {
        Optional<PackageDeclaration> packageDeclarationOptional = compilationUnit.getPackageDeclaration();
        if (!packageDeclarationOptional.isPresent()) {
            return;
        }

        PackageDeclaration packageDeclaration = packageDeclarationOptional.get();
        PackageDeclarationWithLink packageDeclarationWithLink = new PackageDeclarationWithLink(packageDeclaration);
        List<CompilationUnitWithLink> collect = compilationUnits.stream().filter(t -> t.getPackageDeclaration().isPresent()).filter(t -> Objects.equals(t.getPackageDeclaration().get().getName().asString(), packageDeclarationWithLink.getName().asString())).collect(Collectors.toList());
        packageDeclarationWithLink.setOtherCompilationUnits(collect);
        compilationUnit.setPackageDeclaration(packageDeclarationWithLink);
    }

    /**
     * 处理类的import
     *
     * @param compilationUnit
     * @param result
     */
    public static void dealCompilationUnitImport(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> result) {
        NodeList<ImportDeclaration> imports = compilationUnit.getImports();
        NodeList<ImportDeclaration> targetList = new NodeList<>();
        for (ImportDeclaration importItem : imports) {
            ImportDeclarationWithLink importDeclarationWithLink = new ImportDeclarationWithLink(importItem);
            String importClassName = importDeclarationWithLink.getName().asString();
            CompilationUnitWithLink compilationUnitWithLink = result.stream()
                                                                    .filter(
                                                                        t -> {
                                                                            PackageDeclaration packageDeclaration = t.getPackageDeclaration().orElse(null);
                                                                            if (packageDeclaration != null) {
                                                                                String packageName = packageDeclaration.getName().asString();
                                                                                return t.getTypes()
                                                                                        .stream()
                                                                                        .anyMatch(
                                                                                            item -> Objects.equals(packageName + "." + item.getName().asString(), importClassName)
                                                                                        );
                                                                            } else {
                                                                                return t.getTypes()
                                                                                        .stream()
                                                                                        .anyMatch(
                                                                                            item -> Objects.equals(item.getName().asString(), importClassName)
                                                                                        );
                                                                            }

                                                                        }
                                                                    )
                                                                    .findFirst()
                                                                    .orElse(null);
            importDeclarationWithLink.setTargetCompilationUnit(compilationUnitWithLink);
            targetList.add(importDeclarationWithLink);
        }
        compilationUnit.setImports(targetList);
    }

    /**
     * 替换 method
     *
     * @param compilationUnit
     * @param result
     */
    public static void dealCompilationUnitMethods(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> result) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            NodeList<BodyDeclaration<?>> targetList = new NodeList<>();
            for (BodyDeclaration<?> member : members) {
                if (!(member instanceof MethodDeclaration)) {
                    targetList.add(member);
                    continue;
                }
                MethodDeclarationWithLink method = new DeclarationFactory().createMethod((MethodDeclaration) member, compilationUnit);
                targetList.add(method);

            }
            type.setMembers(targetList);
        }
    }


    /**
     * 替换 属性
     *
     * @param compilationUnit
     * @param result
     */
    public static void dealCompilationUnitFields(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> result) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            NodeList<BodyDeclaration<?>> targetMembers = new NodeList<>();

            for (BodyDeclaration<?> member : members) {
                if (!(member instanceof FieldDeclaration)) {
                    targetMembers.add(member);
                    continue;
                }
                // 处理属性的每一个参数
                FieldDeclarationWithLink fieldDeclarationWithLink = new DeclarationFactory().createField((FieldDeclaration) member, compilationUnit);
                targetMembers.add(fieldDeclarationWithLink);
            }
            type.setMembers(targetMembers);
        }
    }


    /**
     * 替换方法中的每一行
     *
     * @param compilationUnit
     * @param result
     */
    public static void dealCompilationUnitMethodRow(CompilationUnitWithLink compilationUnit, List<CompilationUnitWithLink> result) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {

            List<MethodDeclaration> methods = type.getMethods();
            NodeList<ImportDeclaration> imports = compilationUnit.getImports();
            PackageDeclaration packageDeclaration = compilationUnit.getPackageDeclaration().orElse(null);

            for (MethodDeclaration method : methods) {
                dealMethodCallExprWithLink(method, imports, (PackageDeclarationWithLink) packageDeclaration);
            }
        }
    }

    /**
     * 获取一个节点下所有的方法调用
     *
     * @param method
     * @param imports
     * @param packageDeclaration
     *
     * @return
     */
    private static void dealMethodCallExprWithLink(MethodDeclaration method, NodeList<ImportDeclaration> imports, PackageDeclarationWithLink packageDeclaration) {
        Optional<BlockStmt> body = method.getBody();
        if (!body.isPresent()) {
            return;
        }
        BlockStmt blockStmt = body.get();
        NodeList<Statement> statements = blockStmt.getStatements();
        // <局部变量名称,局部变量>
        Map<String, CompilationUnitWithLink> vars = new HashMap<>();
        for (Statement statement : statements) {
            if (statement.isExpressionStmt()) {
                ExpressionStmt expressionStmt = statement.asExpressionStmt();
            } else if (statement.isReturnStmt()) {
                ReturnStmt returnStmt = statement.asReturnStmt();
            } else if (statement.isIfStmt()) {
                IfStmt ifStmt = statement.asIfStmt();
            } else {
                LogUtil.error("不存在的类型:" + statement.getClass().getName());
            }
        }
    }
}
