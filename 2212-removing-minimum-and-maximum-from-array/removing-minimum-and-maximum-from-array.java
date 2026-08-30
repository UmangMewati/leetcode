class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        int fromFront = maxIndex + 1;

        int fromBack = n - minIndex;

        int fromBoth = (minIndex + 1) + (n - maxIndex);

        return Math.min(fromFront, Math.min(fromBack, fromBoth));
    }
}
