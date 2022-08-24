import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			sb.append("Case ").append(tNum).append(": ");
			if(process()) {
				sb.append("CORRECT\n");
			}
			else {				
				sb.append("INCORRECT\n");
			}
			if (tNum != t) {
				br.readLine();
			}
		}
		System.out.print(sb.toString());
	}
	public static boolean process() throws IOException {
		int[][] rowCheck = new int[9][9];
		int[][] colCheck = new int[9][9];
		int[][] areaCheck = new int[9][9];
		boolean result = true;
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; result && j < 9; j++) {
				int num = Integer.parseInt(st.nextToken()) - 1;
				if (++rowCheck[i][num] > 1) {
					result = false;
					break;
				}
				if (++colCheck[j][num] > 1) {
					result = false;
					break;
				}
				if (++areaCheck[(i / 3) * 3 + j / 3][num] > 1) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
}