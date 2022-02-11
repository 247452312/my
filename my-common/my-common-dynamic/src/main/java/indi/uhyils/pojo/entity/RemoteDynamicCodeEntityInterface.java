package indi.uhyils.pojo.entity;

import indi.uhyils.facade.DynamicCodeFacade;
import indi.uhyils.pojo.entity.base.IdEntity;
import java.util.function.Supplier;

/**
 * 远程动态方法替换
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月11日 19时50分
 */
public interface RemoteDynamicCodeEntityInterface extends IdEntity {

    /**
     * 补全动态执行代码
     *
     * @param facade
     */
    void fillDynamicCode(DynamicCodeFacade facade);

    /**
     * 是否匹配应用成功
     *
     * @return
     */
    Boolean isMatchSuccess();

    /**
     * 是否是临时
     *
     * @return
     */
    Boolean isTemp();

    /**
     * 临时永久代码并执行业务
     *
     * @param pjp
     *
     * @return
     */
    Object tempDynamic(Supplier<Object> pjp);

    /**
     * 替换永久代码并执行业务
     *
     * @param pjp
     *
     * @return
     */
    Object permanentDynamic(Supplier<Object> pjp);

    /**
     * 从静态变量中替换classLoader
     */
    void replaceClassLoaderFromContent();

    /**
     * 从静态变量中替换classLoader
     */
    ClassLoader replaceClassLoaderToContent(ClassLoader classLoader);
}
