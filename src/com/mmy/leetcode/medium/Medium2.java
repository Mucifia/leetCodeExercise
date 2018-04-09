package com.mmy.leetcode.medium;

import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void test(){
            int are = maxArea(new int[]{0,3});
        }

}
