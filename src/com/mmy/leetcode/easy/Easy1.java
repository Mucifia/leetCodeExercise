package com.mmy.leetcode.easy;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
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

    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * @param strs
     * @return
     */
    /**
     * 典型的代码水平无法实现想法的例子！
     * 在后面弥补了大量补偿代码，代码量过少的提现。
     * ********！！！！！！！！！
     * @param strs
     * @return
     */
//    public String longestCommonPrefix(String[] strs) {
//        StringBuilder string = new StringBuilder();
//        if (strs.length>0){
//            int index=0,len=strs[0].length();
//            string.append(strs[0].length()>0 ? strs[0].charAt(0):"");
//            String temp;
//            for (int i =1;i<strs.length;i++){
//                if (strs[i].length()==len&&index<len-1){
//                    temp=string.toString().concat(String.valueOf(strs[0].charAt(index+1)));
//                    if (strs[i].indexOf(temp)==0){
//                        index++;
//                        string.append(strs[0].charAt(index));
//                    }
//                }else if (strs[i].length()<len){
//                    len=strs[i].length();
//                    index=strs[i].length()-1;
//                    while (index!=0&&strs[i].indexOf(string.toString())!=0){
//                        string.delete(index,index+1);
//                        index--;
//                    }
//                }else {
//                    while (index!=-1&&strs[i].indexOf(string.toString())!=0){
//                        string.delete(index,index+1);
//                        index--;
//                    }
//                }
//
//            }
//        }
//        return string.toString();
//    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    @Test
    public void test(){
        String a = longestCommonPrefix(new String[]{"123","124","125"});
        String b =longestCommonPrefix(new String[]{"23","24","25"});
        String c = longestCommonPrefix(new String[]{"1","124","125"});
        String d = longestCommonPrefix(new String[]{""});
    }

}
