class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int mid = (n - 1) / 2;
        int high = n - 1;

        for (int i = 0; i < n; i += 2) {
            nums[i] = sorted[mid--];
        }

        for (int i = 1; i < n; i += 2) {
            nums[i] = sorted[high--];
        }
    }
}