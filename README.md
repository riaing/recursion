递归思考诀窍

分析递归问题的关键点：

1, 设计递归体的目标，即递归到底求的是什么。
2, 划分问题，对子问题调用递归函数求出结果，然后利用这个结果求原问题的解（即如何利用递归的结果）
3, 考虑清楚递归退出的边界条件。（即何时退出递归过程）

参考 
http://www.nowamagic.net/librarys/veda/detail/2314
Recursion :  寻找基线条件，recursion式子。 
基线条件常包括：判断root是否为nul，有时得保证leave是否为leave（判断leave以下是否还有node（root.left/right ==null）如找一条从root到leave的path。 , 
 Due to its recursive definition, many binary tree problems can be solved by recursion!  

Same Tree (E), Symmetric Tree (E),
以上两题换汤不换药。 
T(p)和T(q)是same tree的条件：

(1) p和q等价（同时为NULL，或同时不为NULL且val相同）

(2) T(p->left)和T(q->left)为same tree，且T(p->right)和T(q->right)为same tree。

T(r)是symmetric的条件：实际就是判断T(r->left)和T(r-right)是否互为镜像的问题。假设p和q分别指向同一个tree内部两个镜像对称的节点，例如题目例子中的两个节点2

(1) p和q等价（同时为NULL，或同时不为NULL且val相同）

(2) T(p->left)和T(q->right)必须互为镜像，且T(p->right)和T(q->left)必须互为镜像。


 Maximum/Minimum Depth of Binary Tree (E),
Min: 其实跟Maximum Depth of Binary Tree非常类似，只是这道题因为是判断最小深度，所以必须增加一个叶子的判断（因为如果一个节点如果只有左子树或者右子树，我们不能取它左右子树中小的作为深度，因为那样会是0，我们只有在叶子节点才能判断深度，而在求最大深度的时候，因为一定会取大的那个，所以不会有这个问题）。

 Invert Binary Tree (E), 

 新!! 用一个helper class 来减少重复性递归。 
Balance Binary Tree (E), 
当x.right 与x.left的subtree平衡，判断abs ( depth(x.right) - depth(x.left))< 1, 然x才平衡 .    

类似求树的路径的题: 
这类题都是1，创立return typ；2，考虑基线条件： root ==null; leave是否有子节点（path end的条件） 3，recursion； 4， 把头如何接到整个list上完善list。 

Path Sum I/II  (E)（M）
Binary tree Paths （E）
Binary Tree Maximum Path Sum(H) 
Sum Root to Leaf Numbers (M) : path的题（但最优解法不是算出所有path，详见题和below） 

新！将每次递归都要被重复求的值作为参数传递，减少重复运算。 
Sum Root to Leaf Numbers (M) : 
Convert string to int: Integer.parseInt(string); 
当当前函数中的值要被下一次递归用到时， 作为参数传递下去。 
本题解法： 递归到该点的时候就把这点的值与前面的值乘以10，然后相加
需要如何保存前面的值？以参数传递的方式保存，以供下一层使用。
Count Complete Tree Nodes (M), 
java中求幂可用 << ， x << y  : x *( y ^2)。两边都得有数字。与其他加减乘除一起用时要加括号。
因为算左子树的leftheight时，就是当前节点的leftheight-1，所以把height作为节点传下去可以避免重复计算。注意：求左子树的rightheight时不能传当前节点的rightheight-1， 这时就得重新计算。 所以做左子树的递归时，参数应该写成（leftheight-1， -1）。 



新！  用array 的start， end 来递归：
Convert Sorted Array to Binary Search Tree (M), 
第一次用array的recursion题，不是改变array或者创建新的array，而是通过另一个函数，在原array上定义start和end、 
1. choose the middle one as root,
2. build left sub BST via left part array
3. build right sub BST via right part array
4. do this recursively.
用到部分array的recursion， 考虑ArrayOutOfBound Exception ： start > end 
Construct Binary Tree from Preorder/Postorder and Inorder Traversal (M),
如 Convert Sorted Array to Binary Search Tree ， 用array的recursion，给出array的start，end来递归。 
              1
        --------|-------
        2               3
    ----|----       ----|----
    4       5       6       7
 1245367 : preorder :  parent -> left ->right 
 4251637: inorder : left child -> parent -> right child
 4526731: postorder : left child -> right child -> parent node.
1，找根节点： Preoder，第一个元素一定是根节点，也就是1。
Postorder: 最后一个元素是跟节点。
2， 在inorder中找到根节点，count 跟节点前的数（*）为所有的left，后的数（#）为所有的right。
3，第二步中已知左右子树的节点数量，现在返回preorder，第一个node（根节点）后的（*）个数为所有的左子树节点，（*）后的所有数到结尾为右子树节点。因为preorder性质为p-l-r。 所以所有左子树节点的第一个为左子树的head，所有右子树的第一个为右子树的head。
Postorder中只是相反,根据 l-r-p的性质，前（*）个为所有左子树节点，（*+ #）个为所有右子树节点，前（*）个中的最后一个为左子树的head，所有右子树节点中的最后一个为右子树的head。同样根据性质，整个array的最后一个为整棵树的root。 
