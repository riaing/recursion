1) Node to be deleted is leaf: Simply remove from the tree.
              50                            50
           /     \         delete(20)      /   \
          30      70       --------->    30     70 
         /  \    /  \                     \    /  \ 
       20   40  60   80                   40  60   80
2) Node to be deleted has only one child: Copy the child to the node and delete the child
              50                            50
           /     \         delete(30)      /   \
          30      70       --------->    40     70 
            \    /  \                          /  \ 
            40  60   80                       60   80
3) Node to be deleted has two children: Find inorder successor of the node. Copy contents of the 
inorder successor to the node and delete the inorder successor. Note that inorder predecessor can also be used.
              50                            60
           /     \         delete(50)      /   \
          40      70       --------->    40    70 
                 /  \                            \ 
                60   80                           80
The important thing to note is, inorder successor is needed only when right child is not empty. In this particular case, 
inorder successor can be obtained by finding the minimum value in right child of the node.

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
    public TreeNode deleteNode(TreeNode root, int key) {
        //第一层： 找到key所在的位置
        //to find the key node
        if(root == null ){
            return root; 
        }
        if (key > root.val){
            root.right = deleteNode(root.right, key);
        }
        else if( key < root.val ){
            root.left = deleteNode(root.left, key);
        }
        else{ // root.val = key
        //第二层： 判断怎么delete
            //if having one child: 
            if(root.right == null){
                return root.left; 
            }
            if(root.left == null){
                return root.right;
            }
            
            //if having two child: 
            else{
                //find inorder successor of the key node ( the smallest node on the right tree)
                int minVal = findMin(root.right); 
                
                //assign this value to the key node
                root.val = minVal; 
                
                //recursive go through the right tree to delete this duplicate, this duplicate is the smalles node on right tree, so it could be no child or have a right child. 
                root.right = deleteNode(root.right, root.val);
                
            }
        }
        return root;
    }
    
    private int findMin (TreeNode node){
        int val= 0;
        while(node != null){
            val = node.val;
            node = node.left; 
        }
        return val; 
    }
}
