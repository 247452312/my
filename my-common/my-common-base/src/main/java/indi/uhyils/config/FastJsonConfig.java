package indi.uhyils.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月31日 09时35分
 */
@Configuration
public class FastJsonConfig {

    static {
        ParserConfig.getGlobalInstance().addAccept("indi.uhyils");
    }

}
