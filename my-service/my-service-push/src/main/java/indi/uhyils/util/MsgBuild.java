package indi.uhyils.util;

import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.pojo.DO.MsgDO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月29日 07时56分
 */
public class MsgBuild {

    /**
     * 创建一个成功的消息
     *
     * @param userId  用户id
     * @param title   标题
     * @param content 内容
     * @param type    类型
     * @return 成功的消息
     */
    public static MsgDO buildSuccessMsg(Long userId, String title, String content, PushTypeEnum type) {
        MsgDO msgEntity = new MsgDO();
        msgEntity.setContent(content);
        msgEntity.setSuccess(Boolean.TRUE);
        msgEntity.setTarget(userId);
        msgEntity.setTitle(title);
        msgEntity.setType(type.getCode());
        return msgEntity;
    }

    /**
     * 创建一个失败的消息
     *
     * @param userId  用户id
     * @param title   标题
     * @param content 内容
     * @param type    类型
     * @return 成功的消息
     */
    public static MsgDO buildFaultMsg(Long userId, String title, String content, PushTypeEnum type) {
        MsgDO msgEntity = new MsgDO();
        msgEntity.setContent(content);
        msgEntity.setSuccess(false);
        msgEntity.setTarget(userId);
        msgEntity.setTitle(title);
        msgEntity.setType(type.getCode());
        return msgEntity;
    }
}
