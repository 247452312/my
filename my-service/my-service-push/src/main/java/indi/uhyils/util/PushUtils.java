package indi.uhyils.util;

import indi.uhyils.content.Content;
import indi.uhyils.dao.MsgDao;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.model.MsgEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.util.mail.SendMail;
import indi.uhyils.util.page.SendPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param sendContent 发送内容
     * @return
     */
    public static Boolean emailPush(UserEntity userEntity, String sendContent) {
        String mail = userEntity.getMail();
        String title = "my系统,订阅邮件";
        Boolean send = SendMail.send(mail, title, sendContent);
        saveMsg(userEntity, title, sendContent, PushTypeEnum.EMAIL, send);
        LogUtil.info(PushUtils.class, "向" + userEntity.getNickName() + " 发送邮件");
        return send;
    }


    /**
     * 页面推送
     *
     * @param userEntity  用户信息
     * @param sendContent 发送内容
     * @return
     */
    public static Boolean pagePush(UserEntity userEntity, String sendContent) {
        String userId = userEntity.getId();
        String title = "my系统,订阅邮件";
        Boolean send = SendPage.send(userId, title, sendContent);
        saveMsg(userEntity, title, sendContent, PushTypeEnum.PAGE, send);
        LogUtil.info(PushUtils.class, "向" + userEntity.getNickName() + " 页面推送");
        return send;
    }


    /**
     * 获取api结果内容
     *
     * @param userEntity 用户
     * @param list       api群
     * @return 内容
     */
    public static String getSendContent(UserEntity userEntity, List<ApiEntity> list) {
        HashMap<String, String> parameter = new HashMap<>();
        ApiUtils.callApi(list, userEntity, parameter);
        ApiEntity lastApi = list.get(list.size() - 1);
        String resultFormat = lastApi.getResultFormat();
        // 获取最终要推送的结果
        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            resultFormat = resultFormat.replaceAll(entry.getKey(), entry.getValue());
        }
        return resultFormat;
    }

    /**
     * 保存到消息表中
     *
     * @param userEntity  目标人
     * @param title       标题
     * @param sendContent 内容
     * @param type        类型
     */
    private static void saveMsg(UserEntity userEntity, String title, String sendContent, PushTypeEnum type, Boolean success) {
        MsgDao bean = SpringUtil.getBean(MsgDao.class);
        MsgEntity t;
        if (success) {
            t = MsgBuild.buildSuccessMsg(userEntity.getId(), title, sendContent, type);
        } else {
            t = MsgBuild.buildFaultMsg(userEntity.getId(), title, sendContent, type);
        }
        DefaultRequest request = new DefaultRequest();
        UserEntity user = new UserEntity();
        user.setId(Content.ADMIN_USER_ID);
        request.setUser(user);
        t.preInsert(request);
        bean.insert(t);
    }
}
