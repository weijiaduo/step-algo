package com.wjd.util;

/**
 * 位工具类
 *
 * @author weijiaduo
 * @since 2022/7/9
 */
public final class BitUtil {

    private BitUtil() {
    }

    /**
     * bit位字符串转整数
     *
     * @param bits bit位
     * @return 整数
     */
    public static int bitsToInt(String bits) {
        int n = 0;
        for (int i = 0; i < bits.length(); i++) {
            n = (n << 1) | (bits.charAt(i) - '0');
        }
        return n;
    }

    /**
     * 整数转bit字符串
     *
     * @param n 整数
     * @return bit字符串
     */
    public static String intToBits(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Integer.SIZE; i++) {
            sb.append(n & 1);
            n >>>= 1;
        }
        return sb.reverse().toString();
    }

}
