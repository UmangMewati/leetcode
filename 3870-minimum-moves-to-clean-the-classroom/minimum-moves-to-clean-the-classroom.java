import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int sr = 0, sc = 0;
        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } else if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int allCollected = (1 << litterCount) - 1;

        int[][][] best = new int[m][n][1 << litterCount];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(best[r][c], -1);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        best[sr][sc][0] = energy;
        queue.offer(new int[]{sr, sc, energy, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] cur = queue.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if (mask == allCollected) {
                    return moves;
                }

                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n ||
                        classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    int newEnergy = e - 1;

                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    int newMask = mask;

                    if (cell == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }

                    if (newEnergy <= best[nr][nc][newMask]) {
                        continue;
                    }

                    best[nr][nc][newMask] = newEnergy;
                    queue.offer(new int[]{
                        nr, nc, newEnergy, newMask
                    });
                }
            }

            moves++;
        }

        return -1;
    }
}
