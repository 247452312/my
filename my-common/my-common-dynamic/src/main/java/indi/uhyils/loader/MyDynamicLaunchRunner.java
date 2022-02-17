package indi.uhyils.loader;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月17日 21时45分
 */
public class MyDynamicLaunchRunner implements Runnable {

    private final String mainClassName;

    private final String[] args;

    public MyDynamicLaunchRunner(String mainClassName, String... args) {
        this.mainClassName = mainClassName;
        this.args = args != null ? args : new String[0];
    }


    @Override
    public void run() {

    }
}
