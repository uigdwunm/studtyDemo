package leetCode.medium11;

public class Solution {
    public int maxArea(int[] height) {
        int maxHigh = 0;
        int max = 0;
        int li = 0;
        int ri = height.length-1;

        do {
            // 这次的高度
            int high = Math.min(height[li], height[ri]);
            if (high > maxHigh) {
                maxHigh = high;
                // 如果当前的高度比记录的最高还高，则可以一试
                int currMax = high * (ri - li);
                max = Math.max(max, currMax);
            }

            if (li > ri) {
                ri--;
            } else {
                li++;
            }
            // 两个指针碰上为止
        } while(li != ri);

        return max;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxArea(new int[]{2,3,4,5,18,17,6});
        System.out.println(i);
    }
}
