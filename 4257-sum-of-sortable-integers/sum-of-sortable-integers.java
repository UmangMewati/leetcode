
import java.util.stream.IntStream;

class Solution {
    public int sortableIntegers(int[] nums) {
        final int n = nums.length;

        var prefMax = new int[n + 1];
        var suffMin = new int[n + 1];
        var prefDrop = new int[n];

        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1]
                    + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        return IntStream.rangeClosed(1, (int) Math.sqrt(n))
                .filter(d -> n % d == 0)
                .map(d -> {
                    var other = n / d;

                    var sum = isSortable(
                            nums, prefMax, suffMin, prefDrop, d
                    ) ? d : 0;

                    if (other != d && isSortable(
                            nums, prefMax, suffMin, prefDrop, other)) {
                        sum += other;
                    }

                    return sum;
                })
                .sum();
    }

    private boolean isSortable(
            int[] nums,
            int[] prefMax,
            int[] suffMin,
            int[] prefDrop,
            int k) {

        var n = nums.length;

        for (int start = 0; start < n; start += k) {
            var end = start + k - 1;

            var drops = prefDrop[end] - prefDrop[start];

            if (nums[end] > nums[start]) {
                drops++;
            }

            if (drops > 1 || prefMax[start] > suffMin[start]) {
                return false;
            }
        }

        return true;
    }
}