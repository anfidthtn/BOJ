import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 한쪽 끝 점 없애기위해 -1
		int K = Integer.parseInt(st.nextToken()) - 1;
		int count = 0;
		// 세로와 가로 * 행 또는 열의 수
		count += Math.max(0, N - K + 1) * (M + 1);
		count += Math.max(0, M - K + 1) * (N + 1);

		// x, y모두 변화량이 있는 경우 상하대칭으로 2가지 나올 수 있음
		for (int x = N; x >= K; x--) {
			for (int y = M; y >= K; y--) {
				if (getGCD(x, y) == K) {
					count += 2 * (N - x + 1) * (M - y + 1);
				}
			}
		}
		System.out.println(count);
	}

	public static int getGCD(int a, int b) {
		while (b > 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}