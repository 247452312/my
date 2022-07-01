package indi.uhyils.context;

import indi.uhyils.MyThreadLocal;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月28日 14时19分
 */
public class TempContext {

    public static final MyThreadLocal<String> temp = new MyThreadLocal<>();


    public static final ThreadLocal<String> temp2 = new InheritableThreadLocal<>();

}
