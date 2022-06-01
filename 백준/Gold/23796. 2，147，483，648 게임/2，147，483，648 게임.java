import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static class Point{
		int row;
		int col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[][] map = new long[8][];
		for(int i = 0; i < 8; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		}
		char order = br.readLine().charAt(0);
		Point[] points = new Point[8];
		switch(order) {
		case 'L':
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					points[j] = new Point(i, j);
				}
				linePush(map, points);
			}
			break;
		case 'R':
			for(int i = 0; i < 8; i++) {
				for(int j = 7; j >= 0; j--) {
					points[7 - j] = new Point(i, j);
				}
				linePush(map, points);
			}
			break;
		case 'U':
			for(int j = 0; j < 8; j++) {
				for(int i = 0; i < 8; i++) {
					points[i] = new Point(i, j);
				}
				linePush(map, points);
			}
			break;
		case 'D':
			for(int j = 0; j < 8; j++) {
				for(int i = 7; i >= 0; i--) {
					points[7 - i] = new Point(i, j);
				}
				linePush(map, points);
			}
			break;
		}
		for(int i = 0; i < 8 ; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void linePush(long[][] map, Point[] points) {
		int left = 0;
		int right = 1;
		while(left < 8) {
			if (right == 8) {
				left++;
				right = left + 1;
				continue;
			}
			if (map[points[left].row][points[left].col] == 0 && map[points[right].row][points[right].col] > 0) {
				map[points[left].row][points[left].col] = map[points[right].row][points[right].col];
				map[points[right].row][points[right].col] = 0;
				continue;
			}
			if (map[points[left].row][points[left].col] == map[points[right].row][points[right].col]) {
				if (map[points[right].row][points[right].col] > 0) {
					map[points[right].row][points[right].col] = 0;
					map[points[left].row][points[left].col] *= 2;
					left++;
					right = left + 1;
					continue;
				}
			}
			if (map[points[left].row][points[left].col] > 0 && map[points[right].row][points[right].col] > 0) {
				left++;
				right = left + 1;
				continue;
			}
			if (map[points[right].row][points[right].col] == 0) {
				right++;
			}
		}
	}
}