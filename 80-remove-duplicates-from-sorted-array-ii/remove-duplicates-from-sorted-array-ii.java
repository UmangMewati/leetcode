class Solution {
    public int removeDuplicates(int[] nums) {
        // If the array has 0, 1, or 2 elements, it's already valid.
        if (nums.length <= 2) return nums.length;

        // 'index' is our Slow Runner (points to where we will write the next valid number)
        int index = 2;

        // 'i' is our Fast Runner (explores the original array)
        for (int i = 2; i < nums.length; i++) {
            // Check if current number is different from the number 2 positions back
            if (nums[i] != nums[index - 2]) {
                nums[index] = nums[i];
                index++;
            }
        }

        // 'index' now represents the length of the modified array
        return index;
    }
}