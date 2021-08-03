package indi.uhyils.pojo.model;

import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.pojo.model.base.BaseDbSaveable;
import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * mongo传输数据集(此系统的mongo只存视频,图片,文件等)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 06时36分
 */
public class MongoEntity implements BaseDbSaveable {


    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件本体
     */
    private byte[] bytes;

    @Override
    public void preInsert(DefaultRequest request) {
        // nothing to do
    }

    @Override
    public void preInsert() throws IdGenerationException, InterruptedException {
        // nothing to do
    }

    @Override
    public void preUpdate(DefaultRequest request) {
        // nothing to do
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
