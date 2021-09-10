package indi.uhyils.util;

import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.pojo.DTO.PushMsgDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.util.mail.SendMail;
import indi.uhyils.util.page.SendPage;

/**
 * 推送
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月29日 07时21分
 */
public class PushUtils {


    /**
     * 发送邮件
     *
     * @param userEntity  目标用户
     * @param title       标题
     * @param sendContent 发送内容
     *
     * @return
     */
    public static PushMsgDTO emailPush(UserDTO userEntity, String title, String sendContent) {
        String mail = userEntity.getMail();
        Boolean send = SendMail.send(mail, title, sendContent);
        PushMsgDTO pushMsgDTO = makeMsg(userEntity, title, sendContent, PushTypeEnum.EMAIL, send);
        LogUtil.info(PushUtils.class, "向" + userEntity.getNickName() + " 发送邮件");
        return pushMsgDTO;
    }


    /**
     * 页面推送
     *
     * @param userEntity  用户信息
     * @param title       标题
     * @param sendContent 发送内容
     *
     * @return
     */
    public static PushMsgDTO pagePush(UserDTO userEntity, String title, String sendContent) {
        Long userId = userEntity.getId();
        Boolean send = SendPage.send(userId, title, sendContent);
        PushMsgDTO pushMsgDTO = makeMsg(userEntity, title, sendContent, PushTypeEnum.PAGE, send);
        LogUtil.info(PushUtils.class, "向" + userEntity.getNickName() + " 页面推送");
        return pushMsgDTO;
    }


    /**
     * 保存到消息表中
     *
     * @param userEntity  目标人
     * @param title       标题
     * @param sendContent 内容
     * @param type        类型
     */
    private static PushMsgDTO makeMsg(UserDTO userEntity, String title, String sendContent, PushTypeEnum type, Boolean success) {
        PushMsgDTO t;
        if (success) {
            t = MsgBuild.buildSuccessMsg(userEntity.getId(), title, sendContent, type);
        } else {
            t = MsgBuild.buildFaultMsg(userEntity.getId(), title, sendContent, type);
        }
        return t;
    }
}
