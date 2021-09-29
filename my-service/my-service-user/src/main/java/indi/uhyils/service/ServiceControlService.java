package indi.uhyils.service;


import indi.uhyils.context.MyContext;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.MethodDisableDTO;
import indi.uhyils.pojo.entity.type.InterfaceName;
import indi.uhyils.pojo.entity.type.MethodName;
import java.util.List;

/**
 * 控制接口是否开启和关闭
 * <div>
 *     <h3>
 * redis中的命名规则
 * </h3>
 * <span>
 * 1. hashkey为{@link MyContext#SERVICE_USEABLE_SWITCH}
 * 2. hash中的key:
 *    2.1 如果是classname 则是接口全名
 *    2.2 如果是methodName 则是{接口全名#方法名称}
 * </span>
 * </div>
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分43秒
 */
public interface ServiceControlService extends BaseService {

    /**
     * 获取接口是否允许使用
     *
     * @param interfaceName 接口名称
     * @param methodName    方法名称
     * @param methodType    方法类型
     *
     * @return
     */
    Boolean getMethodDisable(InterfaceName interfaceName, MethodName methodName, ReadWriteTypeEnum methodType);

    /**
     * 获取所有接口是否允许使用
     *
     * @return 全部接口
     */
    List<MethodDisableDTO> getAllMethodDisable();


    /**
     * 方法禁用添加修改接口
     *
     * @return 是否成功
     */
    Boolean addOrEditMethodDisable(MethodDisableDTO dto);

    /**
     * 删除对应的禁用接口项
     *
     * @param interfaceName 要删除的接口
     * @param methodName    要删除的方法
     *
     * @return 删除请求
     */
    Boolean delMethodDisable(InterfaceName interfaceName, MethodName methodName);
}
