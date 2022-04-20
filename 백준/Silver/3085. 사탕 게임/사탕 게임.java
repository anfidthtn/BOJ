import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		char[][] board = new char[N + 2][N + 2];

		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= N; j++) {
				board[i][j] = s.charAt(j - 1);
			}
		}
		int max = maxInit(board, N);

		for (int i = 1; i <= N - 1; i++) {
			for (int j = 1; j <= N; j++) {
				if (board[i][j] == board[i + 1][j])
					continue;
				char temp = board[i][j];
				board[i][j] = board[i + 1][j];
				board[i + 1][j] = temp;
				max = Math.max(max, getStraight(board, i, j, 0) + getStraight(board, i, j, 2) - 1);
				max = Math.max(max, getStraight(board, i, j, 1) + getStraight(board, i, j, 3) - 1);
				max = Math.max(max, getStraight(board, i + 1, j, 0) + getStraight(board, i + 1, j, 2) - 1);
				max = Math.max(max, getStraight(board, i + 1, j, 1) + getStraight(board, i + 1, j, 3) - 1);
				temp = board[i][j];
				board[i][j] = board[i + 1][j];
				board[i + 1][j] = temp;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N - 1; j++) {
				if (board[i][j] == board[i][j + 1])
					continue;
				char temp = board[i][j];
				board[i][j] = board[i][j + 1];
				board[i][j + 1] = temp;
				max = Math.max(max, getStraight(board, i, j, 0) + getStraight(board, i, j, 2) - 1);
				max = Math.max(max, getStraight(board, i, j, 1) + getStraight(board, i, j, 3) - 1);
				max = Math.max(max, getStraight(board, i, j + 1, 0) + getStraight(board, i, j + 1, 2) - 1);
				max = Math.max(max, getStraight(board, i, j + 1, 1) + getStraight(board, i, j + 1, 3) - 1);
				temp = board[i][j];
				board[i][j] = board[i][j + 1];
				board[i][j + 1] = temp;
			}
		}
		System.out.print(max);
	}

	public static int getStraight(char[][] board, int row, int col, int d) {
		if (board[row + dr[d]][col + dc[d]] == 0)
			return 1;
		if (board[row][col] == board[row + dr[d]][col + dc[d]])
			return 1 + getStraight(board, row + dr[d], col + dc[d], d);
		return 1;
	}

	public static int maxInit(char[][] board, int N) {
		int max = 1;
		for (int i = 1; i <= N; i++) {
			char before = board[i][1];
			int straight = 1;
			for (int j = 2; j <= N; j++) {
				if (board[i][j] == before)
					max = Math.max(max, ++straight);
				else {
					before = board[i][j];
					straight = 1;
				}
			}
		}
		for (int j = 1; j <= N; j++) {
			char before = board[1][j];
			int straight = 1;
			for (int i = 2; i <= N; i++) {
				if (board[i][j] == before)
					max = Math.max(max, ++straight);
				else {
					before = board[i][j];
					straight = 1;
				}
			}
		}
		return max;
	}
}