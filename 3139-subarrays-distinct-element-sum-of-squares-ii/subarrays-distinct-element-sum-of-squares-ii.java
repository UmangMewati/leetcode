class Solution {
    private static final int MOD = 1_000_000_007;

    private long[] sum;
    private long[] sqSum;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sqSum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];

            // Increase distinct counts for affected subarrays.
            update(1, 0, n - 1, prev + 1, i, 1);

            // Sum of squared distinct counts for subarrays
            // ending at index i.
            ans = (ans + sqSum[1]) % MOD;

            last[nums[i]] = i;
        }

        return (int) ans;
    }

    private void apply(int node, int left, int right, long val) {
        long len = right - left + 1L;

        // (x + val)^2 = x^2 + 2*val*x + val^2
        sqSum[node] = (
            sqSum[node]
            + 2L * val % MOD * sum[node] % MOD
            + len % MOD * val % MOD * val % MOD
        ) % MOD;

        sum[node] = (sum[node] + len * val) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void pushDown(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        long val = lazy[node];

        apply(node * 2, left, mid, val);
        apply(node * 2 + 1, mid + 1, right, val);

        lazy[node] = 0;
    }

    private void update(
        int node, int left, int right,
        int ql, int qr, long val
    ) {
        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, val);
            return;
        }

        pushDown(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, right, ql, qr, val);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sqSum[node] = (
            sqSum[node * 2] + sqSum[node * 2 + 1]
        ) % MOD;
    }
}