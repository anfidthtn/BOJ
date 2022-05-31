import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			sb.append("Case #").append(tNum).append(":\n");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] length = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int q = 0; q < M; q++) {
				st = new StringTokenizer(br.readLine());
				int L = Integer.parseInt(st.nextToken());
				int R = Integer.parseInt(st.nextToken());
				int count = R - L + 1;
				double result = 1;
				for(int i = L; i <= R; i++) {
					result *= Math.pow(length[i], 1.0 / count);
				}
				sb.append(result).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
}