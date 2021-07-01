package indi.uhyils.pojo.response;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月14日 11时10分
 */
public class HotSpotResponse implements Serializable {

    /**
     * redis中的key
     */
    private String key;

    /**
     * redis中hash内的key
     */
    private String hkey;


    public static HotSpotResponse build(String key, String hkey) {
        HotSpotResponse build = new HotSpotResponse();
        build.setKey(key);
        build.setHkey(hkey);
        return build;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHkey() {
        return hkey;
    }

    public void setHkey(String hkey) {
        this.hkey = hkey;
    }
}
