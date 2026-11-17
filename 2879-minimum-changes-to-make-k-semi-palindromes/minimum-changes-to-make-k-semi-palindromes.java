
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // cost[i][j] = minimum changes to make s[i...j]
        // a semi-palindrome
        int[][] cost = new int[n][n];

        // Precompute costs for every substring
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minCost = n;

                // Try every proper divisor of len
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;
                    int groups = len / d;

                    // Check each group for palindrome property
                    for (int offset = 0; offset < d; offset++) {
                        int left = i + offset;
                        int right = i + offset + (groups - 1) * d;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][j] = minCost;
            }
        }

        // dp[i][j] = minimum changes to divide s[i...n-1]
        // into exactly j semi-palindromes
        int INF = 1000000;
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        // Base case: empty string needs zero partitions
        dp[n][0] = 0;

        // Bottom-up DP
        for (int i = n - 1; i >= 0; i--) {
            for (int parts = 1; parts <= k; parts++) {

                // Every substring must have length >= 2
                int maxEnd = n - 1 - 2 * (parts - 1);

                for (int end = i + 1; end <= maxEnd; end++) {

                    if (dp[end + 1][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        cost[i][end] + dp[end + 1][parts - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}
