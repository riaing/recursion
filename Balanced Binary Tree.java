Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth 
of the two subtrees of every node never differ by more than 1.

 
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
    class TreeInfo{   // helper class ,同时contain判断题目所需的所有infor
        boolean isBalanced; 
        int height; 
        public TreeInfo(int height , boolean isBalanced){
            this.isBalanced = isBalanced;
            this.height = height; 
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        return helper(root).isBalanced;  //用一个type 为TreeInfo 的method
    }
    
    private TreeInfo helper(TreeNode root){
        if(root == null){
            return new TreeInfo(0, true);  //return的必须是一个new的object
        }
        TreeInfo leftTree = helper(root.left);  //recursion
        TreeInfo rightTree = helper(root.right); 
        
        int height = Math.max(leftTree.height, rightTree.height) +1; 
        boolean isBalanced = Math.abs(leftTree.height - rightTree.height)<=1 &&leftTree.isBalanced && rightTree.isBalanced; 
        // 计算两个返回值 
        
        return  new TreeInfo(height, isBalanced);  
    }
    
    
} 
 
 // 这种做法要Left, right 分别要recursion 两遍，来求isbalanced和height。 更好的方法是用一个helper(TreeInfo) class，里面存有要recursion的两个field；
 用一个type为此class的辅助method(helper) 来进行recursion。 在主method call这个辅助method即可。 
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        } 
       //way 1 
      /*  int differ = Math.abs(DepthDiffer(root.right)-DepthDiffer(root.left));
        if(differ > 1) {
            return false; 
        }
        return isBalanced(root.left) && isBalanced(root.right); */
        
        //way 2 
        return Math.abs(height(root.right)-height(root.left))<=1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int height(TreeNode p){
        if(p == null ){
            return 0;
        }
        return Math.max(height(p.right), height(p.left))+1; 
    }
}
