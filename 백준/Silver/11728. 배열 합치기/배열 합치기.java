import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] B = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		StringBuilder sb = new StringBuilder();
		int a = 0;
		int b = 0;
		while (a < N && b < M) {
			if (A[a] < B[b]) {
				sb.append(A[a++]).append(" ");
			} else {
				sb.append(B[b++]).append(" ");
			}
		}
		while (a < N) {
			sb.append(A[a++]).append(" ");
		}
		while (b < M) {
			sb.append(B[b++]).append(" ");
		}
		System.out.println(sb.toString());
	}
}