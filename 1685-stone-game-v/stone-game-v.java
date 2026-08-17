class Solution {
    int[] pre;
    int[][] dp;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        pre = new int[n + 1];
        dp = new int[n][n];

        for (int i = 0; i < n; i++)
            pre[i + 1] = pre[i] + stoneValue[i];

        return solve(0, n - 1);
    }

    int solve(int l, int r) {
        if (l >= r)
            return 0;

        if (dp[l][r] != 0)
            return dp[l][r];

        int ans = 0;

        for (int k = l; k < r; k++) {
            int left = pre[k + 1] - pre[l];
            int right = pre[r + 1] - pre[k + 1];

            if (left < right) {
                ans = Math.max(ans, left + solve(l, k));
            } else if (left > right) {
                ans = Math.max(ans, right + solve(k + 1, r));
            } else {
                ans = Math.max(ans,
                    left + Math.max(solve(l, k), solve(k + 1, r)));
            }
        }

        return dp[l][r] = ans;
    }
}
