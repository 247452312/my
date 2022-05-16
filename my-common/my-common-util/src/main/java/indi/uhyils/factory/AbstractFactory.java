package indi.uhyils.factory;

import com.github.javaparser.ast.CompilationUnitWithLink;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.ImportDeclarationWithLink;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.PackageDeclarationWithLink;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月27日 09时41分
 */
public abstract class AbstractFactory implements AstFactory {

    /**
     * 获取多个名称对应的类
     *
     * @param imports
     * @param packageDeclaration
     * @param typeSimpleNames
     *
     * @return
     */
    protected List<CompilationUnitWithLink> findCompilationUnitWithLinks(NodeList<ImportDeclaration> imports, PackageDeclaration packageDeclaration, List<String> typeSimpleNames) {
        List<CompilationUnitWithLink> links = new ArrayList<>();
        for (String typeSimpleName : typeSimpleNames) {
            CompilationUnitWithLink targetCompilationUnit = null;
            // 1.从import中寻找
            for (ImportDeclaration importItem : imports) {
                ImportDeclarationWithLink importWithLinkItem = (ImportDeclarationWithLink) importItem;
                String identifier = importWithLinkItem.getName().getIdentifier();
                if (Objects.equals(identifier, typeSimpleName)) {
                    targetCompilationUnit = importWithLinkItem.getTargetCompilationUnit();
                    break;
                }
            }
            // 2.从同package中寻找
            if (targetCompilationUnit == null) {
                if (packageDeclaration != null) {
                    PackageDeclarationWithLink packageDeclarationWithLink = (PackageDeclarationWithLink) packageDeclaration;
                    List<CompilationUnitWithLink> otherCompilationUnits = packageDeclarationWithLink.getOtherCompilationUnits();
                    targetCompilationUnit = otherCompilationUnits.stream()
                                                                 .filter(
                                                                     t -> t.getTypes()
                                                                           .stream()
                                                                           // 同package中 sampleName相同即可 不需要package前缀
                                                                           .anyMatch(s -> Objects.equals(s.getName().asString(), typeSimpleName))
                                                                 )
                                                                 .findFirst()
                                                                 .orElse(null);

                }
            }
            if (targetCompilationUnit == null) {
                targetCompilationUnit = new DeclarationFactory().createNotScannedCompilationUnitWithLink("java.util." + typeSimpleName);
            }
            links.add(targetCompilationUnit);
        }
        return links;
    }


    /**
     * 根据imports 和 packageDeclaration 获取对应的类
     *
     * @param imports
     * @param packageDeclaration
     * @param typeSimpleName
     *
     * @return
     */
    protected CompilationUnitWithLink findCompilationUnitWithLink(NodeList<ImportDeclaration> imports, PackageDeclaration packageDeclaration, String typeSimpleName) {
        List<CompilationUnitWithLink> compilationUnitWithLinks = findCompilationUnitWithLinks(imports, packageDeclaration, Collections.singletonList(typeSimpleName));
        if (CollectionUtil.isNotEmpty(compilationUnitWithLinks)) {
            return compilationUnitWithLinks.get(0);
        }
        return null;
    }

}
