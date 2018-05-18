You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11


---- Pass the return value: using DFS ------

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        
        int numPath = 0;
        if (root == null) {
            return numPath;
        }
// Traverse every node and add the total number of paths from this node.       
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.offer(root);
        while(nodes.size() != 0) {
            TreeNode node = nodes.poll();
            if (node.left != null) {
                nodes.offer(node.left);
            }
            if(node.right != null) {
                nodes.offer(node.right);
            }
            numPath = helper(node, sum, numPath); 
        }
        return numPath;
        
    }
    private int helper(TreeNode node, int sum, int numPath) {
        if (node == null) {
            return numPath;
        }
        numPath = helper(node.left, sum - node.val, numPath);
        numPath = helper(node.right, sum - node.val, numPath);
          // Must add the current path if it exist. 
        return node.val == sum ? numPath + 1 : numPath; 
    } 
}

-------- Pass the return value: using recursion ------
      /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        // initialize numPath everytime starting from a node. 
        int numPath = 0;
        if (root == null) {
            return numPath;
        }  
       
        // All path from the root + all path from left and right nodes. 
        return helper(root, sum, numPath) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    private int helper(TreeNode node, int sum, int numPath) {
        if (node == null) {
            return numPath;
        }
        numPath = helper(node.left, sum - node.val, numPath);
        numPath = helper(node.right, sum - node.val, numPath);
        return node.val == sum ? numPath + 1 : numPath; 
    }
}
      
