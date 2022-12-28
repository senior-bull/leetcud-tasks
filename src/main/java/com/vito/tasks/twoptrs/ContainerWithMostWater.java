package com.vito.tasks.twoptrs;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxVolume = (right - left) * Math.min(height[left], height[right]);

        while (left < right) {

            int volume = (right - left) * Math.min(height[left], height[right]);
            maxVolume = Math.max(maxVolume, volume);

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return maxVolume;
    }
}
