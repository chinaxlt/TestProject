package org.chinaxlt.lintCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution7 {

    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public String serialize(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        sHelper(root, ans);
        return ans.toString();
    }

    private void sHelper(TreeNode root, ArrayList<Integer> ans) {
        if (root == null) {
            ans.add(null);
            return;
        }
        ans.add(root.val);
        sHelper(root.left, ans);
        sHelper(root.right, ans);
    }

    public TreeNode deserialize(String data) {
        if (data == null) return null;
        data = data.substring(1, data.length() - 1);
        String[] nodesVal = data.split(", ");
        Deque<String> strList = new LinkedList<String>(Arrays.asList(nodesVal));
        return dHelper(strList);
    }

    private TreeNode dHelper(Deque<String> strList) {
        if (strList.size() == 0) return null;
        String str = strList.pop();
        if (str.equals("#")) {
            return null;
        }
        TreeNode cur = new TreeNode(Integer.parseInt(str));
        cur.left = dHelper(strList);
        cur.right = dHelper(strList);
        return cur;
    }

    @Test
    public void testS7() {
        String data = "{3, 9, 20, #, #, 15, 7}";
        TreeNode tree = deserialize(data);
        System.out.println(tree.toString());
    }

}
