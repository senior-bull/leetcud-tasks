package com.vito.eeasy;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {

        int result = Integer.MIN_VALUE;
        int minPrefixSum = 0;
        int currentPrefixSum = 0;

        for (int i = 0; i < nums.length; i++) {

            currentPrefixSum += nums[i];

            int newResult = currentPrefixSum - minPrefixSum;
            if (newResult > result) {
                result = newResult;
            }
            if (currentPrefixSum < minPrefixSum) {
                minPrefixSum = currentPrefixSum;
            }
        }

        return result;
    }
}
