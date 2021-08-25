package indi.uhyils.pojo.cqe.event;

import indi.uhyils.pojo.cqe.BaseCQE;

/**
 * 通知,标记一件已经发生过的事情,通知给系统,这时系统应该作出响应的改变,使用event入参的方法大概率不会有返回值
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时58分
 */
public interface BaseEvent extends BaseCQE {

}
