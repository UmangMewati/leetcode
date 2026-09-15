class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            expand(s, i, i, k, dp);

            expand(s, i, i + 1, k, dp);
        } 
        return dp[n];
    }

    private void expand(String s, int left, int right, int k, int[] dp) {
        int n = s.length();

        while (left >= 0 && right < n &&
               s.charAt(left) == s.charAt(right)) {

            int len = right - left + 1;

            if (len >= k ) {
                dp[right + 1] = Math.max(
                    dp[right + 1],
                    dp[left] + 1
                );
            }    
            left--;
            right++;
        }
    }
}