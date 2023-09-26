package com.wjd.algorithm.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 质数
 *
 * @author weijiaduo
 * @since 2023/7/2
 */
public class Primes {

    /**
     * 暴力法
     * <p>
     * 复杂度：时间 O(n * sqrt(n)) 空间 O(1)
     */
    public int[] brute(int n) {
        int[] flg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            flg[i] = isPrime(i) ? 1 : -1;
        }
        return flg;
    }

    /**
     * 是否是质数
     *
     * @param n 数字
     * @return true 质数/false 非质数
     */
    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 埃氏筛
     * <p>
     * 复杂度：时间 O(nloglogn) 空间 O(1)
     */
    public int[] eratosthenes(int n) {
        int[] flg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (flg[i] != 0) {
                continue;
            }
            flg[i] = 1;
            for (int j = i * i; 0 < j && j <= n; j += i) {
                flg[j] = -1;
            }
        }
        return flg;
    }

    /**
     * 线性筛
     * <p>
     * 去掉埃氏筛中重复标记和合数
     * <p>
     * 复杂度：时间 O(n) 空间 O(n)
     */
    public int[] line(int n) {
        List<Integer> primes = new ArrayList<>();
        int[] flg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (flg[i] != -1) {
                flg[i] = 1;
                primes.add(i);
            }
            for (int prime : primes) {
                int x = i * prime;
                if (x < 0 || x > n) {
                    break;
                }
                flg[x] = -1;
                if (i % prime == 0) {
                    break;
                }
            }
        }
        return flg;
    }

}
