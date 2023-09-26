package com.wjd.algorithm.graph.undirected.build.impl;

import com.wjd.algorithm.graph.undirected.build.SymbolGraphBuilder;
import com.wjd.structure.graph.undirected.SymbolGraph;
import com.wjd.structure.graph.undirected.impl.SymbolGraphImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * 基于文件的无向符号图构建器
 *
 * @author weijiaduo
 * @since 2023/3/10
 */
public class FileSymbolGraphBuilder implements SymbolGraphBuilder {

    /**
     * 文件目录
     */
    private static final String DIR = Objects.requireNonNull(SymbolGraphBuilder.class.getResource("")).getPath();
    /**
     * 文件名称
     */
    private final String fileName;

    public FileSymbolGraphBuilder(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SymbolGraph build() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(DIR, fileName)))) {
            Set<String> keys = new HashSet<>();
            List<String[]> edges = new ArrayList<>();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] ts = line.trim().split("\\s+");
                edges.add(ts);
                Collections.addAll(keys, ts);
            }
            SymbolGraph sg = new SymbolGraphImpl(keys);
            for (String[] edge : edges) {
                sg.addEdge(edge[0], edge[1]);
            }
            return sg;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
