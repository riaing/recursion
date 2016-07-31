/*Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
*/ 


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
    public boolean isSameTree(TreeNode p, TreeNode q) {
       if(p == null && q == null){
           return true;
       }
       if(p == null || q == null){
           return false;
       }
       if(p.val != q.val){
           return false;
       }
      return  isSameTree(p.left, q.left) &&  isSameTree(p.right, q.right); 
      
    }
}
/////非递归解法： 类似Binary Tree Level Order Traversal，用queue存当前层续的节点，同时加上下一层节点，直到Queue size为零 
 public boolean isSameTree(TreeNode p, TreeNode q) {
       /*if(p == null && q == null){
           return true;
       }
       if(p == null || q == null){
           return false;
       }
       if(p.val != q.val ){
           return false;
       }
      return  isSameTree(p.left, q.left) &&  isSameTree(p.right, q.right); */
      
      //interation 
      Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
      Queue<TreeNode> queue2  = new LinkedList<TreeNode>(); 
      queue1.offer(p);
      queue2.offer(q); 
      
      while(queue1.size() != 0  &&  queue2.size() != 0 ){// 最好不用 queue == null 这种写法
          TreeNode p1 = queue1.poll();
          TreeNode p2 = queue2.poll(); 
          
          //三步顺序不能错
          if(p1 == null && p2== null ){//子树可能有空节点，空节点也加了进来，“两个都为null”
              continue;
          }
          else if(p1==null || p2 == null ){//“只有一个为null”因为前一步考虑了两个都为null，这里用 or
          //可以，否则会报错。 
              return false;
          }
          else if(p1.val != p2.val){//必须写在最后，不然 null.val 会throw NullPointerException 
              return false; 
          }
          queue1.offer(p1.left);
          queue1.offer(p1.right); 
          queue2.offer(p2.left); 
          queue2.offer(p2.right); 
      }
      
      if(queue1.size() != 0  ||  queue2.size() != 0 ){// 从while出来后，两树是否有剩余节点。（如果while用了 ||， 这里就用 && ）
          return false;
      }
      return true; 
      
      
    }
}
