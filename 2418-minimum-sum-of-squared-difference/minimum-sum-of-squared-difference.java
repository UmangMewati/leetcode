
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int[] diff = new int[n];
        long total = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (total <= k) {
            return 0L;
        }

        int low = 0, high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long operations = 0;

            for (int d : diff) {
                if (d > mid) {
                    operations += d - mid;
                }
            }

            if (operations <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        long remaining = k;
        long ans = 0;

        for (int d : diff) {
            if (d > low) {
                remaining -= d - low;
                d = low;
            }

            ans += (long) d * d;
        }

        // Use leftover operations to reduce low to low - 1.
        if (low > 0) {
            long reduction = Math.min(remaining, n);
            // Only differences equal to low can be reduced here.
            // Count them and adjust their squared contribution.
            long count = 0;

            for (int d : diff) {
                if (d >= low) {
                    count++;
                }
            }

            reduction = Math.min(remaining, count);
            ans -= reduction * (2L * low - 1);
        }

        return ans;
    }
}