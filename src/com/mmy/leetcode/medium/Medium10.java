package com.mmy.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

/**
 * @author: mmy
 * @date: 2018/08/21
 * @description:
 */
public class Medium10 {

  /**
   * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
   * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
   * @param nums
   */
  public void sortColors(int[] nums) {
    int redIndex = 0;
    int blueIndex = nums.length-1;
    int start =redIndex;
    while (start<=blueIndex){
      if (nums[start]==0){
        if (start!=redIndex){
          swap(nums,start,redIndex);
        }else {
          start++;
        }
        redIndex++;
      }else if (nums[start]==2){
        if(start!=blueIndex){
          swap(nums,start,blueIndex);
        }else {
          start++;
        }
        blueIndex--;
      }else {
        start++;
      }
    }
  }
  void swap(int[] nums,int x,int y){
    int temp = nums[x];
    nums[x]=nums[y];
    nums[y]=temp;
  }

  /**
   * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
   * Input: n = 4, k = 2
   * Output:
   * [
   *   [2,4],
   *   [3,4],
   *   [2,3],
   *   [1,2],
   *   [1,3],
   *   [1,4],
   *   TLS
   * @param n
   * @param k
   * @return
   */
  public List<List<Integer>> combine(int n, int k) {
    Set<List<Integer>> result = new HashSet<>();
    addResult(result,new ArrayList<>(),n,k,1);
    return new ArrayList<>(result);
  }

  public void addResult(Set<List<Integer>> result , List<Integer> hasCount ,int n,int k, int start){
    if(hasCount.size()<k){
      for (int i =start;i<=n;i++){
        hasCount.add(i);
        addResult(result,new ArrayList<>(hasCount),n,k,i+1);
        hasCount.add(hasCount.size()-1);
      }
    }else {
    result.add(hasCount);
    }

  }

  /**
   * my anwser
   * TLS
   *
   * @param n
   * @param k
   * @return
   */
  public List<List<Integer>> combine2(int n, int k) {
    List<Integer> rest = new ArrayList<>();
    for (int i =0;i<n;i++){
      rest.add(i+1);
    }
    Set<List<Integer>> result = new HashSet<>();
    addResult(result,new ArrayList<>(),rest,k);
    return new ArrayList<>(result);
  }

  public void addResult(Set<List<Integer>> result , List<Integer> hasCount ,List<Integer> rest, int k){
    if(hasCount.size()<k){
      int restCount =rest.size();
      for (int i =0;i<restCount;i++){
        int pivot = rest.remove(i);
        hasCount.add(pivot);
        addResult(result,new ArrayList<>(hasCount),rest,k);
        rest.add(i,pivot);
        hasCount.remove(hasCount.size()-1);
      }
    }else {
      Collections.sort(hasCount);
      result.add(hasCount);
    }
  }

  @Test
  public void test(){
//    int[] test1 = new int[]{2,0,2,1,1,0};
//    sortColors(test1);
    int n =4;
    int k=2;
    List<List<Integer>> result = combine(n,k);
  }
}
