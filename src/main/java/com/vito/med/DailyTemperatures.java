package com.vito.med;

import java.util.Stack;

public class DailyTemperatures {

    /**
    * Given an array of integers temperatures represents the daily temperatures,
    * return an array answer such that answer[i] is the number of days you have to
    * wait after the ith day to get a warmer temperature. If there is no future
    * day for which this is possible, keep answer[i] == 0 instead.
    */
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }
}
