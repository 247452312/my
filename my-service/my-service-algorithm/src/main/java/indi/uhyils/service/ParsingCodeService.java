package indi.uhyils.service;

/**
 * 解析代码的service
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月10日 08时45分
 */
public interface ParsingCodeService extends BaseService {

    /**
     * 解析代码
     *
     * @param classValue
     *
     * @return
     */
    String executeCode(String classValue);
}
