import java.util.ArrayList;
import java.util.List;

class Solution {
	public static void main(String[] args){
		new Solution().solution(3, 3, new String[] {"DBA", "C*A", "CDB"});
	}
	class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	int[] dr = { -1, 0, 1, 0 };
	int[] dc = { 0, -1, 0, 1 };
	char[][] map;
	int mm, nn;
	List<List<Point>> points;

	public String solution(int m, int n, String[] board) {
		String answer = "";
		map = new char[m][];
		points = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			points.add(new ArrayList<>());
		}
		mm = m;
		nn = n;
		int count = 0;
		for (int i = 0; i < m; i++) {
			map[i] = board[i].toCharArray();
			for (int j = 0; j < n; j++) {
				if ('A' <= map[i][j] && map[i][j] <= 'Z') {
					points.get(map[i][j] - 'A').add(new Point(i, j));
					count++;
				}
			}
		}
		count /= 2;
		List<Character> result = new ArrayList<>();
		for(int i = 0; i < count; i++) {
			for(int j = 0; j <= 26; j++) {
				if (j == 26) {
					return "IMPOSSIBLE";
				}
				if(points.get(j).size() == 0) {
					continue;
				}
				boolean find = false;
				for(int d = 0; d < 4; d++) {
					if (search(points.get(j).get(0).row, points.get(j).get(0).col, d, 0, (char)(j + 'A'))) {
						map[points.get(j).get(0).row][points.get(j).get(0).col] = '.';
						map[points.get(j).get(1).row][points.get(j).get(1).col] = '.';
						points.get(j).clear();
						result.add((char)(j + 'A'));
						find = true;
						break;
					}
				}
				if (find) {
					break;
				}
			}
		}
		for(char c : result) {
			answer += c;
		}
		return answer;
	}

	public boolean search(int row, int col, int d, int count, char target) {
		int targetRow = points.get(target - 'A').get(1).row;
		int targetCol = points.get(target - 'A').get(1).col;
		if (count == 1) {
			if (d % 2 == 0) {
				if (col != targetCol) {
					return false;
				}
				if (d == 0) {
					if (targetRow > row) {
						return false;
					}
				} else {
					if (targetRow < row) {
						return false;
					}
				}
			} else {
				if (row != targetRow) {
					return false;
				}
				if (d == 1) {
					if (targetCol > col) {
						return false;
					}
				} else {
					if (targetCol < col) {
						return false;
					}
				}
			}
		}
		int nextRow = row + dr[d];
		int nextCol = col + dc[d];
		if (boundaryCheck(nextRow, nextCol)) {
			if (targetRow == nextRow && targetCol == nextCol) {
				return true;
			}
			if (map[nextRow][nextCol] == '.') {
				if (search(nextRow, nextCol, d, count, target)) {
					return true;
				}
			}
		}
		if (count == 0) {
			for (int flip : new int[] { 1, 3 }) {
				int nextD = (d + flip) % 4;
				nextRow = row + dr[nextD];
				nextCol = col + dc[nextD];
				if (boundaryCheck(nextRow, nextCol)) {
					if (targetRow == nextRow && targetCol == nextCol) {
						return true;
					}
					if (map[nextRow][nextCol] == '.') {
						if (search(nextRow, nextCol, nextD, count + 1, target)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean boundaryCheck(int row, int col) {
		if (row < 0 || col < 0 || row >= mm || col >= nn) {
			return false;
		}
		return true;
	}
}