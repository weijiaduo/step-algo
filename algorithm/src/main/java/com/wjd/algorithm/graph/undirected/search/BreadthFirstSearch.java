package com.wjd.algorithm.graph.undirected.search;

import com.wjd.algorithm.graph.Search;
import com.wjd.structure.graph.undirected.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先搜索
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class BreadthFirstSearch implements Search {

    /**
     * 标记数组
     */
    private final boolean[] marked;
    /**
     * 遍历到的顶点数量
     */
    private int count;

    public BreadthFirstSearch(Graph g, int s) {
        marked = new boolean[g.vs()];
        Arrays.fill(marked, false);
        bfs(g, s);
    }

    /**
     * 广度搜索
     *
     * @param g 无向图
     * @param v 当前顶点
     */
    private void bfs(Graph g, int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        marked[v] = true;
        count = 1;
        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int x : g.adj(w)) {
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
