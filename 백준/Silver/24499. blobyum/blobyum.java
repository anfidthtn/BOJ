import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new LinkedList<>();
		int maxSum = 0;
		int tempSum = 0;
		int[] taste = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < N + K; i++) {
			queue.offer(taste[i % N]);
			tempSum += taste[i % N];
			if (queue.size() > K) {
				tempSum -= queue.poll();
			}
			if (queue.size() == K) {
				maxSum = Math.max(tempSum, maxSum);
			}
		}
		System.out.print(maxSum);
	}
}
