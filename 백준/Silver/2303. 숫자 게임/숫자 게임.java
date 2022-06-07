import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] scores = new int[N + 1];
		int maxScore = 0;
		int maxNum = 0;
		for (int i = 1; i <= N; i++) {
			int[] info = new int[5];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				info[j] = Integer.parseInt(st.nextToken());
			}
			for(int a = 0; a < 5; a++) {
				for (int b = a + 1; b < 5; b++){
					for(int c = b + 1; c < 5; c++) {
						if (scores[i] < (info[a] + info[b] + info[c]) % 10) {
							scores[i] = (info[a] + info[b] + info[c]) % 10;
						}
					}
				}
			}
			if (maxScore <= scores[i]) {
				maxNum = i;
				maxScore = scores[i];
			}
		}
		System.out.print(maxNum);
	}
}