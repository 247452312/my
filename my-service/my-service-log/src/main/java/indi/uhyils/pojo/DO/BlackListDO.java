package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 黑名单(BlackList)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时39分29秒
 */
@TableName(value = "sys_black_list")
public class BlackListDO extends BaseDO {

    private static final long serialVersionUID = 312396996558425264L;


    /**
     * 用户ip
     */
    @TableField
    private String ip;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
