package indi.uhyils.config;

import indi.uhyils.aop.DynamicAop;
import indi.uhyils.facade.impl.DynamicCodeFacadeImpl;
import indi.uhyils.protocol.mq.runner.DynamicRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * 动态代码配置类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月11日 08时55分
 */
@Configuration
@Import(value = {
    DynamicAop.class,
    DynamicCodeFacadeImpl.class,
    DynamicRunner.class
})
public class DynamicCodeConfiguration {

}
