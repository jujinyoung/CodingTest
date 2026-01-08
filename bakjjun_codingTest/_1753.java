package bakjjun_codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최단경로
 */
public class _1753 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        // 해당 정점에서 다른 정점에 도달하는 간선
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v2, w));
        }

        // 최단 경로 배열 세팅 dist[정점] = 정점의 최단 가중치
        int[] dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // 우선순위 큐(최소 힙)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.vertex;
            int cost = cur.cost;

            // 가중치 보다 이미 작다면 해당 정점의 더 적은 루트가 구해진것
            if (dist[now] < cost) continue;

            for (Node next : graph.get(now)) {
                int newCost = dist[now] + next.cost;    // 다음 정점에 가는 비용
                if (newCost < dist[next.vertex]) {
                    dist[next.vertex] = newCost;
                    pq.add(new Node(next.vertex, newCost));
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost; // 비용이 작은 순으로 정렬
        }
    }
}
