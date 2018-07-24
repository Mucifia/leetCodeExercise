package com.mmy.leetcode.medium;

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

  @Test
  public void test() {
    rotate(new int[][]{
        {5, 1, 9, 11},
        {2, 4, 8, 10},
        {13, 3, 6, 7},
        {15, 14, 12, 16}
    });
  }

}
