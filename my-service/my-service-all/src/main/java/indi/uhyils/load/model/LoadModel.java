package indi.uhyils.load.model;

import indi.uhyils.util.MapUtil;
import java.io.Serializable;
import java.util.Map;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月18日 10时12分
 */
public class LoadModel implements Serializable {

    private Map<String, byte[]> classCache;

    private Map<String, byte[]> sourceCache;

    public static LoadModel build(Map<String, byte[]> classCache, Map<String, byte[]> sourceCache) {
        LoadModel build = new LoadModel();
        build.classCache = classCache;
        build.sourceCache = sourceCache;
        return build;
    }

    public Map<String, byte[]> getClassCache() {
        return classCache;
    }

    public void setClassCache(Map<String, byte[]> classCache) {
        this.classCache = classCache;
    }

    public Map<String, byte[]> getSourceCache() {
        return sourceCache;
    }

    public void setSourceCache(Map<String, byte[]> sourceCache) {
        this.sourceCache = sourceCache;
    }

    public void putAllIfAbsent(LoadModel loadModel) {
        Map<String, byte[]> classCache = loadModel.getClassCache();
        Map<String, byte[]> sourceCache = loadModel.getSourceCache();
        MapUtil.putAllIfAbsent(this.classCache, classCache);
        MapUtil.putAllIfAbsent(this.sourceCache, sourceCache);
    }
}
