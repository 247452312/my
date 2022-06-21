package indi.uhyils.util.page;

import indi.uhyils.assembler.PushPageMsgAssembler;
import indi.uhyils.context.MyContext;
import indi.uhyils.dao.PushPageMsgDao;
import indi.uhyils.pojo.DO.PushPageMsgDO;
import indi.uhyils.pojo.DTO.PushPageMsgDTO;
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

    public static Boolean send(Long userId, String title, String sendContent) {
        // 获取dao
        PushPageMsgDao bean = SpringUtil.getBean(PushPageMsgDao.class);
        PushPageMsgAssembler assembler = SpringUtil.getBean(PushPageMsgAssembler.class);
        // 获取要插入的bean
        PushPageMsgDTO sendPageEntity = SendPageBuild.buildSendPage(userId, title, sendContent);
        // 构造系统请求
        DefaultCQE request = new DefaultCQE();
        UserDTO user = new UserDTO();
        user.setId(MyContext.ADMIN_USER_ID);
        request.setUser(user);
        PushPageMsgDO pushPageMsgDO = assembler.toDo(sendPageEntity);

        pushPageMsgDO.preInsert(request);
        //插入
        bean.insert(pushPageMsgDO);
        return Boolean.TRUE;
    }
}
