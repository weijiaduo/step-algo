# step-algo

算法之行，始于足下，亲自写一遍各种经典的数据结构与算法。

[数据结构与算法介绍（待完善）](https://weijiaduo.github.io/)

## 1. 数组（Array）

### 1.1 排序（Sort）

- 交换排序
  - [冒泡排序（Bubble Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/BubbleSort.java)
  - [快速排序（Quick Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/QuickSort.java)
  - [3-向快排（3-Way Quick Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/Quick3WaySort.java)
- 选择排序
  - [选择排序（Select Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/SelectSort.java)
  - [堆排序（Heap Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/HeapSort.java)
- 插入排序
  - [插入排序（Insert Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/InsertSort.java)
  - [二分插入排序（Binary Insert Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/BinaryInsertSort.java)
  - [希尔排序（Shell Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/ShellSort.java)
- 归并排序
  - [归并排序（Merge Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/MergeSort.java)
- 分布排序
  - [桶排序（Bucket Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/BucketSort.java)
  - [计数排序（Count Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/CountSort.java)
  - [基数排序（Radix Sort）](algorithm/src/main/java/com/wjd/algorithm/sort/RadixSort.java)
- 混合排序
  - [Tim 排序（Tim Sort）]

### 1.2 二分查找（Binary Search）

- [等于（Equal）](algorithm/src/main/java/com/wjd/algorithm/binary/EqualSearch.java)
- [第一个等于（First Equal）](algorithm/src/main/java/com/wjd/algorithm/binary/FirstEqualSearch.java)
- [最后一个等于（Last Equal）](algorithm/src/main/java/com/wjd/algorithm/binary/LastEqualSearch.java)
- [第一个大于等于（First Great Equal）](algorithm/src/main/java/com/wjd/algorithm/binary/FirstGreatEqualSearch.java)
- [最后一个小于等于（Last Less Equal）](algorithm/src/main/java/com/wjd/algorithm/binary/LastLessEqualSearch.java)

## 2. 链表（List）

- [单向链表（Singly Linked List）](structure/src/main/java/com/wjd/structure/list/SinglyList.java)
- [双向链表（Doubly Linked List）](structure/src/main/java/com/wjd/structure/list/DoublyList.java)
- 循环链表（Circular）
  - [单向循环链表（Singly Circular Linked List）]
  - [双向循环链表（Doubly Circular Linked List）]
- [LRU 链表（LRU Linked List）](structure/src/main/java/com/wjd/structure/list/LRUList.java)
- 跳表（SkipList）
  - [整数跳表（Integer-SkipList）](structure/src/main/java/com/wjd/structure/skiplist/SimpleSkipList.java)
  - [数组跳表（Array-SkipList）](structure/src/main/java/com/wjd/structure/skiplist/ArraySkipList.java)
  - [链表跳表（Linked-SkipList）](structure/src/main/java/com/wjd/structure/skiplist/LinkedSkipList.java)

## 3. 队列（Queue）

- 单向队列（Singly）
  - [顺序单向队列（Array-Queue）](structure/src/main/java/com/wjd/structure/queue/ArrayQueue.java)
  - [链式单向队列（List-Queue）](structure/src/main/java/com/wjd/structure/queue/ListQueue.java)
- 双向队列（Doubly）
  - [顺序双向队列（Array-Deque）]
  - [链式双向队列（Linked-Deque）]
- 循环队列（Circular）
  - [单向循环队列（Circular Queue）](structure/src/main/java/com/wjd/structure/queue/CircularQueue.java)
  - [双向循环队列（Circular Deque）](structure/src/main/java/com/wjd/structure/queue/CircularDeque.java)

## 4. 栈（Stack）

- 栈（Stack）
  - [顺序栈（Array-Stack）](structure/src/main/java/com/wjd/structure/stack/ArrayStack.java)
  - [链式栈（List-Stack）](structure/src/main/java/com/wjd/structure/stack/ListStack.java)
- 单调栈（Monotonic Stack）
  - [下一个更大值（Next Greater）](algorithm/src/main/java/com/wjd/algorithm/stack/monotonic/NextGreater.java)
  - [上一个更大值（Prev Greater）](algorithm/src/main/java/com/wjd/algorithm/stack/monotonic/PrevGreater.java)
  - [下一个更小值（Next Smaller）](algorithm/src/main/java/com/wjd/algorithm/stack/monotonic/NextSmaller.java)
  - [上一个更小值（Prev Smaller）](algorithm/src/main/java/com/wjd/algorithm/stack/monotonic/PrevSmaller.java)

## 5. 树（Tree）

### 5.1 结构（Structure）

- [二叉树（Binary Tree）](structure/src/main/java/com/wjd/structure/tree/binary/BinaryTreeImpl.java)
- [二叉搜索树（Binary Search Tree，BST）](structure/src/main/java/com/wjd/structure/tree/bst/BSTreeImpl.java)
- 平衡二叉搜索树（Balanced Binary Search Tree）
  - [平衡二叉树（AVL）](structure/src/main/java/com/wjd/structure/tree/avl/AVLTreeImpl.java)
  - [双偏向红黑树（Both-Leaning RedBlack Tree）](structure/src/main/java/com/wjd/structure/tree/redblack/BLRBTree.java)
  - [左偏向红黑树（Left-Leaning RedBlack Tree）](structure/src/main/java/com/wjd/structure/tree/redblack/LLRBTree.java)
  - [伸展树（Splay Tree）]
- 线索二叉树（Thread Binary Tree）
  - [前序线索二叉树（Preorder Thread Tree）](structure/src/main/java/com/wjd/structure/tree/thread/PreorderThreadTree.java)
  - [中序线索二叉树（Inorder Thread Tree）](structure/src/main/java/com/wjd/structure/tree/thread/InorderThreadTree.java)
  - [后序线索二叉树（Postorder Thread Tree）](structure/src/main/java/com/wjd/structure/tree/thread/PostorderThreadTree.java)
- [哈夫曼树（Huffman Tree）](structure/src/main/java/com/wjd/structure/tree/huffman/HuffmanTreeImpl.java)
- 并查集（Union Find Set）
  - [数组并查集（Array-UFS）](structure/src/main/java/com/wjd/structure/tree/ufs/ArrayUnionFind.java)
  - [映射并查集（Map-UFS）](structure/src/main/java/com/wjd/structure/tree/ufs/MapUnionFind.java)
- 线段树（Segment Tree）
  - [数组线段树（Array-Segment Tree）](structure/src/main/java/com/wjd/structure/tree/segment/ArraySegmentTree.java)
  - [链表线段树（List-Segment Tree）](structure/src/main/java/com/wjd/structure/tree/segment/LinkSegmentTree.java)
- [树状数组/二叉索引树（Binary Index Tree，BIT）](structure/src/main/java/com/wjd/structure/tree/binaryindex/BinaryIndexTree.java)
- 多叉树（Multi Tree）
  - [B-树（B-Tree）](structure/src/main/java/com/wjd/structure/tree/btree/BTreeImpl.java)
  - [B+树（B Plus Tree）](structure/src/main/java/com/wjd/structure/tree/bplus/BPTreeImpl.java)

### 5.2 构建（Build）

- 二叉树（Binary Tree）
  - [层序构建（Build Level）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/build/LevelTreeBuilder.java)
  - [中序+后序构建（Inorder + Postorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/build/InAndPostTreeBuilder.java)
  - [中序+前序构建（Inorder + Preorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/build/PreAndInTreeBuilder.java)
- 二叉搜索树（Binary Search Tree，BST）
  - [前序构建（Preorder）](algorithm/src/main/java/com/wjd/algorithm/tree/bst/build/PreorderBSTreeBuilder.java)

### 5.3 遍历（Traverse）

- 层序遍历（Level Order）
  - [构建层序遍历（Build Level）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/levelorder/BuildLevelTraverse.java)
  - [简单层序遍历（Simple Level）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/levelorder/SimpleLevelTraverse.java)
  - [Z 形层序遍历（Zigzag Level）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/levelorder/ZigzagLevelTraverse.java)
- 前序遍历（Preorder）
  - [递归法（Recursive Preorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/preorder/RecursivePreorderTraverse.java)
  - [迭代法（Iterative Preorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/preorder/IteratePreorderTraverse.java)
  - [标记法（Mark Preorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/preorder/MarkPreorderTraverse.java)
  - [线索化（Thread Preorder）](structure/src/main/java/com/wjd/structure/tree/thread/PreorderThreadTree.java)
  - [Morris（Morris Preorder）]
- 中序遍历（Inorder）
  - [递归法（Recursive Inorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/inorder/RecursiveInorderTraverse.java)
  - [迭代法（Iterative Inorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/inorder/IterateInorderTraverse.java)
  - [标记法（Mark Inorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/inorder/MarkInorderTraverse.java)
  - [线索化（Thread Inorder）](structure/src/main/java/com/wjd/structure/tree/thread/InorderThreadTree.java)
  - [Morris（Morris Inorder）]
- 后序遍历（Postorder）
  - [递归法（Recursive Postorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/postorder/RecursivePostorderTraverse.java)
  - [迭代法（Iterative Postorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/postorder/IteratePostorderTraverse.java)
  - [标记法（Mark Postorder）](algorithm/src/main/java/com/wjd/algorithm/tree/binary/traverse/postorder/MarkPostorderTraverse.java)
  - [线索化（Thread Postorder）](structure/src/main/java/com/wjd/structure/tree/thread/PostorderThreadTree.java)
  - [Morris（Morris Postorder）]

## 6. 堆（Heap）

- [堆（Heap）](structure/src/main/java/com/wjd/structure/heap/binary/HeapImpl.java)
- [索引堆（Index Heap）](structure/src/main/java/com/wjd/structure/heap/index/IndexHeapImpl.java)
- [左倾堆（Leftist Heap）](structure/src/main/java/com/wjd/structure/heap/leftist/LeftistHeapImpl.java)
- [斜堆（Skew Heap）](structure/src/main/java/com/wjd/structure/heap/skew/SkewHeapImpl.java)
- [二项堆（Binomial Heap）]
- [斐波那契堆（Fibonacci Heap）]

## 7. 散列表（Hash Table）

- [开放寻址法散列表（Open Hash）](structure/src/main/java/com/wjd/structure/hashtable/LinkedHashTable.java)
- [拉链法散列表（Linked Hash）](structure/src/main/java/com/wjd/structure/hashtable/LinkedHashTable.java)

## 8. 图（Graph）

### 8.1 结构（Structure）

- 无向图（Graph）
  - [邻接矩阵（Adjacency Matrix）](structure/src/main/java/com/wjd/structure/graph/undirected/MatrixGraph.java)
  - [邻接表（Adjacency List）](structure/src/main/java/com/wjd/structure/graph/undirected/ListGraph.java)
  - [邻接多重表]
  - [符号无向图（Symbol Graph）](structure/src/main/java/com/wjd/structure/graph/undirected/SymbolGraphImpl.java)
- 加权无向图（Weighted Graph）
  - [邻接矩阵（Adjacency Matrix）](structure/src/main/java/com/wjd/structure/graph/undirected/MatrixWeightedGraph.java)
  - [邻接表（Adjacency List）](structure/src/main/java/com/wjd/structure/graph/undirected/ListWeightedGraph.java)
- 有向图（Digraph）
  - [邻接矩阵（Adjacency Matrix）](structure/src/main/java/com/wjd/structure/graph/directed/MatrixDigraph.java)
  - [邻接表（Adjacency List）](structure/src/main/java/com/wjd/structure/graph/directed/ListDigraph.java)
  - [十字链表]
  - [符号有向图（Symbol Graph）](structure/src/main/java/com/wjd/structure/graph/directed/SymbolDigraphImpl.java)
- 加权有向图（Weighted Digraph）
  - [邻接矩阵（Adjacency Matrix）](structure/src/main/java/com/wjd/structure/graph/directed/MatrixWeightedDigraph.java)
  - [邻接表（Adjacency List）](structure/src/main/java/com/wjd/structure/graph/directed/ListWeightedDigraph.java)

### 8.2 遍历（Search）

- 无向图（Graph）
  - [广度优先搜索（BFS）](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/search/BreadthFirstSearch.java)
  - [深度优先搜索（DFS）](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/search/DepthFirstSearch.java)
- 有向图（Digraph）
  - [广度优先搜索（BFS）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/search/DirectedBFS.java)
  - [深度优先搜索（DFS）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/search/DirectedDFS.java)
  - [前序遍历（Pre Order）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/order/PreDFO.java)
  - [后序遍历（Post Order）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/order/PostDFO.java)
  - [逆后序遍历（Reverse Post Order）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/order/ReversePostDFO.java)
  - [拓扑排序（Topological）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/order/Topological.java)

### 8.3 可达性（Paths）和连通性（Connected）

- 无向图（Graph）
  - 可达性路径（Paths）
    - [基于 DF 的路径（DFP）](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/path/DepthFirstPaths.java)
    - [基于 BF 的路径（BFP）](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/path/BreadthFirstPaths.java)
  - [连通分量（Connected）](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/connected/DepthFirstConnected.java)
  - [环检测（Circle Detect）](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/cycle/UndirectedCycle.java)
  - [二分图（Bipartite）](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/bipartite/BipartiteImpl.java)
- 有向图（Digraph）
  - 可达性路径（Paths）
    - [基于 DFS 的路径（DFP）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/path/DirectedDFP.java)
    - [基于 BFS 的路径（BFP）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/path/DirectedBFP.java)
  - [强连通分量（Kosaraju）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/connected/KosarajuStrongConnected.java)
  - [环检测（Circle Detect）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/cycle/DirectedCycle.java)
  - [出入度（Degree）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/degree/Degree.java)

### 8.4 最小生成树（MST）

- 加权无向图（Weighted Graph）
  - [Prim](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/mst/PrimMST.java)
  - [Kruskal](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/mst/KruskalMST.java)

### 8.5 最短路径（Shortest Paths）

- 无向图（Graph）
  - [基于 BF 的最短路径（BF-SP）](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/path/BreadthFirstPaths.java)
- 加权无向图（Weighted Graph）
  - [Dijkstra](algorithm/src/main/java/com/wjd/algorithm/graph/undirected/path/shortest/DijkstraSP.java)
- 有向图（Digraph）
  - [基于 BF 的最短路径（BF-SP）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/path/DirectedBFP.java)
- 加权有向图（Weighted Digraph）
  - [Dijkstra（有环无负权边）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/path/shortest/DijkstraSP.java)
  - [Acyclic-SP（无环有负权边）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/path/shortest/AcyclicSP.java)
  - [Bellman-Ford（无负权重环）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/path/shortest/BellmanFordSP.java)

### 8.6 关键路径（Critical Path）

- 加权有向图（Weighted Digraph）
  - [Acyclic-LP（最长路径）](algorithm/src/main/java/com/wjd/algorithm/graph/directed/path/longest/AcyclicLP.java)

## 9. 字符串（String）

### 9.1 排序（Sort）

- [低位优先排序（LSD）](algorithm/src/main/java/com/wjd/algorithm/strings/sorts/LSD.java)
- [高位优先排序（MSD）](algorithm/src/main/java/com/wjd/algorithm/strings/sorts/MSD.java)
- [三向快速排序（3-Way Quick Sort）](algorithm/src/main/java/com/wjd/algorithm/strings/sorts/Quick3Way.java)

### 9.2 字典树（Trie）

- [字母字典树（Letter-Trie）](structure/src/main/java/com/wjd/structure/tree/trie/LetterTrie.java)
- [字符字典树（Character-Trie）](structure/src/main/java/com/wjd/structure/tree/trie/CharacterTrie.java)
- [三向字典树（TernaryTrie）](structure/src/main/java/com/wjd/structure/tree/trie/TernaryTrie.java)

### 9.3 匹配（Match）

- [Brute-Force, BF](algorithm/src/main/java/com/wjd/algorithm/strings/search/BruteForceSearch.java)
- [Rabin-Karp, RK](algorithm/src/main/java/com/wjd/algorithm/strings/search/RabinKarpSearch.java)
- [Boyer-Moore, BM](algorithm/src/main/java/com/wjd/algorithm/strings/search/BoyerMooreSearch.java)
- Knuth-Morris-Pratt, KMP
  - [KMP-Pmt](algorithm/src/main/java/com/wjd/algorithm/strings/search/PmtKMPSearch.java)
  - [KMP-Next](algorithm/src/main/java/com/wjd/algorithm/strings/search/NextKMPSearch.java)
  - [KMP-DFA](algorithm/src/main/java/com/wjd/algorithm/strings/search/DFAKMPSearch.java)
- [AhoCorasick](algorithm/src/main/java/com/wjd/algorithm/strings/regex/AhoCorasickPattern.java)

### 9.4 正则表达式（Regex）

- [NFA](algorithm/src/main/java/com/wjd/algorithm/strings/regex/NFAPattern.java)
