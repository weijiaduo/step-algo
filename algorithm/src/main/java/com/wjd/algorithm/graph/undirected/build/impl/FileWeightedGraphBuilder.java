package com.wjd.algorithm.graph.undirected.build.impl;

import com.wjd.algorithm.graph.undirected.build.WeightedGraphBuilder;
import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;
import com.wjd.structure.graph.undirected.impl.ListWeightedGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 * 基于文件生成加权无向图
 *
 * @author weijiaduo
 * @since 2023/3/12
 */
public class FileWeightedGraphBuilder implements WeightedGraphBuilder {

    /**
     * 文件目录
     */
    private static final String DIR = Objects.requireNonNull(WeightedGraphBuilder.class.getResource("")).getPath();
    /**
     * 文件名称
     */
    private final String fileName;

    public FileWeightedGraphBuilder(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public WeightedGraph build() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(DIR, fileName)))) {
            int vs = Integer.parseInt(reader.readLine());
            WeightedGraph wg = new ListWeightedGraph(vs);
            int es = Integer.parseInt(reader.readLine());
            for (int i = 0; i < es; i++) {
                String[] ts = reader.readLine().trim().split("\\s+");
                Edge edge = new Edge(Integer.parseInt(ts[0]), Integer.parseInt(ts[1]), Double.parseDouble(ts[2]));
                wg.addEdge(edge);
            }
            return wg;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
