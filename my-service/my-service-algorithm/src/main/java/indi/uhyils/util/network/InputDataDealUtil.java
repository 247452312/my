package indi.uhyils.util.network;

import indi.uhyils.util.DoubleArrayUtil;
import indi.uhyils.util.MFCC;
import indi.uhyils.util.WaveFileReader;
import indi.uhyils.util.network.core.Datable;
import indi.uhyils.util.network.data.ThreeDimensionalData;
import indi.uhyils.util.network.data.TwoDimensionalData;
import org.apache.commons.lang3.ArrayUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 输入类型处理
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 10时38分
 */
public class InputDataDealUtil {
    static final int MAX = 1 << 30;

    /**
     * 获取图像数据
     *
     * @param image 图像
     * @return 数据
     */
    public static Datable getImageInputData(BufferedImage image) {
        Integer[][][] result = new Integer[4][image.getWidth()][image.getHeight()];
        //方式一：通过getRGB()方式获得像素矩阵
        //此方式为沿Height方向扫描
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int rgb = image.getRGB(i, j);
                result[0][i][j] = (rgb >> 24) & 0xff;
                result[1][i][j] = (rgb >> 16) & 0xff;
                result[2][i][j] = (rgb >> 8) & 0xff;
                result[3][i][j] = (rgb) & 0xff;
            }
        }
        ThreeDimensionalData threeDimensionalData = new ThreeDimensionalData();
        threeDimensionalData.setData(result);
        return threeDimensionalData;
    }

    public static Datable getImageInputData(String imagePath) throws IOException {
        return getImageInputData(ImageIO.read(new File(imagePath)));
    }

    /**
     * 获取音频波形数据(声道 * 数据长度) 原始
     *
     * @param wavPath 文件路径
     * @return 二维数组 波形数据
     */
    public static Datable getWavInputData(String wavPath) {
        WaveFileReader wfr = new WaveFileReader(wavPath);
        Integer[][] data = wfr.getData();
        TwoDimensionalData twoDimensionalData = new TwoDimensionalData();
        twoDimensionalData.setData(data);
        return twoDimensionalData;
    }

    /**
     * 获取音频数据 (只有第一声道 每帧大小 * 位移数 ->MFCC) 预加重 + 分帧 + 加窗
     *
     * @param wavPath 文件
     * @param size    每帧大小 (毫秒)
     * @param shift   帧移 (大小)
     * @return 分帧后
     */
    public static Datable getWavInputData(String wavPath, Integer size, Integer shift) {
        WaveFileReader wfr = new WaveFileReader(wavPath);
        // 只获取第一声道
        Integer[] data = wfr.getData()[0];
        Double[] dataDouble = new Double[data.length];
        /* 预加重*/
        dataDouble = Arrays.stream(data).map(integer -> 1 - 0.97 * (1.0 / integer)).collect(Collectors.toList()).toArray(dataDouble);
        /*分帧*/
        // 获取采样率
        int fs = (int) wfr.getSampleRate();
        // 数组总长度
        int length = dataDouble.length;
        // 每帧大小(数组)
        int sizeD = size;
        sizeD = tableSizeFor(sizeD);
        // 帧移大小(数组)
        int shiftD = shift;
        // 一共切分次数
        int count = (length - sizeD) / (shiftD) + 1;
        Double[][] result = new Double[(count + 1)][sizeD];

        for (int i = 0; i < count; i++) {
            int start = i * shiftD;
            System.arraycopy(dataDouble, start, result[i], 0, sizeD);
        }
        // 最后一帧修改为: 从最后一个音频点往前推的sizeD个点 使整个音频饱满起来
        System.arraycopy(dataDouble, dataDouble.length - sizeD, result[count], 0, sizeD);

        MFCC mfcc = new MFCC(13, fs, 24, sizeD, Boolean.FALSE, 24, Boolean.FALSE);
        double[][] parameters = new double[(count + 1)][13];
        // 汉明窗的a
        double hammingA = 0.46;
        // 每一帧
        for (int i = 0; i < result.length; i++) {
            // 汉明窗的N
            int hammingN = result[i].length;
            for (int j = 0; j < result[i].length; j++) {
                /*加窗(汉明窗)*/
                result[i][j] *= (1 - hammingA) - hammingA * Math.cos(2 * Math.PI * j / (hammingN - 1));
            }
            parameters[i] = mfcc.getParameters(ArrayUtils.toPrimitive(result[i]));
        }
        TwoDimensionalData<Double> twoDimensionalData = new TwoDimensionalData();
        twoDimensionalData.setData(DoubleArrayUtil.cenvert(parameters));
        return twoDimensionalData;

    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    private static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        return (n < 0) ? 1 : (n >= MAX) ? MAX : n + 1;
    }


    public static void main(String[] args) {
        Datable wavInputData = getWavInputData("D:\\share\\ideaSrc\\speechRecognition\\data\\data_thchs30\\data\\A2_0.wav", 256, 128);
        Double[][] data = (Double[][]) wavInputData.getData();
        for (Double[] datum : data) {
            for (Double integer : datum) {
                System.out.printf(integer + " ");
            }
            System.out.println();
        }
    }
}
