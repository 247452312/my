package indi.uhyils.util;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.loadbalancer.DefaultNIWSServerListFilter;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import com.netflix.niws.loadbalancer.EurekaNotificationServerListUpdater;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.ribbon.DefaultServerIntrospector;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpRequest;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpResponse;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.cloud.netflix.ribbon.support.RibbonRequestCustomizer;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandContext;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月26日 10时07分
 */
public class SpringCloudApiUtil {

    void springCloudApiTool(){

        EurekaClientConfigBean bean = new EurekaClientConfigBean();
        Map<String, String> map = new HashMap<String, String>();
        //eureka服务注册中心地址
        map.put(EurekaClientConfigBean.DEFAULT_ZONE,"http://xx.xx.xxx.xx:1062/eureka/,http://xx.xx.xxx.xx:1063/eureka/,http://xx.xx.xxx.xx:1064/eureka/");
        bean.setServiceUrl(map);

        EurekaInstanceConfigBean instanceConfigBean = new EurekaInstanceConfigBean(new InetUtils(new InetUtilsProperties()));
        instanceConfigBean.setPreferIpAddress(true);

        ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(instanceConfigBean);
        //以上完成全局配置，基本使用默认配置

        //实例化eureka服务消费端，查询Eureka Server中的服务实例列表
        final EurekaClient client = new DiscoveryClient(applicationInfoManager, bean);
        Provider<EurekaClient> eurekaClientProvider = new Provider<EurekaClient>() {
            @Override
            public synchronized EurekaClient get() {
                return client;
            }
        };

        IClientConfig clientConfig = new DefaultClientConfigImpl();
        clientConfig.loadDefaultValues();
        //设置vipAddress，该值对应spring.application.name配置，指定某个应用
        clientConfig.set(CommonClientConfigKey.DeploymentContextBasedVipAddresses,"ZPROVIDER");

        //根据eureka client获取服务列表，client以provide形式提供
        DiscoveryEnabledNIWSServerList discoveryEnabledNIWSServerList = new DiscoveryEnabledNIWSServerList(clientConfig, eurekaClientProvider);

        //实例化负载均衡器接口ILoadBalancer，这里使用了ZoneAwareLoadBalancer，这也是spring cloud默认使用的。该实现可以避免了跨区域（Zone）访问的情况。
        //其中的参数分别为，1）某个具体应用的客户端配置，2）负载均衡的处理规则IRule对象，负载均衡器实际进行服务实例选择任务是委托给了IRule实例中的choose函数来实现,
        //这里使用了ZoneAvoidanceRule，3）实例化检查服务实例是否正常服务的IPing接口对象，负载均衡器启动一个用于定时检查Server是否健康的任务。该任务默认的执行间隔为：10秒。
        //这里没有做真实的ping操作，他只是检查DiscoveryEnabledNIWSServerList定时刷新过来的服务列表中的每个服务的状态；4）如上，ServerList接口有两个方法，分别为
        //获取初始化的服务实例清单和获取更新的服务实例清单；5）ServerListFilter接口实现，用于对服务实例列表的过滤，根据规则返回过滤后的服务列表；6）ServerListUpdater服务更新器接口
        //实现动态获取更新服务列表，默认30秒执行一次
        ILoadBalancer loadBalancer = new ZoneAwareLoadBalancer(clientConfig, new ZoneAvoidanceRule(), new NIWSDiscoveryPing(),
                discoveryEnabledNIWSServerList,new DefaultNIWSServerListFilter(), new EurekaNotificationServerListUpdater(eurekaClientProvider));

        //实例化request client,他对由负载均衡器选择的Server进行请求，Spring cloud封装了apache HttpClient和OkHttp两种实现
        RibbonLoadBalancingHttpClient ribbonLoadBalancingHttpClient = new RibbonLoadBalancingHttpClient(clientConfig, new DefaultServerIntrospector());
        ribbonLoadBalancingHttpClient.setLoadBalancer(loadBalancer);

//        OkHttpLoadBalancingClient okHttpLoadBalancingClient = new OkHttpLoadBalancingClient(clientConfig, new DefaultServerIntrospector());
//        okHttpLoadBalancingClient.setLoadBalancer(loadBalancer);

        //实例化某个具体request的上下文，如果对应到开放平台上，这些信息就是开放某个具体接口时，录入的API信息
        RibbonCommandContext context = new RibbonCommandContext("ZPROVIDER","get", "/test/weber", Boolean.FALSE, new HttpHeaders(),
                new LinkedMultiValueMap(), null, new ArrayList<RibbonRequestCustomizer>(), null);

        RibbonCommandContext context1 = new RibbonCommandContext("ZPROVIDER","get", "/sum?v=2&vv=3", Boolean.FALSE, new HttpHeaders(),
                new LinkedMultiValueMap(), null, new ArrayList<RibbonRequestCustomizer>(), null);

        BufferedReader br = null;
        String result = "";
        try {
            //实例化request，对Service请求调用
            RibbonApacheHttpResponse response = ribbonLoadBalancingHttpClient.executeWithLoadBalancer(new RibbonApacheHttpRequest(context1));

//            OkHttpRibbonResponse response = okHttpLoadBalancingClient.executeWithLoadBalancer(new OkHttpRibbonRequest(context1));

            //如果服务接口输出json或xml，可以拿到显示
            br = new BufferedReader(new InputStreamReader(response.getInputStream()));
            String line = null;
            while((line = br.readLine())!=null){
                result+=line;
            }
            System.out.println("Result: "+result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null!=br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
