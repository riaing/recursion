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

----------------------- #6 用一个 list 来存到此 node 为止的 path：此方法可 apply 到其他题，记！另外可看#3，也很巧妙 -----------------------
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/*
用一个 path 存到 node 的 path 上的所有 node。每层从 node 往上推，找 sum
对每个 node都要遍历一遍它的 path。最后一层的path 是 lgn（就是 depth），所以算大 O 是 N*lgN
如果是 unbalance tree，就是1+2+3+4+...+n = n*2
所以平均 O(NlgN), 最坏 O(N*2)

Space: unbalanced Tree 的话 list 要记所有的 node，所以是 O（n）， recursion stack 也是 O（n）。总共就 o(n)
*/
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        int total = 0;
        if (root == null) {
            return total;
        }
        return helper(root, targetSum, new ArrayList<Integer>());
    }
    
    public int helper(TreeNode root, int targetSum, List<Integer> path) {
        int total = 0;
        if (root == null) {
            return total;
        }
        if (root.val == targetSum) {
            total++;
        }
        int sum = targetSum;
        for (int i = path.size() - 1; i >=0; i--) { 
            if (root.val + path.get(i) == sum) {
                total++;
            }
            sum -= path.get(i);
        }
        path.add(root.val);
        total = total + helper(root.left, targetSum, path) + helper(root.right, targetSum, path);
        path.remove(path.size()-1);
        return total; 
    }
}      
 ------------------------------------------------------------------------------------------     
一下所有 solution 的时间分析
Recursion and DFS: 
Time complexity: O(n^2)

Space complexity: O(n)
      
 --------------#5  3.30.19 update: 对于每一个node，找以他为root的path：recursion --------------
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
        if (root == null) {
            return 0;
        }
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
      // Given the current node, find all path of it. 
    private int helper(TreeNode node, int sum) {
        int numPathOfCurrentNode = 0; 
        if (node == null) {
            return numPathOfCurrentNode;
        }
           // Diff from previous 2 SOL: Now just add all path from the current node. 
        // Even though node.val == sum, still has to continue for child node. 
        return (node.val == sum ? numPathOfCurrentNode + 1 : numPathOfCurrentNode) + helper(node.left, sum - node.val) + helper(node.right, sum - node.val);
    }
}

--------------#4 - 3.30.19 update: 对于每一个node，找以他为root的path：iteration. 和以上一样的helper function ------------------
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
            // Add all path from current node. 
            numPath = helper(node, sum) + numPath;
        }
        return numPath;
        
    }
    private int helper(TreeNode node, int sum) {
        int numPathOfCurrentNode = 0; 
        if (node == null) {
            return numPathOfCurrentNode;
        }
    
        // Even though node.val == sum, still has to continue for child node. 
        return (node.val == sum ? numPathOfCurrentNode + 1 : numPathOfCurrentNode) + helper(node.left, sum - node.val) + helper(node.right, sum - node.val);
    }
}
---------------#3 - 3.30.19 recursion：这里提供了一个不同的思路，我们通过传递一个boolean，来确保是path----------------------------
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
        return helper(root, sum, false);
    }
    
    // 用一个flag来保证是一条path。 flag是指上一层的node是否被用，
    private int helper(TreeNode root, int sum, boolean must_choose_root) {
        int numPath = 0; 
        if (root == null) {
            return numPath;
        }
        if (root.val == sum) {
            numPath = 1;
        }
        
        // 如果上一层的node用了并且还没达到sum，这层也必须用node
        if (must_choose_root) {
            return numPath + helper(root.left, sum - root.val, true) + helper(root.right, sum - root.val, true);
        } 
        //如果上一层没有用node，那么这层可以用node，也可以不用
        else {
          return numPath + helper(root.left, sum, false) + helper(root.right, sum, false) + helper(root.left, sum - root.val, true) + helper(root.right, sum - root.val, true);
        }
    }
}


---- #2 - 之前的解法，就看看吧。。。 SOL1: Pass the return value: using DFS ------

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

-------- # 1 - SOL 2: Pass the return value: using recursion ------
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
