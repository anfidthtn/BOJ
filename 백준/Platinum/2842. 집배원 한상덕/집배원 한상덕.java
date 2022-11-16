import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N;
	static int startRow, startCol;
	static int Kcount;
	static List<Integer> Hlist;
	static int pMin, pMax;
	
	static class Point{
		int row;
		int col;
		char c;
		int h;
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static Point[][] points;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pMin = 1 << 30;
		pMax = 0;
		points = new Point[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				points[i][j] = new Point(i, j);
			}
		}
		
		Kcount = 0;
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				points[i][j].c = str.charAt(j);
				if (points[i][j].c == 'K') {
					Kcount++;
				}
				else if (points[i][j].c == 'P') {
					startRow = i;
					startCol = j;
				}
			}
		}
		Set<Integer> Hset = new TreeSet<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				points[i][j].h = Integer.parseInt(st.nextToken());
				if (points[i][j].c == 'P' || points[i][j].c == 'K') {
					pMin = Math.min(points[i][j].h, pMin);
					pMax = Math.max(points[i][j].h, pMax);
				}
				Hset.add(points[i][j].h);
			}
		}
		Hlist = new ArrayList<>(Hset);
		Hlist.sort(Integer::compareTo);
		int minIdx = -1;
		for (int i = 0; i < Hlist.size(); i++) {
			if (Hlist.get(i) == pMin) {
				minIdx = i;
				break;
			}
		}
		int maxIdx = -1;
		for (int i = Hlist.size() - 1; i >= 0; i--) {
			if (Hlist.get(i) == pMax) {
				maxIdx = i;
				break;
			}
		}
		
		int answer = Hlist.get(Hlist.size() - 1) - Hlist.get(0);
		for(int a = 0; a <= minIdx; a++) {
			int target = Hlist.get(a) + answer;
			int left = maxIdx;
			int right = Hlist.size() - 1;
			int startIdx = maxIdx - 1;
			while(left <= right) {
				int mid = (left + right) >> 1;
				if (Hlist.get(mid) >= target) {
					right = mid - 1;
				}
				else {
					startIdx = mid;
					left = mid + 1;
				}
			}
			for(int b = startIdx; b >= maxIdx; b--) {
				if (checkBFS(Hlist.get(a), Hlist.get(b))) {
					answer = Hlist.get(b) - Hlist.get(a);
				}
				else {
					break;
				}
			}
		}
		System.out.println(answer);
	}
	public static boolean checkBFS(int min, int max) {
		boolean[][] visited = new boolean[N][N];
		Queue<Point> queue = new LinkedList<>();
		queue.add(points[startRow][startCol]);
		visited[startRow][startCol] = true;
		int bfsKcount = 0;
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			for(int d = 0; d < 8; d++) {
				int nextRow = now.row + dr[d];
				int nextCol = now.col + dc[d];
				if (!boundaryCheck(nextRow, nextCol)) {
					continue;
				}
				if (visited[nextRow][nextCol]) {
					continue;
				}
				if (points[nextRow][nextCol].h < min || max < points[nextRow][nextCol].h) {
					continue;
				}
				visited[nextRow][nextCol] = true;
				if (points[nextRow][nextCol].c == 'K') {
					if (++bfsKcount == Kcount) {
						return true;
					}
				}
				queue.add(points[nextRow][nextCol]);
			}
		}
		return false;
	}
	public static boolean boundaryCheck(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
}