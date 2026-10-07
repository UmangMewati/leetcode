import java.util.Arrays;
import java.util.Collections;
class Solution {
    public int hIndex(int[] citations) {
        Integer[] arr = new Integer[citations.length];
        for (int i = 0; i < citations.length; i++) {
            arr[i] = citations[i];
        }

        Arrays.sort(arr, Collections.reverseOrder());

        int h = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }

        return h; 
    }
}