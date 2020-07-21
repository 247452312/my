package indi.uhyils.util.audio.main;


import indi.uhyils.util.audio.FeatureExtract;
import indi.uhyils.util.audio.PreProcess;
import indi.uhyils.util.audio.WaveData;
import indi.uhyils.util.audio.feature.FeatureVector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MFCCFeatureMain {

    //	private static FormatControlConf fc = new FormatControlConf();
    // (int) fc.getRate();
    private static final int SAMPLING_RATE = 1020;
    // int samplePerFrame = 256; // 16ms for 8 khz
    // 512,23.22ms
    private static final int SAMPLE_PER_FRAME = 256;
    private static final int FEATURE_DIMENSION = 39;
    private static FeatureExtract featureExtract;
    private static WaveData waveData = new WaveData();
    private static PreProcess prp;
    private static List<double[]> allFeaturesList = new ArrayList<double[]>();

    private static final String BASE_DIR = "data";

    public MFCCFeatureMain() {

    }

    /**
     * 主函数
     */
    public static void main(String[] args) {
        MFCCFeatureMain mfcc = new MFCCFeatureMain();
        if (args.length != 1) {
            System.err.println("Usage: <string-type> e.g. knn--KNN,svmiris--SVM-Iris,svm--SVM,ann--ANN");
            System.exit(-1);
        }
        /**
         * 将data下面的train和test的所有音频文件的特征写到对应的文件夹下面，Iris格式
         * 分别为data/knn/train.data,data/knn/test.data
         */
        if ("knn".equalsIgnoreCase(args[0])) {
            mfcc.writeFeaturesKnn("train");
            mfcc.writeFeaturesKnn("test");
        }
        /**
         * 将data下面的train和test的所有音频文件的特征写到对应的文件夹下面，Iris格式
         * 分别为data/svm-iris/train.data,data/svm-iris/test.data
         */
        if ("svmiris".equalsIgnoreCase(args[0])) {
            mfcc.writeFeaturesIris("train");
            mfcc.writeFeaturesIris("test");
        }
        /**
         * 将data下面的train和test的所有音频文件的特征写到对应的文件夹下面，SimpleSVM格式
         * 分别为data/svm/train_bc,data/svm/test_bc
         */
        if ("svm".equalsIgnoreCase(args[0])) {
            mfcc.writeFeaturesSimpleSVM("train");
            mfcc.writeFeaturesSimpleSVM("test");
        }
        /**
         * 将data下面的train和test的所有音频文件的特征写到对应的文件夹下面，CNN格式
         * 分别为data/ann/train.feature,data/ann/train.label,data/ann/test.feature,data/ann/test.label
         */
        if ("ann".equalsIgnoreCase(args[0])) {
            //		mfcc.writeFeaturesCNNTrain();
            //		mfcc.writeFeaturesCNNTest();
            mfcc.writeFeaturesANN("train");
            mfcc.writeFeaturesANN("test");
        }
    }

    /**
     * 按照ANN格式输出,1-male,0-female
     */
    public static void writeFeaturesANN(String dirName) {
        String dataName = BASE_DIR + "/ann/" + dirName + ".feature";
        String labelName = BASE_DIR + "/ann/" + dirName + ".label";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dataName)));
             BufferedWriter label = new BufferedWriter(new FileWriter(new File(labelName)));) {
            File maleDir = new File(BASE_DIR + "/" + dirName + "/male");
            for (File f : maleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2CNNStr(getFeature(f.getPath())));
                bw.newLine();
                label.write("1");
                label.newLine();
            }
            File femaleDir = new File(BASE_DIR + "/" + dirName + "/female");
            for (File f : femaleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2CNNStr(getFeature(f.getPath())));
                bw.newLine();
                label.write("0");
                label.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照CNN格式输出,1-male,0-female
     */
    public static void writeFeaturesCNNTrain() {
        String dataName = BASE_DIR + "/train/train.format";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dataName)));) {
            File maleDir = new File(BASE_DIR + "/train/male");
            for (File f : maleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2CNNStr(getFeature(f.getPath())) + ",1");
                bw.newLine();
            }
            File femaleDir = new File(BASE_DIR + "/train/female");
            for (File f : femaleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2CNNStr(getFeature(f.getPath())) + ",0");
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFeaturesCNNTest() {
        String dataName = BASE_DIR + "/test/test.format";
        String labelName = BASE_DIR + "/test/test.label";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dataName)));
             BufferedWriter label = new BufferedWriter(new FileWriter(new File(labelName)));) {
            File maleDir = new File(BASE_DIR + "/test/male");
            for (File f : maleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2CNNStr(getFeature(f.getPath())));
                bw.newLine();
                label.write("1");
                label.newLine();
            }
            File femaleDir = new File(BASE_DIR + "/test/female");
            for (File f : femaleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2CNNStr(getFeature(f.getPath())));
                bw.newLine();
                label.write("0");
                label.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将特征数据转换成CNN格式，针对每条数据
     */
    private static String transFeature2CNNStr(double[] feature) {
        StringBuffer sb = new StringBuffer();
        for (double f : feature) {
            sb.append(f + "").append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * 按照Iris格式输出
     */
    public static void writeFeaturesKnn(String dirName) {
        String dataName = BASE_DIR + "/knn/" + dirName + ".data";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dataName)));) {
            File maleDir = new File(BASE_DIR + "/" + dirName + "/male");
            for (File f : maleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2IrisStr(getFeature(f.getPath()), "male"));
                bw.newLine();
            }
            File femaleDir = new File(BASE_DIR + "/" + dirName + "/female");
            for (File f : femaleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2IrisStr(getFeature(f.getPath()), "female"));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照Iris格式输出
     */
    public static void writeFeaturesIris(String dirName) {
        String dataName = BASE_DIR + "/svm-iris/" + dirName + ".data";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dataName)));) {
            File maleDir = new File(BASE_DIR + "/" + dirName + "/male");
            for (File f : maleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2IrisStr(getFeature(f.getPath()), "male"));
                bw.newLine();
            }
            File femaleDir = new File(BASE_DIR + "/" + dirName + "/female");
            for (File f : femaleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2IrisStr(getFeature(f.getPath()), "female"));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将特征数据转换成iris格式，针对每条数据
     */
    private static String transFeature2IrisStr(double[] feature, String cate) {
        StringBuffer sb = new StringBuffer();
        for (double f : feature) {
            sb.append(f + "").append(",");
        }
        sb.append(cate);
        return sb.toString();
    }

    /**
     * 按照SimpleSVM格式输出
     */
    public static void writeFeaturesSimpleSVM(String dirName) {
        String dataName = BASE_DIR + "/svm/" + dirName + "_bc";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dataName)));) {
            File maleDir = new File(BASE_DIR + "/" + dirName + "/male");
            for (File f : maleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2SimpleStr(getFeature(f.getPath()), 1));
                bw.newLine();
            }
            File femaleDir = new File(BASE_DIR + "/" + dirName + "/female");
            for (File f : femaleDir.listFiles()) {
                System.out.println(f.getPath());
                bw.write(transFeature2SimpleStr(getFeature(f.getPath()), -1));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将特征数据转换成SimpleSVM格式，针对每条数据
     * cate:  1--男，-1--女
     */
    public static String transFeature2SimpleStr(double[] feature, int cate) {
        StringBuffer sb = new StringBuffer();
        sb.append(cate + "").append("\t");
        int count = 1;
        for (double f : feature) {
            sb.append(count++ + ":" + f).append("\t");
        }
        return sb.toString();
    }

    /**
     * 提取单个音频的特征数据
     */
    public static double[] getFeature(String fileName) {
        return getFeature(new File(fileName));
    }

    public static double[] getFeature(File file) {
        int totalFrames = 0;
        FeatureVector feature = extractFeatureFromFile(file);
        for (int k = 0; k < feature.getNoOfFrames(); k++) {
            allFeaturesList.add(feature.getFeatureVector()[k]);
            totalFrames++;
        }
        // 行代表帧数，列代表特征
        double allFeatures[][] = new double[totalFrames][FEATURE_DIMENSION];
        for (int i = 0; i < totalFrames; i++) {
            double[] tmp = allFeaturesList.get(i);
            allFeatures[i] = tmp;
        }

        // 计算每帧对应特征的平均值
        double avgFeatures[] = new double[FEATURE_DIMENSION];
        for (int j = 0; j < FEATURE_DIMENSION; j++) { // 循环每列
            double tmp = 0.0d;
            for (int i = 0; i < totalFrames; i++) { // 循环每行
                tmp += allFeatures[i][j];
            }
            avgFeatures[j] = tmp / totalFrames;
        }

        return avgFeatures;
    }

    public static FeatureVector extractFeatureFromFile(File speechFile) {
        float[] arrAmp;
        arrAmp = waveData.extractAmplitudeFromFile(speechFile);
        prp = new PreProcess(arrAmp, SAMPLE_PER_FRAME, SAMPLING_RATE);
        featureExtract = new FeatureExtract(prp.framedSignal, SAMPLING_RATE, SAMPLE_PER_FRAME);
        featureExtract.makeMfccFeatureVector();
        return featureExtract.getFeatureVector();
    }

}
