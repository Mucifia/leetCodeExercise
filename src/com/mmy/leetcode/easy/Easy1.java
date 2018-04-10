package com.mmy.leetcode.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Easy1 {

    /**
     * Two sum
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum(int[] nums, int target) {
        for(int i =0;i<nums.length;i++)
            for (int j=i+1;j<nums.length;j++)
                if(nums[i]+nums[j]==target)
                    return new int[]{i,j};
        return null;
    }

    /**
     * reverse a  32 bit interger
     * attention : must consider the range of INTERGER
     * or your operation may overflow
     * @param x
     * @return
     */
    public int reverse(int x) {
        int temp = x > 0  ? x :-(1*x);
        int end  = temp%10;
        int reversed =0;
        while (temp!=0){
            if(reversed >Integer.MAX_VALUE/10||reversed<Integer.MIN_VALUE/10){
                return 0;
            }
            reversed=reversed*10+end;
            temp=temp/10;
            end=temp%10;
        }

        return reversed*(x>0 ? 1 : -1);
    }


    /**
     * Is an Integer is a Palindrome Integer
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        int tailNum=0;
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        while(x>tailNum){
            tailNum=tailNum*10+x%10;
            x=x/10;
            if (x==tailNum/10||x==tailNum){
                return true;
            }
        }
        return false;
    }



    /**
     * Given a roman numeral, convert it to an integer.
     Input is guaranteed to be within the range from 1 to 3999
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        StringBuilder string = new StringBuilder(s);
        int[] _num = {1,5,10,50,100,500,1000};
        String[] _string = {"I","V","X","L","C","D","M"};
        List list = Arrays.asList(_string);
        int out=0;
        for (int i =0;i<string.length();i++){
            int var1=list.indexOf(string.substring(i,i+1));
            int var2=i+2>string.length() ? 0 : list.indexOf(string.substring(i+1,i+2));
            if (var1<var2){
                out=out+_num[var2]-_num[var1];
                i++;
            }else{
                out=out+_num[var1];
            }
        }
        return out;
    }
    @Test
    public void test(){
        boolean a = isPalindrome(123);
        boolean b =isPalindrome(0);
        boolean c = isPalindrome(-12321);
        boolean d = isPalindrome(10);
    }

}
