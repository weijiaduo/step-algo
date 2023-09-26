package com.wjd.algorithm.strings.search.impl;

import com.wjd.algorithm.strings.search.Search;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin Karp 算法
 *
 * @author weijiaduo
 * @since 2023/3/30
 */
public class RabinKarpSearch implements Search {

    /**
     * 进制数
     */
    private static final int R = 10;
    /**
     * 对哈希值取余，避免溢出
     */
    private long mod = 99999999999997L;

    @Override
    public int search(String pat, String txt) {
        mod = longRandomPrime();
        int m = pat.length(), n = txt.length();
        long rm = 1;
        for (int i = 1; i < m; i++) {
            rm = (R * rm) % mod;
        }
        long phash = hash(pat, m);
        long thash = hash(txt, m);
        if (phash == thash && check(pat, txt, 0, m)) {
            return 0;
        }
        for (int i = 1; i <= n - m; i++) {
            thash = (thash - txt.charAt(i - 1) * rm + mod) % mod;
            thash = (R * thash + txt.charAt(i + m - 1)) % mod;
            if (phash == thash && check(pat, txt, i, m)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 计算哈希值
     *
     * @param s 字符串
     * @param n 长度
     * @return 哈希值
     */
    private long hash(String s, int n) {
        long h = 0;
        for (int i = 0; i < n; i++) {
            h = h * R + s.charAt(i);
            h %= mod;
        }
        return h;
    }

    /**
     * 校验两个字符串是否相等
     *
     * @param pat    模式串
     * @param txt    主串
     * @param index  主串起始索引
     * @param length 长度
     * @return true/false
     */
    private boolean check(String pat, String txt, int index, int length) {
        for (int i = 0; i < length; i++) {
            if (pat.charAt(i) != txt.charAt(index + i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return 随机大素数
     */
    private static long longRandomPrime() {
        // 素数的大小要随着测试用例变化而修改
        BigInteger prime = BigInteger.probablePrime(42, new Random());
        return prime.longValue();
    }

}
