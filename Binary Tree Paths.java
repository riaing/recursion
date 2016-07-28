Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]


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
    public List<String> binaryTreePaths(TreeNode root) {
     List<String> allPath = new ArrayList<String>(); 
     if(root == null ){
         return allPath;
     }
     if (root.left == null && root.right == null){ //考虑第二个基线条件，leave的左右子node会不会影响。 
            allPath.add(root.val+ ""); // can't write in one line?
            return allPath;
     }
     
     allPath.addAll(binaryTreePaths(root.left));
     allPath.addAll(binaryTreePaths(root.right));
     //for (String i : allPath){  //since string is immutable, change i here won't change element in allPath 见下补充。
                                    
       //  i = root.val+"->" + i; 
     //}
     for(int i =0; i<allPath.size(); i ++ ){
         allPath.set (i, root.val + "->" + allPath.get(i)); //覆盖之前的旧string。 
     }
     return allPath;
       
    }
}

关于string性质的补充，immutable

List<String> s = new ArrayList<String>();
		s.add("abc");
		s.add("def");
		
		for( String i : s){
			i = 1 +"->"+i;
			System.out.println(i); // 1->abc ， 1->def
		} 
		System.out.println(s.get(0)); //abc, for loop只是用list里的string重新创了个string，但list里的string并没有改变
