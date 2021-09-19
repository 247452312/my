package indi.uhyils.protocol.register;

import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.protocol.register.base.Register;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 14时05分
 */
@indi.uhyils.annotation.Register
public class BlackRegister implements Register {

    @Override
    public List<Class<? extends BaseEvent>> targetEvent() {
        return new ArrayList<>();
    }

    @Override
    public void onEvent(BaseEvent event) {
        System.out.println("空白事件监听(测试用)");
    }
}
