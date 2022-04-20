import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][][] table = new int[21][21][9];
		int[][] direction = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {1, 1}, {1, 0}, {1, -1}, {0, 1}};

		for (int row = 1; row <= 19; row++) {
			for (int col = 1; col <= 19; col++) {
				int color = sc.nextInt();
				table[row][col][8] = color;
				if (color > 0) {
					for (int dir = 0; dir < 4; dir++) {
						table[row][col][dir] = 1;
						if (table[row + direction[dir][0]][col + direction[dir][1]][8] == color) {
							table[row][col][dir] += table[row + direction[dir][0]][col + direction[dir][1]][dir];
						}
					}
				}
			}
			sc.nextLine();
		}
		for (int row = 19; row >= 1; row--) {
			for (int col = 19; col >= 1; col--) {
				int color = table[row][col][8];
				if (color > 0) {
					for (int dir = 4; dir < 8; dir++) {
						table[row][col][dir] = 1;
						if (table[row + direction[dir][0]][col + direction[dir][1]][8] == color) {
							table[row][col][dir] += table[row + direction[dir][0]][col + direction[dir][1]][dir];
						}
					}
				}
			}
		}
		for (int row = 1; row <= 19; row++) {
			for (int col = 1; col <= 19; col++) {
				for (int i : new int[] {2, 4, 5, 7}) {
					if (table[row][col][i] == 5 && table[row][col][(i + 4) % 8] == 1) {
						System.out.printf("%d\n%d %d\n", table[row][col][8], row, col);
						System.exit(0);
					}
				}
			}
		}
		System.out.println(0);
	}
}
