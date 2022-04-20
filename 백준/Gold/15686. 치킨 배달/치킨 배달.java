import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		int row;
		int col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public int getDistance(Point other) {
			return Math.abs(row - other.row) + Math.abs(col - other.col);
		}
	}
	
	static int minDistance = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Point> house = new ArrayList<>();
		List<Point> chicken = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				char now = st.nextToken().charAt(0);
				if(now == '1') house.add(new Point(i, j));
				if(now == '2') chicken.add(new Point(i, j));
			}
		}
		// 선택된 치킨집의 인덱스
		int[] cIndex = new int[M];
		getMinDistance(house, chicken, cIndex, 0, 0, M);
		System.out.print(minDistance);
	}
	
	public static void getMinDistance(List<Point> house, List<Point> chicken, int[] cIndex, int depth, int count, int M) {
		if (count == M) {
			int distanceSum = 0;
			// 집들에 대해서 최저거리 합계를 구한다.
			for(Point h : house) {
				int distance = Integer.MAX_VALUE;
				for(int idx : cIndex) {
					distance = Math.min(distance, h.getDistance(chicken.get(idx)));
				}
				distanceSum += distance;
			}
			minDistance = Math.min(minDistance, distanceSum);
			return;
		}
		if (depth == chicken.size()) return;
		getMinDistance(house, chicken, cIndex, depth + 1, count, M);
		cIndex[count] = depth;
		getMinDistance(house, chicken, cIndex, depth + 1, count + 1, M);
	}
}