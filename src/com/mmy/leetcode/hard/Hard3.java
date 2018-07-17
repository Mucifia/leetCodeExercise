package com.mmy.leetcode.hard;

import org.junit.Test;

/**
 * @author: mmy
 * @date: 2018/07/17
 * @description:
 */
public class Hard3 {

  /**
   * Given an unsorted integer array, find the smallest missing positive integer.
   * @param nums
   * @return
   */
  public int firstMissingPositive(int[] nums) {
    int[] temp = new int[nums.length+1];
    for (int i=0;i<nums.length;i++) {
      if (nums[i] > 0&&nums[i]<nums.length) {
        temp[nums[i]-1]=nums[i];
      }
    }
    for (int j=0;j<nums.length+1;j++){
      if (temp[j]!=j+1){
        return j+1;
      }
    }
    return 1;
  }

  @Test
  public void test(){
    int[] nums = new int[]{1};
    int index =firstMissingPositive(nums);
  }
}
