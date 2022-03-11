package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 节点关联表表(PlatformNodeLink)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public class PlatformNodeLinkDTO extends IdDTO {

    private static final long serialVersionUID = -1L;
    
    /**
     * 父id
     */
    private Long fid;
    /**
     * 子id
     */
    private Long cid;
    
    public void setFid(Long fid) {
            this.fid = fid;
        }

    public Long getFid() {
            return fid;
        }
        
    public void setCid(Long cid) {
            this.cid = cid;
        }

    public Long getCid() {
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
