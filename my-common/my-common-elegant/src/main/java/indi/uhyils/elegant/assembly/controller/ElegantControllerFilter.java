package indi.uhyils.elegant.assembly.controller;

import indi.uhyils.elegant.AbstractElegantHandler;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月03日 08时22分
 */
public class ElegantControllerFilter extends AbstractElegantHandler implements Filter {

    /**
     * 是否在线
     */
    private final AtomicBoolean online = new AtomicBoolean(Boolean.TRUE);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (Boolean.FALSE.equals(online.get())) {
            return; // todo 这里是否可以报错来代替return
        }
        if (Boolean.FALSE.equals(canPublish())) {
            return;
        }
        newRequest();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            requestOver();
        }
    }

    @Override
    protected void doShutdown() {
        online.set(Boolean.FALSE);
    }

    @Override
    public Boolean isOnline() {
        return online.get();
    }

    @Override
    public void close() {
        doShutdown();
    }

    @Override
    public String name() {
        return "controller";
    }
}
