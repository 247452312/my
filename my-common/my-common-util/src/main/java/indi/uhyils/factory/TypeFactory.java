package indi.uhyils.factory;

import com.github.javaparser.ast.ClassOrInterfaceTypeWithLink;
import com.github.javaparser.ast.CompilationUnitWithLink;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月29日 15时21分
 */
public class TypeFactory extends AbstractFactory {

    /**
     * 创建一个包含目标的类型 (定义局部变量时的类型)
     *
     * @param type
     * @param compilationUnit
     *
     * @return
     */
    public ClassOrInterfaceTypeWithLink createType(ClassOrInterfaceType type, CompilationUnitWithLink compilationUnit) {
        ClassOrInterfaceTypeWithLink classOrInterfaceTypeWithLink = new ClassOrInterfaceTypeWithLink(type);
        CompilationUnitWithLink compilationUnitWithLink = findCompilationUnitWithLink(compilationUnit.getImports(), compilationUnit.getPackageDeclaration().orElse(null), type.getName().asString());
        classOrInterfaceTypeWithLink.setTypeTarget(compilationUnitWithLink);
        return classOrInterfaceTypeWithLink;
    }


    /**
     * 创建一个数组类型
     *
     * @param type
     * @param compilationUnit
     *
     * @return
     */
    public ClassOrInterfaceTypeWithLink createArrayType(ClassOrInterfaceType type, CompilationUnitWithLink compilationUnit) {
        ClassOrInterfaceTypeWithLink classOrInterfaceTypeWithLink = createType(type, compilationUnit);
        classOrInterfaceTypeWithLink.setArrayFlag(Boolean.TRUE);
        return classOrInterfaceTypeWithLink;
    }

    /**
     * 创建一个未扫描到的类型
     *
     * @return
     */
    public ClassOrInterfaceTypeWithLink createNotScannedType(String name) {
        ClassOrInterfaceType target = new ClassOrInterfaceType();
        target.setName(name);
        return new ClassOrInterfaceTypeWithLink(target);
    }

    /**
     * 创建一个未扫描到的类型
     *
     * @return
     */
    public ClassOrInterfaceTypeWithLink createPrimitiveType(String name) {
        ClassOrInterfaceType target = new ClassOrInterfaceType();
        target.setName(name);
        ClassOrInterfaceTypeWithLink classOrInterfaceTypeWithLink = new ClassOrInterfaceTypeWithLink(target);
        classOrInterfaceTypeWithLink.setPrimitiveFlag(Boolean.TRUE);
        return classOrInterfaceTypeWithLink;
    }

}
