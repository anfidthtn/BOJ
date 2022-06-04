import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int maxStart = 0;
		int minEnd = 1_000_000;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (maxStart < start) {
				maxStart = start;
			}
			if (end < minEnd) {
				minEnd = end;
			}
		}
		if (maxStart <= minEnd) {
			System.out.println(0);
		}
		else {
			System.out.println(maxStart - minEnd);
		}
	}
}