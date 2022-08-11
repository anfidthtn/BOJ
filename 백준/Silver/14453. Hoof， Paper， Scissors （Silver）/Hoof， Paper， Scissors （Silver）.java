import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] frontResult = new int[N + 2][3];
		int[][] backResult = new int[N + 2][3];
		for (int i = 1; i <= N; i++) {
			switch (br.readLine()) {
			case "P":
				frontResult[i][0]++;
				backResult[i][0]++;
				break;
			case "H":
				frontResult[i][1]++;
				backResult[i][1]++;
				break;
			case "S":
				frontResult[i][2]++;
				backResult[i][2]++;
				break;
			}
		}
		for (int i = 1; i <= N; i++) {
			for(int j = 0; j < 3; j++) {
				frontResult[i][j] += frontResult[i - 1][j];
			}
		}
		for (int i = N; i >= 1; i--) {
			for(int j = 0; j < 3; j++) {
				backResult[i][j] += backResult[i + 1][j];
			}
		}
		int result = 0;
		for(int i = 1; i <= N; i++) {
			result = Math.max(result, max(frontResult[i]) + max(backResult[i + 1]));
		}
		System.out.println(result);
		
	}
	public static int max(int[] result) {
		int res = result[0];
		if (res < result[1]) {
			res = result[1];
		}
		if (res < result[2]) {
			res = result[2];
		}
		return res;
	}
}