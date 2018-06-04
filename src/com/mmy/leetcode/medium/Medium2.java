package com.mmy.leetcode.medium;

import com.mmy.leetcode.medium.Medium1.ListNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author: mmy
 * @date: 2018/04/09
 * @description:
 */
public class Medium2 {

    /**
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
     * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
     * @param height
     * @return
     */
     /*
     超时了
     */
        public int maxArea(int[] height) {
            int largest = 0;
            for (int i =0;i<height.length;i++)
                for (int j =i+1;j<height.length;j++){
                int containerArea = height[i] >= height[j] ? height[j]*(j-i) :height[i]*(j-i);
                if (containerArea>largest){
                    largest=containerArea;
                }
            }
                return largest;
        }

    /**
     * plan2
     *两侧向里逼进，即两边的较低点向中心缩，试图获得更大面积的可能
     * 若两边较高边内缩，则所容必然小于或等于之前
     * 若两边较低边内缩，则有可能大于之前容积，
     */
    public int maxArea2(int[] height){
        int l =0, r = height.length-1,maxArea=0;
        while (l<r){
            int containerArea = height[l] >= height[r] ? height[r]*(r-l) :height[l]*(r-l);
            if (containerArea>maxArea) maxArea=containerArea;
            if (height[l]<=height[r]){
                l++;
            }else{
                r--;
            }
        }
        return maxArea;
    }

    /**
    Given an integer, convert it to a roman numeral.
    Input is guaranteed to be within the range from 1 to 3999.
     Ⅰ（1）、Ⅴ（5）、Ⅹ（10）、Ⅼ（50）、Ⅽ（100）、Ⅾ（500）、Ⅿ（1000）
    **/
    /**
     * use recursion
     * 里面数据结构优化下，可能会更快
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder romanString = new StringBuilder();
        transfer(num,6,romanString);
        return  romanString.toString();
    }

    private void transfer(int input,int index , StringBuilder output){
        int[] _num = {1,5,10,50,100,500,1000};
        String[] _string = {"I","V","X","L","C","D","M"};
        if (input==0) return;
        int head = input/_num[index];
        int _numWithoutHead = input%_num[index];
        if (head>0&&head<4){
            for (int i=0;i<head;i++) output.append(_string[index]);
        }else if (head>0){
            List list =Arrays.asList(_string);
            String tail="";
            if (output.length()!=0&&list.indexOf(output.subSequence(output.length()-1,output.length()))-index==1){
                output.delete(output.length()-1,output.length());
                tail = _string[index].concat(_string[index+2]);
            }else{
                tail=_string[index].concat(_string[index+1]);
            }
            output.append(tail);
        }
        transfer(_numWithoutHead,index-1,output);
    }

    //讨论区的某解法，学到了
//    public enum Type{
//        M(1000),CM(900),D(500),CD(400),C(100),XC(90),L(50),XL(40),X(10),IX(9),V(5),IV(4),I(1);
//        private final int value;
//        Type(int value) {
//            this.value = value;
//        }
//    };
//    public String intToRoman2(int num) {
//        StringBuilder output = new StringBuilder();
//        for (Type t:Type.values()) {
//            while (num>=t.value) {
//                output.append(t);
//                num -= t.value;
//            }
//        }
//        return output.toString();
//    }

    /*
    Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
    Find all unique triplets in the array which gives the sum of zero.
    Note: The solution set must not contain duplicate triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set set = new HashSet();
        Arrays.sort(nums);//之前只知道Collection.Sort,现在知道了新的方法
        //先固定一个值，随机从剩余部分两端滑动，逻辑理清就可以解
        //num[i]<0,减少循环次数
        //nums.length>0， []情况
        //i<nums.length-2 方式数组越界
        for (int i=0;i<nums.length-2&&nums.length>0&&nums[i]<0;i++){
            int left = i+1;
            int right = nums.length-1;
            while (i<left&&left<right){
                if (nums[i]+nums[left]+nums[right]<0){
                    left++;
                }else if (nums[i]+nums[left]+nums[right]>0){
                    right--;
                }else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    set.add(list);
                    left++;
                    right--;
                }
            }
        }

        return new ArrayList<>(set);
    }
    @Test
    public void test(){
        List<List<Integer>> listList = threeSum(new int[]{2,1,-3,0,0,-2,4,-2});
        List<List<Integer>> listList2 = threeSum(new int[]{0,0,0});

        }

}
