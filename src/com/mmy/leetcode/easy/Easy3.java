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




  //58. Length of Last Word
  /**
   *Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
    If the last word does not exist, return 0.
    Note: A word is defined as a character sequence consists of non-space characters only.
   *
   * @param s
   * @return
   */
  public int lengthOfLastWord(String s) {
    boolean isNewWord = false;
    int length = 0;
    int lastLength=length;
    char[] c  = s.toLowerCase().toCharArray();
    for (int i=0;i<c.length;i++){
      if (c[i]>='a'&&c[i]<='z'&&isNewWord){
        length++;
        lastLength=length;
      }else if (c[i]<'a'||c[i]>'z'){
        isNewWord = false;
        length=0;
      }else if (c[i]>='a'&&c[i]<='z'&&!isNewWord){
        length = 1;
        isNewWord = true;
        lastLength=length;
      }
    }
    return lastLength;
  }







  @Test
  public  void test(){
//    int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
//    int result = maxSubArray(nums);
//    String s = "Hello World   ";
//    int result =  lengthOfLastWord(s);
  }


}
