import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		br.readLine();
		boolean[] isVisited = new boolean[n + 1];
		Queue<Integer[]> bfs = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		while (st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			// 입력 중복 제거
			if (isVisited[num]) continue;
			// 방문 여부 검사
			isVisited[num] = true;
			// 큐에 넣기
			bfs.offer(new Integer[] {num, 0});
		}
		
		while (!bfs.isEmpty()) {
			Integer[] info = bfs.poll();
			max = Math.max(max, info[1]);
			int nowNum = info[0];
			for (int bit = 1; bit <= n; bit <<= 1) {
				int nextNum = nowNum^bit;
				if (nextNum > n) continue;
				if (!isVisited[nextNum]) {
					isVisited[nextNum] = true;
					bfs.offer(new Integer[] {nextNum, info[1] + 1});
				}
			}
		}
		System.out.print(max);
	}
}