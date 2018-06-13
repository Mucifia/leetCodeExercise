package com.mmy.leetcode.medium;


import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.jupiter.api.Test;

/**
 * @author: mmy
 * @date: 2018/06/12
 * @description:
 */
public class Medium4 {

  public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  /**
   * Given a linked list, swap every two adjacent nodes and return its head.
   *
   * Node:Your algorithm should use only constant extra space. You may not modify the values in the
   * list's nodes, only nodes itself may be changed.
   */
  public ListNode swapPairs(ListNode head) {
    Queue<ListNode> queue = new ArrayDeque<>(2);
    ListNode newHead = new ListNode(-1);
    newHead.next = head;
    ListNode copy = newHead;
    while (newHead != null) {
      if (queue.size() < 2) {
        queue.offer(newHead);
      } else {
        ListNode first = queue.poll();
        ListNode second = queue.poll();
        second.next = newHead.next;
        newHead.next = second;
        first.next = newHead;
        queue.offer(second);
        newHead = second;
      }
      newHead=newHead.next;
    }
    return copy.next;
  }

  @Test
  public void test() {
    ListNode s = swapPairs(null);

  }
}


