package com.vito.eeasy;

import java.util.ArrayList;
import java.util.List;

public class AllSubsets {

    public List<List<Integer>> subsets(int[] nums) {

        boolean[] mask = new boolean[nums.length];
        List<List<Integer>> out = new ArrayList<>();
        generate(nums, mask, 0, out);
        return out;
    }

    private void generate(
            int[] nums,
            boolean[] mask,
            int pos,
            List<List<Integer>> output) {

        if (pos == nums.length) {

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < mask.length; i++) {
                if (mask[i]) {
                    result.add(nums[i]);
                }
            }
            output.add(result);
        } else {

            mask[pos] = false;
            generate(nums, mask, pos + 1, output);
            mask[pos] = true;
            generate(nums, mask, pos + 1, output);
        }
    }
}
