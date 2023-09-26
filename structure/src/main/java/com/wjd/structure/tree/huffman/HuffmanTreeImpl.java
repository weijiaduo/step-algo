package com.wjd.structure.tree.huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 哈夫曼编码
 *
 * @author weijiaduo
 * @since 2023/2/23
 */
public class HuffmanTreeImpl implements HuffmanTree {

    /**
     * 左连接的编码值
     */
    private final char leftCode;
    /**
     * 右连接的编码值
     */
    private final char rightCode;
    /**
     * 关键字-编码串的映射关系
     */
    private Map<String, String> codeMap;
    /**
     * 根节点
     */
    private HTNode root;

    public HuffmanTreeImpl() {
        this('0', '1');
    }

    public HuffmanTreeImpl(char leftCode, char rightCode) {
        this.leftCode = leftCode;
        this.rightCode = rightCode;
    }

    /**
     * 哈夫曼树构建器
     *
     * @param weightMap 关键字-权重的映射关系
     * @return 哈夫曼树
     */
    public static HuffmanTreeImpl build(Map<String, Integer> weightMap) {
        if (weightMap == null || weightMap.isEmpty()) {
            return null;
        }

        PriorityQueue<HTNode> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        for (Map.Entry<String, Integer> e : weightMap.entrySet()) {
            queue.offer(new HTNode(e.getKey(), e.getValue()));
        }
        while (queue.size() > 1) {
            HTNode l = queue.poll();
            HTNode r = queue.poll();
            HTNode p = new HTNode(null, l.weight + r.weight);
            p.left = l;
            p.right = r;
            queue.offer(p);
        }

        HuffmanTreeImpl tree = new HuffmanTreeImpl();
        tree.root = queue.poll();
        return tree;
    }

    @Override
    public String getCode(String key) {
        if (codeMap == null) {
            // 保证关键字编码串已经生成
            codeMap = new HashMap<>();
            collect(root, new StringBuilder());
        }
        return codeMap.get(key);
    }

    /**
     * 收集叶子节点的编码映射集合
     *
     * @param h  当前节点
     * @param sb 当前字符串
     */
    private void collect(HTNode h, StringBuilder sb) {
        if (h == null) {
            return;
        }

        // 叶子节点
        if (h.left == null && h.right == null) {
            codeMap.put(h.key, sb.toString());
            return;
        }

        // 遍历左子树
        sb.append(leftCode);
        collect(h.left, sb);
        sb.deleteCharAt(sb.length() - 1);

        // 遍历右子树
        sb.append(rightCode);
        collect(h.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

}
