import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Want {
		int start;
		int end;

		public Want(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Want[] wants = new Want[M + 1];
			for (int student = 1; student <= M; student++) {
				st = new StringTokenizer(br.readLine());
				wants[student] = new Want(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			int[] matching = new int[N + 1];
			int count = 0;
			for (int student = 1; student <= M; student++) {
				boolean[] visited = new boolean[N + 1];
				if (match(wants, matching, visited, student)) {
					count++;
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static boolean match(Want[] wants, int[] matching, boolean[] visited, int student) {
		for(int book = wants[student].start; book <= wants[student].end; book++) {
			if (visited[book]) {
				continue;
			}
			visited[book] = true;
			if (matching[book] == 0 || match(wants, matching, visited, matching[book])) {
				matching[book] = student;
				return true;
			}
		}
		return false;
	}
}