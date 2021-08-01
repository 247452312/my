/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package indi.uhyils.util;


import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

/**
 * 存储spring上下文缓存的地方
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 16时46分
 */
public class SpringUtil implements ApplicationContextInitializer, ApplicationListener<ContextRefreshedEvent> {

    private volatile static Boolean atomicBoolean = Boolean.FALSE;

    private static ApplicationContext applicationContext = null;

    /**
     * 获取applicationContext
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        if (applicationContext != null) {
            return applicationContext;
        }
        return (ApplicationContext) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{ApplicationContext.class}, (proxy, method, args) -> null);
    }


    /**
     * 根据指定的annotation获取beans
     *
     * @param clazz
     * @param <T>
     *
     * @return
     */
    public static <T extends Annotation> Map<String, Object> getBeansWithAnnotation(Class<T> clazz) {
        return applicationContext.getBeansWithAnnotation(clazz);
    }

    /**
     * 判断spring是否初始化
     *
     * @return
     */
    public static boolean isStart() {
        if (applicationContext == null) {
            return false;
        }
        if (!atomicBoolean) {
            return false;
        }
        return true;
    }

    /**
     * 判断spring是否初始化
     *
     * @return
     */
    public static boolean isNotStart() {
        return !isStart();
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name bean名称
     *
     * @return bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);

    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz class
     * @param <T>   类型
     *
     * @return 对应类型的bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  bean名称
     * @param clazz class
     * @param <T>   类型
     *
     * @return 对应的bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 通过key 获取环境变量
     *
     * @param key 环境变量的key
     *
     * @return 环境变量的值
     */
    public static String getProperty(String key) {
        Environment environment = getApplicationContext().getEnvironment();
        if (environment != null) {
            return environment.getProperty(key);
        }
        return null;
    }

    /**
     * 通过key 获取环境变量
     *
     * @param key          环境变量的key
     * @param defaultValue 默认值
     *
     * @return 环境变量的值
     */
    public static String getProperty(String key, String defaultValue) {
        Environment environment = getApplicationContext().getEnvironment();
        if (environment != null) {
            return environment.getProperty(key, defaultValue);
        }
        return null;
    }


    /**
     * 通过key 查询是否存在bean
     *
     * @param beanName bean名称
     *
     * @return 是否存在
     */
    public static Boolean containsBean(String beanName) {
        return getApplicationContext().containsBean(beanName);
    }

    /**
     * 通过key 查询是否存在bean
     *
     * @param beanClass bean名称
     *
     * @return 是否存在
     */
    public static <T> Boolean containsBean(Class<T> beanClass) {
        try {
            getBean(beanClass);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (SpringUtil.applicationContext == null) {
            LogUtil.info(SpringUtil.class, "set applicationContext");
            SpringUtil.applicationContext = applicationContext;
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        atomicBoolean = true;
    }
}
