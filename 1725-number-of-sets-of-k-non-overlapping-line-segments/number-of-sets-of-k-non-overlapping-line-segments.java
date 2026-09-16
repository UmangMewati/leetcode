class Solution {
    static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];

        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[N] = modPow(fact[N], MOD - 2);

        for (int i = N - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        return (int) (fact[N] * invFact[R] % MOD * invFact[N - R] % MOD);
    }

    private long modPow(long a, long e) {
        long result = 1;

        while (e > 0) {
            if ((e & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            e >>= 1;
        }

        return result;
    }
}
