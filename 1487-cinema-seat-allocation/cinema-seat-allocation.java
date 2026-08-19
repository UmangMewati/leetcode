import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats for each row as a bitmask
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0)
                    | (1 << (col - 1)));
        }

        // Rows with no reservations can always fit 2 families
        int ans = (n - map.size()) * 2;

        // Valid groups of 4 seats:
        //
        // Left   -> 2,3,4,5
        // Middle -> 4,5,6,7
        // Right  -> 6,7,8,9

        int left = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4);
        int middle = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6);
        int right = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8);

        for (int reserved : map.values()) {

            boolean canLeft = (reserved & left) == 0;
            boolean canMiddle = (reserved & middle) == 0;
            boolean canRight = (reserved & right) == 0;

            // Both left and right are available
            if (canLeft && canRight) {
                ans += 2;
            }

            // At least one group is available
            else if (canLeft || canMiddle || canRight) {
                ans += 1;
            }
        }

        return ans;
    }
}