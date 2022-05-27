package indi.uhyils;

import com.github.javaparser.AstContext;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import indi.uhyils.internal.InternalUtil;
import indi.uhyils.util.LogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月22日 08时19分
 */
public class JavaAstUtil {


    /**
     * java文件后缀
     */
    public static final String JAVA_FILE_SUFFIX = ".java";


    /**
     * 解析器
     */
    private static final JavaParser JAVA_PARSER = new JavaParser();


    /**
     * 加载java项目为ast
     *
     * @param projectPaths
     *
     * @return
     */
    public static List<CompilationUnit> loadProjectPath(String... projectPaths) throws FileNotFoundException {
        List<CompilationUnit> result = new ArrayList<>();
        for (String projectPath : projectPaths) {
            List<File> javaFiles = parseProjectToJavaFile(new File(projectPath));
            for (File javaFile : javaFiles) {
                ParseResult<CompilationUnit> parse = JAVA_PARSER.parse(javaFile);
                Optional<CompilationUnit> optional = parse.getResult();
                if (optional.isPresent()) {
                    CompilationUnit compilationUnit = optional.get();
                    result.add(compilationUnit);
                }
            }
        }
        return result;
    }

    /**
     * 转换CompilationUnit为带有目标link的属性
     *
     * @param compilationUnits
     *
     * @return
     */
    public static void integrationCompilationUnit(List<CompilationUnit> compilationUnits) {
        // 初始化所有type
        AstContext.initTypeCache(compilationUnits);
        LogUtil.info("开始替换package和import");
        for (CompilationUnit compilationUnit : compilationUnits) {
            // 替换package
            compilationUnit.dealPackage(compilationUnits);
            // 替换import
            compilationUnit.dealImport(compilationUnits);
        }
        LogUtil.info("开始替换属性和方法出入参");
        for (CompilationUnit compilationUnit : compilationUnits) {
            // 替换继承
            compilationUnit.dealExtend();
            // 替换属性
            compilationUnit.dealFields();
            // 替换方法(入参出参)
            compilationUnit.dealMethods();
        }
        LogUtil.info("开始替换方法中的每一行");
        // 这里要等第一批所有文件执行完成才能执行这里. 否则会有找不到的问题
        for (CompilationUnit compilationUnit : compilationUnits) {
            // 替换方法中的每一行
            compilationUnit.dealMethodRow();
//            InternalUtil.dealCompilationUnitMethodRow(compilationUnit);
        }
        InternalUtil.print();
    }

    /**
     * 解析项目工程文件夹为多个java文件
     *
     * @param projectFile
     *
     * @return
     */
    private static List<File> parseProjectToJavaFile(File projectFile) {
        List<File> result = new ArrayList<>();
        if (projectFile.isFile()) {
            if (projectFile.getName().endsWith(JAVA_FILE_SUFFIX)) {
                result.add(projectFile);
            }
            return result;
        }

        File[] files = projectFile.listFiles();
        if (files == null) {
            return result;
        }
        for (File file : files) {
            List<File> listFile = parseProjectToJavaFile(file);
            result.addAll(listFile);
        }
        return result;
    }

}
