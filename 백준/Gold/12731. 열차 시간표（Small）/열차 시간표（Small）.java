import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Course{
		int start;
		int end;
		int target;
		public Course(int start, int end, int target) {
			this.start = start;
			this.end = end;
			this.target = target;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			sb.append("Case #").append(tNum).append(": ");
			int T = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int NA = Integer.parseInt(st.nextToken());
			int NB = Integer.parseInt(st.nextToken());
			PriorityQueue<Course> pq = new PriorityQueue<>(new Comparator<Course>() {
				@Override
				public int compare(Course o1, Course o2) {
					return o1.start - o2.start;
				}
			});
			for(int i = 0; i < NA; i++) {
				st = new StringTokenizer(br.readLine());
				pq.add(new Course(parseTime(st.nextToken()), parseTime(st.nextToken()) + T, 0));
			}
			for(int i = 0; i < NB; i++) {
				st = new StringTokenizer(br.readLine());
				pq.add(new Course(parseTime(st.nextToken()), parseTime(st.nextToken()) + T, 1));
			}
			int[] counts = new int[2];
			PriorityQueue<Integer> trainA = new PriorityQueue<>();
			PriorityQueue<Integer> trainB = new PriorityQueue<>();
			while(!pq.isEmpty()) {
				Course now = pq.poll();
				PriorityQueue<Integer> start = null;
				PriorityQueue<Integer> end = null;
				if (now.target == 0) {
					start = trainA;
					end = trainB;
				}
				else {
					start = trainB;
					end = trainA;
				}
				if (start.isEmpty() || now.start < start.peek()) {
					counts[now.target]++;
				}
				else {
					start.poll();
				}
				end.add(now.end);
			}
			sb.append(counts[0]).append(" ").append(counts[1]).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static int parseTime(String time) {
		String[] times = time.split(":");
		return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
	}
}