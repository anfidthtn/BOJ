import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Task {
		int date;
		int score;

		public Task(int date, int score) {
			this.date = date;
			this.score = score;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			tasks.add(new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		// 일자에 대한 내림차순
		tasks.sort(new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return o2.date - o1.date;
			}
		});

		// 점수에 대한 내림차순
		PriorityQueue<Task> pq = new PriorityQueue<>(new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return o2.score - o1.score;
			}
		});

		int scoreSum = 0;
		int idx = 0;
		for (int day = 1000; day >= 1; day--) {
			while (idx < N && tasks.get(idx).date >= day) {
				pq.add(tasks.get(idx++));
			}
			if (!pq.isEmpty()) {
				scoreSum += pq.poll().score;
			}
		}
		System.out.println(scoreSum);
	}
}