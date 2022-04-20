import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;

	static class Enemy {
		int r;
		int c;
		int[] distance;
		boolean status;

		public Enemy(int r, int c, int[] archer) {
			this.r = r;
			this.c = c;
			this.distance = new int[3];
			for (int i = 0; i < 3; i++) {
				distance[i] = N - r + Math.abs(archer[i] - c);
			}
			this.status = true;
		}
	}
	static class Point{
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		List<Point> enemyPoints = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (st.nextToken().charAt(0) == '1') {
					enemyPoints.add(new Point(i, j));
				}
			}
		}
		int maxCount = 0;
		for (int a = 0; a < M; a++) {
			for (int b = a + 1; b < M; b++) {
				for (int c = b + 1; c < M; c++) {
					maxCount = Math.max(maxCount, simulate(new int[] {a, b, c}, enemyPoints, D));
//					if(maxCount == 28) {
//						System.out.println(a + " " + b + " " + c);
//						return;
//					}
				}
			}
		}
		System.out.println(maxCount);
	}

	public static int simulate(int[] archer, List<Point> enemyPoints, int D) {
		List<PriorityQueue<Enemy>> pqList = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			final int idx = i;
			pqList.add(new PriorityQueue<>(new Comparator<Enemy>() {
				@Override
				public int compare(Enemy o1, Enemy o2) {
					if (o1.distance[idx] - o2.distance[idx] == 0) {
						return o1.c - o2.c;
					}
					return o1.distance[idx] - o2.distance[idx];
				}
			}));
		}
		int count = 0;
		for(Point enemyPoint : enemyPoints) {
			Enemy enemy = new Enemy(enemyPoint.r, enemyPoint.c, archer);
			for(int i = 0; i < 3; i++) {
				if (Math.abs(enemy.c - archer[i]) < D) {
					pqList.get(i).add(enemy);
				}
			}
		}
		int turn = 0;
		while(!pqList.get(0).isEmpty() || !pqList.get(1).isEmpty() || !pqList.get(2).isEmpty()) {
			for(int i = 0; i < 3; i++) {
				while(!pqList.get(i).isEmpty()) {
					Enemy enemy = pqList.get(i).peek();
					if (enemy.distance[i] > D + turn)
						break;
					if (enemy.r + turn < N && enemy.status) {
						break;
					}
					pqList.get(i).poll();
				}
			}
			for(int i = 0; i < 3; i++) {
				if (!pqList.get(i).isEmpty()) {
					Enemy enemy = pqList.get(i).peek();
					if (enemy.distance[i] > D + turn)
						continue;
					if (enemy.r + turn < N && enemy.status) {
						pqList.get(i).peek().status = false;
						count++;
					}
				}
			}
			turn++;
		}
		
		return count;
	}
}