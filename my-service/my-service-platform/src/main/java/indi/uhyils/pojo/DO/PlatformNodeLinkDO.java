package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* 节点关联表(PlatformNodeLink)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@TableName(value = "sys_platform_node_link")
public class PlatformNodeLinkDO extends BaseDO {
    
    private static final long serialVersionUID = -1L;

    /**
     * 父id
     */
    @TableField
    private Long fid;
    /**
     * 子id
     */
    @TableField
    private Long cid;

    public void setFid(Long fid){
        this.fid = fid;
    }

    public Long getFid(){
        return fid;
    }
    
    
    
    
    
    
    
    
    public void setCid(Long cid){
        this.cid = cid;
    }

    public Long getCid(){
        return cid;
    }
    

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fid", getFid())
                .append("id", getId())
                .append("cid", getCid())
                .toString();
    }
}
