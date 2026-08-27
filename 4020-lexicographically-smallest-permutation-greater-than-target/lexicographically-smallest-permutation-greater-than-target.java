import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (char c : s.toCharArray()) {
            totalCount[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] count = totalCount.clone();
            boolean prefixValid = true;

            for (int j = 0; j < i; j++) {
                int charIdx = target.charAt(j) - 'a';
                if (--count[charIdx] < 0) {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            int targetCharIdx = target.charAt(i) - 'a';
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (count[c] > 0) {
                    count[c]--;

                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, i);
                    sb.append((char) ('a' + c));

                    for (int rem = 0; rem < 26; rem++) {
                        while (count[rem] > 0) {
                            sb.append((char) ('a' + rem));
                            count[rem]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}