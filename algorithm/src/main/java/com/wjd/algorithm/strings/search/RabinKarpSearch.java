package com.wjd.algorithm.strings.search;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin Karp 算法，又称指纹字符串查找算法
 * <p>
 * 复杂度：最好时间 O(n) 最坏时间 O(mn) 空间 O(1)
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
    private final long mod;

    /**
     * 模式串
     */
    final String pat;
    /**
     * 模式串哈希值
     */
    long phash;

    public RabinKarpSearch(String pat) {
        this.pat = pat;
        phash = 0;
        int m = pat.length();
        mod = longRandomPrime();
        for (int i = 0; i < m; i++) {
            phash = (R * phash + pat.charAt(i)) % mod;
        }
    }

    @Override
    public int search(String txt) {
        int m = pat.length(), n = txt.length();
        if (m == 0 || n < m) {
            return -1;
        }

        long thash = 0, rm = 1;
        for (int i = 0; i < m - 1; i++) {
            thash = (R * thash + txt.charAt(i)) % mod;
            // 首字符的幂值 R^(M-1)
            rm = (R * rm) % mod;
        }

        for (int i = m - 1, j = 0; i < n; i++, j++) {
            // 追加新字符的哈希值
            thash = (R * thash + txt.charAt(i)) % mod;
            // 验证子串[j, i]与模式串是否匹配
            if (phash == thash && check(pat, txt, j, m)) {
                return j;
            }
            // 减去首字符的哈希值
            thash = (thash - txt.charAt(j) * rm + mod) % mod;
        }
        return -1;
    }

    /**
     * 校验两个字符串是否相等
     *
     * @param pat    模式串
     * @param txt    主串
     * @param offset 主串起始索引
     * @param length 长度
     * @return true/false
     */
    private boolean check(String pat, String txt, int offset, int length) {
        for (int i = 0; i < length; i++) {
            if (pat.charAt(i) != txt.charAt(offset + i)) {
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
