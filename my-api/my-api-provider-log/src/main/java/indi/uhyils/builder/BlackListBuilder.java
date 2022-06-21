package indi.uhyils.builder;


import indi.uhyils.pojo.DTO.BlackListDTO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月16日 07时28分
 */
public class BlackListBuilder {

    /**
     * 根据id创建一个黑名单
     *
     * @param ip
     *
     * @return
     */
    public static BlackListDTO buildByIp(String ip) {
        BlackListDTO blackListEntity = new BlackListDTO();
        blackListEntity.setIp(ip);
        return blackListEntity;
    }
}
