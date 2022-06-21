package indi.uhyils.util.formula.pojo;

import indi.uhyils.util.Asserts;
import java.util.HashMap;
import java.util.Map;

/**
 * 括号的栈帧
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月22日 15时02分
 */
public class BracketsStackItem {

    public static final Map<Character, Character> BARCKETS_CORRESPONDING_MAP;

    static {
        BARCKETS_CORRESPONDING_MAP = new HashMap<>();
        BARCKETS_CORRESPONDING_MAP.put(')', '(');
        BARCKETS_CORRESPONDING_MAP.put(']', '[');
        BARCKETS_CORRESPONDING_MAP.put('}', '{');
        BARCKETS_CORRESPONDING_MAP.put('>', '<');
    }

    /**
     * 括号的符号
     */
    private char barcket;

    /**
     * 相关的符号
     */
    private char corresponding;

    /**
     * 此符号所在的下标
     */
    private Integer index;

    public BracketsStackItem(char barcket, Integer index) {
        this.barcket = barcket;
        Asserts.assertTrue(BARCKETS_CORRESPONDING_MAP.containsKey(barcket), "公式中指定符号在配置中不存在");
        this.corresponding = BARCKETS_CORRESPONDING_MAP.get(barcket);
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    /**
     * 匹配是否是本符号的对应符号
     *
     * @param c
     *
     * @return
     */
    public Boolean matching(char c) {
        char character = BARCKETS_CORRESPONDING_MAP.get(this.barcket);
        return c == character;
    }
}
