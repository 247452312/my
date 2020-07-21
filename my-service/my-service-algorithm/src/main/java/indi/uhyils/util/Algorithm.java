package indi.uhyils.util;

import indi.uhyils.util.audio.main.MFCCFeatureMain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 算法集合
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月19日 09时16分
 */
public class Algorithm {
    public static double[] getMfccVoice() throws IOException {
        FileInputStream fis = null;
        File file = null;
        try {
            Object[] objects = MatlabCell.matlabCell("MatlabVideo.Video", "getVideo", "temp");
            assert objects != null;
            String fileName = objects[0].toString();
            file = new File(fileName);
            return MFCCFeatureMain.getFeature(file);
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
        double[] mfccVoice = getMfccVoice();

    }
}
