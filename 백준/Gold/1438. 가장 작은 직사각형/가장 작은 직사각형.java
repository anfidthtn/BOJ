import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static List<Point> points;
	static Map<Integer, Integer> Xmap;
	static List<Integer> Xlist;
	static Set<Integer> Xset;
	static int minArea;

	public static void main(String[] args) throws IOException {
		minArea = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		points = new ArrayList<>();
		Xmap = new TreeMap<>();
		Xset = new TreeSet<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points.add(new Point(x, y));
			x = points.get(i).x;
			if (!Xmap.containsKey(x)) {
				Xmap.put(x, 0);
			}
			Xmap.put(x, Xmap.get(x) + 1);
			Xset.add(x);
		}
		Xlist = new ArrayList<>(Xset);
		Xlist.sort(Integer::compareTo);
		for (int xLeft = 0; xLeft < Xlist.size(); xLeft++) {
			int xCount = 0;
			for (int xRight = xLeft - 1; xRight < Xlist.size();) {
				if (xCount >= N / 2) {
					List<Integer> Ylist = new ArrayList<>();
					for (int i = 0; i < N; i++) {
						if (Xlist.get(xLeft) <= points.get(i).x && points.get(i).x <= Xlist.get(xRight)) {
							Ylist.add(points.get(i).y);
						}
					}
					Ylist.sort(Integer::compareTo);
					int xSide = Xlist.get(xRight) - Xlist.get(xLeft) + 2;
					for (int i = N / 2 - 1; i < Ylist.size(); i++) {
						minArea = Math.min(minArea, xSide * (Ylist.get(i) - Ylist.get(i - N / 2 + 1) + 2));
					}
				}
				if (++xRight != Xlist.size()) {
					xCount += Xmap.get(Xlist.get(xRight));
				}
			}
		}
		System.out.println(minArea);

	}
}