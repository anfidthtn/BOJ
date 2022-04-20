import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		long x;
		long y;
		public Point() {
			super();
		}
		public Point(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		List<Point> points = new ArrayList<>(N);
		long stdtempX = 40000;
		long stdtempY = 40000;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Point now = new Point();
			now.x = Integer.parseInt(st.nextToken());
			now.y = Integer.parseInt(st.nextToken());
			if (now.y < stdtempY) {
				stdtempY = now.y;
				stdtempX = now.x;
			} else if (now.y == stdtempY) {
				if (now.x < stdtempX) {
					stdtempX = now.x;
				}
			}
			points.add(now);
		}
		final long stdX = stdtempX;
		final long stdY = stdtempY;
//		System.out.println();
//		System.out.println(stdX + " " + stdY);
//		System.out.println();
		points.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				long ax = o1.x - stdX;
				long ay = o1.y - stdY;
				long bx = o2.x - stdX;
				long by = o2.y - stdY;
				if (ax * by < ay * bx) return 1;
				else if (ax * by > ay * bx) return -1;
				else {
					if (ax * ax + ay * ay < bx * bx + by * by) {
						return 1;
					}
					return -1;
				}
			}
		});
//		for (Point point : points) {
//			System.out.println(point.x + " " + point.y);
//		}
		Stack<Point> stack = new Stack<>();
		stack.add(new Point(stdX, stdY));
		for (Point point : points) {
			while(stack.size() > 2 && crossProduct(stack.get(stack.size() - 2).x, stack.get(stack.size() - 2).y, stack.peek(), point) <= 0) {
				stack.pop();
			}
			stack.add(point);
		}
//		System.out.println();
//		for (Point point : stack) {			
//			System.out.println(point.x + " " + point.y);
//		}
		System.out.println(stack.size() - 1);
	}
	
	
	public static long crossProduct(long stdX, long stdY, Point o1, Point o2) {
		long ax = o1.x - stdX;
		long ay = o1.y - stdY;
		long bx = o2.x - stdX;
		long by = o2.y - stdY;
		return ax * by - ay * bx;
	}
}