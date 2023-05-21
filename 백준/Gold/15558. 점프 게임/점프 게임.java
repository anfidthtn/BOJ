import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int[][] records;
	static int N;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[2][];
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map[0] = br.readLine().toCharArray();
		map[1] = br.readLine().toCharArray();
		records = new int[2][N];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		records[0][0] = 1;
		int time = 1;
		while (!queue.isEmpty()) {
			time++;
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				int now = queue.poll();
				int nowRow = now / 100000;
				int nowCol = now % 100000;
				if (nowCol + K >= N) {
					System.out.println(1);
					return;
				}
				if (time <= nowCol && map[nowRow][nowCol - 1] == '1' && records[nowRow][nowCol - 1] == 0) {
					records[nowRow][nowCol - 1] = time;
					queue.add(nowRow * 100000 + nowCol - 1);
				}
				if (map[nowRow][nowCol + 1] == '1' && records[nowRow][nowCol + 1] == 0) {
					records[nowRow][nowCol + 1] = time;
					queue.add(nowRow * 100000 + nowCol + 1);
				}
				if (map[nowRow ^ 1][nowCol + K] == '1' && records[nowRow ^ 1][nowCol + K] == 0) {
					records[nowRow ^ 1][nowCol + K] = time;
					queue.add((nowRow ^ 1) * 100000 + nowCol + K);
				}
			}
		}
		System.out.println(0);
	}
}