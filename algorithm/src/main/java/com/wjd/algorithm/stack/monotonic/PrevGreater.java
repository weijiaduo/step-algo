package com.wjd.algorithm.stack.monotonic;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调递增栈：上一个更大元素的索引
 *
 * @author weijiaduo
 * @since 2023/10/12
 */
public class PrevGreater {

    /**
     * 正向遍历，不存在更大值时返回 -1
     *
     * @param arr 数组
     * @return 上一个更大元素索引数组
     */
    public int[] forward(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> incStack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 注意这里是 >=
            while (!incStack.isEmpty() && arr[i] >= arr[incStack.peek()]) {
                incStack.pop();
            }
            ans[i] = incStack.isEmpty() ? -1 : incStack.peek();
            incStack.push(i);
        }
        return ans;
    }

    /**
     * 逆向遍历，不存在更大值时返回 -1
     *
     * @param arr 数组
     * @return 上一个更大元素索引数组
     */
    public int[] backward(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> incStack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            // 注意这里是 >
            while (!incStack.isEmpty() && arr[i] > arr[incStack.peek()]) {
                int idx = incStack.pop();
                ans[idx] = i;
            }
            incStack.push(i);
        }
        return ans;
    }

}
