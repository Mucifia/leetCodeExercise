package com.mmy.leetcode.easy;

import java.util.Arrays;
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


  /**
   * 66. Plus One
   * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
   * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
   * You may assume the integer does not contain any leading zero, except the number 0 itself.
   * @param digits
   * @return
   */
  public int[] plusOne(int[] digits) {
    int[] result;
    result=new int[digits.length+1];
    for (int j=digits.length-1;j>=0;j--){
      int temp;
      if (j==digits.length-1){
         temp= result[j+1]+digits[j]+1;
      }else {
        temp=result[j+1]+digits[j];
      }
      if (temp>=10){
        result[j]=1;
      }
      result[j+1]=temp%10;
    }
    if (result[0]==0){
      return Arrays.copyOfRange(result,1,result.length);
    }
    return result;
  }


  /**
   * Given two binary strings, return their sum (also a binary string).
   *
   * The input strings are both non-empty and contains only characters 1 or 0.
   * @param a
   * @param b
   * @return
   */
  public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int indexA=a.length()-1;
    int indexB=b.length()-1;
    int newestTemp=0;
    while (indexA>-1&&indexB>-1){
      int iA = a.charAt(indexA)-'0';
      int iB = b.charAt(indexB)-'0';
      int sum = iA+iB+newestTemp;
      if (sum>=2){
        newestTemp=1;
      }else {
        newestTemp=0;
      }
      sb.append(sum%2);
      indexA--;
      indexB--;
    }
    while (indexA>-1){
      int iA = a.charAt(indexA)-'0';
      int sum=iA+newestTemp;
      if (sum>=2){
        newestTemp=1;
      }else {
        newestTemp=0;
      }
      sb.append(sum%2);
      indexA--;
    }
    while (indexB>-1){
      int iB = b.charAt(indexB)-'0';
      int sum=iB+newestTemp;
      if (sum>=2){
        newestTemp=1;
      }else {
        newestTemp=0;
      }
      sb.append(sum%2);
      indexB--;
    }
    if (newestTemp>0){
      sb.append(newestTemp);
    }
    return sb.reverse().toString();
  }


  /**
   * Implement int sqrt(int x).
   *
   * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
   *
   * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
   * @param x
   * @return
   */
  public int mySqrt(int x) {
    if (x==0){
      return 0;
    }
    int result =1;
    while (result<=x/result){
      result++;
    }
    return result-1;
  }

  /**
   * You are climbing a stair case. It takes n steps to reach to the top.
   *
   * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
   *
   * Note: Given n will be a positive integer.
   * @param n
   * @return
   * TLS
   */
  public int climbStairs(int n) {
    if (n==1){
      return 1;
    }else if (n==2){
      return 2;
    }
    return climbStairs(n-1)+climbStairs(n-2);
  }


  public int climbStairs2(int n) {
    int[] result = new int[n];
    result[0]=0;
    result[1]=1;
    for (int i=2;i<n;i++){
      result[i]=result[i-1]+result[i-2];
    }
    return result[n-1];
  }

  @Test
  public  void test(){
//    int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
//    int result = maxSubArray(nums);
//    String s = "Hello World   ";
//    int result =  lengthOfLastWord(s);
//    int[] test1 = new int[]{1,2,3};
//    int[] test2 = new int[]{9,9,9};
//    int[] test3 = new int[]{0};
//    int[] result =plusOne(test1);
//    int[] result2 =plusOne(test2);
//    int[] result3 =plusOne(test3);
    String test1A = "0";String test1B = "0";
    String test2A = "110";String test2B = "1";
    String test3A = "011";String test3B = "1";
    String result = addBinary(test1A,test1B);
    String result2 = addBinary(test2A,test2B);
    String result3 = addBinary(test3A,test3B);
  }


}
