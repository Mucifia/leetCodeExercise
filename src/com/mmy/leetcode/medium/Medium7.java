package com.mmy.leetcode.medium;

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
      for (int j = i; j < matrix.length; j++) {
        swap(matrix, i, j, j, i);
      }
    }
    //竖直对称交换
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length / 2; j++) {
        swap(matrix, i, j, i, matrix.length - j - 1);
      }
    }
  }

  void swap(int[][] matrix, int i, int j, int m, int n) {
    int t = matrix[i][j];
    matrix[i][j] = matrix[m][n];
    matrix[m][n] = t;
  }


  /**
   * Given an array of strings, group anagrams together. Input: ["eat", "tea", "tan", "ate", "nat",
   * "bat"], Output: [ ["ate","eat","tea"], ["nat","tan"], ["bat"] ]
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> hashmap = new HashMap<>();
    for (String s : strs) {
      char[] c = s.toCharArray();
      Arrays.sort(c);
      String str = String.valueOf(c);
      if (hashmap.get(str) == null) {
        List<String> list = new ArrayList<>();
        list.add(s);
        hashmap.put(str, list);
      } else {
        hashmap.get(str).add(s);
      }
    }
    return new ArrayList<>(hashmap.values());
  }

  /**
   * Implement pow(x, n), which calculates x raised to the power n (xn).
   *
   * -100.0 < x < 100.0 n is a 32-bit signed integer, within the range [−231, 231 − 1]
   *
   * 超时了
   */
  public double myPow(double x, int n) {
    if (n == 0) {
      return 1.0;
    }
    if (x == 0) {
      return 0;
    }
    int exp = 1;
    double result = Math.abs(x);
    long most = Math.abs((long) n);
    while (result != Double.POSITIVE_INFINITY && exp * 2 <= most) {
      result *= result;
      exp += exp;
    }
    if (result != Double.POSITIVE_INFINITY) {
      int expCopy = exp;
      double resultCopy = result;
      while (expCopy / 2 > 0) {
        expCopy /= 2;
        resultCopy = Math.sqrt(resultCopy);
        while (exp + expCopy <= most) {
          result *= resultCopy;
          exp += expCopy;
        }
      }
      int j = n & 1;
      if (n > 0) {
        if (j == 0) {
          return result;
        } else {
          return x > 0 ? result : -1 * result;
        }
      } else {
        if (j == 0) {
          return 1 / result;
        } else {
          return x > 0 ? 1 / result : -1 / result;
        }
      }
    } else if (n > 0) {
      return x > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
    } else {
      return 0;
    }
  }

  double mypow2(double x, int n) {
    if (n == 0) {
      return 1;
    }
    if (n < 0) {
      if (n == Integer.MIN_VALUE) {
        if (x < 0) {
          x = -x;
        }
        x = 1 / x;
        return x * myPow(x, -(n + 1));
      } else {
        n = -n;
      }
      x = 1 / x;
    }
    return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
  }

  /**
   * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in
   * spiral order.
   *
   * Input: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] Output: [1,2,3,6,9,8,7,4,5]
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    if (matrix.length==0){
      return result;
    }
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    int x = 0, y = 0;
    int directX = 0, directY = 0;
    //key  {store the x and y position move thread}
    int[] directXRoute = {0, 1, 0, -1};
    int[] directYRoute = {1, 0, -1, 0};
    while (!visited[x][y]) {
      int desX = x + directXRoute[directX];
      int desY = y + directYRoute[directY];
      if (desX == matrix.length
          || desY == matrix[0].length
          || desY == -1
          || visited[desX][desY]) {
        directY = (directY + 1) % directYRoute.length;
        directX = (directX + 1) % directXRoute.length;
      }
      result.add(matrix[x][y]);
      visited[x][y] = true;
      x = x + directXRoute[directX];
      y = y + directYRoute[directY];
      if (x<0
          ||x>matrix.length-1
          ||y<0
          ||y>matrix[0].length-1){
        break;
      }
    }
    return result;
  }


  @Test
  public void test() {
//    rotate(new int[][]{
//        {5, 1, 9, 11},
//        {2, 4, 8, 10},
//        {13, 3, 6, 7},
//        {15, 14, 12, 16}
//    });
//    String[] s  = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
//    List<List<String>> lists = groupAnagrams(s);

//    Double x = 2.0;
//    int n = -2;
//    Double result = myPow(x, n);
//    Double test = Math.pow(x, n);

    int[][] matrix = new int[][]{
        {5, 1, 9, 11},
        {2, 4, 8, 10},
        {13, 3, 6, 7},
        {15, 14, 12, 16}
    };
    int[][] test = new int[][]{{1},{4}};
    List<Integer> result = spiralOrder(test);
  }

}
