package indi.uhyils.log;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月28日 21时04分
 */
public class CreateLogReport {

    public static void main(String[] args) {
        String path = "D:\\my\\logs";
        File logs = new File(path);
        if (!logs.exists()) {
            System.out.println("路径不存在");
            return;
        }
        File[] dirs = logs.listFiles();
        Map<String, File> dirMap = Arrays.stream(dirs).collect(Collectors.toMap(File::getName, t -> t));
        for (Entry<String, File> dir : dirMap.entrySet()) {
            String applicationName = dir.getKey();
            File value = dir.getValue();
            File[] logFiles = value.listFiles();
        }

    }

}
