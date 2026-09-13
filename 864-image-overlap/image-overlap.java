class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        java.util.List<int[]> a = new java.util.ArrayList<>();
        java.util.List<int[]> b = new java.util.ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    a.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    b.add(new int[]{i, j});
                }
            }
        }

        java.util.Map<String, Integer> count = new java.util.HashMap<>();

        int ans = 0;

        for (int[] p1 : a) {
            for (int[] p2 : b) {
                int dx = p1[0] - p2[0];
                int dy = p1[1] - p2[1];

                String key = dx + "," + dy;

                int freq = count.getOrDefault(key, 0) + 1;
                count.put(key, freq);

                ans = Math.max(ans, freq);
            }
        }
        return ans;
    }
}