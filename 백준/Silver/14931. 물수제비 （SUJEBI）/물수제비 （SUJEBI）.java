import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int[] value = new int[L + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= L; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		long maxScore = Long.MIN_VALUE;
		int d = 0;
		for (int i = 1; i <= L; i++) {
			long score = 0;
			for (int j = i; j <= L; j += i) {
				score += value[j];
			}
			if (maxScore < score) {
				d = i;
				maxScore = score;
			}
		}
		if (maxScore > 0) {
			System.out.print(d + " " + maxScore);
		}
		else {
			System.out.print("0 0");
		}
	}
}