import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static final int SEA = -1;
	static final int OUTSIDE = -2;

	static class Point {
		int r;
		int c;
		int group;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
			this.group = OUTSIDE;
		}
	}
	static class Edge {
		int s;
		int e;
		int distance;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// Point 클래스에서 equals나 hashcode 재정의 해도 되긴 되는데,
		// 같은 지점에 대해서는 같은 정보만 저장될 예정이라
		// 그냥 2차원 배열에 주소를 모두 저장해놓고 필요에 따라 갖다쓰는 식으로 함.
		Point[][] points = new Point[N + 2][M + 2];
		Queue<Point> landPoints = new LinkedList<>();
		
		for(int i = 0; i <= N + 1; i++) {
			for(int j = 0; j <= M + 1; j++) {
				points[i][j] = new Point(i, j);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				if (st.nextToken().charAt(0) == '0') {
					points[i][j].group = SEA;
				} else {
					landPoints.add(points[i][j]);
				}
			}
		}
		List<List<Point>> groupsPoints = new ArrayList<>();
		int group = -1;
		while (!landPoints.isEmpty()) {
			group++;
			List<Point> nowGroup = new LinkedList<>();
			groupsPoints.add(nowGroup);
			Queue<Point> check = new LinkedList<>();
			Point start = landPoints.poll();
			check.add(start);
			while (!check.isEmpty()) {
				Point now = check.poll();
				now.group = group;
				nowGroup.add(now);
				for (int d = 0; d < 4; d++) {
					int nextR = now.r + dr[d];
					int nextC = now.c + dc[d];
					if (landPoints.contains(points[nextR][nextC])) {
						landPoints.remove(points[nextR][nextC]);
						check.add(points[nextR][nextC]);
					}
				}
			}
		}
		
		PriorityQueue<Edge> bridges = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.distance - o2.distance;
			}
		});
		
		int groupSize = groupsPoints.size();
		
		for(int groupNum = 0; groupNum < groupSize; groupNum++) {
			for(Point point : groupsPoints.get(groupNum)) {
				for(int d = 0; d < 4; d++) {
					if (points[point.r + dr[d]][point.c + dc[d]].group == SEA) {
						if (points[point.r + dr[d] * 2][point.c + dc[d] * 2].group != SEA)
							continue;
						Edge bridge = setBridge(points, groupNum, point.r + dr[d] * 3, point.c + dc[d] * 3, d);
						if (bridge != null) {
							bridges.add(bridge);
						}
					}
				}
			}
		}
		int[] parents = new int[groupSize];
		for(int i = 1; i < groupSize; i++) {
			parents[i] = i;
		}
		int distance = 0;
		int groupAmount = groupSize;
		while(groupAmount > 1 && !bridges.isEmpty()) {
			Edge bridge = bridges.poll();
			int sp = getParent(parents, bridge.s);
			int ep = getParent(parents, bridge.e);
			if(sp != ep) {
				distance += bridge.distance;
				parents[sp] = ep;
				groupAmount--;
			}
		}
		if (groupAmount >= 2) {
			System.out.println(-1);
		}
		else {
			System.out.println(distance);
		}
	}
	public static int getParent(int[] parents, int now) {
		if (parents[now] == now)
			return now;
		return parents[now] = getParent(parents, parents[now]);
	}
	public static Edge setBridge(Point[][] points, int start, int row, int col, int d) {
		int distance = 2;
		while(points[row][col].group >= SEA) {
			switch(points[row][col].group) {
			case SEA:
				distance++;
				row += dr[d];
				col += dc[d];
				break;
			default:
				if (points[row][col].group != start) {
					Edge bridge = new Edge();
					bridge.s = start;
					bridge.e = points[row][col].group;
					bridge.distance = distance;
					return bridge;
				}
				else {
					return null;
				}
			}
		}
		return null;
	}
}