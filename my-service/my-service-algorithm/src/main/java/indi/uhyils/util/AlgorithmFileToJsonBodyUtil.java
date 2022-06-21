package indi.uhyils.util;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * 文件转换为json体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年01月04日 08时28分
 */
public class AlgorithmFileToJsonBodyUtil {


    /**
     * 文件转换为json体
     *
     * @param files 文件
     *
     * @return
     */
    public static String fileToJsonBody(File[] files) throws FileNotFoundException {
        Map<String, BufferedReader> bufferedReaderMap = new HashMap<>(files.length);
        for (File file : files) {
            String name = file.getName();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReaderMap.put(name, bufferedReader);
        }
        return fileToJsonBody(bufferedReaderMap);
    }

    /**
     * 字符流转换为json体
     *
     * @param bufferedReaderMap key->文件名称, value->字符流
     *
     * @return
     */
    public static String fileToJsonBody(Map<String, BufferedReader> bufferedReaderMap) {
        JSONObject result = new JSONObject();
        for (Entry<String, BufferedReader> entry : bufferedReaderMap.entrySet()) {
            String key = entry.getKey();
            BufferedReader value = entry.getValue();
            result.put(key, value.lines().collect(Collectors.joining("\n")));
        }
        return result.toJSONString();
    }


}
