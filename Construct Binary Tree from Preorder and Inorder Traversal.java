Given preorder and inorder traversal of a tree, construct the binary tree.

              1
        --------|-------
        2               3
    ----|----       ----|----
    4       5       6       7
 1245367 : preorder :  parent -> left ->right 
 4251637: inorder : left child -> parent -> right child
 4526731: postorder : left child -> right child -> parent node.
 
 思路： 
1，找根节点： Preoder，第一个元素一定是根节点，也就是1。
Postorder: 最后一个元素是跟节点。
2， 在inorder中找到根节点，count 跟节点前的数（*）为所有的left，后的数（#）为所有的right。
3，第二步中已知左右子树的节点数量，现在返回preorder，第一个node（根节点）后的（*）个数为所有的左子树节点，
（*）后的所有数到结尾为右子树节点。因为preorder性质为p-l-r。 所以所有左子树节点的第一个为左子树的head，所有右子树的第一个为右子树的head。
Postorder中只是相反,根据 l-r-p的性质，前（*）个为所有左子树节点，（*+ #）个为所有右子树节点，前（*）个中的最后一个为左子树的head，
所有右子树节点中的最后一个为右子树的head。同样根据性质，整个array的最后一个为整棵树的root。 

解法一： 不用array的start，end方法，每次recursion新建一个array（性能差）：
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        // base 1 : if  == null; 
        if(preorder== null || inorder == null ){
            return null; 
        }
        //base2 : if empty arry 
        if( preorder.length == 0 || inorder.length == 0){ //* 没此条件就会下面preorder【0】 OutOfBound
            return null; 
        }
        
        // base3: if leave node 
        if(preorder.length ==1 && inorder.length== 1){
            TreeNode head = new TreeNode(preorder[0]);
            return head; 
        }
        
        TreeNode head = new TreeNode(preorder[0]);  //why arrayIndexOutOfBound  //* 
        int index = 0;
        for (int i=0; i< inorder.length; i ++){//在inoder中找到head的index
            if (inorder[i] == head.val){
                index = i ; 
            }
        }
        int[] leftInorder = new int[index];
        int rightSize = inorder.length-1-index;
        int[] rightInorder = new int[rightSize];
        
        int[] leftPreorder = new int[index];
        int[] rightPreorder = new int[rightSize];
        for (int j = 0; j < index; j ++){
            leftInorder[j] = inorder[j];
            leftPreorder[j] =preorder[1+j];
        }
        for(int z = 0; z<rightSize; z ++ ){
            rightInorder[z] = inorder[index +1 +z];
            rightPreorder[z] = preorder[1+index +z]; 
        }
        //head.left = new TreeNode(leftPreorder[0]); //* initilized twice. 
        //head.right = new TreeNode(rightPreorder[0]); //* twice 
        
        head.left = buildTree(leftPreorder, leftInorder); //左右两边直接递归
        head.right = buildTree(rightPreorder, rightInorder);
        
        return head; 
        
    }
}
