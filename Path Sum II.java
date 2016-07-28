
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

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
   
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<List<Integer>>(); //（0）创建return type
        ArrayList<Integer> addVal =new ArrayList<Integer>(); //way1 
            if(root == null){   //(1) 考虑null节点
            return paths;
        }
        if(root.val == sum && root.left == null && root.right == null){  //(2) 考虑leave是否有子节点。
            //ArrayList<Integer> addVal =new ArrayList<Integer>();  //way1 ,doen't matter where to put 
            addVal.add(root.val);
            //paths.add(new ArrayList<Integer>(addVal)); //way 2, copy 了一次； 
            paths.add(addVal); //way2 
            return paths;
        }
        
        paths.addAll(pathSum(root.left, sum - root.val));  //（3）递归，add substring 
        paths.addAll(pathSum(root.right, sum - root.val));
        
        for (int i = 0 ; i<paths.size(); i ++){  //（4）将root加到递归完的list中，完善list
            paths.get(i).add(0, root.val);
        }
        
        
        return paths;
        
    }
}
