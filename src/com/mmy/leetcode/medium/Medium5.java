package com.mmy.leetcode.medium;

import org.junit.Test;

/**
 * @author: mmy
 * @date: 2018/06/29
 * @description:
 */
public class Medium5 {

  public int[] searchRange(int[] nums, int target) {
    if (nums.length == 0) {
      return new int[]{-1, -1};
    }
    int pivot = binarySearch(0, nums.length - 1, nums, target);
    int left = -1, right = -1;
    if (pivot != -1) {
      left = pivot;
      right = pivot;
      while (left >= 1 && nums[left - 1] == target) {
        left--;
      }
      while (right <= nums.length - 2 && nums[right + 1] == target) {
        right++;
      }
    }
    return new int[]{left, right};

  }

  /**
   * 自己的方法，使用2分法找到值，再往左右扩散，明显没纯粹2分法的快
   */
  public int binarySearch(int left, int right, int[] nums, int target) {
    if (left != right) {
      int middle = (left + right) / 2;
      if (nums[middle] == target) {
        return middle;
      }
      if (nums[middle] < target) {
        return binarySearch(middle + 1, right, nums, target);
      }
      if (nums[middle] > target) {
        return binarySearch(left, middle, nums, target);
      }
    } else {
      if (nums[left] == target) {
        return left;
      }
    }
    return -1;
  }


  private int extremeInsertionIndex(int[] nums, int target, boolean left) {
    int lo = 0;
    int hi = nums.length;

    while (lo < hi) {
      int mid = (lo + hi) / 2;
      if (nums[mid] > target || (left && target == nums[mid])) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }

    return lo;
  }

  /**
   * 纯粹用2分法确定
   */
  public int[] searchRange2(int[] nums, int target) {
    int[] targetRange = {-1, -1};

    int leftIdx = extremeInsertionIndex(nums, target, true);

    // assert that `leftIdx` is within the array bounds and that `target`
    // is actually in `nums`.
    if (leftIdx == nums.length || nums[leftIdx] != target) {
      return targetRange;
    }

    targetRange[0] = leftIdx;
    targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

    return targetRange;
  }


  /**
   * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according
   * to the following rules: 1.Each row must contain the digits 1-9 without repetition. 2.Each
   * column must contain the digits 1-9 without repetition. 3.Each of the 9 3x3 sub-boxes of the
   * grid must contain the digits 1-9 without repetition.
   */
  public boolean isValidSudoku(char[][] board) {

    //row x colum y means  rows x have y numbers
    boolean columValid[][] = new boolean[9][9];
    //row x colum y means the sub-box x have y numbers;
    boolean subValid[][] = new boolean[9][9];
    for (int i = 0; i < 9; i++) {
      boolean rowValid[] = new boolean[9];
      for (int j = 0; j < 9; j++) {
        char c = board[i][j];
        if (c != '.') {
          int index = c - '1';
          int subBoxIndex = i / 3 * 3 + j / 3;
          if (rowValid[index] || columValid[j][index] || subValid[subBoxIndex][index]) {
            return false;
          }
          rowValid[index] = true;
          columValid[j][index] = true;
          subValid[subBoxIndex][index] = true;
        }
      }
    }
    return true;
  }


  /**
   * 回溯法
   * 一个一个试
   */
  public void solveSudoku(char[][] board) {
    boolean[][] columnValid = new boolean[board.length][board[0].length];
    boolean[][] subBoxValid = new boolean[board.length][board[0].length];
    boolean[][] rowValid = new boolean[board.length][board[0].length];
    validArrInit(board,columnValid,subBoxValid,rowValid);
    search(board,columnValid,subBoxValid,rowValid);
  }

  public boolean search(char[][] board, boolean[][] columnValid, boolean[][] subBoxValid,
      boolean[][] rowValid){
    for (int i=0;i<board.length;i++){
      for (int j=0;j<board.length;j++){
        if (board[i][j]=='.'){
          for (int k=0;k<9;k++){
            char c = (char) ('1'+k);
            int index = i/3*3+j/3;
            if (!(columnValid[j][k] || subBoxValid[index][k] || rowValid[i][k])){
              board[i][j]=c;
              columnValid[j][k] = true;
              subBoxValid[index][k] = true;
              rowValid[i][k] = true;
              if (search(board,columnValid,subBoxValid,rowValid)){
                return true;
              }
              //backTrace
              columnValid[j][k] = false;
              subBoxValid[index][k] = false;
              rowValid[i][k] = false;
              board[i][j]='.';
            }
          }
          //已经试完所有的字符，没有匹配的
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 状态矩阵初始化
   * @param board
   * @param columnValid
   * @param subBoxValid
   * @param rowValid
   */
  public void validArrInit(char[][] board, boolean[][] columnValid, boolean[][] subBoxValid,
      boolean[][] rowValid) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] != '.') {
          int c = board[i][j];
          int num = c - '1';
          int index = i / 3 * 3 + j / 3;
          if (columnValid[j][num] || subBoxValid[index][num] || rowValid[i][num]) {
            return;
          }
          columnValid[j][num] = true;
          subBoxValid[index][num] = true;
          rowValid[i][num] = true;
        }
      }
    }
  }


  @Test
  public void test() {
    char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

    solveSudoku(board);

  }
}
