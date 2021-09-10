package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDbSaveable;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * mongo传输数据集(此系统的mongo只存视频,图片,文件等)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 06时36分
 */
public class MongoDO implements BaseDbSaveable {


    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件本体
     */
    private byte[] bytes;

    @Override
    public void preInsert(DefaultCQE request) {
        // nothing to do
    }

    @Override
    public void preInsert(UserDTO userDO) {

    }

    @Override
    public void preInsert() {
        // nothing to do
    }

    @Override
    public void preUpdate(DefaultCQE request) {
        // nothing to do
    }

    @Override
    public void preUpdate(UserDTO userDO) {

    }

    @Override
    public void preUpdate() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
