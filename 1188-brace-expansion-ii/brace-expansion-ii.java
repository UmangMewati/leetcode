class Solution {
    int index;

    public List<String> braceExpansionII(String expression) {
        index = 0;

        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length() && s.charAt(index) != '}') {
            Set<String> current;

            char ch = s.charAt(index);

            if (ch == '{') {
                index++; 
                current = parse(s);
                index++; 
            } else if (ch == ',') {
                index++;
                Set<String> next = parse(s);

                result.addAll(next);
                return result;
            } else {
                current = new HashSet<>();
                current.add(String.valueOf(ch));
                index++;
            }

            result = combine(result, current);
        }

        return result;
    }

    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}
