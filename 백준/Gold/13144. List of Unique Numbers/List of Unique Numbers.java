import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] idx = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int startIdx = 0;
		long count = 0;
		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			startIdx = Math.max(startIdx, idx[num] + 1);
			idx[num] = i;
			count += i - startIdx + 1;
		}
		System.out.print(count);
	}
}