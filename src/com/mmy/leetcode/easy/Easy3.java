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
    int n=2;
    String s = countAndSay(3);
  }


}
