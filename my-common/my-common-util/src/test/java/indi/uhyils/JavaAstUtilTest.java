package indi.uhyils;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.CompilationUnitWithLink;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月26日 08时48分
 */
class JavaAstUtilTest {

    @org.junit.jupiter.api.Test
    void loadProjectPath() throws FileNotFoundException {
        List<CompilationUnit> compilationUnits = JavaAstUtil.loadProjectPath("F:\\share\\my\\my-common\\my-common-util\\src");
        int i = 1;
    }

    @org.junit.jupiter.api.Test
    void integrationCompilationUnit() throws FileNotFoundException {
        List<CompilationUnit> compilationUnits = JavaAstUtil.loadProjectPath("F:\\share\\my\\my-common\\my-common-util\\src");

        List<CompilationUnitWithLink> compilationUnitWithLinks = JavaAstUtil.integrationCompilationUnit(compilationUnits);
        int i = 1;
    }

    @org.junit.jupiter.api.Test
    void temp() throws FileNotFoundException {
        System.out.println(String.format("%04d", Long.MAX_VALUE));
    }
}
