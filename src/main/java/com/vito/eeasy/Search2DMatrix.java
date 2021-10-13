package com.vito.eeasy;

public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) {
            return false;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int l = 0, r = (matrix.length * matrix[0].length - 1);
        while(l <= r) {
            int mid = l + (r - l)/2;
            int val = matrix[mid / cols][mid % cols];
            if(val == target) {
                return true;
            } else if(val > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return false;
    }
}
