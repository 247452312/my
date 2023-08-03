package indi.uhyils.util;

import indi.uhyils.MyThreadLocal;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 临时存储modelNotes的地方
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月29日 09时19分
 */
public class ModelNoteThreadLocalContext {

    private static final MyThreadLocal<Set<String>> PARSEING_MODEL_NOTES = new MyThreadLocal<>();

    public static <T> T tryInit(Supplier<T> supplier) {
        boolean init = init();
        try {
            return supplier.get();
        } finally {
            if (init) {
                remove();
            }
        }
    }

    public static Boolean contains(String typeName) {
        Set<String> strings = PARSEING_MODEL_NOTES.get();
        if (strings == null) {
            return false;
        }
        return strings.contains(typeName);
    }

    public static void add(String typeName) {
        Set<String> strings = PARSEING_MODEL_NOTES.get();
        if (strings == null) {
            return;
        }
        strings.add(typeName);
    }

    public static void remove(String typeName) {
        Set<String> strings = PARSEING_MODEL_NOTES.get();
        if (strings == null) {
            return;
        }
        strings.remove(typeName);
    }

    private static boolean init() {
        if (PARSEING_MODEL_NOTES.get() == null) {
            PARSEING_MODEL_NOTES.set(new HashSet<>());
            return true;
        }
        return false;
    }

    private static void remove() {
        PARSEING_MODEL_NOTES.remove();
    }
}
