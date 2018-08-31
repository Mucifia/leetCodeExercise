package com.mmy.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author: mmy
 * @date: 2018/08/29
 * @description:
 */
public class Medium11 {

  /**
   * Given a 2D board and a word, find if the word exists in the grid.
   *
   * The word can be constructed from letters of sequentially adjacent cell,
   * where "adjacent" cells are those horizontally or vertically neighboring.
   * The same letter cell may not be used more than once.
   *
   * board =
   * [
   *   ['A','B','C','E'],
   *   ['S','F','C','S'],
   *   ['A','D','E','E']
   * ]
   *
   * Given word = "ABCCED", return true.
   * Given word = "SEE", return true.
   * Given word = "ABCB", return false.
   * @param board
   * @param word
   * @return
   */
  public boolean exist(char[][] board, String word) {
    boolean[][] hasCount = new boolean[board.length][board[0].length];
    for (int i =0;i<board.length;i++){
      for (int j=0;j<board[0].length;j++){
        if (isValid(board,i,j,word,0,hasCount)){
          return true;
        }
      }
    }
    return false;
  }
  public boolean isValid(char[][] board,int x,int y ,String word,int wordIndex,boolean[][] hasCount){
    if (wordIndex==word.length()){
      return true;
    }
    if ((x>=0&&x<board.length&&y>=0&&y<board[0].length)
        &&!hasCount[x][y]
        &&board[x][y]==word.charAt(wordIndex)){
      hasCount[x][y]=true;
      boolean res = isValid(board,x,y+1,word,wordIndex+1,hasCount)||
          isValid(board,x,y-1,word,wordIndex+1,hasCount)||
          isValid(board,x+1,y,word,wordIndex+1,hasCount)||
          isValid(board,x-1,y,word,wordIndex+1,hasCount);
      hasCount[x][y]=false;
      return res;
    }else {
      return false;
    }
  }


  /**
   * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
   *
   * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
   *
   * 去重，最多保留两个重复值！
   * @param nums
   * @return
   */
  public int removeDuplicates(int[] nums) {
    int len =0;
    int sameCount=0;
    for (int i =1;i<nums.length;i++){
      if (nums[i]!=nums[len]){
        sameCount=0;
        len++;
        if (i!=len){
          nums[len]=nums[i];
        }
      }else {
        sameCount++;
        if (sameCount<=1){
          len++;
          if (i!=len){
            nums[len]=nums[i];
          }
        }
      }
    }
    return len+1;
  }
  @Test
  public void test(){
    int[] test1 = new int[]{1,1,1,2,2,3};
    int[] test2 = new int[]{0,0,1,1,1,1,2,3,3};
    int res1= removeDuplicates(test1);
    int res2 =removeDuplicates(test2);
  }

}
