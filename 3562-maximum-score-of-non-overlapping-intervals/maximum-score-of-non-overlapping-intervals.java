class Solution { public int[] maximumWeight(List<List<Integer>> intervals) {
     int n = intervals.size();
      int[][] a = new int[n][4];
       for (int i = 0; i < n; i++) { 
        a[i][0] = intervals.get(i).get(0); 
        a[i][1] = intervals.get(i).get(1);
         a[i][2] = intervals.get(i).get(2);
          a[i][3] = i;
          
           } 
           Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
            List<Integer>[][] dp = new ArrayList[n + 1][5];
             long[][] sum = new long[n + 1][5];
              for (int k = 0; k <= 4; k++) 
              dp[n][k] = new ArrayList<>();
               for (int i = n - 1; i >= 0; i--) {
                 for (int k = 0; k <= 4; k++) {
                     dp[i][k] = new ArrayList<>(dp[i + 1][k]);
                      sum[i][k] = sum[i + 1][k];
                       if (k == 0) continue;
                        int l = i + 1, r = n;
                         while (l < r) { int m = (l + r) >>> 1;
                          if (a[m][0] > a[i][1]) r = m; else l = m + 1;
                           } 
                           long val = a[i][2] + sum[l][k - 1]; 
                           List<Integer> take = new ArrayList<>(dp[l][k - 1]);
                            take.add(a[i][3]); Collections.sort(take);
                             if (val > sum[i][k] || (val == sum[i][k] && smaller(take, dp[i][k]))) { 
                                sum[i][k] = val; dp[i][k] = take;
                                 } 
                                 }
                                  }
                                   return dp[0][4].stream().mapToInt(Integer::intValue).toArray();
                                    }
                                     private boolean smaller(List<Integer> a, List<Integer> b) {
                                         for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                                             if (!a.get(i).equals(b.get(i))) return a.get(i) < b.get(i); 
                                             } 
                                             return a.size() < b.size();
                                              }
                                               }