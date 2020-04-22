package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.model.Class;
import indi.uhyils.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 12时35分
 */
@Service
public class ClassServiceImpl implements ClassService {
    private static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

    @Override
    public Class getById(Integer id) {
        Class cls = new Class();
        cls.setId(id);
        cls.setName("name");
        return cls;
    }

    @Override
    public Boolean addOrUpdate(Class cls) {
        if (cls.getId() == null) {
            return true;
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        return true;
    }
}
