class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {

        String start = arriveAlice.compareTo(arriveBob) > 0
                       ? arriveAlice : arriveBob;

        String end = leaveAlice.compareTo(leaveBob) < 0
                     ? leaveAlice : leaveBob;

        if (start.compareTo(end) > 0) {
            return 0;
        }

        int[] days = {31, 28, 31, 30, 31, 30,
                      31, 31, 30, 31, 30, 31};

        int startDay = 0;
        int endDay = 0;

        int startMonth = Integer.parseInt(start.substring(0, 2));
        int endMonth = Integer.parseInt(end.substring(0, 2));

        for (int i = 0; i < startMonth - 1; i++) {
            startDay += days[i];
        }
        startDay += Integer.parseInt(start.substring(3));

        for (int i = 0; i < endMonth - 1; i++) {
            endDay += days[i];
        }
        endDay += Integer.parseInt(end.substring(3));

        return endDay - startDay + 1;
    }
}