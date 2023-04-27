import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] min = new int[N + 100];
		min[0] = 0;
		min[1] = -1;
		min[2] = 1;
		min[3] = -1;
		min[4] = 2;
		min[5] = 1;
		for(int i = 6; i <= N; i++) {
			if (min[i - 2] != -1) {
				min[i] = min[i - 2] + 1;
			}
			if (min[i - 5] != -1) {
				min[i] = Math.min(min[i - 5] + 1, min[i]);
			}
		}
		System.out.println(min[N]);
	}
}