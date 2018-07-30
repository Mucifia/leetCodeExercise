package com.mmy.leetcode.easy;

import org.junit.Test;

/**
 * @author: mmy
 * @date: 2018/07/13
 * @description:
 */
public class Easy3 {

  /**
   * 数数字！
   * @param n
   * @return
   */
  public String countAndSay(int n) {
    StringBuffer s = new StringBuffer("1");
    for (int i=0;i<n-1;i++){

      int index = 0;
      char c  = s.charAt(0);
      int num =0;
      StringBuffer temp = new StringBuffer();
      while (index<s.length()){
        if (c==s.charAt(index)){
          num++;
          index++;
          if (index==s.length()){
            temp.append(num);
            temp.append(c);
          }
        }else {
          temp.append(num);
          temp.append(c);
          c=s.charAt(index);
          num=0;
        }
      }
      s=temp;
    }
    return s.toString();
  }

  @Test
  public  void test(){
    int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
    int result = maxSubArray(nums);
  }


  public int maxSubArray(int[] nums) {
    //largest sum left to i
    int[] bigest =  new int[nums.length];
    bigest[0]=nums[0];
    int result = bigest[0];
    for (int i=1;i<nums.length;i++){
      bigest[i] = Math.max(bigest[i-1]+nums[i],nums[i]);
      if (bigest[i]>result){
        result= bigest[i];
      }
    }
    return result;
  }


}
