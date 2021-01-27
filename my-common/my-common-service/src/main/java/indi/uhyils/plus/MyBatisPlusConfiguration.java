package indi.uhyils.plus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import indi.uhyils.rpc.util.PackageUtils;
import indi.uhyils.util.LogUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月27日 18时38分
 */
@Configuration
public class MyBatisPlusConfiguration {


    private static final String ENTITY = "_entity";

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        sb.deleteCharAt(0);
        return sb.toString();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() throws IOException, ClassNotFoundException {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        Set<Class<?>> classByPackageName = PackageUtils.getClassByPackageName("indi.uhyils.pojo.model", null, true);


        Map<String, TableNameHandler> map = new HashMap<>(classByPackageName.size());

        for (Class<?> clazz : classByPackageName) {
            String simpleName = clazz.getSimpleName();
            final String tableName = humpToLine(simpleName);

            map.put(tableName, (sql, t) -> {
                String convertTableName = tableName;
                if (convertTableName.endsWith(ENTITY)) {
                    convertTableName = "sys_" + convertTableName.substring(0, convertTableName.length() - ENTITY.length());
                }
                return convertTableName;
            });
        }

        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(map);
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return interceptor;
    }
}
