package com.vito.easy

import java.util.*

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {

        val state = mutableMapOf<Int, Int>()

        nums.asSequence()
                .forEachIndexed { index, i ->
                    if (state.containsKey(target - i)) {
                        return intArrayOf(index, state[target - i]!!)
                    }
                    state[i] = index
                }

        return intArrayOf()
    }
}

fun main() {
    val s = Solution()
    println(s.twoSum(intArrayOf(2,7,11,15), 9).contentToString())
}