package com.mmy.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Test;

/**
 * @author: mmy
 * @date: 2018/06/10
 * @description:
 */
public class Easy2 {

  public boolean isValid(String s) {
    Stack<Character> stack = new Stack();
    Map<Character, Character> map = new HashMap();
    map.put('{', '}');
    map.put('[', ']');
    map.put('(', ')');
    for (int i = 0; i < s.length(); i++) {
      if (map.get(s.charAt(i)) != null) {
        stack.push(s.charAt(i));
      } else {
        if (!stack.empty()) {

          char c = stack.peek();
          if (map.get(c) == s.charAt(i)) {
            stack.pop();
          } else {
            return false;
          }
        } else {
          return false;
        }
      }
    }
    return stack.empty();
  }


  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(-1);
    ListNode copy = head;
    while (l1 != null && l2 != null) {
      if (l1.val >= l2.val) {
        head.next = l2;
        l2 = l2.next;
        head = head.next;
      } else {
        head.next = l1;
        l1 = l1.next;
        head = head.next;
      }
    }
    if (l1 == null) {
      head.next = l2;
    }
    if (l2 == null) {
      head.next = l1;
    }
    return copy.next;
  }


  /**
   * 第一次思路是，元素逐渐左移，最后多余操作过多
   */
  public int removeDuplicates(int[] nums) {
    int index = 0;
    int count = 0;
    for (int i = 1; i < nums.length; ) {

      if (nums[i] == nums[index]) {
        if (count > nums.length - index) {
          break;
        }
        for (int j = i; j < nums.length - 1; j++) {
          nums[j] = nums[j + 1];
        }
        count++;
      } else {
        index = i;
        i++;
        count = 0;
      }
    }
    return index + 1;
  }

  /**
   * 看了网上的优秀答案，启发，直接用不同值替换前n个即可，学到了
   */
  public int removeDuplicates2(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int size = 1;
    int num = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (num == nums[i]) {
        continue;
      }
      nums[size++] = nums[i];
      num = nums[i];
    }
    return size;
  }


  /**
   * Given an array nums and a value val, remove all instances of that value in-place and return the
   * new length. 学以致用，嘻嘻
   */
  public int removeElement(int[] nums, int val) {
    int size = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[size++] = nums[i];
      }
    }
    return size;
  }

  /**
   * 原本通过遍历可以得到，但是突然想到了kmp算法，就将其应用到此处
   * @param haystack
   * @param needle
   * @return
   */
  public int strStr(String haystack, String needle) {
    if (needle.length() == 0 || needle.length() == 0) {
      return 0;
    }
    int[] next = new int[needle.length()];
    getNextTable(needle, next);
    int k = -1;
    for (int i = 0; i < haystack.length(); i++) {
      /**
       *此处应该理解为，到haystatck第i个的时候，needle匹配到了第k个  `
       */
      while (k > -1 && needle.charAt(k + 1) != haystack.charAt(i))//ptr和str不匹配，且k>-1（表示ptr和str有部分匹配）
      {
        k = next[k];//往前回溯
      }
      if (needle.charAt(k + 1) == haystack.charAt(i)) {
        k = k + 1;
      }
      if (k == needle.length() - 1)//说明k移动到ptr的最末端
      {
        return i - needle.length() + 1;//返回相应的位置
      }
    }
    return -1;
  }

  public void getNextTable(String string, int[] next) {
    int len = string.length();
    int k = -1;
    next[0] = -1;
    for (int q = 1; q < len; q++) {
      //进入循环后，k为next[q-1]的最长相同前后缀
      //下面比较为对相同前后缀补位，若补位后值，则next[q]的最长前后缀值为next[q-1]+1
      //若补位后不同，则寻找next[q-1]的最小前后缀，进行补位，重复匹配
      //若仔细模拟一遍，发现完全符合道理！有一部分动态规划的思想在其中。
      while (k > -1 && string.charAt(k + 1) != string.charAt(q)) {
        k = next[k];
      }

      if (string.charAt(k + 1) == string.charAt(q)) {
        k = k + 1;
      }
      next[q] = k;
    }
  }

  /**
   * binarySearch 注意边角料。不是right = nums.length就是while(left<right)
   * @param nums
   * @param target
   * @return
   */
  public int searchInsert(int[] nums, int target) {
    int left = 0,right = nums.length;
    if (nums.length==1){
      return nums[0]>target?0:1;
    }
    while (left<right){
      int middle = (left+right)/2;
      if (nums[middle]<target){
        left=middle+1;
      }else if(nums[middle]>target){
        right=middle;
      }else {
        return middle;
      }
    }
    return left;
  }



  @Test
  public void test() {
    int[] a = new int[]{1,3,5,6};
    int index = searchInsert(a,2);
  }
}

class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}