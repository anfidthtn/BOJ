import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Point{
	char r;
	char c;
	public Point(char r, char c) {
		this.r = r;
		this.c = c;
	}
	
	public ArrayList<Integer> getNext() {
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) return false;
		Point object = (Point) obj;
		if (this.r == object.r && this.c == object.c) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return (r << 10) + c;
	}
}
public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		tCase : for (int tNum = 1; tNum <= t; tNum++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			Queue<Object[]> fireInit = new LinkedList<>();
			Map<Point, Integer> fire = new HashMap<>();
			Set<Point> emptySpace = new HashSet<>();
			Point player = null;
			
			for(int i = 0; i < h; i++) {
				String s = br.readLine();
				for(int j = 0; j < w; j++) {
					switch(s.charAt(j)) {
					case '*':
						Point newFire = new Point((char)i, (char)j);
						// 불이면 불을 번지게할 큐에 불 정보를 넣는다.
						fireInit.offer(new Object[] {newFire, 0});
						// 해당 위치에 불이 언제 번지는지 정보를 저장한다.
						fire.put(newFire, 0);
						break;
					case '@':
						// 플레이어 위치를 받아서 저장한다.
						player = new Point((char)i, (char)j);
					case '.':
						// 플레이어의 초기 위치를 포함한 모든 빈칸을 저장한다.
						// 만약 플레이어의 초기 위치를 빈칸으로 저장하지 않으면, 빈 칸에 불이 번진다는 논리에서 플레이어의 초기 위치에 번지지 않는 상황이 나온다.
						emptySpace.add(new Point((char)i, (char)j));
						break;
					}
				}
			}
			
			// 불을 번지게하며 번진 시간 정보를 저장시키는 반복문
			while (!fireInit.isEmpty()) {
				Object[] now = fireInit.poll();
				Point nowPoint = (Point) now[0];
				int nowTime = (int) now[1];
				for (int d = 0; d < 4; d++) {
					Point nextPoint = new Point((char)(nowPoint.r + dr[d]), (char)(nowPoint.c + dc[d]));
					
					// 빈 칸에만 번질 수 있다. (초기 불 위치도 빈 칸이어야 논리적으로 맞긴 한데, 어차피 불 있는 곳으로 플레이어가 못가니까 빈칸이든 아니든 상관없다.)
					if (!emptySpace.contains(nextPoint)) continue;
					// 이미 불이 번진 곳은 번지지 않는다.
					if (fire.containsKey(nextPoint)) continue;
					// 다음 지점을 번진 곳으로 만든다.
					fire.put(nextPoint, nowTime + 1);
					// 번진 지점에서 또 번지게 할 수 있는지를 보게 큐에 다음지점을 넣는다.
					fireInit.offer(new Object[] {nextPoint, nowTime + 1});
				}
			}
			
			
			Queue<Object[]> playerMove = new LinkedList<>();
			// 플레이어의 시작위치와 시작시간을 큐에 넣는다.
			playerMove.offer(new Object[] {player, 0});
			/**
			 * ######
			 * #*#@.#
			 * #.#..#
			 * ######
			 * 이런 상황에서 플레이어는 탈출할 수 없기 때문에 이럴때를 대비해서 불과 관련없이 방문한 곳도 체크를 해줘야한다.
			 */
			Set<Point> isVisited = new HashSet<>();
			isVisited.add(player);
			// 플레이어의 이동을 돌린다.
			while (!playerMove.isEmpty()) {
				Object[] now = playerMove.poll();
				Point nowPlayer = (Point) now[0];
				int nextTime = (int) now[1] + 1;
				for (int d = 0; d < 4; d++) {
					int nextR = nowPlayer.r + dr[d];
					int nextC = nowPlayer.c + dc[d];
					if (nextR < 0 || nextR >= h || nextC < 0 || nextC >= w) {
						// 맵 밖으로 나갈 수 있는 경우 나가는 시간을 기록한다.
						sb.append(nextTime).append("\n");
						// 나갈 수 있는 테케니까 여기서 멈추고 다음 테케로 넘어간다.
						continue tCase;
					}
					Point nextPoint = new Point((char)nextR, (char)nextC);
					if (isVisited.contains(nextPoint)) continue;
					// 플레이어는 빈 칸으로만 이동할 수 있다.
					if (!emptySpace.contains(nextPoint)) continue;
					// 플레이어는 불이 오기 전까지만 이동할 수 있다.
					if (fire.containsKey(nextPoint) && fire.get(nextPoint) <= nextTime) continue;
					// 이동가능한 곳이면 다음 이동지점으로 이동해본다.
					isVisited.add(nextPoint);
					playerMove.offer(new Object[] {nextPoint, nextTime});
				}
			}
			// 플레이어의 이동으로 밖으로 나올 수 없는 상황이면 임파서블을 출력한다.
			sb.append("IMPOSSIBLE\n");
		}
		System.out.print(sb.toString());
	}
}