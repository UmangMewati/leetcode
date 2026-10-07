class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int currentTank = 0;
        int startingStation = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            
            // Calculate current fuel balance
            currentTank += gas[i] - cost[i];

            // If we run out of gas, this starting station (and all before it) 
            // are invalid. Reset and try the next station.
            if (currentTank < 0) {
                startingStation = i + 1;
                currentTank = 0;
            }
        }

        // Final check: if total gas is less than total cost, impossible.
        // Otherwise, the last startingStation we set is guaranteed to work.
        return (totalGas >= totalCost) ? startingStation : -1;
    }
}