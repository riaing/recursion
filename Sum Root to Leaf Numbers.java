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
 
//按照找到所有path的解法： 
public class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }
       List<String> allPath = findPath(root); 
       int sum = 0 ;
       for(int i = 0; i<allPath.size(); i ++){ 
           sum = sum + Integer.parseInt(allPath.get(i)); //把所有的path转化成int， 进行求和
       }
       return sum; 
    }
    
    private List<String> findPath(TreeNode root){ //先找出所有的path， 存到一个string array 中
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
 这个方法一些node会重复走几遍，eg 1-2 ， 1-3 这两条path中1 走了两遍。 
 较好的方法是： 递归到该点的时候就把这点的值与前面的值乘以10，然后相加
    需要如何保存前面的值？以参数传递的方式保存，以供下一层使用。
    
// 优化解法：参数传递

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
        return sumNumbers(root, 0);
    }
    private int sumNumbers(TreeNode root, int tmp){
        int sum = 0; 
        if(root == null){
            return sum; 
        }
        if(root.left == null && root.right == null){ //path题大多都要考虑leave node
            sum = tmp*10 + root.val;
            return sum;
        }
    
        sum = sumNumbers(root.left , tmp*10+root.val) + sumNumbers(root.right ,tmp*10+root.val);
        return sum ; 
        
    }
}

------ update: 更加clear的写法。把sum（primitive type）作为整个class的参数。helper method更新这个参数（类似path sum的思路） --- 
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
    int sum;
    
    public int sumNumbers(TreeNode root) {
        helper(root, 0);
        return sum;
    }
    
    private void helper(TreeNode root, int currentSum) {
        if (root == null) {
            return;
        }
        currentSum = currentSum * 10 + root.val; 
        if (root.left == null && root.right == null) {
            sum = sum + currentSum;
            return;
        }

        helper(root.left, currentSum);
        helper(root.right, currentSum); 
    }
}
