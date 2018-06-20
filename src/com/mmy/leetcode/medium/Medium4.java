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

  /**
   * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
   *
   * Return the quotient after dividing dividend by divisor.
   *
   * The integer division should truncate toward zero.
   *
   * hint: we can use bit calculation or use a int to record times
   * @param dividend
   * @param divisor
   * @return
   */
  public int divide(int dividend, int divisor) {
    if (dividend==Integer.MIN_VALUE&&divisor==-1){
      return Integer.MAX_VALUE;
    }
    //key point: make Integer.MIN_VALUE reverse not overflow
    long tempDividend=Math.abs((long)dividend);
    long tempDivisor=Math.abs((long)divisor);
    int result=0;
    int times;
    long divisorCopy=tempDivisor;
    boolean beyondZero = (dividend>0&&divisor<0)||(dividend<0&&divisor>0)?false:true;
    while (tempDividend>tempDivisor){
      divisorCopy=tempDivisor;
      times = 1;
      while (tempDividend>=divisorCopy<<1){
        divisorCopy = divisorCopy<<1;
        times = times <<1;
        //also can change to divisorCopy +=divisorCopy
        //times +=time
      }
      tempDividend = tempDividend-divisorCopy;
      result=result+times;
    }
    if (tempDividend==tempDivisor){
      result++;
    }
    return beyondZero?result:-1*result;
  }


  @Test
  public void test() {
    int a  = divide(-2147483648,1);
  }
}


