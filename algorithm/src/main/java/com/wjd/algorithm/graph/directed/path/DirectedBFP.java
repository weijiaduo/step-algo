package com.wjd.algorithm.graph.directed.path;

import com.wjd.algorithm.graph.Paths;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于广度优先搜索实现的路径
 *
 * @author weijiaduo
 * @since 2023/3/11
 */
public class DirectedBFP implements Paths {

    /**
     * 边的终点->起点
     */
    private final int[] edgeFrom;
    /**
     * 标记数组
     */
    private final boolean[] marked;

    /**
     * @param dg 有向图
     * @param s  指定起点
     */
    public DirectedBFP(Digraph dg, int s) {
        edgeFrom = new int[dg.vs()];
        Arrays.fill(edgeFrom, -1);
        marked = new boolean[dg.vs()];
        Arrays.fill(marked, false);
        bfs(dg, s);
    }

    /**
     * 广度搜索
     *
     * @param dg 有向图
     * @param v  当前顶点
     */
    private void bfs(Digraph dg, int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        marked[v] = true;
        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int x : dg.adj(w)) {
                if (marked[x]) {
                    continue;
                }
                edgeFrom[x] = w;
                queue.offer(x);
                marked[x] = true;
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int x = v; x != -1; x = edgeFrom[x]) {
            stack.push(x);
        }
        return stack;
    }

}
