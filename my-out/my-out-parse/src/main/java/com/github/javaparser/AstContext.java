package com.github.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年05月23日 16时27分
 */
public class AstContext {

    /**
     * 所有的type缓存, 包括未找到 new出来的
     */
    public static final ThreadLocal<List<TypeDeclaration<?>>> allTypeCache = new ThreadLocal<>();


    /**
     * 初始化缓存
     *
     * @param compilationUnits
     */
    public static void initTypeCache(List<CompilationUnit> compilationUnits) {
        List<TypeDeclaration<?>> result = new ArrayList<>();
        List<TypeDeclaration<?>> types = compilationUnits.stream().flatMap(t -> t.getTypes().stream()).collect(Collectors.toList());
        while (CollectionUtil.isNotEmpty(types)) {
            result.addAll(types);
            // 获取内部类
            types = types.stream().flatMap(AstContext::streamTypesInnerClass).collect(Collectors.toList());
        }
        result = result.stream().distinct().collect(Collectors.toList());
        allTypeCache.set(result);

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
}
