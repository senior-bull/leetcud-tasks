package com.vito.eeasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {

    private List<List<Integer>> result;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result = new ArrayList<>();
        Arrays.sort(nums);

        int[] mask = new int[nums.length];

        iterate(nums, mask, 0);

        return result;
    }

    private void iterate(int[] nums, int[] mask, int pos) {
        if(pos >= nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < mask.length; i++) {
                if(mask[i] == 1) {
                    list.add(nums[i]);
                }
            }
            result.add(list);
            return;
        }

        iterate(nums, mask, pos + 1);

        if((pos > 0 && ((nums[pos] != nums[pos - 1]) || (nums[pos] == nums[pos - 1] && mask[pos - 1] == 1))) || pos == 0) {
            mask[pos] = 1;
            iterate(nums, mask, pos + 1);
            mask[pos] = 0;
        }
    }
}
