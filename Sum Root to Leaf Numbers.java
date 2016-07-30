Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.



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
    public int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }
       List<String> allPath = findPath(root); 
       int sum = 0 ;
       for(int i = 0; i<allPath.size(); i ++){
           sum = sum + Integer.parseInt(allPath.get(i));
       }
       return sum; 
    }
    
    private List<String> findPath(TreeNode root){
         List<String> path = new ArrayList<String>();
        
        if(root == null){
            return null;
        }
        if (root.left == null && root.right == null){
            path.add(root.val+ ""); 
            return path; 
        }
        
        path.addAll(findPath(root.left));
        path.addAll(findPath(root.right));
        
        for(int i =0; i < path.size(); i ++){
            path.set(i, root.val+ path.get(i)); 
        }
        return path; 
        
    }
}
