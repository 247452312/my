package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 节点api表(OrderApi)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分47秒
 */
public class OrderApiDTO extends IdDTO {

    private static final long serialVersionUID = -43066730957927350L;


    /**
     * bean名称
     */
    private String beanName;


    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

}
