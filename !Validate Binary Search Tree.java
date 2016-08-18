Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 class ResultType{ //创新class存储每层需要的min，max，是否为bst
     boolean is_bst;
     int min;
     int max;
     public ResultType(boolean is_bst, int min, int max){
         this.is_bst = is_bst;
         this.min = min;
         this.max = max; 
     }
 }
 
public class Solution {
    public boolean isValidBST(TreeNode root){
        ResultType r= dfs(root);
        return r.is_bst; 
    }

    private ResultType dfs(TreeNode root) {
        if(root == null){
            return new ResultType(true, Integer.MAX_VALUE, Integer.MIN_VALUE);// 注意这里的min为MAX_VALUE；
        }
        //get left and right tree's info
        ResultType left = dfs(root.left); //先进行递归
        ResultType right = dfs(root.right); 
        
        //determine if is a BST 
        判断root.left ！=null有必要，不然left.max为MIN，则不进这个if。
        if((root.left != null && root.val <= left.max) || (root.right != null && root.val >=right.min)){
            return new ResultType (false, 0, 0); 
        }//若果左子树的最大值大于root，不为bst； 右子树的最小值小于root，不为bst
        
        if(!left.is_bst || !right.is_bst){   // if is_bst is false then minValue and maxValue are useless
            return new ResultType(false, 0 , 0); 
        }
        
        return new ResultType(true, Math.min(root.val, left.min), Math.max(root.val, right.max));
        //返回这棵树的最小值，最大值
    }
}
