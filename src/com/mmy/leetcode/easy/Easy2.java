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
    ListNode head = new ListNode(-1) ;
    ListNode copy=head;
    while (l1!=null&&l2!=null){
      if (l1.val>=l2.val){
        head.next=l2;
        l2=l2.next;
        head=head.next;
      }else {
        head.next=l1;
        l1=l1.next;
        head=head.next;
      }
    }
    if (l1==null){
      head.next=l2;
    }
    if (l2==null){
      head.next=l1;
    }
    return copy.next;
  }


  /**
   * 第一次思路是，元素逐渐左移，最后多余操作过多
   * @param nums
   * @return
   */
  public int removeDuplicates(int[] nums) {
    int index =0;
    int count=0;
    for (int i =1;i<nums.length;){

      if (nums[i]==nums[index]){
        if (count>nums.length-index){
          break;
        }
        for (int j =i;j<nums.length-1;j++){
          nums[j]=nums[j+1];
        }
        count++;
      }else {
        index=i;
        i++;
        count=0;
      }
    }
    return index+1;
  }

  /**
   * 看了网上的优秀答案，启发，直接用不同值替换前n个即可，学到了
   * @param nums
   * @return
   */
  public int removeDuplicates2(int[] nums){
    if (nums.length==0){
      return 0;
    }
    int size=1;
    int num =nums[0];
    for (int i =1;i<nums.length;i++){
      if (num==nums[i]){
        continue;
      }
      nums[size++]=nums[i];
      num=nums[i];
    }
    return size;
  }

  @Test
  public void test() {
    int j =removeDuplicates2(new int[]{1,1,2});
  }
}

class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}