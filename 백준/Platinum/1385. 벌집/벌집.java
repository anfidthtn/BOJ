import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> levelStartPoint;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		if (a == b) {
			System.out.print(a);
			return;
		}
		int N = Math.max(a, b);

		levelStartPoint = new ArrayList<>();
		levelStartPoint.add(1);

		for (int i = 2; levelStartPoint.get(levelStartPoint.size() - 1) <= N; i += 6 * (levelStartPoint.size() - 1)) {
			levelStartPoint.add(i);
		}

//		for (int i = 1; i < 20; i++) {
//			System.out.print(i + " : ");
//			for (int next : getNext(i)) {
//				System.out.print(next + " ");
//			}
//			System.out.println();
//		}

		int[] parent = new int[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		
		// 최종 도착지는 -1로 설정한다.
		parent[b] = -1;
		// 도착지점부터 탐색을 시작한다.
		queue.add(b);
		boolean finished = false;
		while(!finished) {
			int now = queue.poll();
			for(int next : getNext(now)) {
				// 범위 벗어나면 넘김
				if (N < next) {
					continue;
				}
				// 0이 아니면 도착지점이거나 방문한 지점
				if (parent[next] != 0) {
					continue;
				}
				// 방문 안 한 지점은 도착지점에서 최단거리 위치 찍어줌
				parent[next] = now;
				if (next == a) {
					finished = true;
					break;
				}
				
				// 탐색지점 등록
				queue.add(next);
			}
		}
		StringBuilder sb = new StringBuilder();
		int idx = a;
		while(idx != -1) {
			sb.append(idx).append(" ");
			idx = parent[idx];
		}
		System.out.println(sb.toString());
	}

	public static int getLevel(int now) {
		return binarySearch(0, levelStartPoint.size(), now) - 1;
	}

	public static int binarySearch(int left, int right, int target) {
		if (left == right) {
			return left;
		}
		int mid = (left + right) >> 1;

		if (levelStartPoint.get(mid) <= target) {
			return binarySearch(mid + 1, right, target);
		} else {
			return binarySearch(left, mid, target);
		}
	}

	public static int[] getNext(int now) {
		if (now == 1) {
			return new int[] { 2, 3, 4, 5, 6, 7 };
		}
		int level = getLevel(now);
		int count = now - levelStartPoint.get(level);
		int section = count / level;
		int[] next = new int[6];
		next[0] = now - 1;
		next[1] = now + 1;
		
		if (count == 0) {
			next[2] = levelStartPoint.get(level + 1) - 1;
		} else {
			next[2] = now - 6 * (level - 1) - (section + 1);
		}
		
		if (count % level != level - 1) {
			next[3] = now - 6 * (level - 1) - section;
		}
		else if (section == 5){
			next[3] = levelStartPoint.get(level);
		}
		else {
			next[3] = now + level * 6 + (section + 2);
		}
		
		next[4] = now + level * 6 + section;
		next[5] = now + level * 6 + section + 1;
		
		return next;
	}
}