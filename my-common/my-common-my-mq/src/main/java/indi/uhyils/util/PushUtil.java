package indi.uhyils.util;

import com.alibaba.fastjson.JSON;
import indi.uhyils.core.message.Message;
import indi.uhyils.core.register.Register;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 22时16分
 * @Version 1.0
 */
public class PushUtil {
    public static Boolean push(Register register, Message message) {
        // todo 推送消息
        System.out.println("推送消息,这里要改: " + JSON.toJSONString(message));
        return true;
    }
}
