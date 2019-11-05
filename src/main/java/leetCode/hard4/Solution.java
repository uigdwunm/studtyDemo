package leetCode.hard4;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/19 15:53
 **/
public class Solution {


    public static void main(String[] args) {
        Solution solution = new Solution();
        double medianSortedArrays = solution.findMedianSortedArrays(new int[]{1,2}, new int[]{4});
        System.out.println(medianSortedArrays);
    }


    private int nums1Index = 0;
    private int nums2Index = 0;
    private int[] nums1 = null;
    private int[] nums2 = null;
    private int currentIndex = 0;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        this.nums1 = nums1;
        this.nums2 = nums2;

        if(totalLength % 2 == 0) {
            // 偶数个
            int needIndex2 = totalLength/2;
            int needIndex1 = needIndex2-1;

            while(true) {
                if(needIndex1 == currentIndex) {
                    return (propel() + propel()) / 2.0;
                }
                propel();
            }
        } else {
            // 奇数个
            if(totalLength == 1) {
                return propel();
            }
            int needIndex = totalLength/2;

            while(true) {
                if(needIndex == currentIndex) {
                    return propel();
                }
                propel();
            }
        }
    }

    private int propel() {
        int temp;
        if(nums1Index == nums1.length) {
            temp = nums2[nums2Index];
            nums2Index++;
            currentIndex++;
            return temp;
        }
        if(nums2Index == nums2.length) {
            temp = nums1[nums1Index];
            nums1Index++;
            currentIndex++;
            return temp;
        }

        if(nums1[nums1Index] < nums2[nums2Index]) {
            temp = nums1[nums1Index];
            nums1Index++;
            currentIndex++;
            return temp;
        } else {
            temp = nums2[nums2Index];
            nums2Index++;
            currentIndex++;
            return temp;
        }
    }
}
