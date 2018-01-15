import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


public class Medium {

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

    @Test
    public  void main(){
    }

}
