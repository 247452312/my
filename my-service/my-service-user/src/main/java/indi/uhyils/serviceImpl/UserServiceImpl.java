package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.model.User;
import indi.uhyils.service.UserService;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User getById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("name");
        user.setClassId(1);
        return user;
    }

    @Override
    public List<User> getByClassId(Integer classId) {
        List<User> users = new ArrayList<User>();

        for (int i = 0; i < 2; i++) {
            User user = new User();
            user.setId(i);
            user.setName("name");
            user.setClassId(classId);
            users.add(user);
        }
        return users;
    }

    @Override
    public Boolean addOrUpdate(User user) {
        if (user.getId() == null) {
            logger.info("ADD");
            return true;
        }
        logger.info("UPDATE");
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        logger.info("delete");
        return true;
    }
}
