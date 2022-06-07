import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			StringBuilder lineSb = new StringBuilder();
			char[][] map = new char[R][C];
			String line = st.nextToken();
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					map[i][j] = line.charAt(i * C + j);
				}
			}
			int count = R * C;
			int num = 0;
			int turn = 0;
			char d = 'R';
			int row = 0;
			int col = 0;
			while (count > 0) {
				num <<= 1;
				num += map[row][col] - '0';
				map[row][col] = 0;
				if (++turn == 5) {
					if (num == 0) {
						lineSb.append(" ");
					}
					else {
						lineSb.append((char) (num - 1 + 'A'));
					}
					num = 0;
					turn = 0;
				}
				switch(d) {
				case 'R':
					if (R != 1 && (col == C - 1 || map[row][col + 1] == 0)) {
						d = 'D';
					}
					break;
				case 'D':
					if (C != 1 && (row == R - 1 || map[row + 1][col] == 0)) {
						d = 'L';
					}
					break;
				case 'L':
					if (col == 0 || map[row][col - 1] == 0) {
						d = 'U';
					}
					break;
				case 'U':
					if (row == 0 || map[row - 1][col] == 0) {
						d = 'R';
					}
					break;
				}
				switch(d) {
				case 'R':
					col++;
					break;
				case 'D':
					row++;
					break;
				case 'L':
					col--;
					break;
				case 'U':
					row--;
					break;
				}
				count--;
			}
			sb.append(lineSb.toString().trim()).append("\n");
		}
		System.out.print(sb.toString());
	}
}