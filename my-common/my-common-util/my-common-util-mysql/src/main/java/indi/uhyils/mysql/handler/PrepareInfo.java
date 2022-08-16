package indi.uhyils.mysql.handler;

import indi.uhyils.mysql.pojo.DTO.PrepareParamInfo;
import indi.uhyils.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月06日 15时39分
 */
public class PrepareInfo {

    /**
     * 预处理语句
     */
    private String prepareInfo;

    /**
     * 占位符所在
     */
    private List<Integer> placeholderIndex;

    /**
     * 占位符数量
     */
    private Integer count;

    public PrepareInfo(String prepareInfo) {
        this.prepareInfo = prepareInfo;
        placeholderIndex = new ArrayList<>();
        // 0-> 不在引号中 1->在单引号中 2->在双引号中
        int inQuotation = 0;
        for (int i = 0; i < prepareInfo.length(); i++) {
            char c = prepareInfo.charAt(i);
            // 在单引号中
            if (inQuotation == 1 && c == '\'') {
                inQuotation = 0;
            } else if (inQuotation == 2 && c == '"') {
                // 在双引号中
                inQuotation = 0;
            } else if (inQuotation == 0) {
                // 不在引号中
                if (c == '\'') {
                    inQuotation = 1;
                } else if (c == '"') {
                    inQuotation = 2;
                } else if (c == '?') {
                    placeholderIndex.add(i);
                }
            }
        }
        count = prepareInfo.length();
    }

    public String getPrepareInfo() {
        return prepareInfo;
    }

    public List<Integer> getPlaceholderIndex() {
        return placeholderIndex;
    }

    public Integer getCount() {
        return count;
    }

    public String replacePrepareSql(List<PrepareParamInfo> params) {
        List<String> result = StringUtil.splitByIndexList(prepareInfo, "?", placeholderIndex);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (params.size() > i) {
                Object paramValue = params.get(i).getParamValue();
                sb.append(paramValue.toString());
            }
        }
        return sb.toString();
    }
}
