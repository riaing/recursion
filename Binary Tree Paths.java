Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

----------------------  D&C 1 ---------------------------------------------------------------------------------------
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
     List<String> allPath = new ArrayList<String>();  //(1)
     if(root == null ){
         return allPath;
     }
     if (root.left == null && root.right == null){ // (2) 考虑第二个基线条件，leave的左右子node会不会影响。 
            allPath.add(root.val+ ""); 
            return allPath;
     }
     
     allPath.addAll(binaryTreePaths(root.left //(3) add substring 
     allPath.addAll(binaryTreePaths(root.right));
     //for (String i : allPath){  //since string is immutable, change i here won't change element in allPath 见下补充。
                                    
       //  i = root.val+"->" + i; 
     //}
     for(int i =0; i<allPath.size(); i ++ ){   //(4) 完善list
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
				    
				    
-----------         3.31.19 update D&C 2：和以上思路一样------------------------------------------------------------
				    
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if (root == null) {
            return res;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val+"");
        }
            
        //divide 
        List<String> l = binaryTreePaths(root.left);
        List<String> r = binaryTreePaths(root.right);
        
        
        // conquer 
        for (String s : l) {
            res.add(root.val + "->" + s);
        }
        for (String s : r) {
            res.add(root.val + "->" + s);
        }
        return res;
    }
}

---------------------------------3.31.19 update recursion写法--------------------------------------------------------
				    
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        recursion(root, res, "");
        return res;
    }
    
    private void recursion(TreeNode root, List<String> res, String path) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
            return;
        }

        recursion(root.left, res,  path + root.val + "->");
        recursion(root.right, res,  path + root.val+ "->" );   

 
    }
}				

--------------------------------- 原始解法，不用看了。。。 ------------------------
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>(); 
        List<Integer> result = new ArrayList<Integer>(); 
        
        allPath(root, results, result);
        
        List<String> returnedResult = new ArrayList<String>();
        for (int i = 0; i < results.size(); i ++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < results.get(i).size() -1; j ++) {
                builder.append(results.get(i).get(j));
                builder.append("->");
            }
            // Append the last node of current list.
            builder.append(results.get(i).get(results.get(i).size() -1));
            returnedResult.add(builder.toString());
        }
        return returnedResult;
    }
    private void allPath(TreeNode root, List<List<Integer>> results,  List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        // If leaf, add to results and remove the node from current list. 
        if (root.left == null && root.right == null) {
            results.add(new ArrayList<Integer>(result));
            result.remove(result.size() -1);
            return;
        }
        allPath(root.left, results, result);
        //result.remove(result.size() -1);
        allPath(root.right, results, result);
        result.remove(result.size() -1);
    }  
}
