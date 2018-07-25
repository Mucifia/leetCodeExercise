package com.mmy.leetcode.medium;

import com.mmy.leetcode.medium.Medium1.ListNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @author: mmy
 * @date: 2018/07/24
 * @description:
 */
public class Medium7 {


  /**
   * You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees
   * (clockwise).
   */
  public void rotate(int[][] matrix) {
    //转置
    for (int i = 0; i < matrix.length; i++) {
      for(int j=i;j<matrix.length;j++){
        swap(matrix,i,j,j,i);
      }
    }
    //竖直对称交换
    for (int i = 0; i < matrix.length; i++) {
      for(int j=0;j<matrix.length/2;j++){
        swap(matrix,i,j,i,matrix.length-j-1);
      }
    }
  }

  void swap(int[][] matrix, int i, int j, int m, int n) {
    int t = matrix[i][j];
    matrix[i][j] = matrix[m][n];
    matrix[m][n] = t;
  }


  /**
   * Given an array of strings, group anagrams together.
   * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
   * Output:
   * [
   *   ["ate","eat","tea"],
   *   ["nat","tan"],
   *   ["bat"]
   * ]
   * @param strs
   * @return
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String,List<String>> hashmap = new HashMap<>();
    for (String s : strs){
      char[] c = s.toCharArray();
      Arrays.sort(c);
      String str = String.valueOf(c);
      if (hashmap.get(str)==null){
        List<String> list = new ArrayList<>();
        list.add(s);
        hashmap.put(str,list);
      }else {
        hashmap.get(str).add(s);
      }
    }
    return new ArrayList<>(hashmap.values());
  }










  @Test
  public void test() {
//    rotate(new int[][]{
//        {5, 1, 9, 11},
//        {2, 4, 8, 10},
//        {13, 3, 6, 7},
//        {15, 14, 12, 16}
//    });
    String[] s  = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
    List<List<String>> lists = groupAnagrams(s);


  }

}
