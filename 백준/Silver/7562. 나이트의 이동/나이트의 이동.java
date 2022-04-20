import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;


class Point{
	static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
	int r;
	int c;
	int move;
	public Point(int r, int c, int move) {
		super();
		this.r = r;
		this.c = c;
		this.move = move;
	}
	
	public Point getNextPoint(int d, int L) {
		if (r + dr[d] < 0 || c + dc[d] < 0 || r + dr[d] >= L || c + dc[d] >= L) return null;
		return new Point(r + dr[d], c + dc[d], move + 1);
	}
	@Override
	public boolean equals(Object obj) {
		Point o = (Point) obj;
		if (r == o.r && c == o.c) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return (r << 10) + c;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		tCase : for (int tNum = 1; tNum <= t; tNum++) {
			int L = Integer.parseInt(br.readLine());
			// 말 초기위치 저장
			int[] RC = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Point start = new Point(RC[0], RC[1], 0);
			// 말 목표지점 저장
			RC = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Point end = new Point(RC[0], RC[1], 0);
			
			
			if (start.equals(end)) {
				// 시작 지점과 목표지점이 같으면 바로 끝내면 된다.
				sb.append(0).append("\n");
				continue;
			}
			Set<Point> isVisited = new HashSet<>();
			Queue<Point> bfs = new LinkedList<>();
			// 초기위치를 방문했다고 해주고
			isVisited.add(start);
			// 초기위치를 탐색지점으로 넣어주고
			bfs.offer(start);
			// bfs를 돌린다.
			while(!bfs.isEmpty()) {
				// bfs 안의 가장 이동횟수가 적은 곳부터 꺼내서 탐색한다.
				Point nowPoint = bfs.poll();
				// 8방탐색을 한다.
				for (int d = 0; d < 8; d++) {
					Point nextPoint = nowPoint.getNextPoint(d, L);
					if (nextPoint == null) continue;
					if (isVisited.contains(nextPoint)) continue;
					if (nextPoint.equals(end)) {
						// 다음 탐색 지점이 끝 지점이면 발견한 것이다.
						sb.append(nextPoint.move).append("\n");
						continue tCase;
					}
					isVisited.add(nextPoint);
					bfs.offer(nextPoint);
				}
			}
		}
		System.out.print(sb.toString());
	}
}