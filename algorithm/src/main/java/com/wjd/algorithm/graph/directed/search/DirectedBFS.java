package com.wjd.algorithm.graph.directed.search;

import com.wjd.algorithm.graph.Search;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先搜索
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class DirectedBFS implements Search {

    /**
     * 标记数组
     */
    private final boolean[] marked;
    /**
     * 遍历到的顶点数量
     */
    private int count;

    public DirectedBFS(Digraph dg, int s) {
        marked = new boolean[dg.vs()];
        Arrays.fill(marked, false);
        bfs(dg, s);
    }

    /**
     * 广度搜索
     *
     * @param dg 无向图
     * @param v  当前顶点
     */
    private void bfs(Digraph dg, int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        marked[v] = true;
        count = 1;
        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int x : dg.adj(w)) {
                if (marked[x]) {
                    continue;
                }
                queue.offer(x);
                marked[x] = true;
                count++;
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }

}
