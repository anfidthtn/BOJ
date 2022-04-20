import java.util.ArrayList;
import java.util.Scanner;

class Point{
	int row;
	int col;
	
	public Point(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;
		if (p.row == this.row && p.col == this.col) {
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return (row << 10) + col;
	}
}
public class Main {
	public static void main(String[] args) {
		/**
		 * 전체 알고리즘 흐름
		 * 파인 육각형을 직사각형 2개로 분리하여 넓이의 차를 구한다.
		 * 각 좌표의 최대 최소값을 찾아서, 없는 나머지 1좌표가 파인 직사각형의 점이다.
		 * 각 좌표의 최대 최소값으로 전체 넓이를 구한 다음
		 * 중간에 있는 좌표를 찾아서 위에서 구한 파인 직사각형의 점과의 좌표거리로 넓이를 구해서
		 * 곱해준다. 그 다음 k곱한다.
		 */
		Scanner s = new Scanner(System.in);
		int k = s.nextInt();
		s.nextLine();
		int nowRow = 0;
		int nowCol = 0;
		
		// 각 최대최소 좌표
		int maxRow = Integer.MIN_VALUE;
		int maxCol = Integer.MIN_VALUE;
		int minRow = Integer.MAX_VALUE;
		int minCol = Integer.MAX_VALUE;
		
		ArrayList<Point> points = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			switch(s.nextInt()) {
			case 1:
				// 동쪽
				nowCol += s.nextInt();
				break;
			case 2:
				// 서쪽
				nowCol -= s.nextInt();
				break;
			case 3:
				// 남쪽
				nowRow -= s.nextInt();
				break;
			case 4:
				// 북쪽
				nowRow += s.nextInt();
				break;
			}
			// 최대최저 갱신
			maxRow = Math.max(nowRow, maxRow);
			maxCol = Math.max(nowCol, maxCol);
			minRow = Math.min(nowRow, minRow);
			minCol = Math.min(nowCol, minCol);
			points.add(new Point(nowRow, nowCol));
		}
		
		// 최대최소에 포함되지 않은 파인 직사각형의 점을 구한다.
		Point newPoint = null;
		if (!points.contains(new Point(maxRow, maxCol))) {
			newPoint = new Point(maxRow, maxCol);
		}
		if (!points.contains(new Point(maxRow, minCol))) {
			newPoint = new Point(maxRow, minCol);
		}
		if (!points.contains(new Point(minRow, maxCol))) {
			newPoint = new Point(minRow, maxCol);
		}
		if (!points.contains(new Point(minRow, minCol))) {
			newPoint = new Point(minRow, minCol);
		}
		
		// 중앙의 점을 제외하고는 전부 최대최소 좌표를 1개 이상씩 갖고있으니, 그것을 이용해 중앙의 점 하나만 남겨서 points.get(0)을 하면 중앙의 점이 나오게 한다.
		for (int i = points.size() - 1; i >= 0; i--) {
			int pointRow = points.get(i).row;
			int pointCol = points.get(i).col;
			if (pointRow == maxRow || pointRow == minRow || pointCol == maxCol || pointCol == minCol) {
				// 최대최소가 아닌 중간에 있는 단 하나의 좌표만 남기기 위해 최대최소좌표를 포함하면 다 버린다.
				points.remove(i);
			}
		}
		
		// 전체 넓이
		int totalSize = (maxRow - minRow) * (maxCol - minCol);
		// 파인 직사각형의 넓이
		int subSize = Math.abs((newPoint.row - points.get(0).row) * (newPoint.col - points.get(0).col));
		
		// 출력
		System.out.println(k * (totalSize - subSize));
	}
}