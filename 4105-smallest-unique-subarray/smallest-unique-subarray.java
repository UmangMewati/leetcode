
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long base = 911382323L;
        long mod = 1_000_000_007L;

        long[] pow = new long[n + 1];
        pow[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow[i] = (pow[i - 1] * base) % mod;
        }

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] =
                (prefix[i] * base + nums[i]) % mod;
        }

        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            HashMap<Long, Integer> freq = new HashMap<>();

            for (int i = 0; i + mid <= n; i++) {
                long hash = (prefix[i + mid]
                    - prefix[i] * pow[mid] % mod + mod) % mod;

                freq.put(hash, freq.getOrDefault(hash, 0) + 1);
            }

            boolean found = false;

            for (int count : freq.values()) {
                if (count == 1) {
                    found = true;
                    break;
                }
            }

            if (found) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}
