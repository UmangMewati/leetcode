import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] parent, depth, heavy, head, pos;
    private int curPos;
    private int[] tree;
    private int[] nodeMask;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        nodeMask = new int[n];
        Arrays.fill(heavy, -1);

        dfs1(0, -1, 0);
        curPos = 0;
        dfs2(0, 0);

        tree = new int[4 * n];
        for (int i = 0; i < n; i++) {
            nodeMask[i] = 1 << (s.charAt(i) - 'a');
            updateTree(1, 0, n - 1, pos[i], nodeMask[i]);
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                nodeMask[u] = 1 << (c - 'a');
                updateTree(1, 0, n - 1, pos[u], nodeMask[u]);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int xorVal = queryPath(u, v, n);
                ans.add(xorVal == 0 || (xorVal & (xorVal - 1)) == 0);
            }
        }
        return ans;
    }

    private int dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        int size = 1, maxSubSize = 0;
        for (int v : adj[u]) {
            if (v != p) {
                int subSize = dfs1(v, u, d + 1);
                size += subSize;
                if (subSize > maxSubSize) {
                    maxSubSize = subSize;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private void updateTree(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) updateTree(2 * node, l, mid, idx, val);
        else updateTree(2 * node + 1, mid + 1, r, idx, val);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int queryTree(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;
        return queryTree(2 * node, l, mid, ql, qr) ^ queryTree(2 * node + 1, mid + 1, r, ql, qr);
    }

    private int queryPath(int u, int v, int n) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= queryTree(1, 0, n - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= queryTree(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }
}