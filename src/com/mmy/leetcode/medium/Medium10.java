package com.mmy.leetcode.medium;

import org.junit.jupiter.api.Test;

/**
 * @author: mmy
 * @date: 2018/08/21
 * @description:
 */
public class Medium10 {

  /**
   * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
   * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
   * @param nums
   */
  public void sortColors(int[] nums) {
    int redIndex = 0;
    int blueIndex = nums.length-1;
    int start =redIndex;
    while (start<=blueIndex){
      if (nums[start]==0){
        if (start!=redIndex){
          swap(nums,start,redIndex);
        }else {
          start++;
        }
        redIndex++;
      }else if (nums[start]==2){
        if(start!=blueIndex){
          swap(nums,start,blueIndex);
        }else {
          start++;
        }
        blueIndex--;
      }else {
        start++;
      }
    }
  }
  void swap(int[] nums,int x,int y){
    int temp = nums[x];
    nums[x]=nums[y];
    nums[y]=temp;
  }


  @Test
  public void test(){
    int[] test1 = new int[]{2,0,2,1,1,0};
    sortColors(test1);
  }
}
