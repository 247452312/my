package indi.uhyils.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import indi.uhyils.model.ClassEntity;
import indi.uhyils.request.IdRequest;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.response.WebResponse;
import indi.uhyils.service.ClassService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月22日 09时32分
 */
@Controller
@RequestMapping("class")
public class ClassController {

    @Reference
    private ClassService classService;


    @RequestMapping("getClassById")
    @ResponseBody
    public WebResponse<ClassEntity> getClassById(Integer classId) {
        ServiceResult<ClassEntity> byId = classService.getById(IdRequest.build(String.valueOf(classId)));
        return WebResponse.build(byId);
    }


}
