class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        for (int i = 0; i < n; ) {
            int j = i + 1;

            while (j < n &&
                   (long) nums[indices[j]] - nums[indices[j - 1]] <= limit) {
                j++;
            }

            Integer[] group = Arrays.copyOfRange(indices, i, j);

            Arrays.sort(group);

            for (int k = i; k < j; k++) {
                ans[group[k - i]] = nums[indices[k]];
            }

            i = j;
        }

        return ans;
    }
}
