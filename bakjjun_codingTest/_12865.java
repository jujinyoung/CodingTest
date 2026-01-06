package bakjjun_codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 평범한 배낭
 * dp 문제
 * 점화식 구하기
 * 4 7
 * 6 13
 * 4 8
 * 3 6
 * 5 12
 *
 * dp[n][w] : n = 고려한 물건, w = 배낭 최대 무게 -> ex) dp[1][2] = 물건이 1개 있을떄 2의 무게를 가진다면 최댓가치를 의미
 * 1번 물건 -> dp[1] = 0 0 0 0 0 0 13 13 -> dp[1][6], dp[1][7] 일때 13의 가치를 가짐
 * 2번 물건 -> dp[2] = 0 0 0 0 8 8 13 13
 * 3번 물건 -> dp[3] = 0 0 0 6 8 8 13 14
 * 4번 물건 -> dp[4] = 0 0 0 6 8 12 13 14 -> 즉 4개의 물건을 고려했을떄 dp[4][7] = 14가 최댓값을 가짐
 *
 * 새로 들어온 w보다 작은 값은 다 이전 값 = dp[i-1][w]
 * 더 크거나 같은 값은 새로들어오는 배낭 무게를 뺸 값 + 이전 값 = dp[i-1][w-w2(새로들어온 배낭값)] + v(새로들어온 배낭 가치)
 *
 * 즉 dp[i-1][w] 와 dp[i-1][w-w2(새로들어온 배낭값)] + v(새로들어온 배낭 가치) 값 중 최대값이 dp[i][w] 의 값으로 결정
 *
 * dp[3][7]의 경우 3번 물건의 이전 최대값 dp[2][7] = 13 과 3의 무게를 뺸 이전 최대값 + v, 즉 dp[2][7-3] + 6 = 8 + 6 = 14 로 14가 최대값
 */
public class _12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 배낭 개수
        int k = Integer.parseInt(st.nextToken());   // 최대 배낭 무게

        int[][] dp = new int[n+1][k+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());   // 배낭 무게
            int v = Integer.parseInt(st.nextToken());   // 배낭 가치

            for (int j = 0; j <= k; j++) {
                // 배낭 무게보다 작다면 이전 값
                if (j < w) {
                    dp[i][j] = dp[i-1][j];
                }
                // 배낭무게보다 크다면 이전 최고값과 비교
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w] + v);
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
