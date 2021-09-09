package indi.uhyils.service;


import indi.uhyils.pojo.DTO.request.DbInformation;
import java.util.List;
import java.util.Map;

/**
 * 项目生成
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 17时09分
 */
public interface KproService extends BaseService {

    /**
     * 项目生成接口
     *
     * @param list 项目生成请求
     *
     * @return map<文件名, 内容>
     */
    Map<String, String> projectGenerate(List<DbInformation> list);
}
