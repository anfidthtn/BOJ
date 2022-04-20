import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] data = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(data);
		
		StringBuilder sb = new StringBuilder();
		permu(N, M, data, new int[M], 0, 0, sb);
		System.out.println(sb.toString());
	}
	public static void permu(int N, int M, int[] data, int[] choice, int idx, int count, StringBuilder sb) {
		if (count == M) {
			for(int i = 0; i < M; i++) {
				sb.append(data[choice[i]]).append(" ");
			}
			sb.append("\n");
			return;
		}
		if (idx == N) {
			return;
		}
		choice[count] = idx;
		permu(N, M, data, choice, idx, count + 1, sb);
		permu(N, M, data, choice, idx + 1, count, sb);
	}
}