package com.mmy.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * @author: mmy
 * @date: 2018/06/07
 * @description:
 */
public class Medium3 {

  /**
   * 有多余遍历，缺少一些情况下跳过
   */
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Set set = new HashSet();
    List<Integer> list;
    int left, right, result;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 3; i++) {
      if (nums.length == 0) {
        break;
      }
      for (int j = i + 1; j < nums.length - 2; j++) {
        left = j + 1;
        right = nums.length - 1;
        while (left < right) {
          result = nums[i] + nums[j] + nums[left] + nums[right] - target;
          if (result < 0) {
            left++;
          } else if (result > 0) {
            right--;
          } else {
            list = new ArrayList<>();
            list.add(nums[i]);
            list.add(nums[j]);
            list.add(nums[left]);
            list.add(nums[right]);
            set.add(list);
            left++;
            right--;
          }
        }
      }

    }
    return new ArrayList<>(set);
  }


  /**
   * 我以为one pass是一遍遍历完成。。。队列操作费时很多
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    Queue<ListNode> queue = new LinkedList<>();
    ListNode head1 = new ListNode(0);
    head1.next=head;
    ListNode headCopy =head1;
    while (headCopy.next!=null){
      ListNode node = headCopy;
      if (queue.size()<n){
        queue.offer(node);
      }else {
        queue.poll();
        queue.offer(node);
      }
      headCopy = headCopy.next;
    }
    if (queue.size()==1){
      queue.poll().next =null;
    }else if (queue.size()==0){
      return null;
    }else {
        ListNode pre = queue.poll();
        ListNode deleteNode = queue.poll();
        pre.next = deleteNode.next;
      }
    return head1.next;
  }

  /**
   * OK 这次是两次遍历的，显然两次遍历快一些
   * 我曹，好像看缘的
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd2(ListNode head, int n) {
    ListNode listNode =head;
    int len=0;
    while (listNode!=null){
      len++;
      listNode=listNode.next;
    }
    ListNode newHead = new ListNode(0);
    newHead.next=head;
    ListNode copy=newHead;
    int index=0;
    while (index<len-n){
      copy=copy.next;
      index++;
    }
    copy.next=copy.next.next;
    return newHead.next;
  }

  @Test
  public void test() {
    ListNode s = new ListNode(2);
    ListNode s1 = new ListNode(3);
    s.next=s1;
    ListNode sx = removeNthFromEnd2(s,1);
  }

  public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}
