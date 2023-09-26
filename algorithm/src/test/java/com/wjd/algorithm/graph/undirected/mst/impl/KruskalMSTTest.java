package com.wjd.algorithm.graph.undirected.mst.impl;

import com.wjd.algorithm.graph.undirected.build.impl.FileWeightedGraphBuilder;
import com.wjd.algorithm.graph.undirected.mst.MinSpanningTree;
import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KruskalMSTTest {

    @Test
    void edges() {
        WeightedGraph wg = new FileWeightedGraphBuilder("tinyEWG.txt").build();
        MinSpanningTree mst = new KruskalMST(wg);

        Iterable<Edge> edges = mst.edges();
        List<Edge> actual = new ArrayList<>();
        for (Edge e : edges) {
            actual.add(e);
        }
        assertEquals("[(0, 7, 0.16), (2, 3, 0.17), (1, 7, 0.19), (0, 2, 0.26), (5, 7, 0.28), (4, 5, 0.35), (6, 2, 0.40)]", actual.toString());
    }

    @Test
    void weight() {
        WeightedGraph wg = new FileWeightedGraphBuilder("tinyEWG.txt").build();
        MinSpanningTree mst = new KruskalMST(wg);
        assertEquals(1.81, mst.weight());
    }

}