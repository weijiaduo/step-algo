package com.wjd.structure.graph.undirected.impl;

import com.wjd.structure.graph.undirected.Graph;
import com.wjd.structure.graph.undirected.SymbolGraph;

import java.util.*;

/**
 * 无向符号图实现
 *
 * @author weijiaduo
 * @since 2023/3/8
 */
public class SymbolGraphImpl implements SymbolGraph {

    /**
     * 名称->索引
     */
    private final Map<String, Integer> idxMap;
    /**
     * 索引->名称
     */
    private final String[] keys;
    /**
     * 无向图
     */
    private final Graph graph;

    public SymbolGraphImpl(Set<String> keySet) {
        int n = keySet.size();
        idxMap = new HashMap<>();
        keys = new String[n];
        int k = 0;
        for (String name : keySet) {
            idxMap.put(name, k);
            keys[k++] = name;
        }
        graph = new ListGraph(n);
    }

    @Override
    public boolean contains(String key) {
        return idxMap.containsKey(key);
    }

    @Override
    public int idx(String key) {
        return idxMap.getOrDefault(key, -1);
    }

    @Override
    public String key(int idx) {
        if (idx < 0 || idx > keys.length) {
            return null;
        }
        return keys[idx];
    }

    @Override
    public Graph graph() {
        return graph;
    }

    @Override
    public void addEdge(String v, String w) {
        graph.addEdge(idxMap.get(v), idxMap.get(w));
    }

    @Override
    public boolean hasEdge(String v, String w) {
        return graph.hasEdge(idxMap.get(v), idxMap.get(w));
    }

    @Override
    public Iterable<String> adj(String v) {
        Iterable<Integer> it = graph().adj(idxMap.get(v));
        List<String> list = new ArrayList<>();
        for (Integer index : it) {
            list.add(keys[index]);
        }
        return list;
    }

}
