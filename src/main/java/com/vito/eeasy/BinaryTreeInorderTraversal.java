package com.vito.eeasy;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {

        var list = new ArrayList<Integer>();
        inorderTraversalImpl(root, list);
        return list;
    }

    public void inorderTraversalImpl(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorderTraversalImpl(root.left, list);

            list.add(root.val);

            inorderTraversalImpl(root.right, list);
        }
    }
}
