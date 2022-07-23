import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		boolean[] destroy = new boolean[N + 1];
		for (int i = 0; i < B; i++) {
			destroy[Integer.parseInt(br.readLine()) - 1] = true;
		}
		int minFix = Integer.MAX_VALUE;
		int unuseable = 0;
		int left = 0;
		int right = -1;
		while (right < N) {
			if (right - left + 1 >= K) {
				minFix = unuseable;
			}
			if (left > right) {
				if (destroy[++right]) {
					unuseable++;
				}
			}
			else if (minFix <= unuseable) {
				if (destroy[left++]) {
					unuseable--;
				}
			}
			else {
				if (destroy[++right]) {
					unuseable++;
				}
			}
		}
		System.out.println(minFix);
	}
}