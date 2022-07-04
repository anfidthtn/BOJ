import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] guards = new int[N][];
		int max = 0;
		int total = 0;
		int[] watch = new int[1000];
		for(int i = 0; i < N; i++) {
			guards[i] = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			guards[i][0] = Integer.parseInt(st.nextToken());
			guards[i][1] = Integer.parseInt(st.nextToken());
			for(int w = guards[i][0]; w < guards[i][1]; w++) {
				watch[w]++;
				if (watch[w] == 1) {
					total++;
				}
			}
		}
		for(int d = 0; d < N; d++) {
			int temp = total;
			for(int w = guards[d][0]; w < guards[d][1]; w++) {
				if (watch[w] == 1) {
					temp--;
				}
			}
			max = Math.max(temp, max);
		}
		System.out.println(max);
	}
}