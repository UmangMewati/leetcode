
import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 1];
        }

        void update(int i, long val) {
            while (i <= n) {
                bit[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= i & -i;
            }
            return sum;
        }

        int kth(long k) {
            int idx = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0; step >>= 1) {

                int next = idx + step;

                if (next <= n && bit[next] < k) {
                    idx = next;
                    k -= bit[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;
        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] vals = Arrays.copyOf(sorted, m);

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = Arrays.binarySearch(vals, nums[i]) + 1;
        }

        long[] cost = new long[n - x + 1];

        long windowSum = 0;

        for (int i = 0; i < n; i++) {

            count.update(id[i], 1);
            sum.update(id[i], nums[i]);
            windowSum += nums[i];

            if (i >= x) {
                int j = i - x;

                count.update(id[j], -1);
                sum.update(id[j], -nums[j]);
                windowSum -= nums[j];
            }

            if (i >= x - 1) {

                int rank = (x + 1) / 2;

                int medianIdx = count.kth(rank);
                long median = vals[medianIdx - 1];

                long leftCount = count.query(medianIdx);
                long leftSum = sum.query(medianIdx);

                long rightCount = x - leftCount;
                long rightSum = windowSum - leftSum;

                cost[i - x + 1] =
                    median * leftCount - leftSum
                    + rightSum - median * rightCount;
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
