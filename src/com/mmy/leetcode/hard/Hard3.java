package com.mmy.leetcode.hard;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * @author: mmy
 * @date: 2018/07/17
 * @description:
 */
public class Hard3 {

  /**
   * Given an unsorted integer array, find the smallest missing positive integer.
   * @param nums
   * @return
   */
  public int firstMissingPositive(int[] nums) {
    int[] temp = new int[nums.length+1];
    for (int i=0;i<nums.length;i++) {
      if (nums[i] > 0&&nums[i]<nums.length) {
        temp[nums[i]-1]=nums[i];
      }
    }
    for (int j=0;j<nums.length+1;j++){
      if (temp[j]!=j+1){
        return j+1;
      }
    }
    return 1;
  }


  /**
   * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
   * @param height
   * @return
   */
  public int trap(int[] height) {
    int left =0; int right =height.length-1;
    Set<Integer> s = new HashSet<>();
    search(s,height,left,right);
    int result=0;
    for (int low :s){
      int leftWall=low-1;
      int rightWall = low;
      while (height[leftWall]<height[leftWall-1]){
        leftWall--;
        if (leftWall==0){
          break;
        }
      }
      while (height[rightWall]<height[rightWall+1]){
        rightWall++;
        if (rightWall==height.length-1){
          break;
        }
      }
      int cloum = Math.min(height[rightWall],height[leftWall]);
      int sum = cloum*(rightWall-leftWall-1);
      for (int i=leftWall+1;i<rightWall;i++){
        sum-=((height[i]>cloum) ? cloum : height[i]);
      }
      result+=sum;
    }
    return result;
  }

  /**
   * 找极小值只过了1/3的testcase
   * @param s
   * @param height
   * @param l
   * @param r
   */
  public void search(Set s,int[] height,int l,int r){
    if (l>r){
      return;
    }
    int middle =(l+r)/2;
    if (middle>=1&&middle<=height.length-2){
      if (height[middle]<height[middle+1]
          &&height[middle]<height[middle-1]){
        s.add(middle);
      }
      search(s,height,l,middle-1);
      search(s,height,middle+1,r);
    }
  }


  /**
   * 左右坐标，若左坐标值小于右坐标，则容纳大小依靠于左坐标值
   * 此时左有落差，则计入容积
   * 同样，若右坐标值小于左，则容纳大小依靠于右坐标值
   * 右有落差，计入容积
   * @param height
   * @return
   */
  int trap2(int[] height)
  {
    int left = 0, right = height.length - 1;
    int ans = 0;
    int left_max = 0, right_max = 0;
    while (left < right) {
      if (height[left] < height[right]) {
        if (height[left]>=left_max){
          left_max=height[left];
        }else {
          ans += (left_max - height[left]);
        }
        ++left;
      }
      else {
        if (height[right] >= right_max){
          right_max = height[right];
        }else {
          ans += (right_max - height[right]);
        }
        --right;
      }
    }
    return ans;
  }

  /**
   * dynamic programing
   * @param height
   * @return
   */
  int trap3(int[] height){
    if (height==null){
      return 0;
    }
    int result = 0;
    int[] left = new int[height.length];
    int[] right =new int[height.length];
    left[0] = height[0];
    for (int i=1;i<height.length;i++){
      left[i]=Math.max(height[i],left[i-1]);
    }
    right[height.length-1]=height[height.length-1];
    for (int j=height.length-2;j>0;j--){
      right[j]=Math.max(height[j],right[j+1]);
    }
    for (int k=1;k<height.length;k++){
      result +=Math.min(left[k]-height[k],right[k]);
    }
    return result;
  }


  /**
   * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
   * '?' Matches any single character.
   * '*' Matches any sequence of characters (including the empty sequence).
   * @param s
   * @param p
   * @return
   */
  public boolean isMatch(String s, String p) {
    char[] charS = s.toCharArray();
    char[] charP = p.toCharArray();

    boolean[][] ismatch = new boolean[charS.length+1][charP.length+1];
    ismatch[0][0]=true;

    for (int i =1;i<=charP.length;i++){
      if (charP[i-1]=='*'&&ismatch[0][i-1]){
        ismatch[0][i]=true;
      }
    }
    for (int i = 0;i<charS.length;i++){
      for (int j =0;j<charP.length;j++){
        if (charP[j]=='?'||charP[j]==charS[i]){
          ismatch[i+1][j+1]=ismatch[i][j];
        }
        if (charP[j]=='*'){
          ismatch[i+1][j+1]=ismatch[i][j+1]||ismatch[i+1][j];
        }
      }
    }
    return ismatch[charS.length][charP.length];
  }

  /**
   * Given an array of non-negative integers, you are initially positioned at the first index of the array.
   *
   * Each element in the array represents your maximum jump length at that position.
   *
   * Your goal is to reach the last index in the minimum number of jumps.
   * 二维数组DP
   * @param nums
   * @return
   */
  public int jump(int[] nums) {
    if (nums.length==0){
      return 0;
    }
    int dis[][] = new int[nums.length+1][nums.length+1];
    for (int i=0;i<=nums.length;i++){
      for (int j=0;j<=nums.length;j++){
        if (i==j){
          dis[i][j]=0;
        }else if (i<j){
          dis[i][j]=Integer.MAX_VALUE;
        }else {
          dis[i][j]=-1;
        }
      }
    }
    for (int i=0;i<nums.length-1;i++){
      for (int j=i+1;j<nums.length;j++){
        for (int k = 1;k<=nums[j-1];k++){
          if ((j+k)<=nums.length){
            if (dis[i+1][j+k]>dis[i+1][j]+1){
              dis[i+1][j+k]=dis[i+1][j]+1;
            }
          }

        }
      }
    }
    return dis[1][nums.length];
  }

  /**
   * 最快答案，没看懂
   * @param A
   * @return
   */
  public int jump2(int[] A) {
    if (A == null || A.length == 0) {
      return -1;
    }
    int start = 0, end = 0, jumps = 0;
    while (end < A.length - 1) {
      jumps++;
      int farthest = end;
      for (int i = start; i <= end; i++) {
        if (A[i] + i > farthest) {
          farthest = A[i] + i;
        }
      }
      start = end + 1;
      end = farthest;
    }
    return jumps;
  }

  /**
   * 一维数组DP
   * @param nums
   * @return
   */
  public int jump3(int[] nums) {
    if (nums.length==0){
      return 0;
    }
    int dis[] = new int[nums.length+1];
    dis[0]=0;
    dis[1]=0;
    for (int i=2;i<=nums.length;i++){
      dis[i]=Integer.MAX_VALUE;
    }
    int j =1;
    while (j<nums.length){
      for (int k = 1;k<=nums[j-1];k++){
        if ((j+k)<=nums.length){
          if (dis[j+k]>dis[j]+1){
            dis[j+k]=dis[j]+1;
          }
        }

      }
    }
    return dis[nums.length];
  }


  @Test
  public void test(){
    int[] nums = new int[]{2,3,1,1,4};
    int j  = jump2(nums);
  }
}
