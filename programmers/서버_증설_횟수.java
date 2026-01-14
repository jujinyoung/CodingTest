package programmers;

public class 서버_증설_횟수 {

    public static void main(String[] args) {
        // 1번 -> 7
        int m = 3;
        int k = 5;
        int[] players = new int[]{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};

        System.out.println(solution(players, m, k));

        // 2번 -> 11
        m = 5;
        k = 1;
        players = new int[]{0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0};

        System.out.println(solution(players, m, k));

        // 3번 -> 12
        m = 1;
        k = 1;
        players = new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};

        System.out.println(solution(players, m, k));
    }

    public static int solution(int[] players, int m, int k) {
        int result = 0;
        int[] server = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            // 최대수용가능한 인원수와 증설된 서버 인원수의 합보다 이용자수가 많으면
            if (players[i] >= m + server[i]) {
                // 이용자수 - 증설된서버의 이용자수를 나눠서 서버개수 구하기
                int totalServer = (players[i] - server[i]) / m;
                result += totalServer;

                // 다음 k번 만큼의 증설된 서버의 이용자수 누적해서 넣기
                for (int j = 0; j < k; j++) {
                    if (i + j < players.length) {
                        server[i+j] += totalServer * m;
                    }
                }
            }
        }

        return result;
    }
}
