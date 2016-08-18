Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
使用递归来做。

1. 先定义递归的参数为左边界、右边界，即1到n.

2. 考虑从left, 到right 这n个数字中选取一个作为根，余下的使用递归来构造左右子树。 

3. 当无解时，应该返回一个null树，这样构造树的时候，我们会比较方便，不会出现左边解为空，或是右边解为空的情况。

4. 如果说左子树有n种组合，右子树有m种组合，那最终的组合数就是n*m. 把这所有的组合组装起来即可

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
    public List<TreeNode> generateTrees(int n) {

        if (n == 0){
            return new ArrayList<TreeNode>();
        }
        return dfs(1,n); 
    }
    
    private List<TreeNode> dfs(int start, int end ){
        List<TreeNode> rst = new ArrayList<TreeNode>();
        
        if(start > end){
            rst.add(null);//important！
            return rst;
        }
        
        for (int i = start; i <= end; i ++){
            List<TreeNode> ltree = dfs( start, i-1); //左子树的各种情况
            List<TreeNode> rtree = dfs(i+1, end); //右子树的各种情况
            for(TreeNode l : ltree){ //如果不添加null，
            //if 左边树为空，就进不了这个loop，就无法添加元素，尽管右树不为空
                for(TreeNode r : rtree){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    rst.add(root);
                }
            }
        }
        return rst;
    }
} 

