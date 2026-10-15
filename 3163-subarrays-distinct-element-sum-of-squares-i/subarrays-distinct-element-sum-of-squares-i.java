import java.util.List;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[101];
            int distinct = 0;

            for (int j = i; j < n; j++) {
                int x = nums.get(j);

                if (freq[x] == 0) {
                    distinct++;
                }

                freq[x]++;
                ans += distinct * distinct;
            }
        }

        return ans;
    }
}