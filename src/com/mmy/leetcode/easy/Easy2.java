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


  @Test
  public void test() {
    isValid("}");
  }
}

class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}