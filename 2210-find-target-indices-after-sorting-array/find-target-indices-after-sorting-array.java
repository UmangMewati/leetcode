import java.util.*;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();

        int smaller = 0;
        int equal = 0;

        for (int num : nums) {
            if (num < target) {
                smaller++;
            } else if (num == target) {
                equal++;
            }
        }

        for (int i = smaller; i < smaller + equal; i++) {
            result.add(i);
        }
        return result;
    }
}