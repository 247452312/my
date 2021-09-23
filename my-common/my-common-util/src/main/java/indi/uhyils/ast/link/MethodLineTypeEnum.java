package indi.uhyils.ast.link;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月21日 09时17分
 */
public enum MethodLineTypeEnum {
    /**
     * 正常(赋值,调用方法)
     */
    NORMAL,
    /**
     * try
     */
    TRY,
    /**
     * throw
     */
    THROW,
    /**
     * if语句
     */
    IF,
    /**
     * switch语句
     */
    SWITCH,

    /**
     * while
     */
    WHILE,
    /**
     * synchronized
     */
    SYNC,
    /**
     * 开始节点
     */
    START,
    /**
     * 结束
     */
    END,

    /**
     * for
     */
    FOR;
}
