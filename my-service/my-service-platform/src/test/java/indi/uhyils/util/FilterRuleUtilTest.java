package indi.uhyils.util;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月27日 17时23分
 */
public class FilterRuleUtilTest {

    @Test
    public void matchRules() {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "abc");
        param.put("age", 10);
        param.put("ssf", "15");
        param.put("bbc", "16");
        param.put("strTest", "12");
        param.put("gtTest", "34");
        param.put("geTest", "34");
        param.put("ltTest", "-8");
        param.put("leTest", "999");
        param.put("likeTest", "3455k");
        param.put("inTest", "addd");
        param.put("likeStrTest", "abcxaaaa");
        param.put("neTest", "45");
        String sy = "((${name} == abc)&&${age} > 10)||${ssf}+${bbc} > 30&&${strTest} != 13&&${neTest} != 44&&${gtTest} > 33&&${geTest} >= 34&&${ltTest} < 1&&${leTest} <= 999&&${likeTest} like '34%'&&${inTest} in (\"addd\",\"bbgv\",43.9)&&${likeStrTest} like 'abcx%'&&true&&3+4";
        Boolean aBoolean = FilterRuleUtil.matchRules(sy, param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));
        aBoolean = FilterRuleUtil.matchRules(sy, param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));
        aBoolean = FilterRuleUtil.matchRules("${name} == abc", param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));
        aBoolean = FilterRuleUtil.matchRules("${inTest} in (\"addd\",\"bbgv\")", param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));
        param = new HashMap<>();
        param.put("testBoolean", true);
        aBoolean = FilterRuleUtil.matchRules("${testBoolean}", param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));
        param.put("testBoolean", false);
        aBoolean = FilterRuleUtil.matchRules("${testBoolean}", param);
        Asserts.assertTrue(Boolean.FALSE.equals(aBoolean));
        param.put("testBoolean", "false");
        aBoolean = FilterRuleUtil.matchRules("${testBoolean}", param);
        Asserts.assertTrue(Boolean.FALSE.equals(aBoolean));
        param.put("testBoolean", "true");
        aBoolean = FilterRuleUtil.matchRules("${testBoolean}", param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));

    }

    @Test
    public void matchRulesIn() {
        Map<String, Object> param = new HashMap<>();
        param.put("testIn_1", 34.9);
        param.put("testIn_2", 0);
        param.put("testIn_3", "aaccb");
        Boolean aBoolean = FilterRuleUtil.matchRules("\"aaccb\" in (${testIn_1},${testIn_2},${testIn_3})", param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));
        aBoolean = FilterRuleUtil.matchRules("0 in (${testIn_1},${testIn_2},${testIn_3})", param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));
        aBoolean = FilterRuleUtil.matchRules("34.9 in (${testIn_1},${testIn_2},${testIn_3})", param);
        Asserts.assertTrue(Boolean.TRUE.equals(aBoolean));
    }
}
