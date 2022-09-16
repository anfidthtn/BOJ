import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] counts = new int[100];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			counts[(int) (Math.log10(Integer.parseInt(st.nextToken())) / Math.log10(2))]++;
		}
		int limit = (N + 1) / 2;
		int idx = 0;
		long answer = 0;
		while(limit > 0) {
			answer += Math.min(limit, counts[idx]) * idx;
			limit -= Math.min(limit, counts[idx]);
			idx++;
		}
		System.out.println(answer + 1);
	}
}