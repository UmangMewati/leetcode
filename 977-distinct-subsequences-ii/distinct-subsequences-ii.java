class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        long[] last = new long[26];

        long total = 0;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long oldTotal = total;

            total = (2 * total + 1 - last[idx] + MOD) % MOD;

            last[idx] = (oldTotal + 1) % MOD;
        }
        return (int) total;
    }
}