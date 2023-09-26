package com.wjd.algorithm.graph.directed.build.impl;

import com.wjd.algorithm.graph.directed.build.DigraphBuilder;
import com.wjd.structure.graph.directed.Digraph;
import com.wjd.structure.graph.directed.impl.ListDigraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 * 基于文件生成有向图
 *
 * @author weijiaduo
 * @since 2023/3/8
 */
public class FileDigraphBuilder implements DigraphBuilder {

    /**
     * 文件目录
     */
    private static final String DIR = Objects.requireNonNull(DigraphBuilder.class.getResource("")).getPath();
    /**
     * 文件名称
     */
    private final String fileName;

    public FileDigraphBuilder(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Digraph build() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(DIR, fileName)))) {
            int vs = Integer.parseInt(reader.readLine());
            Digraph dg = new ListDigraph(vs);
            int es = Integer.parseInt(reader.readLine());
            for (int i = 0; i < es; i++) {
                String[] ts = reader.readLine().trim().split("\\s+");
                dg.addEdge(Integer.parseInt(ts[0]), Integer.parseInt(ts[1]));
            }
            return dg;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
