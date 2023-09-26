package com.wjd.util;

/**
 * 字节工具类
 *
 * @author weijiaduo
 * @since 2023/2/25
 */
public final class ByteUtil {

    private ByteUtil() {
    }

    public static byte[] toBytes(int[] numbers) {
        byte[] bytes = new byte[numbers.length * 4];
        int k = 0;
        for (int number : numbers) {
            bytes[k++] = (byte) ((number >>> 24) & 0xFF);
            bytes[k++] = (byte) ((number >>> 16) & 0xFF);
            bytes[k++] = (byte) ((number >>> 8) & 0xFF);
            bytes[k++] = (byte) (number & 0xFF);
        }
        return bytes;
    }

    public static byte[] toBytes(int number) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((number >>> 24) & 0xFF);
        bytes[1] = (byte) ((number >>> 16) & 0xFF);
        bytes[2] = (byte) ((number >>> 8) & 0xFF);
        bytes[3] = (byte) (number & 0xFF);
        return bytes;
    }

    public static int[] toInts(byte[] bytes) {
        int size = bytes.length / 4;
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            int num = 0;
            int k = i * 4;
            num |= (bytes[k++] & 0xFF) << 24;
            num |= (bytes[k++] & 0xFF) << 16;
            num |= (bytes[k++] & 0xFF) << 8;
            num |= (bytes[k] & 0xFF);
            ints[i] = num;
        }
        return ints;
    }

}
