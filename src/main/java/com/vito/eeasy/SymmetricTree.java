package com.vito.eeasy;

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        } else {
            return isSymmetric(root.left, root.right);
        }
    }

    private boolean isSymmetric(TreeNode lhs, TreeNode rhs) {
        if (lhs == null) {
            return rhs == null;
        } else {
            return rhs != null && (rhs.val == lhs.val) && isSymmetric(lhs.left, rhs.right) &&
                    isSymmetric(lhs.right, rhs.left);
        }
    }
}
