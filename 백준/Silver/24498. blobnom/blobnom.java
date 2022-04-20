import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.print(Integer.parseInt(br.readLine()));
			return;
		}
		if (N == 2) {
			int[] H = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			System.out.print(Math.max(H[0], H[1]));
			return;
		}
		int[] heights = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int max = Math.max(heights[0], heights[N - 1]);
		for(int i = 1; i < N - 1; i++) {
			max = Math.max(max, Math.min(heights[i - 1], heights[i + 1]) + heights[i]);
		}
		System.out.print(max);
		
	}
}