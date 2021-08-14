package indi.uhyils.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月28日 14时06分
 */
public final class ObjectByteUtil {

    private ObjectByteUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 对象转数组
     *
     * @param obj
     *
     * @return
     */
    public static byte[] toByteArray(Object obj) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数组转对象
     *
     * @param bytes
     *
     * @return
     */
    public static <T> T toObject(byte[] bytes, Class<T> clazz) {
        Object obj = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
            obj = ois.readObject();
            ois.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return (T) obj;
    }
}
