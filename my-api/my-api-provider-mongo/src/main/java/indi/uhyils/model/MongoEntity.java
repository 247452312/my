package indi.uhyils.model;

import indi.uhyils.pojo.model.base.BaseDbSaveable;
import indi.uhyils.pojo.request.base.DefaultRequest;

import java.util.HashMap;

/**
 * mongo传输数据集(此系统的mongo只存视频,图片,文件等)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 06时36分
 */
public class MongoEntity implements BaseDbSaveable {

    /**
     * mongo中的数据结构
     */
    private HashMap<String, byte[]> map;

    @Override
    public void preInsert(DefaultRequest request) {
        // nothing to do
    }

    @Override
    public void preUpdate(DefaultRequest request) {
        // nothing to do
    }

    public HashMap<String, byte[]> getMap() {
        return map;
    }

    public void setMap(HashMap<String, byte[]> map) {
        this.map = map;
    }
}
