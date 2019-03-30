Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

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
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
       if(root.val == sum && root.left == null && root.right == null){ //基线条件，必须是为leave时，才返回true
            return true; 
        }
        return hasPathSum(root.left , sum - root.val) || hasPathSum(root.right, sum - root.val); 
        
    }
}
------------------用stack(iteration)来实现dfs-----------------------------------------------------------------------
  /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**用iteration来实现dfs的思想，需要借助stack。也是iterate每个node，如果不成，就把child放到stack里面去，那么下次循环就会用到child，
则遵循了dfs的思想。
Time complexity : the same as the recursion approach mathcal{O}(N).
Space complexity : {O}(N) since in the worst case, when the tree is completely unbalanced, e.g. each node has only one 
child node, we would keep all NN nodes in the stack. But in the best case (the tree is balanced), the height of the tree 
would be log(N). Therefore, the space complexity in this case would be O(log(N)).
*/

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        Deque<TreeNode> s = new LinkedList<TreeNode>();
        Deque<Integer> val = new LinkedList<Integer>();
        if (root == null) {
            return false;
        }
        s.push(root);
        val.push(sum);
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            int curVal = val.pop();
            // 找到满足条件的leave
            if (cur.val == curVal && cur.left == null && cur.right == null) {
                return true;
            }
            if (cur.left != null) {
                s.push(cur.left);
                val.push(curVal - cur.val);
            }
            if (cur.right != null) {
                s.push(cur.right);
                val.push(curVal - cur.val);
            }
        }
        return false;
    }
}
