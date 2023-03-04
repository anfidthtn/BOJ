import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(":");
		int[][] cases = { { 0, 1, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 }, { 0, 2, 1 }, { 1, 0, 2 } };
		int count = 0;
		for (int[] ca : cases) {
			int a = Integer.parseInt(str[ca[0]]);
			int b = Integer.parseInt(str[ca[1]]);
			int c = Integer.parseInt(str[ca[2]]);
			if (1 <= a && a <= 12 && b < 60 && c < 60) {
				count++;
			}
		}
		System.out.print(count);
	}
}