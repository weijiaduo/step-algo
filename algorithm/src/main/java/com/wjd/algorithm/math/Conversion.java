package com.wjd.algorithm.math;

/**
 * 进制转换
 *
 * @author weijiaduo
 * @since 2023/7/14
 */
public class Conversion {

    static final char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    static final int[] numbers = new int[128];

    static {
        for (int i = digits.length - 1; i >= 0; i--) {
            numbers[digits[i]] = i;
        }
    }

    /**
     * 二进制字符串转成十进制数字
     *
     * @param binaryStr 二进制字符串
     * @return 十进制数字
     */
    public int fromBinaryStr(String binaryStr) {
        return fromUnsignedStr(binaryStr, 1);
    }

    /**
     * 八进制字符串转成十进制数字
     *
     * @param octalStr 八进制字符串
     * @return 十进制数字
     */
    public int fromOctalStr(String octalStr) {
        octalStr = octalStr.toLowerCase();
        if (octalStr.charAt(0) == '0') {
            octalStr = octalStr.substring(1);
        }
        return fromUnsignedStr(octalStr, 3);
    }

    /**
     * 十六进制字符串转成十进制数字
     *
     * @param hexStr 十六进制字符串
     * @return 十进制数字
     */
    public int fromHexStr(String hexStr) {
        hexStr = hexStr.toLowerCase();
        if (hexStr.startsWith("0x")) {
            hexStr = hexStr.substring(2);
        }
        return fromUnsignedStr(hexStr, 4);
    }

    /**
     * 将指定进制字符串转成数字
     *
     * @param str   指定进制字符串
     * @param shift 进制位数
     * @return 数字
     */
    private int fromUnsignedStr(String str, int shift) {
        int val = 0;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            val <<= shift;
            val |= numbers[str.charAt(i)];
        }
        return val;
    }

    /**
     * 浮点数转成二进制字符串
     *
     * @param val 浮点数
     * @return 二进制字符串
     */
    public String toBinaryStr(double val) {
        StringBuilder sb = new StringBuilder();
        int num = (int) val;
        String intStr = toBinaryStr(num);
        sb.append(intStr).append(".");
        val -= num;
        for (int i = 0; i < 32 && val != 0; i++) {
            val *= 2;
            int d = (int) val;
            sb.append(d);
            val -= d;
        }
        return sb.toString();
    }

    /**
     * 十进制数字转成二进制字符串
     *
     * @param val 十进制数字
     * @return 二进制字符串
     */
    public String toBinaryStr(int val) {
        return toUnsignedStr(val, 1);
    }

    /***
     * 十进制数字转成八进制字符串
     *
     * @param val 十进制数字
     * @return 八进制字符串
     */
    public String toOctalStr(int val) {
        return toUnsignedStr(val, 3);
    }

    /**
     * 十进制数字转成十六进制字符串
     *
     * @param val 十进制数字
     * @return 十六进制字符串
     */
    public String toHexStr(int val) {
        return toUnsignedStr(val, 4);
    }

    /**
     * 数字转成无符号字符串
     *
     * @param val   数字
     * @param shift 进制位数
     * @return 字符串
     */
    private String toUnsignedStr(int val, int shift) {
        // 找到有效的数位长度
        int mag = Integer.SIZE - Integer.numberOfLeadingZeros(val);
        // 向上取整拿到最长位数
        int len = Math.max(((mag + (shift - 1)) / shift), 1);
        byte[] buf = new byte[len];
        formatUnsignedInt(val, shift, buf);
        return new String(buf);
    }

    /**
     * 按照某种进制格式化数字
     *
     * @param val   数字
     * @param shift 进制位数
     * @param buf   缓冲区
     */
    private void formatUnsignedInt(int val, int shift, byte[] buf) {
        int radix = 1 << shift;
        int mask = radix - 1;
        for (int i = buf.length - 1; i >= 0; i--) {
            buf[i] = (byte) digits[val & mask];
            val >>>= shift;
        }
    }

}
