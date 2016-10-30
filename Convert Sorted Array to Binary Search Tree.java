Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null){
            return null;
        }
        return buildTree(nums, 0, nums.length -1); 
    }
    private TreeNode buildTree(int[] nums, int start, int end){
        if(start == end){ //actually don't need it, since've considered in recursion 
              TreeNode head  = new TreeNode(nums[start]); 
              return head;
        }
        if(start > end ){ //avoid attry index out of bound, since start + end could be negetive is don't have this condition
            return null; 
        }
        TreeNode head = new TreeNode(nums[(start + end)/2]);
        head.left = buildTree(nums, start, (start + end)/2-1);
        head.right = buildTree(nums, (start+end)/2 +1, end); 
        
        return head; 
        
    }
}
