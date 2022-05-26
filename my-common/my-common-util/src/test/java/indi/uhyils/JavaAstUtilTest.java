package indi.uhyils;

import com.github.javaparser.AstContext;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月25日 08时50分
 */
class JavaAstUtilTest {

    @Test
    void loadProjectPath() throws FileNotFoundException {
        List<CompilationUnit> compilationUnits = JavaAstUtil.loadProjectPath("D:\\share\\ideaSrc\\parseJavaAst");
        return;

    }

    @Test
    void integrationCompilationUnit() throws FileNotFoundException {
        LogUtil.info("解析文件开始");
        List<CompilationUnit> compilationUnits = JavaAstUtil.loadProjectPath("F:\\share\\my");
        LogUtil.info("解析文件完成,分析文件开始");
        JavaAstUtil.integrationCompilationUnit(compilationUnits);

        TypeDeclaration<?> typeDeclaration = AstContext.getAllCompilationUnitMap().get("com.github.javaparser.ast.Node");
        Asserts.assertTrue(typeDeclaration != null);

        System.out.println("graph TD;");
        printAllChild(typeDeclaration);
    }

    private void printAllChild(TypeDeclaration<?> typeDeclaration) {
        List<String> str = getAllChildList(typeDeclaration);
        str.stream().distinct().forEach(System.out::println);

    }

    private List<String> getAllChildList(TypeDeclaration<?> typeDeclaration) {
        List<String> result = new ArrayList<>();
        List<TypeDeclaration<?>> childType = typeDeclaration.getChildType();
        String nameAsString = typeDeclaration.getNameAsString();

        for (TypeDeclaration<?> declaration : childType) {
            result.add("  " + nameAsString + "-->" + declaration.getNameAsString() + ";");
            result.addAll(getAllChildList(declaration));
        }
        return result;
    }
}
