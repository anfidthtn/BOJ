import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static class Pin{
		int num;
		int row;
		int col;
		boolean status;
		public Pin(int num, int row, int col) {
			this.num = num;
			this.row = row;
			this.col = col;
			this.status = true;
		}
	}
	static int minCount;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			char[][] map = new char[7][11];
			Pin[][] pins = new Pin[7][11];
			List<Pin> pinList = new ArrayList<>();
			
			int num = 0;
			for (int i = 1; i <= 5; i++) {
				String str = br.readLine();
				for (int j = 1; j <= 9; j++) {
					map[i][j] = str.charAt(j - 1);
					if(map[i][j] == 'o') {
						pins[i][j] = new Pin(++num, i, j);
						pinList.add(pins[i][j]);
						map[i][j] = '.';
					}
				}
			}
			count = num;
			minCount = count;
			dfs(map, pins, pinList);
			sb.append(minCount).append(" ").append(num - minCount).append("\n");
			if (tNum != t) {
				br.readLine();
			}
		}
		System.out.print(sb.toString());
	}
	public static void dfs(char[][] map, Pin[][] pins, List<Pin> pinList) {
		for(Pin pin : pinList) {
			if (!pin.status) {
				continue;
			}
			int row = pin.row;
			int col = pin.col;
			for(int d = 0; d < 4; d++) {
				int nextRow = pin.row + dr[d];
				int nextCol = pin.col + dc[d];
				if (pins[nextRow][nextCol] == null) {
					continue;
				}
				if (!pins[nextRow][nextCol].status) {
					continue;
				}
				int jumpRow = nextRow + dr[d];
				int jumpCol = nextCol + dc[d];
				if (map[jumpRow][jumpCol] != '.') {
					continue;
				}
				if (pins[jumpRow][jumpCol] != null && pins[jumpRow][jumpCol].status) {
					continue;
				}
				pins[nextRow][nextCol].status = false;
				Pin temp = pins[nextRow][nextCol];
				pin.row = jumpRow;
				pin.col = jumpCol;
				Pin temp2 = pins[jumpRow][jumpCol];
				pins[jumpRow][jumpCol] = pin;
				pins[row][col] = null;
				count--;
				if (minCount > count) {
					minCount--;
				}
				dfs(map, pins, pinList);
				count++;
				pins[nextRow][nextCol] = temp;
				temp.status = true;
				pins[row][col] = pin;
				pins[jumpRow][jumpCol] = temp2;
				pin.row = row;
				pin.col = col;
			}
		}
	}
}