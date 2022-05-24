package indi.uhyils;

import com.github.javaparser.ast.CompilationUnit;
import indi.uhyils.util.LogUtil;
import java.io.FileNotFoundException;
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
    void integrationComplationUnit() throws FileNotFoundException {
        LogUtil.info("解析文件开始");
        List<CompilationUnit> compilationUnits = JavaAstUtil.loadProjectPath("F:\\share\\my");
        LogUtil.info("解析文件完成,分析文件开始");
        JavaAstUtil.integrationCompilationUnit(compilationUnits);
        int i = 1;
        return;
    }
}
