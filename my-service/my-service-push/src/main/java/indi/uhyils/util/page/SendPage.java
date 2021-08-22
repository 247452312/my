package indi.uhyils.util.page;

import indi.uhyils.content.Content;
import indi.uhyils.dao.SendPageDao;
import indi.uhyils.pojo.model.SendPageDO;
import indi.uhyils.pojo.model.UserDO;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.util.SendPageBuild;
import indi.uhyils.util.SpringUtil;

/**
 * 页面推送
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月29日 08时22分
 */
public class SendPage {
    public static Boolean send(Long userId, String title, String sendContent) throws Exception {
        // 获取dao
        SendPageDao bean = SpringUtil.getBean(SendPageDao.class);
        // 获取要插入的bean
        SendPageDO sendPageEntity = SendPageBuild.buildSendPage(userId, title, sendContent);
        // 构造系统请求
        DefaultRequest request = new DefaultRequest();
        UserDO user = new UserDO();
        user.setId(Content.ADMIN_USER_ID);
        request.setUser(user);
        sendPageEntity.preInsert(request);
        //插入
        bean.insert(sendPageEntity);
        return Boolean.TRUE;
    }
}
