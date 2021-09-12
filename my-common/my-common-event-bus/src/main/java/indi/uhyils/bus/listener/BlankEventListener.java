package indi.uhyils.bus.listener;

import indi.uhyils.pojo.cqe.event.BlankEvent;
import indi.uhyils.util.LogUtil;
import org.springframework.stereotype.Component;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月12日 20时07分
 */
@Component
public class BlankEventListener implements EventListener<BlankEvent> {

    @Override
    public void execute(BlankEvent event) {
        LogUtil.info("空事件");
    }

    @Override
    public void execute(Class<BlankEvent> event) {
        LogUtil.info("空事件");
    }
}
