package com.wjd.algorithm.math;

/**
 * 字符串加法
 *
 * @author weijiaduo
 * @since 2023/7/3
 */
public class StrAdd {

    /**
     * 正序的大整数相加
     * <p>
     * （s[0]表示整数的低位，s[n-1]表示整数的高位）
     * <p>
     * 思路：直接模拟加法即可，注意进位
     * <p>
     * 复杂度：时间 O(n) 空间 O(1)
     *
     * @param num1 整数1
     * @param num2 整数2
     * @return 相加结果
     */
    public String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int m = num1.length(), n = num2.length();
        int i = 0, j = 0, carry = 0;
        while (i < m || j < n || carry != 0) {
            int d1 = i < m ? num1.charAt(i) - '0' : 0;
            int d2 = j < n ? num2.charAt(j) - '0' : 0;
            int sum = d1 + d2 + carry;

            carry = sum / 10;
            sum = sum % 10;
            sb.append(sum);

            i++;
            j++;
        }
        return sb.toString();
    }

    /**
     * 逆序的大整数相加
     * <p>
     * （s[0]表示整数的高位，s[n-1]表示整数的低位）
     * <p>
     * 思路：倒序模拟相加即可（类似于栈相加），记得最后反转回来
     * <p>
     * 复杂度：时间 O(n) 空间 O(n)
     *
     * @param num1 整数1
     * @param num2 整数2
     * @return 相加结果
     */
    public String reverseAdd(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int m = num1.length(), n = num2.length();
        int i = m - 1, j = n - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int d1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int d2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = d1 + d2 + carry;

            carry = sum / 10;
            sum = sum % 10;
            sb.append(sum);

            i--;
            j--;
        }
        return sb.reverse().toString();
    }

}
