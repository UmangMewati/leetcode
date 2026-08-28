class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
        }

        int m = n / 2;

        int[] remaining = half.clone();
        boolean possible = true;

        for (int i = 0; i < m; i++) {

            int c = target.charAt(i) - 'a';

            if (remaining[c] == 0) {
                possible = false;
                break;
            }

            remaining[c]--;
        }

        if (possible) {

            String left = target.substring(0, m);

            StringBuilder candidate = new StringBuilder();

            candidate.append(left);

            if (n % 2 == 1) {
                candidate.append((char) ('a' + middle));
            }
            candidate.append(new StringBuilder(left).reverse());

            if (candidate.toString().compareTo(target) > 0) {
                return candidate.toString();
            }
        }

        for (int i = m - 1; i >= 0; i--) {

            int[] freq = half.clone();

            boolean ok = true;

            for (int j = 0; j < i; j++) {

                int c = target.charAt(j) - 'a';

                if (freq[c] == 0) {
                    ok = false;
                    break;
                }

                freq[c]--;
            }

            if (!ok) {
                continue;
            }

            int targetChar = target.charAt(i) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] == 0) {
                    continue;
                }

                freq[c]--;

                StringBuilder left = new StringBuilder();

                for (int j = 0; j < i; j++) {
                    left.append(target.charAt(j));
                }

                left.append((char) ('a' + c));

                for (int x = 0; x < 26; x++) {
                    while (freq[x] > 0) {
                        left.append((char) ('a' + x));
                        freq[x]--;
                    }
                }

                StringBuilder answer = new StringBuilder();

                answer.append(left);

                if (n % 2 == 1) {
                    answer.append((char) ('a' + middle));
                }

                answer.append(
                    new StringBuilder(left).reverse()
                );

                return answer.toString();
            }
        }

        return "";
    }
}