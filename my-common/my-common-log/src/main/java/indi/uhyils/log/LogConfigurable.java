package indi.uhyils.log;

import indi.uhyils.log.filter.controller.ControllerLogFilter;
import indi.uhyils.log.filter.db.DbLogFilter;
import indi.uhyils.log.filter.task.TaskLogAop;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月25日 14时24分
 */
@Configuration
@Import({DbLogFilter.class, TaskLogAop.class})
public class LogConfigurable {

    @Bean
    public FilterRegistrationBean<ControllerLogFilter> setFilter() {

        FilterRegistrationBean<ControllerLogFilter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new ControllerLogFilter());
        filterBean.setName("controllerFilter");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }
}
