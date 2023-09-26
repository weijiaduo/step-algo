package com.wjd.algorithm.tree.thread.build;

import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.thread.ThreadNode;

/**
 * 线索二叉树构建
 *
 * @author weijiaduo
 * @since 2022/12/3
 */
public interface ThreadBuilder {

    ThreadNode build(TreeNode root);

}
