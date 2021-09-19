package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.BaseDTO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月14日 11时10分
 */
public class HotSpotDTO implements BaseDTO {

    /**
     * redis中的key
     */
    private String key;

    /**
     * redis中hash内的key
     */
    private String hkey;


    public static HotSpotDTO build(String key, String hkey) {
        HotSpotDTO build = new HotSpotDTO();
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
