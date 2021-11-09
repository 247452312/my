package indi.uhyils.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月30日 08时44分
 */
public final class MathUtil {

    private MathUtil() {
    }


    /**
     * 获取方差
     *
     * @param times
     *
     * @return
     */
    public static double getVariance(List<Long> times) {
        int sum = 0;
        double avg;
        for (Long time : times) {
            sum += time;
        }
        avg = (double) sum / times.size();
        double result = 0.0;

        for (Long time : times) {
            result += Math.pow(time - avg, 2);
        }
        result /= times.size();
        return result;
    }

    /**
     * 数组是否正序排列
     *
     * @param array
     *
     * @return
     */
    public static boolean isSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 计算字符串
     *
     * @param s
     *
     * @return
     */
    public static double calculate(String s) {
        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        int sign = 1;

        double ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                double num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }


    /**
     * @return
     *
     * @Comment SHA1实现
     * @Author Ron
     * @Date 2017年9月13日 下午3:30:36
     */
    public static String shaEncode(String inStr) {
        return new String(shaEncode(inStr.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    /**
     * @return
     *
     * @Comment SHA1实现
     * @Author Ron
     * @Date 2017年9月13日 下午3:30:36
     */
    public static byte[] shaEncode(byte[] byteArray) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            LogUtil.error(e);
            return new byte[0];
        }

        byte[] md5Bytes = sha.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = md5Byte & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().getBytes(StandardCharsets.UTF_8);
    }


    /**
     * 异或
     *
     * @param encodeOnePassword
     * @param encodeRandom
     *
     * @return
     */
    public static byte[] xor(byte[] encodeOnePassword, byte[] encodeRandom) {
        byte[] first;
        byte[] second;
        if (encodeOnePassword.length > encodeRandom.length) {
            first = encodeOnePassword;
            second = new byte[encodeOnePassword.length];
            System.arraycopy(encodeRandom, 0, second, encodeOnePassword.length - encodeRandom.length, encodeRandom.length);
        } else {
            first = encodeRandom;
            second = new byte[encodeOnePassword.length];
            System.arraycopy(encodeOnePassword, 0, second, encodeRandom.length - encodeOnePassword.length, encodeOnePassword.length);
        }
        for (int i = 0; i < first.length; i++) {
            first[i] ^= second[i];
        }
        return first;
    }
}
