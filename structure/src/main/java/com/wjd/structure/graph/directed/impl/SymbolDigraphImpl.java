package com.wjd.structure.graph.directed.impl;

import com.wjd.structure.graph.directed.Digraph;
import com.wjd.structure.graph.directed.SymbolDigraph;

import java.util.*;

/**
 * 符号有向图实现
 *
 * @author weijiaduo
 * @since 2023/3/16
 */
public class SymbolDigraphImpl implements SymbolDigraph {

    /**
     * 名称->索引
     */
    private final Map<String, Integer> idxMap;
    /**
     * 索引->名称
     */
    private final String[] keys;
    /**
     * 有向图
     */
    private final Digraph graph;

    public SymbolDigraphImpl(Set<String> keySet) {
        int n = keySet.size();
        idxMap = new HashMap<>();
        keys = new String[n];
        int k = 0;
        for (String name : keySet) {
            idxMap.put(name, k);
            keys[k++] = name;
        }
        graph = new ListDigraph(n);
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
        return keys[idx];
    }

    @Override
    public Digraph graph() {
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
