package com.wjd.algorithm.math;

/**
 * 矩阵幂
 *
 * @author weijiaduo
 * @since 2023/12/30
 */
public class MatrixPower {

    /**
     * 矩阵的幂次方
     *
     * @param a 矩阵
     * @param n 幂次，n > 0
     * @return 矩阵幂
     */
    public long[][] pow(long[][] a, int n) {
        long[][] ans = new long[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        long[][] e = a;
        while (n != 0) {
            if ((n & 1) == 1) {
                ans = multiply(ans, e);
            }
            e = multiply(e, e);
            n >>>= 1;
        }
        return ans;
    }

    /**
     * 矩阵乘法
     *
     * @param a 矩阵
     * @param b 矩阵
     * @return 矩阵相乘结果
     */
    private long[][] multiply(long[][] a, long[][] b) {
        int m = a.length, n = b[0].length;
        long[][] c = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < a[i].length; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

}
