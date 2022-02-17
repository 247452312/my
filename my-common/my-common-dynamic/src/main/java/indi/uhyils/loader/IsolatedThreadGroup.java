package indi.uhyils.loader;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月17日 21时55分
 */
public class IsolatedThreadGroup extends ThreadGroup {

    private final Object monitor = new Object();

    private Throwable exception;

    IsolatedThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!(ex instanceof ThreadDeath)) {
            synchronized (this.monitor) {
                this.exception = this.exception == null ? ex : this.exception;
            }

            ex.printStackTrace();
        }

    }

    public synchronized void rethrowUncaughtException() throws RuntimeException {
        synchronized (this.monitor) {
            if (this.exception != null) {
                throw new RuntimeException("An exception occurred while running. " + this.exception.getMessage(), this.exception);
            }
        }
    }
}
