package indi.uhyils.pojo;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月01日 14时30分
 */
public class IdMapping {

    private Long newId;

    private Long oldId;

    public IdMapping(Long newId, Long oldId) {
        this.newId = newId;
        this.oldId = oldId;
    }

    public static IdMapping build(Long newId, Long oldId) {
        return new IdMapping(newId, oldId);
    }

    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }
}
