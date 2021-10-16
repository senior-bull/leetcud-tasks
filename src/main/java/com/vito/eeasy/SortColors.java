package com.vito.eeasy;

public class SortColors {

    public void sortColors(int[] nums) {
        int g = 0, b = 0;

        for(int i = 0; i < nums.length; i++) {
            int v = nums[i];
            nums[i] = 2;
            if(v < 2) {
                nums[b] = 1;
                b++;
            }
            if(v == 0) {
                nums[g] = 0;
                g++;
            }
        }
    }
}
