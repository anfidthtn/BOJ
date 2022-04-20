import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int max = 0;
		int[] input = new int[T];
		for (int i = 0; i < T; i++) {
			input[i] = Integer.parseInt(br.readLine());
			if (max < input[i]) {
				max = input[i];
			}
		}
		int[] ways = new int[max + 1];
		ways[0] = 1;
		for(int i = 1; i <= max; i++) {
			getWay(ways, i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < T; i++) {
			sb.append(getWay(ways, input[i])).append("\n");
		}
		System.out.println(sb.toString());
	}
	public static int getWay(int[] ways, int num) {
		if (num < 0) {
			return 0;
		}
		if (ways[num] > 0) {
			return ways[num];
		}
		for(int i = 1; i <= 3; i++) {
			ways[num] = (ways[num] + getWay(ways, num - i)) % 1_000_000_009;
		}
		return ways[num];
	}
}