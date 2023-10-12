package com.wjd.algorithm.stack.monotonic;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调递减栈：上一个更小元素的索引
 * 
 * @author weijiaduo
 * @since 2023/10/12
 */
public class PrevSmaller {

    /**
     * 正向遍历，不存在更小值时返回 -1
     *
     * @param arr 数组
     * @return 下一个更小元素索引数组
     */
    public int[] forward(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> decStack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 注意这里是 <=
            while (!decStack.isEmpty() && arr[i] <= arr[decStack.peek()]) {
                decStack.pop();
            }
            ans[i] = decStack.isEmpty() ? -1 : decStack.peek();
            decStack.push(i);
        }
        return ans;
    }

    /**
     * 逆向遍历，不存在更小值时返回 -1
     *
     * @param arr 数组
     * @return 下一个更小元素索引数组
     */
    public int[] backward(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> decStack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            // 注意这里是 <
            while (!decStack.isEmpty() && arr[i] < arr[decStack.peek()]) {
                int idx = decStack.pop();
                ans[idx] = i;
            }
            decStack.push(i);
        }
        return ans;
    }
    
}
