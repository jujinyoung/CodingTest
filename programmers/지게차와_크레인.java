package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 지게차와_크레인 {
    // 상하좌우 이동을 위한 방향 벡터
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {
        String[] storage = new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] request = new String[]{"A", "BB", "A"};

        // 11
        System.out.println(solution(storage,request));

        storage = new String[]{"HAH", "HBH", "HHH", "HAH", "HBH"};
        request = new String[]{"C", "B", "B", "B", "B", "H"};

        // 4
        System.out.println(solution(storage,request));
    }

    public static int solution(String[] storage, String[] requests) {
        int rowSize = storage.length;
        int colSize = storage[0].length();

        char[][] storages = new char[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                storages[i][j] = storage[i].charAt(j);
            }
        }


        for (String request : requests) {
            // 전체 탐색
            if (request.length() > 1) {
                for (int i = 0; i < rowSize; i++) {
                    for (int j = 0; j < colSize; j++) {
                        if (storages[i][j] == request.charAt(0)) {
                            storages[i][j] = '.';
                        }
                    }
                }
            }
            // bfs
            else {
                bfs(storages, request.charAt(0));
            }
        }

        int answer = 0;
        for (char[] results : storages) {
            for (char result : results) {
                if ('.' != result) {
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void bfs(char[][] storages, char target) {
        int n = storages.length;
        int m = storages[0].length;

        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        // 외부와 연결된 빈공간 탐색 시작점 (테두리에서 '.'만 큐에 추가)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    if (storages[i][j] == '.') {
                        visited[i][j] = true;
                        queue.offer(new int[]{i, j});
                    } else if (storages[i][j] == target) {
                        visited[i][j] = true;
                        storages[i][j] = '.';
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
                    if (storages[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    } else if (storages[nx][ny] == target) {
                        storages[nx][ny] = '.'; // 제거
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}
