import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			Integer num = Integer.parseInt(br.readLine());
			if (num == 0) {
				num = pq.poll();
				sb.append(num == null ? 0 : num).append("\n");
			}
			else {
				pq.add(num);
			}
		}
		System.out.print(sb.toString());
	}
}