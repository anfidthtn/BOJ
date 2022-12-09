import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static PriorityQueue<Integer> pq1;
	static PriorityQueue<Integer> pq2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		pq1 = new PriorityQueue<>((a, b) -> b - a);
		pq2 = new PriorityQueue<>((a, b) -> a - b);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			fill(num);
			sb.append(pq1.peek()).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void fill(int num) {
		if (pq1.isEmpty()) {
			pq1.add(num);
		} else if (pq2.isEmpty()) {
			if (pq1.peek() <= num) {
				pq2.add(num);
			} else {
				pq2.add(pq1.poll());
				pq1.add(num);
			}
		} else if (pq1.size() == pq2.size()){
			if (num <= pq2.peek()) {
				pq1.add(num);
			}
			else {
				pq1.add(pq2.poll());
				pq2.add(num);
			}
		}
		else {
			if (pq1.peek() <= num) {
				pq2.add(num);
			}
			else {
				pq2.add(pq1.poll());
				pq1.add(num);
			}
		}
	}
}