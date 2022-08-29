package indi.uhyils.pojo.dto.response;

import indi.uhyils.annotation.NotNull;
import indi.uhyils.pojo.dto.FieldInfoDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取接口信息接口返回
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月29日 09时00分
 */
public class GetInterfaceInfoResponse implements Serializable {

    /**
     * 字段信息
     */
    private Map<String, FieldInfoDTO> fieldInfoMap;

    public static GetInterfaceInfoResponse build(@NotNull List<FieldInfoDTO> fieldInfoDTOS) {
        GetInterfaceInfoResponse getInterfaceInfoResponse = new GetInterfaceInfoResponse();
        final Map<String, FieldInfoDTO> collect = fieldInfoDTOS.stream().collect(Collectors.toMap(FieldInfoDTO::getName, t -> t, (key1, key2) -> key1));
        getInterfaceInfoResponse.setFieldInfoMap(collect);
        return getInterfaceInfoResponse;
    }

    public Map<String, FieldInfoDTO> getFieldInfoMap() {
        return fieldInfoMap;
    }

    public void setFieldInfoMap(Map<String, FieldInfoDTO> fieldInfoMap) {
        this.fieldInfoMap = fieldInfoMap;
    }
}
