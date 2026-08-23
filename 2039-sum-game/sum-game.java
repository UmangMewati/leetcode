class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        double deltaSum = 0;
        int deltaSlots = 0;

        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                if (num.charAt(i) == '?') {
                    deltaSlots++;
                } else {
                    deltaSum += num.charAt(i) - '0';
                }
            } else {
                if (num.charAt(i) == '?') {
                    deltaSlots--;
                } else {
                    deltaSum -= num.charAt(i) - '0';
                }
            }
        }

        return deltaSum + (deltaSlots * 4.5) != 0;
    }
}