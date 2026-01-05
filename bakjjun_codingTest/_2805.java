package bakjjun_codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나무 자르기
 * 이분 탐색
 * 단순하게 -1씩 처리할 경우 타임아웃 발생
 */
public class _2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int[] trees = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        int low = 0;
        int high = max;
        int result = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            // 나무들의 높이만큼 더하기
            long sum = 0;
            for (int i : trees) {
                if (i > mid) {
                    sum += (i - mid);
                }
            }

            // 합계가 조건(m)보다 크면 low를 한칸 올려서 다시 이분탐색
            if (sum >= m) {
                result = mid;
                low = mid + 1;
            }
            // 반대의 경우 높이를 더 낮춘 후 다시 이분탐색
            else {
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}
