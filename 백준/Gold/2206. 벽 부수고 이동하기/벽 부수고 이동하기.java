import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int r;
		int c;
		boolean isBursted;

		public Node(int r, int c, boolean isBursted) {
			super();
			this.r = r;
			this.c = c;
			this.isBursted = isBursted;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		if ((N | M) == 1) {
			// 1 by 1 사이즈이면 1출력
			System.out.print(1);
			return;
		}
		char[][] map = new char[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j - 1);
			}
		}

		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		Queue<Node> queue = new LinkedList<>();
		boolean[][][] isVisited = new boolean[N + 2][M + 2][2];
		isVisited[1][1][0] = true;
		queue.add(new Node(1, 1, false));
		int nowDistance = 1;
		while (!queue.isEmpty()) {
			nowDistance++;
			Queue<Node> temp = new LinkedList<>();
			while (!queue.isEmpty()) {
				Node now = queue.poll();
//				System.out.println(now.r + " " + now.c + " " + now.isBursted);
				for (int d = 0; d < 4; d++) {
					int nextRow = now.r + dr[d];
					int nextCol = now.c + dc[d];
					if (nextRow == N && nextCol == M) {
						System.out.print(nowDistance);
						return;
					}
					
					// 안 뿌순 상태로도 왔던 곳이면 넘어감
					if (isVisited[nextRow][nextCol][0])
						continue;
					// 안 뿌순 상태로 왔으니 안 뿌순 상태로 왔다고 등록함.
					if (!now.isBursted)
						isVisited[nextRow][nextCol][0] = true;
					// 뿌순 상태로 왔던 곳인데 뿌순 상태로 오면 넘어감
					if (isVisited[nextRow][nextCol][1] && now.isBursted)
						continue;
					// 뿌순 상태로 왔으니 뿌순 상태로 왔다고 등록함.
					if (now.isBursted)
						isVisited[nextRow][nextCol][1] = true;
					switch (map[nextRow][nextCol]) {
					case '0':
						temp.add(new Node(nextRow, nextCol, now.isBursted));
						break;
					case '1':
						if(!now.isBursted) {
							temp.add(new Node(nextRow, nextCol, true));							
						}
						break;
					}
				}
			}
			queue = temp;
		}
		System.out.print(-1);
	}
}