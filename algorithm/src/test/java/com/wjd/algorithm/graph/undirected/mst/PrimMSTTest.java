package com.wjd.algorithm.graph.undirected.mst;

import com.wjd.algorithm.graph.undirected.build.FileWeightedGraphBuilder;
import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimMSTTest {

    @Test
    void edges() {
        WeightedGraph wg = new FileWeightedGraphBuilder("tinyEWG.txt").build();
        MinSpanningTree mst = new PrimMST(wg);

        Iterable<Edge> edges = mst.edges();
        List<Edge> actual = new ArrayList<>();
        for (Edge e : edges) {
            actual.add(e);
        }
        assertEquals("[(0, 7, 0.16), (1, 7, 0.19), (0, 2, 0.26), (2, 3, 0.17), (5, 7, 0.28), (4, 5, 0.35), (6, 2, 0.40)]", actual.toString());
    }

    @Test
    void weight() {
        WeightedGraph wg = new FileWeightedGraphBuilder("tinyEWG.txt").build();
        MinSpanningTree mst = new PrimMST(wg);
        assertEquals(1.81, mst.weight());
    }

}