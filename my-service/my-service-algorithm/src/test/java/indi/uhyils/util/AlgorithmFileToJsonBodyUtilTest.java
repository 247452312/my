package indi.uhyils.util;

import indi.uhyils.rpc.util.LogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年01月04日 08时39分
 */
class AlgorithmFileToJsonBodyUtilTest {

    public static List<File> transPathToFiles(String path) {
        File file = new File(path);
        return transFileToFiles(file);
    }

    public static List<File> transFileToFiles(File file) {
        if (file == null) {
            return Collections.emptyList();
        }
        if (file.isFile()) {
            List<File> files = new ArrayList<>();
            files.add(file);
            return files;
        }
        List<File> result = new ArrayList<>();
        File[] files = file.listFiles();
        for (File lastFile : files) {
            List<File> lastFiles = transFileToFiles(lastFile);
            result.addAll(lastFiles);
        }
        return result;
    }

    @Test
    void fileToJsonBody() throws FileNotFoundException {
        List<File> files = transPathToFiles("F:\\share\\my\\my-service\\my-service-algorithm\\src\\main\\java\\indi\\uhyils\\util\\genetic");
        String s = AlgorithmFileToJsonBodyUtil.fileToJsonBody(files.toArray(new File[0]));
        LogUtil.info(s);
    }

}
