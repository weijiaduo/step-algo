package com.wjd.algorithm.graph.undirected.build.impl;

import com.wjd.algorithm.graph.undirected.build.GraphBuilder;
import com.wjd.structure.graph.undirected.Graph;
import com.wjd.structure.graph.undirected.impl.ListGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 * 基于文件生成图
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class FileGraphBuilder implements GraphBuilder {

    /**
     * 文件目录
     */
    private static final String DIR = Objects.requireNonNull(GraphBuilder.class.getResource("")).getPath();
    /**
     * 文件名称
     */
    private final String fileName;

    public FileGraphBuilder(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Graph build() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(DIR, fileName)))) {
            int vs = Integer.parseInt(reader.readLine());
            Graph g = new ListGraph(vs);
            int es = Integer.parseInt(reader.readLine());
            for (int i = 0; i < es; i++) {
                String[] ts = reader.readLine().trim().split("\\s+");
                g.addEdge(Integer.parseInt(ts[0]), Integer.parseInt(ts[1]));
            }
            return g;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
