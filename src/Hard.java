public class Hard {
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


    public static void main(String[] args){
        int[] a = {1,2};
        int[] b = {3,4};
        findMedianSortedArrays(a,b);
    }
}
