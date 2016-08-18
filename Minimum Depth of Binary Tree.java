Given a binary tree, find its minimum depth.
还可用BFS解。 

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

其实跟Maximum Depth of Binary Tree非常类似，只是这道题因为是判断最小深度，所以必须增加一个叶子的判断
（因为如果一个节点如果只有左子树或者右子树，我们不能取它左右子树中小的作为深度，因为那样会是0，我们只有在叶子节点才能判断深度
，而在求最大深度的时候，因为一定会取大的那个，所以不会有这个问题）。


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null ){
            return 0; 
        }
        if(root.left == null){
            return minDepth(root.right) +1 ;
        }
        if(root.right == null){
            return minDepth(root.left) +1 // 基线条件都要return
        }
        return 1+ Math.min(minDepth(root.left), minDepth(root.right));
    }
}

相似的题像是 find the max value of a binary tree: 

public int maxValueInBinaryTree(TreeNode root) {
	if(root == null){
		Return 0 ;
	}
	if(root.left == null && root.right ==null) { 如果为根节点
		Return root.val;
	}
	if(root.left != null && root.right == null){ 如果只有左子树
		Return Math.max(root.val , maxValueInBinaryTree(root.left);
	}
	if(root.left == null && root.right != null){ only has right tree
		Return Math.max(root.val , maxValueInBinaryTree(root.right );
	}
	//如果左右子树都有
	Int cur = Math.max(maxValueInBinaryTree(root.left),  maxValueInBinaryTree(root.right));
	Return Math.max(root.val , cur);
}
