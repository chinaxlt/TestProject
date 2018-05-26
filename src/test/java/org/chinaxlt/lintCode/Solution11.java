package org.chinaxlt.lintCode;

import java.util.ArrayList;

public class Solution11 {


    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        ArrayList<Integer> results = new ArrayList<Integer>();
        dfs(root, k1, k2, results);
        //Collections.reverse(results);
        return results;
    }

    private void dfs(TreeNode root, int k1, int k2, ArrayList<Integer> res) {
        if (root == null)
            return;
        if (root.val > k1) {
            dfs(root.left, k1, k2, res);
        }
        if (root.val >= k1 && root.val <= k2) {
            res.add(root.val);
        }
        if (root.val < k2)
            dfs(root.right, k1, k2, res);
    }

}
