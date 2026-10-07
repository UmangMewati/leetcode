class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        
        // Start from the second day
        for (int i = 1; i < prices.length; i++) {
            // If today's price is higher than yesterday's, 
            // "pretend" we bought yesterday and sold today.
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        
        return maxProfit;
    }
}