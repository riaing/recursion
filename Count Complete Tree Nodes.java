Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.


完全二叉树的一个性质是，如果左子树最左边的深度，等于右子树最右边的深度，说明这个二叉树是满的，
即最后一层也是满的，则以该节点为根的树其节点一共有2^h-1个。
如果不等于，则是左子树的节点数，加上右子树的节点数，加上自身这一个。（这里递归）

//待提高的解法： 
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
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0 ;
        }
        int l = lheight(root) ;  //如果左边和右边高度相等，利用性质
        int r = rheight(root) ; 
        if(l == r){ 
            return   ( 1<<l) -1;
        }
        
     //   if(lheight(root) == rheight(root)) 为什么会超时？ 
        return 1 + countNodes(root.left) + countNodes(root.right); 
    }
    
    // 算左右两边的高度
    private int rheight(TreeNode root){ //include the root node  
        int count = 0;
        while(root != null){
            count ++;
            root = root.right; 
        }
        return count ; 
    }
      private int lheight(TreeNode root){
        int count = 0;
        while(root != null){
            count ++;
            root = root.left; 
        }
        return count ; 
    }
}

因为算左子树的leftheight时，就是当前节点的leftheight-1，所以把height作为节点传下去可以避免重复计算。
注意：求左子树的rightheight时不能传当前节点的rightheight-1， 这时就得重新计算。 所以做左子树的递归时，
参数应该写成（leftheight-1， -1）。 
同样运用参数传递减少运算的递归题： Sum Root to Leaf Numbers (M) 

//运用传递参数减少运算次数 
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
    public int countNodes(TreeNode root) {
            return countNodes(root, -1 , -1 );  // -1 是初始值，说明下一步需要计算
    }
    
    private int countNodes(TreeNode root, int leftHeight , int rightHeight){ 
         if(root == null){
            return 0 ;
        }
        
        if(leftHeight == -1) { //如果上一步没有计算好的左子树深度，在这里计算
             leftHeight = 0 ;
            //while(root != null){ //can't 直接改变root！要用个tmp 
            TreeNode tmp = root; 
            while(tmp != null){
                leftHeight ++;
                tmp = tmp.left; 
            }
        }
        if(rightHeight == -1) { // //如果上一步没有计算好的右子树深度，在这里计算
             rightHeight = 0 ;
             TreeNode tmp = root; 
            while(tmp != null){
                rightHeight ++;
                tmp = tmp.right; 
            }
        }
        if (rightHeight == leftHeight){
            return (1<<leftHeight) -1 ;
        }
        return 1 + countNodes(root.left, leftHeight-1, -1) + countNodes(root.right, -1 ,rightHeight -1); 
        
    }  
    
    
}
