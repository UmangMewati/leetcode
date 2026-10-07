class Solution {
    public int maxProfit(int[] prices) {
         int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            // Track the lowest price seen so far
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate potential profit
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }
}