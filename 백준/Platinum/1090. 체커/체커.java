import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Point[] points = new Point[N];
		Set<Integer> Xs = new TreeSet<>();
		Set<Integer> Ys = new TreeSet<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Xs.add(points[i].x);
			Ys.add(points[i].y);
		}

		int[] minMoves = new int[N];
		for (int i = 0; i < N; i++) {
			minMoves[i] = Integer.MAX_VALUE;
		}

		for (int x : Xs) {
			for (int y : Ys) {
				List<Integer> diffs = new ArrayList<>();
				for (int i = 0; i < N; i++) {
					diffs.add(Math.abs(x - points[i].x) + Math.abs(y - points[i].y));
				}
				diffs.sort(Integer::compareTo);
				int sum = 0;
				for(int i = 0; i < N; i++) {
					sum += diffs.get(i);
					if (minMoves[i] > sum) {
						minMoves[i] = sum;
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.print(minMoves[i]);
			System.out.print(" ");
		}
	}
}