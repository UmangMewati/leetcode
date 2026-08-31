class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = 0;

        int firstCritical = -1;
        int prevCritical = -1;

        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {
            int val = curr.val;

            boolean isLocalMax =
                val > prev.val && val > curr.next.val;

            boolean isLocalMin =
                val < prev.val && val < curr.next.val;

            if (isLocalMax || isLocalMin) {

                if (firstCritical == -1) {
                    firstCritical = index;
                }
                if (prevCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        index - prevCritical
                    );

                    maxDistance = Math.max(
                        maxDistance,
                        index - firstCritical
                    );
                }

                prevCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (prevCritical == -1 || firstCritical == prevCritical) {
            return new int[] {-1, -1};
        }

        return new int[] {minDistance, maxDistance};
    }
}
