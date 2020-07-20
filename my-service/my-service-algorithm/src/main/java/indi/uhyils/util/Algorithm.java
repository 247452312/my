package indi.uhyils.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 算法集合
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月19日 09时16分
 */
public class Algorithm {
    public static byte[] getVoice() throws IOException {
        FileInputStream fis = null;
        File file = null;
        try {
            Object[] objects = MatlabCell.matlabCell("MatlabVideo.Video", "getVideo", "temp");
            assert objects != null;
            String fileName = objects[0].toString();
            file = new File(fileName);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            return b;
        } catch (Exception e) {
            LogUtil.error(Algorithm.class, e);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (file != null && file.exists()) {
                file.delete();
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        byte[] tests = getVoice();

    }
}
