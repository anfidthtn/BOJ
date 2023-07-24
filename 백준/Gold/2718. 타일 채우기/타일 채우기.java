import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] ans = { 0, 1, 5, 11, 36, 95, 281, 781, 2245, 6336, 18061, 51205, 145601, 413351, 1174500, 3335651,
			9475901, 26915305, 76455961, 217172736, 616891945, 1752296281 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			sb.append(ans[Integer.parseInt(br.readLine())]).append("\n");
		}
		System.out.print(sb.toString());
	}
	// static long[][][] ans;
	// public static void main(String[] args) throws IOException {
	// ans = new long[100][4][1 << 4];
	// for(int i = 0; i < 100; i++) {
	// for(int j = 0; j < 4; j ++) {
	// for(int k = 0; k < (1 << 4); k++) {
	// ans[i][j][k] = -1;
	// }
	// }
	// }
	// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// int t = Integer.parseInt(br.readLine());
	// StringBuilder sb = new StringBuilder();
	// for (int tNum = 1; tNum <= t; tNum++) {
	// int target = Integer.parseInt(br.readLine());
	// System.out.println(getAns(target,0,0));
	// //sb.append(getAns(target, 0, 0)).append("\n");
	// }
	// System.out.print(sb.toString());
	// }
	// public static long getAns(int n, int col, int status) {
	// if (col >= 4) {
	// col -= 4;
	// n--;
	// }
	// if (n <= 0) {
	// return status == 0 && col == 0 ? 1 : 0;
	// }
	// if (ans[n][col][status] >= 0) {
	// return ans[n][col][status];
	// }
	// long res = 0;
	// if ((status & 0b11) == 0 && col < 3) {
	// res += getAns(n, col + 2, status >> 2);
	// }
	// if ((status & 0b1) == 0) {
	// res += getAns(n, col + 1, (status >> 1) + 0b1000);
	// }
	// else {
	// res += getAns(n, col + 1, status >> 1);
	// }
	// return ans[n][col][status] = res;
	// }
}