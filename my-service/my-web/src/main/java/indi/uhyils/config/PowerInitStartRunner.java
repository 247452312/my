package indi.uhyils.config;

import indi.uhyils.util.PowerAddUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 09时40分
 */
@Component
public class PowerInitStartRunner {
    public void run() throws IOException {
        PowerAddUtils.initPower();
    }
}
