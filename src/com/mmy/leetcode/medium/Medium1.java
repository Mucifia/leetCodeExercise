package com.mmy.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Medium1 {

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){this.val=x;}
    }
    /**
     * ADD TWO NUMER
     * @param l1
     * @param l2
     * @author mmy
     * @date 2017/12/18
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode h=new ListNode(0);
        ListNode j=h;
        ListNode pre=h,curr=h;
        boolean overTen=false;
        while(l1!=null&&l2!=null){
            int temp;
            if(overTen)
                temp=l1.val+l2.val+1;
            else
                temp=l1.val+l2.val;
            if(temp<10){
                overTen=false;
            }else{
                overTen=true;
            }
            curr=new ListNode(temp/10);
            h.val=temp%10;
            h.next=curr;
            pre = h;
            h=h.next;
            l1=l1.next;
            l2=l2.next;
        }
        if (h.val==0)
            pre.next=null;
        if (l1!=null){
            if(overTen)
                l1.val=l1.val+1;
            pre.next=l1;
        }else if(l2!=null){
            if(overTen)
                l2.val=l2.val+1;
            pre.next=l2;
        }
        for (ListNode i = j;i!=null;i=i.next){
            if (i.val==10){
                if(i.next!=null){
                    i.val=0;
                    i.next.val=i.next.val+1;
                }else {
                    i.val = 0;
                    i.next = new ListNode(1);
                }
            }
        }
        return j;

    }

    /**
     * leetCode标准答案
     * @param l1
     * @param l2
     * @author leetcode
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    /**
     * 优化版本
     * @author mmy
     * @param l1
     * @param l2
     * @date 2017/12/19
     * @return
     */
    public ListNode addTwoNumer2(ListNode l1, ListNode l2){
        ListNode h=new ListNode(0);
        ListNode pre=h,curr=h;
        boolean overTen=false;
        while(l1!=null&&l2!=null){
            int temp = overTen ? l1.val+l2.val+1 : l1.val+l2.val;
            overTen = temp<10 ? false : true;
            curr.next=new ListNode(temp/10);
            curr.val=temp%10;
            pre = curr;
            curr=curr.next;
            l1=l1.next;
            l2=l2.next;
        }
        if (curr.val==0)
            pre.next=null;
        if (l1!=null){
            if(overTen)l1.val=l1.val+1;
            pre.next=l1;
        }else if(l2!=null){
            if(overTen)l2.val=l2.val+1;
            pre.next=l2;
        }
        for (ListNode i = pre;i!=null;i=i.next){
            if (i.val==10){
                i.val=0;
                if(i.next!=null){
                    i.next.val=i.next.val+1;
                }else {
                    i.next = new ListNode(1);
                }
            }
        }
        return h;
    }


    /** https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
     * 求最长子串的长度
     * 字串中无重复字符
     * @param s
     * @return
     * 2017/12/21
     * 暴力算法
     * Test result O(n2）
     * Time Limit Exceeded（执行完983个测试用例5s）
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0)return 0;
        int startindex,endindex,longestLength=1,temp=1;
        for (startindex=0;startindex<s.length()-longestLength;startindex++)
            for (endindex=startindex+1;endindex<s.length();){
            if (s.substring(startindex,endindex).contains(s.substring(endindex,endindex+1))){
                startindex=startindex+s.substring(startindex,startindex+longestLength).indexOf(s.charAt(endindex))+1;
                endindex=startindex+1;
                if (longestLength>temp)
                    temp=longestLength;
                longestLength=1;
            }
            else{
                longestLength++;
                endindex++;
                }
            }
            return temp > longestLength ? temp : longestLength;
    }

    /**
     * 滑动窗口算法
     * 92ms
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_UseSlideWindow(String s){
        Set<Character> set = new HashSet<>();
        int i=0,j=0,longest=0;
        while(i<s.length() && j<s.length()){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                if (longest<(j-i))
                    longest=j-i;
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return longest;
    }

    /**
     * 字符串最长回文子串
     *
     * @param s
     * @return
     */
    public static String  longestPalindrome(String s) {
        char[] charString = s.toCharArray();
        int longest=1,longl=0,longr=1;
        for (int i =0;i<charString.length;i++){
            int l1=i,l2=i,r1=i,r2=i+1;
            int[] a1,a2;
            a1=Palindrome(l1,r1,charString);
            a2=Palindrome(l2,r2,charString);
            if((a1[1]-a1[0]+1)>longest||(a2[1]-a2[0]+1)>longest){
            if((a1[1]-a1[0])>=(a2[1]-a2[0])){
                longest=a1[1]-a1[0]+1;
                longl=a1[0];longr=a1[1]+1;
            }else{
                if(a2[1]-a2[0]!=1){
                    longest=a2[1]-a2[0]+1;
                    longl=a2[0];longr=a2[1]+1;
                }else{
                    if(s.charAt(a2[1])==s.charAt(a2[0])){
                        longest=2;
                        longl=a2[0];longr=a2[1]+1;
                    }
                }
            }
            }
        }
        if(longest==1)
            return s.substring(0,1);
        return s.substring(longl,longr);
    }

    public static int[] Palindrome(int left , int right , char[] strings){
        if(left<=-1||right>=strings.length) {
            return new int[]{left + 1, right - 1};
        }
        if(left!=0&&right!=strings.length-1&&strings[left-1]!=strings[right+1]){
            return new int[]{left,right};
        }
        if(strings[left]!=strings[right]&&(right-left)>1){
            return  new int[]{left+1,right-1};
        }
        if(strings[left]!=strings[right]&&(right-left)==1){
            return  new int[]{left,right};
        }
        return Palindrome(left-1,right+1,strings);

    }


    /**
     * ZigZag Conversion
     * @param s
     * @param numRows
     * @return
     */

    public String convert(String s, int numRows) {
        char[] chars = s.toCharArray();
        int[][] step=new int[(numRows+1)/2][2];
        if (chars.length<2||numRows==1||numRows>=chars.length){
            return s;
        }
        char[] newchars = new char[chars.length];
        int index=0;
        for (int i=0;i<(numRows+1)/2;i++){
            step[i][0] = 2*(i>numRows-i ? i : numRows-i)-1;
            step[i][1] = 2*numRows -step[i][0];
            if(i==0) {
                step[i][1] = step[i][0];
            }
        }
        for (int j =0;j<numRows;j++){
            int col=0;
            if(j<(numRows+1)/2){
                for (int k=j;k<chars.length;k=k+step[j][(col-1)%2]-1){
                    newchars[index]=chars[k];
                    index++;
                    col++;
                }
            }else{
                for (int k=j;k<chars.length;k=k+step[numRows-1-j][col%2]-1){
                    newchars[index]=chars[k];
                    index++;
                    col++;
                }
            }
        }
        return new String(newchars);
    }

    /**
     * String to integer
     * consider all situation of String and ignore or transform the characters
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        String newString = str.trim();
        boolean minus = false;
        int index = 0;int total=0;
        if(newString.length()==0){
            return 0;
        }
        if(newString.charAt(index) =='+' || newString.charAt(index)=='-'){
            minus =(newString.charAt(index)=='+' ? false : true);
            index++;
        }
        while(index<newString.length()){
            if (newString.charAt(index)<'0'||newString.charAt(index)>'9'){
                break;
            }else{
                int num=newString.charAt(index)-'0';
                if (total>Integer.MAX_VALUE/10 || (total==Integer.MAX_VALUE/10 && Integer.MAX_VALUE%10<=num)){
                    return minus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                total=total*10+num;
                index++;
            }
        }
        return total*(minus ? -1:1);
    }
    @Test
    public void test(){
        int a = myAtoi("2147483648");
    }

    public static void main(String[] args) {
        String a = longestPalindrome("aaabaaaa");
        String b = longestPalindrome("abcda");
        String c = longestPalindrome("babad");
        String d = longestPalindrome("bbbbbbbbgggggg");
        String e = longestPalindrome("ccc");
        String f = longestPalindrome("bcc");
        String g = longestPalindrome("abadd");
        String h = longestPalindrome("bppb1sooos");
    }

}
