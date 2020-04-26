package indi.uhyils;

import indi.uhyils.ApplicationTest;
import indi.uhyils.model.UserEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.request.model.Arg;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时14分
 */
class UserServiceImplTest extends ApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    void getByClassId() {
        Assert.assertTrue(userService.getByClassId(IdRequest.build("")) != null);
    }

    @Test
    void getByArgs() {
        ArgsRequest argsRequest = new ArgsRequest();
        argsRequest.setPaging(false);
        ArrayList<Arg> args = new ArrayList<>();
        Arg e = new Arg();
        e.setName("");
        e.setSymbol("");
        e.setData("");
        args.add(e);
        argsRequest.setArgs(args);
        Assert.assertTrue(userService.getByArgs(argsRequest) != null);
    }

    @Test
    void getById() {
        ServiceResult<UserEntity> byId = userService.getById(IdRequest.build("1"));
        Assert.assertTrue(byId != null);
    }

    @Test
    void insert() {
        try {
            ObjRequest<UserEntity> insert = new ObjRequest<>();
            UserEntity data = new UserEntity();
            data.setId("1");
            data.setClassId("id");
            data.setName("name");
            data.setCreateDate(123123);
            data.setUpdateDate(1231123);
            insert.setData(data);
            userService.insert(insert);
        } catch (Exception e) {
            Assert.assertTrue(false);
            return;
        }
        Assert.assertTrue(true);
        return;
    }

    @Test
    void update() {
        try {
            ObjRequest<UserEntity> insert = new ObjRequest<>();
            UserEntity data = new UserEntity();
            data.setId("1");
            data.setClassId("id");
            data.setName("name_update");
            data.setCreateDate(123123);
            data.setUpdateDate(1231123);
            insert.setData(data);
            userService.update(insert);
        } catch (Exception e) {
            Assert.assertTrue(false);
            return;
        }
        Assert.assertTrue(true);
        return;
    }

//    @Test
    void delete() {
        try {
            userService.delete(IdRequest.build("1"));
        } catch (Exception e) {
            Assert.assertTrue(false);
            return;
        }
        Assert.assertTrue(true);
        return;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}