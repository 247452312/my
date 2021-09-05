package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.event.Event;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * entity不是一个可序列化的逻辑集合,真正的OOP中的对象
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 14时55分
 */
public abstract class AbstractEntity implements HaveIdEntity {

    /**
     * 触发事件
     */
    private final List<Event> events;

    /**
     * id 是可以没有的
     */
    protected Identifier id;

    /**
     * 是否可以修改
     */
    private boolean canUpdate;


    protected AbstractEntity() {
        this.events = new ArrayList<>();
        this.canUpdate = false;
    }

    protected AbstractEntity(Long id) {
        this();
        this.id = new Identifier(id);
    }


    @Override
    public boolean canUpdate() {
        return canUpdate;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public boolean haveId() {
        return id != null && id.getId() != null && id.getId() > 0;
    }

    @Override
    public boolean notHaveId() {
        return !haveId();
    }

    public void onUpdate() {
        this.canUpdate = true;
    }

    /**
     * 添加触发事件
     *
     * @param event 事件
     */
    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * 执行剩下的所有event
     */
    @Override
    public void executeEvent() {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event next = iterator.next();
            next.execute();
            iterator.remove();
        }
    }

}

