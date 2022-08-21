import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] seat = new boolean[101];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int count = 0;
		for (int i = 0; i < N; i++) {
			int idx = Integer.parseInt(st.nextToken());
			if (seat[idx]) {
				count++;
			}
			else {
				seat[idx] = true;
			}
		}
		System.out.print(count);
	}
}