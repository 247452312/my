package indi.uhyils.factory;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.CompilationUnitWithLink;
import com.github.javaparser.ast.FieldDeclarationWithLink;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.MethodDeclarationWithLink;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.VariableDeclaratorWithLink;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.util.StringUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月27日 09时30分
 */
public class DeclarationFactory extends AbstractFactory {

    /**
     * 创建属性
     *
     * @param member
     * @param compilationUnit
     *
     * @return
     */
    public FieldDeclarationWithLink createField(FieldDeclaration member, CompilationUnitWithLink compilationUnit) {
        FieldDeclarationWithLink fieldDeclarationWithLink = new FieldDeclarationWithLink(member);
        NodeList<VariableDeclarator> variables = fieldDeclarationWithLink.getVariables();
        NodeList<VariableDeclarator> variableDeclaratorWithLinks = new NodeList<>();
        for (VariableDeclarator variable : variables) {
            VariableDeclaratorWithLink node = createVariable(compilationUnit, variable);
            variableDeclaratorWithLinks.add(node);
        }
        fieldDeclarationWithLink.setVariables(variableDeclaratorWithLinks);
        return fieldDeclarationWithLink;
    }

    /**
     * 创建方法
     *
     * @param methodTarget
     * @param compilationUnit
     *
     * @return
     */
    public MethodDeclarationWithLink createMethod(MethodDeclaration methodTarget, CompilationUnitWithLink compilationUnit) {
        MethodDeclarationWithLink methodDeclarationWithLink = new MethodDeclarationWithLink(methodTarget);
        // 替换方法返回类型
        NodeList<ImportDeclaration> imports = compilationUnit.getImports();
        PackageDeclaration packageDeclaration = compilationUnit.getPackageDeclaration().orElse(null);
        dealCompilationUnitMethodType(methodDeclarationWithLink, imports, packageDeclaration);

        // 替换方法入参类型
        dealCompilationUnitMethodParams(methodDeclarationWithLink, imports, packageDeclaration);
        return methodDeclarationWithLink;
    }

    /**
     * 创建一个未被扫描到的类
     *
     * @param name 类全名
     *
     * @return
     */
    public CompilationUnitWithLink createNotScannedCompilationUnitWithLink(String name) {
        CompilationUnitWithLink compilationUnitWithLink = new CompilationUnitWithLink();

        CompilationUnit target = new CompilationUnit();
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration();

        SimpleName simpleName = new SimpleName(name);
        simpleName.setIdentifier(StringUtil.transClassNameToSimpleName(name));

        type.setName(simpleName);
        NodeList<TypeDeclaration<?>> types = new NodeList<>();
        types.add(type);
        target.setTypes(types);
        compilationUnitWithLink.setTarget(target);
        return compilationUnitWithLink;
    }

    /**
     * 创建变量
     *
     * @param compilationUnit
     * @param variable
     *
     * @return
     */
    @NotNull
    public VariableDeclaratorWithLink createVariable(CompilationUnitWithLink compilationUnit, VariableDeclarator variable) {
        VariableDeclaratorWithLink node = new VariableDeclaratorWithLink(variable);
        // 寻找node 对应的类型
        CompilationUnitWithLink compilationUnitWithLink = findCompilationUnitWithLink(compilationUnit.getImports(), compilationUnit.getPackageDeclaration().orElse(null), node.getTypeAsString());
        node.setTargetType(compilationUnitWithLink);
        return node;
    }

    /**
     * 替换方法入参类型
     *
     * @param methodDeclarationWithLink
     * @param imports
     * @param packageDeclaration
     */
    private void dealCompilationUnitMethodParams(MethodDeclarationWithLink methodDeclarationWithLink, NodeList<ImportDeclaration> imports, PackageDeclaration packageDeclaration) {
        NodeList<Parameter> parameters = methodDeclarationWithLink.getParameters();
        List<String> typeSimpleNames = parameters.stream().map(NodeWithType::getTypeAsString).collect(Collectors.toList());
        List<CompilationUnitWithLink> targetCompilationUnits = findCompilationUnitWithLinks(imports, packageDeclaration, typeSimpleNames);
        methodDeclarationWithLink.setParamTypes(targetCompilationUnits);
    }

    /**
     * 替换方法返回类型
     *
     * @param methodDeclarationWithLink 方法本身
     * @param imports                   import
     * @param packageDeclaration        所在包
     */
    private void dealCompilationUnitMethodType(MethodDeclarationWithLink methodDeclarationWithLink, NodeList<ImportDeclaration> imports, PackageDeclaration packageDeclaration) {
        String typeSimpleName = methodDeclarationWithLink.getTypeAsString();
        CompilationUnitWithLink targetCompilationUnit = findCompilationUnitWithLink(imports, packageDeclaration, typeSimpleName);
        methodDeclarationWithLink.setReturnType(targetCompilationUnit);
    }

}
