import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static boolean[][] results1;
	static boolean[][] results2;
	static int[] nums;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		results1 = new boolean[N][N];
		results2 = new boolean[N][N];
		nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < N; i++) {
			results1[i][0] = true;
			for(int j = 1; 0 <= i - j && i + j < N; j++) {
				if (nums[i - j] == nums[i + j]) {
					results1[i][j] = true;
				}
				else {
					break;
				}
			}
		}
		for(int i = 0; i < N - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				results2[i][0] = true;
				for(int j = 1; 0 <= i - j && i + 1 + j < N; j++) {
					if (nums[i - j] == nums[i + 1 + j]) {
						results2[i][j] = true;
					}
					else {
						break;
					}
				}
			}
		}
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int q = 0; q < Q; q++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int mid = (b + a) / 2;
			int len = (b - a) / 2;
			if ((b - a) % 2 == 0) {
				// 홀수개
				sb.append(results1[mid][len] ? "1\n" : "0\n");
			}
			else {
				sb.append(results2[mid][len] ? "1\n" : "0\n");				
			}
		}
		System.out.print(sb.toString());
	}
}