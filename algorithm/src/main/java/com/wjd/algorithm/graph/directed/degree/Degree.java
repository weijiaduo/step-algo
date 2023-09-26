package com.wjd.algorithm.graph.directed.degree;

import com.wjd.structure.graph.directed.Digraph;

/**
 * 顶点的出入度
 *
 * @author weijiaduo
 * @since 2023/4/13
 */
public class Degree {

    /**
     * in degree
     */
    private final int[] ins;
    /**
     * out degree
     */
    private final int[] outs;

    public Degree(Digraph dg) {
        ins = new int[dg.vs()];
        outs = new int[dg.vs()];
        for (int v = 0; v < dg.vs(); v++) {
            for (int w : dg.adj(v)) {
                outs[v]++;
                ins[w]++;
            }
        }
    }

    /**
     * In degree.
     *
     * @param v the v
     * @return the int
     */
    public int inDegree(int v) {
        return ins[v];
    }

    /**
     * Out degree.
     *
     * @param v the v
     * @return the int
     */
    public int outDegree(int v) {
        return outs[v];
    }

}
