package indi.uhyils.genetic.env;

import indi.uhyils.genetic.core.EnvRule;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月22日 09时25分
 */
public class EnvRuleFactory {
    private EnvRuleFactory() {
    }

    public static EnvRule createEnvRule() {
        return new EnvRuleImpl();
    }
}
