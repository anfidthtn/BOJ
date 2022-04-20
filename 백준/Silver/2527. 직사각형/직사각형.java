import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		/**
		 * 직사각형의 각 점을 순서(좌표가 증가하는)대로 준다는 점을 파악하자.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			int x11 = Integer.parseInt(st.nextToken());
			int y11 = Integer.parseInt(st.nextToken());
			int x12 = Integer.parseInt(st.nextToken());
			int y12 = Integer.parseInt(st.nextToken());
			int x21 = Integer.parseInt(st.nextToken());
			int y21 = Integer.parseInt(st.nextToken());
			int x22 = Integer.parseInt(st.nextToken());
			int y22 = Integer.parseInt(st.nextToken());
			
			/**
			 * X, Y : 각 도형의  X, Y 좌표관계
			 */
			
			int X;
			int Y;
			// 각각 최저좌표와 최고좌표 사이의 관계에서 서로 만나지 못하면 0이다.
			if (x22 < x11 || x12 < x21) X = 0;
			// 범위에 딱 맞는 경우
			else if (x22 == x11 || x12 == x21) X = 1;
			// 포함되는 경우
			else X = 2;
			if (y22 < y11 || y12 < y21) Y = 0;
			else if (y22 == y11 || y12 == y21) Y = 1;
			else Y = 2;
			
			// 만나지 못하는 좌표가 있으면 공통부분이 없다.
			if (X * Y == 0) System.out.println('d');

			// 각 좌표가 범위에 딱 맞으면 한 점에서 만난다.
			else if (X * Y == 1) System.out.println('c');

			// 한 좌표만 범위에 딱 맞으면 선분으로 만난다.
			else if (X * Y == 2) System.out.println('b');
			
			// 나머지는 둘 다 서로 포함범위이니 직사각형이다.
			else System.out.println('a');
		}
	}
}