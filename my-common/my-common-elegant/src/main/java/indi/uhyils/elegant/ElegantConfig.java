package indi.uhyils.elegant;

import indi.uhyils.elegant.assembly.controller.ElegantControllerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月02日 18时44分
 */
@Configuration
@Import({
            ElegantCoreProcessor.class
        })
public class ElegantConfig {


    @Bean
    public ElegantControllerFilter elegantControllerFilter() {
        return new ElegantControllerFilter();
    }

    @Bean
    public FilterRegistrationBean<ElegantControllerFilter> elegantControllerFilterFilterRegistrationBean(ElegantControllerFilter filter) {
        FilterRegistrationBean<ElegantControllerFilter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(filter);
        filterBean.setName("controllerElegantFilter");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }


}
