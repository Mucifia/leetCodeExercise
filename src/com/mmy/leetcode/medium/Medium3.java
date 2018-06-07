package com.mmy.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * @author: mmy
 * @date: 2018/06/07
 * @description:
 */
public class Medium3 {

  /**
   * 有多余遍历，缺少一些情况下跳过
   * @param nums
   * @param target
   * @return
   */
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Set set = new HashSet();
    List<Integer> list;
    int left,right,result;
    Arrays.sort(nums);
    for (int i=0;i<nums.length-3;i++){
      if (nums.length==0){
        break;
      }
      for (int j=i+1;j<nums.length-2;j++){
        left = j+1;
        right =nums.length-1;
        while (left<right){
          result = nums[i]+nums[j]+nums[left]+nums[right]-target;
          if (result<0){
            left++;
          }else if (result>0){
            right--;
          }else {
            list =new ArrayList<>();
            list.add(nums[i]);
            list.add(nums[j]);
            list.add(nums[left]);
            list.add(nums[right]);
            set.add(list);
            left++;
            right--;
          }
        }
      }

    }
    return new ArrayList<>(set);
  }

  @Test
  public void test(){
    List<List<Integer>> listList = fourSum(new int[]{-1,0,1,2,-1,-4},-1);
  }
}
