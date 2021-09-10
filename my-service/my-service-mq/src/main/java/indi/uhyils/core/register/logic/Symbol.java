package indi.uhyils.core.register.logic;

import indi.uhyils.exception.ExpressionInvalidException;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月18日 15时58分
 */
public enum Symbol {
    /**
     * 小于等于
     */
    LESS_OR_EQUAL("<="),
    /**
     * 小于
     */
    LESS("<"),
    /**
     * 大于等于
     */
    GREATER_OR_EQUAL(">="),
    /**
     * 大于
     */
    GREATER(">"),
    /**
     * 不等于
     */
    NOT_EQUAL("!="),
    /**
     * 等于
     */
    EQUAL("="),
    /**
     * 长于
     */
    LONG_TO("longTO"),
    /**
     * 短于
     */
    SHORT_TO("shortTo"),
    /**
     * 不包含
     */
    NOT_CONTAIN("notContain"),
    /**
     * 不被包含
     */
    NOT_BE_CONTAINED("notBeContained"),
    /**
     * 包含
     */
    CONTAIN("contain"),
    /**
     * 被包含
     */
    BE_CONTAINED("beContained");


    private final String sym;

    Symbol(String sym) {
        this.sym = sym;
    }

    public String getSym() {
        return sym;
    }


    public Symbol trans() {
        switch (this) {
            case LESS:
                return GREATER;
            case EQUAL:
                return EQUAL;
            case GREATER:
                return LESS;
            case LONG_TO:
                return SHORT_TO;
            case NOT_EQUAL:
                return NOT_EQUAL;
            case LESS_OR_EQUAL:
                return GREATER_OR_EQUAL;
            case GREATER_OR_EQUAL:
                return LESS_OR_EQUAL;
            case SHORT_TO:
                return LONG_TO;
            case NOT_CONTAIN:
                return NOT_BE_CONTAINED;
            case NOT_BE_CONTAINED:
                return NOT_CONTAIN;
            case CONTAIN:
                return BE_CONTAINED;
            case BE_CONTAINED:
                return CONTAIN;
            default:
                return null;
        }
    }

    /**
     * 用本符号对比key值和目标值,key值在前,target在后
     *
     * @param key    值
     * @param target 目标值
     *
     * @return
     */
    public Boolean compare(Object key, String target) throws ExpressionInvalidException {
        target = target.trim();
        //如果key是一个数字 target不是一个数字
        if (key instanceof Number && !NumberUtils.isCreatable(target)) {
            throw new ExpressionInvalidException(key, this.sym, target);
        } else if (key instanceof String) {
            String tempKey = (String) key;
            //如果key是一个字符串
            switch (this) {
                case EQUAL:
                    return tempKey.equals(target);
                case NOT_EQUAL:
                    return !tempKey.equals(target);
                case SHORT_TO:
                    return tempKey.length() < target.length();
                case LONG_TO:
                    return tempKey.length() > target.length();
                case NOT_BE_CONTAINED:
                    return !target.contains(tempKey);
                case BE_CONTAINED:
                    return target.contains(tempKey);
                case CONTAIN:
                    return tempKey.contains(target);
                case NOT_CONTAIN:
                    return !tempKey.contains(target);
                default:
                    throw new ExpressionInvalidException(key, this.sym, target);
            }
        }
        double keyNum = Double.parseDouble(key.toString());
        double targetNum = Double.parseDouble(target);

        switch (this) {
            case GREATER_OR_EQUAL:
                return keyNum >= targetNum;
            case LESS_OR_EQUAL:
                return keyNum <= targetNum;
            case NOT_EQUAL:
                return keyNum != targetNum;
            case GREATER:
                return keyNum > targetNum;
            case EQUAL:
                return keyNum == targetNum;
            case LESS:
                return keyNum < targetNum;
            default:
                throw new ExpressionInvalidException(key, this.sym, target);
        }
    }
}
