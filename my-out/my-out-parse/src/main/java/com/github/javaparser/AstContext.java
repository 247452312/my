package com.github.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年05月23日 16时27分
 */
public class AstContext {

    /**
     * type map映射
     */
    private static final Map<String, TypeDeclaration<?>> allTypeMap = new HashMap<>();


    /**
     * 添加缓存
     *
     * @param types
     */
    public static void addCache(TypeDeclaration<?>... types) {
        for (TypeDeclaration<?> type : types) {
            // 解析前缀
            StringBuilder classPrefixName = parseClassPrefixName(type);
            classPrefixName.append(type.getNameAsString());
            allTypeMap.put(classPrefixName.toString(), type);
        }
    }

    /**
     * 初始化缓存
     *
     * @param compilationUnits
     */
    public static Map<String, TypeDeclaration<?>> initTypeCache(List<CompilationUnit> compilationUnits) {
        List<TypeDeclaration<?>> result = new ArrayList<>();
        List<TypeDeclaration<?>> types = compilationUnits.stream().flatMap(t -> t.getTypes().stream()).collect(Collectors.toList());
        while (CollectionUtil.isNotEmpty(types)) {
            result.addAll(types);
            // 获取内部类
            types = types.stream().flatMap(AstContext::streamTypesInnerClass).collect(Collectors.toList());
        }
        result = result.stream().distinct().collect(Collectors.toList());

        for (TypeDeclaration<?> typeDeclaration : result) {
            // 解析前缀
            StringBuilder classPrefixName = parseClassPrefixName(typeDeclaration);
            classPrefixName.append(typeDeclaration.getNameAsString());
            allTypeMap.put(classPrefixName.toString(), typeDeclaration);
        }
        return allTypeMap;
    }

    /**
     * 获取所有的内部类
     *
     * @param type
     *
     * @return
     */
    @NotNull
    private static Stream<TypeDeclaration<?>> streamTypesInnerClass(TypeDeclaration<?> type) {
        return type.getMembers().stream().filter(t -> (t.isClassOrInterfaceDeclaration() || t.isEnumDeclaration())).map(t -> (TypeDeclaration<?>) t);
    }

    /**
     * 获取扫描所有类的名称映射<类全名,typeDeclaration>
     *
     * @return
     */
    public static Map<String, TypeDeclaration<?>> getAllCompilationUnitMap() {
        return allTypeMap;
    }

    /**
     * 解析类前缀名称
     *
     * @param typeDeclaration
     *
     * @return
     */
    private static StringBuilder parseClassPrefixName(TypeDeclaration<?> typeDeclaration) {
        Optional<Node> parent = typeDeclaration.getParentNode();
        if (parent.isPresent()) {
            Node node = parent.get();
            if (node instanceof TypeDeclaration) {
                TypeDeclaration<?> classOrInterfaceDeclaration = (TypeDeclaration<?>) node;
                StringBuilder stringBuilder = parseClassPrefixName(classOrInterfaceDeclaration);
                stringBuilder.append(classOrInterfaceDeclaration.getNameAsString()).append(".");
                return stringBuilder;
            } else if (node instanceof CompilationUnit) {
                StringBuilder sb = new StringBuilder();
                CompilationUnit parentCompilation = (CompilationUnit) node;
                Optional<PackageDeclaration> packageDeclaration = parentCompilation.getPackageDeclaration();
                packageDeclaration.ifPresent(t -> sb.append(t.getNameAsString()).append("."));
                return sb;
            }


        }
        return new StringBuilder();
    }
}
