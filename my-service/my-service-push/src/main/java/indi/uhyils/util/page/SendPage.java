package indi.uhyils.util.page;

import indi.uhyils.context.MyContext;
import indi.uhyils.dao.SendPageDao;
import indi.uhyils.pojo.DO.SendPageDO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
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
        DefaultCQE request = new DefaultCQE();
        UserDTO user = new UserDTO();
        user.setId(MyContext.ADMIN_USER_ID);
        request.setUser(user);
        sendPageEntity.preInsert(request);
        //插入
        bean.insert(sendPageEntity);
        return Boolean.TRUE;
    }
}
