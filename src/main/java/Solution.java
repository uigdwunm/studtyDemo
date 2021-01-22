public class Solution {
    public int eatenApples(int[] apples, int[] days) {
        // 能吃苹果的天数
        int result = 0;
        // 预支的苹果天数
        int applyDay = 0;
        int l = apples.length;

        for (int i = 0; i < l; i++) {
            applyDay = Math.max(applyDay, i);
            // 当天的苹果数
            int currApples = apples[i];
            if (currApples == 0) {
                // 没有，则跳过
                continue;
            }
            // 最后一天能吃的
            int currDay = i + days[i];
            if (currDay > applyDay) {
                currApples = Math.min(currApples, currDay - applyDay);
                result += currApples;
                applyDay += currApples;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};
        apples = new int[]{2, 2, 2};
        days =   new int[]{6, 4, 2};
        int k = 2;
        int i = solution.eatenApples(apples, days);
        System.out.println(i);
    }
}
