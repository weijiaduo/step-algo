package com.wjd.algorithm.graph.directed.build.impl;

import com.wjd.algorithm.graph.directed.build.WeightedDigraphBuilder;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;
import com.wjd.structure.graph.directed.impl.ListWeightedDigraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 * 基于文件生成加权有向图
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public class FileWeightedDigraphBuilder implements WeightedDigraphBuilder {

    /**
     * 文件目录
     */
    private static final String DIR = Objects.requireNonNull(WeightedDigraphBuilder.class.getResource("")).getPath();
    /**
     * 文件名称
     */
    private final String fileName;

    public FileWeightedDigraphBuilder(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public WeightedDigraph build() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(DIR, fileName)))) {
            int vs = Integer.parseInt(reader.readLine());
            WeightedDigraph wdg = new ListWeightedDigraph(vs);
            int es = Integer.parseInt(reader.readLine());
            for (int i = 0; i < es; i++) {
                String[] ts = reader.readLine().trim().split("\\s+");
                int v = Integer.parseInt(ts[0]);
                int w = Integer.parseInt(ts[1]);
                double weight = Double.parseDouble(ts[2]);
                DirectedEdge edge = new DirectedEdge(v, w, weight);
                wdg.addEdge(edge);
            }
            return wdg;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
