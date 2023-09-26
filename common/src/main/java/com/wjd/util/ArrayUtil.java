package com.wjd.util;

/**
 * 数组工具类
 *
 * @author weijiaduo
 * @since 2022/7/9
 */
public final class ArrayUtil {

    private ArrayUtil() {
    }

    public static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int j : arr) {
            sb.append(j).append(" ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }

    public static void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : arr) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void print(boolean[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] booleans : arr) {
            for (boolean aBoolean : booleans) {
                sb.append(aBoolean).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void print(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : arr) {
            for (char anChar : chars) {
                sb.append(anChar).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void print(Object[] arr) {
        StringBuilder sb = new StringBuilder();
        for (Object j : arr) {
            sb.append(j.toString()).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    public static void print(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (char j : arr) {
            sb.append(j).append(" ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }

}
