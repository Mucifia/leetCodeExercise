package com.mmy.leetcode.hard;

import org.junit.Test;

public class Hard1 {
    /**
     * 自己的解法，先将其变为有序，再取其中位数
     * 时间复杂度为nums1.length+nums2.length
     * 空间复杂度为nums1.length+nums2.length
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double[] num3 = new double[nums1.length+nums2.length];
        int i=0,j=0;
        int q =0;
        while(i<nums1.length&&j<nums2.length){
            if(nums1[i]<=nums2[j]){
                num3[q]=nums1[i];
                i++;
            }else{
                num3[q]=nums2[j];
                j++;
            }
            q++;
        }
        if(i<nums1.length){
            while(i< nums1.length){
                num3[q]=nums1[i];
                q++;
                i++;
            }
        }
        if(j<nums2.length){
            while(j< nums2.length){
                num3[q]=nums2[j];
                q++;
                j++;
            }
        }
        int median = (nums1.length+nums2.length)/2;
        int isDouble = (nums1.length+nums2.length)%2;

        if(isDouble==0) {
            return (num3[median] + num3[median - 1])/2;
        }else{
            return num3[median];
        }
    }

    /**
     * 官方答案
     * 空间复杂度为1 优于我
     *https://leetcode.com/problems/median-of-two-sorted-arrays/solution/
     * @param A
     * @param B
     * @return
     */
    public double OfficialfindMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
            }
            int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = halfLen - i;
                if (i < iMax && B[j-1] > A[i]){
                    iMin = iMin + 1; // i is too small
                }
                else if (i > iMin && A[i-1] > B[j]) {
                    iMax = iMax - 1; // i is too big
                }
                else { // i is perfect
                    int maxLeft = 0;
                    if (i == 0) { maxLeft = B[j-1]; }
                    else if (j == 0) { maxLeft = A[i-1]; }
                    else { maxLeft = Math.max(A[i-1], B[j-1]); }
                    if ( (m + n) % 2 == 1 ) { return maxLeft; }

                    int minRight = 0;
                    if (i == m) { minRight = B[j]; }
                    else if (j == n) { minRight = A[i]; }
                    else { minRight = Math.min(B[j], A[i]); }

                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }

    /**
     * 10. Regular Expression Matching
     *
     * Implement regular expression matching with support for '.' and '*'.

     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     *
     * 此方法使用一维数组，无法有效*表示的0个或多个情况下的成立
     * 一开始想的是字符串与Pattern从左开始匹配，每一次匹配已经认为前面匹配为最优解，
     * 跑通370个测试用例，就失败了，一维数组想不出如何表示*的零个或多个的何为最优解
     */
    public boolean isMatch(String s, String p) {
        char[] chars_s = s.toCharArray();
        char[] chars_p = p.toCharArray();
        int s_index=0,p_index =0;
        //1.相同数字匹配，从左开始匹配
        while (s_index<chars_s.length&&p_index<chars_p.length){
            int temp_s=-1,temp_p=-1;
            if (chars_s[s_index]==chars_p[p_index] || chars_p[p_index]=='.'){
                temp_s = s_index;
                temp_p = p_index;
                s_index++;
            }
            p_index++;
            //匹配到了星号，此处应该有超过2字符返回false，要不然，aaa 会与aca*匹配成功，
            if (p_index<chars_p.length&&chars_p[p_index]=='*'){
                //在后面的测试用例中，发现，无法使右侧选择性匹配，即aca 与 aca*a*此类状况，使前一个a*选择性匹配0
                if(temp_s>-1&&temp_p>-1){
                    //为x匹配x*状况
                    int temp=temp_s;
                    while (s_index<chars_s.length&&(chars_s[temp_s]==chars_s[s_index]||chars_p[temp_p]=='.')) {
                        s_index++;
                    }
                    p_index++;
                    //左侧过度匹配了？
                    while (p_index<chars_p.length&&chars_s[temp]==chars_p[p_index]){
                        p_index++;
                    }
                }else{
                    p_index++;
                }

            }
        }
        return s_index==chars_s.length&&p_index==chars_p.length;
        /**
         * 总结：1.*号情况下考虑不周，一维数组无法很好表示匹配情况
         *      2. DP没有选择好数据结构，后面写的纯属修补
         */
    }

    /**
     *使用DP2维数组解决
     * Boolean array[i][j]代表 s[i-1]与p[j-1]是否匹配成功
     * if p[j]==s[i] : array[i+1][j+1]=array[i][j]
     * if p[j]=='.'  ：array[i+1][j+1] = array[i][j]
     * if p[j] =='*' :
     *          if p[j-1]=!s[i] : array[i+1][j+1]=array[i+1][j-1]
     *          if p[j-1]==s[i]||p[j-1]=='.':
     *                      匹配多次，array[i+1][j+1]=array[i][j+1]
     *                      匹配0次, array[i+1][j+1]=array[i+1][j-1]
     *                      匹配1次，array[i+1][j+1]=array[i+1][j]
     *
     */
    /**
     * 此种ismatch数组考虑了空对空，故与数组坐标有偏移，直接使用String.charAt()则可
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p){
        char[] chars_s = s.toCharArray();
        char[] chars_p = p.toCharArray();
        boolean ismatch[][] = new boolean[chars_s.length+1][chars_p.length+1];
        ismatch[0][0]=true;
        //init 2D array
        // think about "" versus a*b*c*
        for (int j =1;j<chars_p.length;j++){
            if (chars_p[j]=='*'&&(j>0&&ismatch[0][j-1])) {
                ismatch[0][j+1]=true;
            }
        }
        for (int i =0; i<chars_s.length;i++){
            for (int j =0; j<chars_p.length;j++){
                if (chars_p[j]==chars_s[i]||chars_p[j]=='.'){
                    ismatch[i+1][j+1]=ismatch[i][j];
                }
                if (chars_p[j]=='*'){
                    if (chars_s[i]!=chars_p[j-1]){
                        ismatch[i+1][j+1]=ismatch[i+1][j-1];
                    }
                    if (chars_s[i]==chars_p[j-1]||chars_p[j-1]=='.'){
                        ismatch[i+1][j+1]=ismatch[i+1][j-1]||ismatch[i+1][j]||ismatch[i][j+1];
                    }
                }
            }
        }
        return ismatch[chars_s.length][chars_p.length];
    }


    @Test
    public void test(){
        boolean a = isMatch1("aa","aca*");
        boolean h = isMatch1("aa","a*");
        boolean b = isMatch1("aa","aa");
        boolean c = isMatch1("aaa","aa");
        boolean d =  isMatch1("aaa", "a*a");
        boolean e = isMatch1("aa", ".*");
        boolean f =  isMatch1("ab", ".*");
        boolean g =  isMatch1("aab", "c*a*b");
    }

}
