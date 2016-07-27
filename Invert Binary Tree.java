Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1




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
    public TreeNode invertTree(TreeNode root) {
      if(root == null){  //这里base case只有一个， 因为就算是leave.right/left也是一样
          return null;
      }
      TreeNode tmp = root.left;
      root.left = root.right; 
      root.right = tmp; 
      
      invertTree(root.left);
      invertTree(root.right);
      return root;
        
    }
}
