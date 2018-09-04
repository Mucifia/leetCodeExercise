package com.mmy.leetcode.medium;

import java.util.List;
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
   * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells
   * are those horizontally or vertically neighboring. The same letter cell may not be used more
   * than once.
   *
   * board = [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ]
   *
   * Given word = "ABCCED", return true. Given word = "SEE", return true. Given word = "ABCB",
   * return false.
   */
  public boolean exist(char[][] board, String word) {
    boolean[][] hasCount = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (isValid(board, i, j, word, 0, hasCount)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean isValid(char[][] board, int x, int y, String word, int wordIndex,
      boolean[][] hasCount) {
    if (wordIndex == word.length()) {
      return true;
    }
    if ((x >= 0 && x < board.length && y >= 0 && y < board[0].length)
        && !hasCount[x][y]
        && board[x][y] == word.charAt(wordIndex)) {
      hasCount[x][y] = true;
      boolean res = isValid(board, x, y + 1, word, wordIndex + 1, hasCount) ||
          isValid(board, x, y - 1, word, wordIndex + 1, hasCount) ||
          isValid(board, x + 1, y, word, wordIndex + 1, hasCount) ||
          isValid(board, x - 1, y, word, wordIndex + 1, hasCount);
      hasCount[x][y] = false;
      return res;
    } else {
      return false;
    }
  }


  /**
   * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most
   * twice and return the new length.
   *
   * Do not allocate extra space for another array, you must do this by modifying the input array
   * in-place with O(1) extra memory.
   *
   * 去重，最多保留两个重复值！
   */
  public int removeDuplicates(int[] nums) {
    int len = 0;
    int sameCount = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[len]) {
        sameCount = 0;
        len++;
        if (i != len) {
          nums[len] = nums[i];
        }
      } else {
        sameCount++;
        if (sameCount <= 1) {
          len++;
          if (i != len) {
            nums[len] = nums[i];
          }
        }
      }
    }
    return len + 1;
  }


  public boolean search(int[] nums, int target) {
    if (nums.length == 0) {
      return false;
    }
    int pivot = 0;
    while (pivot < nums.length - 1) {
      if (nums[pivot + 1] >= nums[pivot]) {
        pivot++;
      } else {
        break;
      }
    }
    return binarySearch(0, pivot, nums, target)
        || binarySearch(pivot + 1, nums.length - 1, nums, target);
  }

  boolean binarySearch(int left, int right, int[] nums, int target) {
    int l = left;
    int r = right;
    while (l <= r) {
      int m = (l + r) / 2;
      if (nums[m] == target) {
        return true;
      } else if (nums[m] < target) {
        return binarySearch(m + 1, r, nums, target);
      } else {
        return binarySearch(l, m - 1, nums, target);
      }
    }
    return false;
  }


  /**
   * one time binarySearch cost 0ms
   */
  public boolean search2(int[] nums, int target) {
    if (nums.length == 0) {
      return false;
    }
    int start = 0, end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        return true;
      }
      if (nums[mid] > nums[start]) {
        if (nums[mid] > target && nums[start] <= target) {
          end = mid - 1;
        } else {
          start = mid + 1;
        }
      } else if (nums[mid] < nums[start]) {
        if (nums[mid] < target && nums[end] >= target) {
          start = mid + 1;
        } else {
          end = mid - 1;
        }
      } else {
        start++;
      }
    }
    return nums[start] == target;
  }


  public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  /**
   * 82. Remove Duplicates from Sorted List II Given a sorted linked list, delete all nodes that
   * have duplicate numbers, leaving only distinct numbers from the original list.
   */
  public ListNode deleteDuplicates(ListNode head) {
    if (head==null){
      return null;
    }
    ListNode newHead = head;
    ListNode newTail = newHead;
    boolean isDuplicate =false;
    ListNode tailPre =null;
    while (newHead.next!=null){
      newHead=newHead.next;
      if (newHead.val==newTail.val){
        isDuplicate=true;
        continue;
      }else {
        if (isDuplicate){
          if (tailPre!=null){
            tailPre.next=null;
          }else {
            head=null;
          }
          newTail=tailPre;
        }
        tailPre=newTail;
        if (newTail!=null){
          newTail.next=newHead;
          newTail=newTail.next;
        }else {
          newTail=newHead;
          head=newTail;
        }
        isDuplicate=false;
      }
    }
    if (isDuplicate){
      if (tailPre!=null){
        tailPre.next=null;
      }else {
        head=null;
      }
    }
    return head;
  }


  /**  Given a sorted linked list, delete all duplicates such that each element appear only once.
   *
   */
  public ListNode deleteDuplicates2(ListNode head) {
    if (head==null){
      return null;
    }
    ListNode newHead = head;
    ListNode tail = newHead;
    while (newHead.next!=null){
      newHead=newHead.next;
      if(newHead.val==tail.val){
        continue;
      }else {
        tail.next=newHead;
        tail=tail.next;
      }
    }
    tail.next=null;
    return head;
  }

  //ont point!
//  public ListNode deleteDuplicates(ListNode head) {
//    ListNode list = head;
//    while(list!=null&&list.next!=null){
//      if(list.val == list.next.val) list.next = list.next.next;
//      else list = list.next;
//    }
//    return head;
//  }
  @Test
  public void test() {
//    int[] test1 = new int[]{1,1,1,2,2,3};
//    int[] test2 = new int[]{0,0,1,1,1,1,2,3,3};
//    int res1= removeDuplicates(test1);
//    int res2 =removeDuplicates(test2);
//    int[] test = new int[]{3, 1};
//    int target = 0;
//    boolean res = search(test, target);
    ListNode node1 = new ListNode(3);
    node1.next = new ListNode(3);
//    node1.next.next = new ListNode(3);
//    node1.next.next.next = new ListNode(3);
//    node1.next.next.next.next = new ListNode(4);
//    node1.next.next.next.next.next = new ListNode(4);
//    node1.next.next.next.next.next.next= new ListNode(5);
    ListNode result = deleteDuplicates2(node1);
  }

}
