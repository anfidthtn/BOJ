import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[] useable = new boolean[1002];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			useable[num] = true;
		}
		int answer = Integer.MAX_VALUE;
		for(int x = 1; x <= 1001; x++) {
			for(int y = 1; y <= 1001; y++) {
				if (x * y > 51 * 51 * 51) {
					break;
				}
				for(int z = 1; z <= 1001; z++) {
					if (x * y * z > 51 * 51 * 51) {
						break;
					}
					if(useable[x] || useable[y] || useable[z]) {
						continue;
					}
					answer = Math.min(answer, Math.abs(N - x * y * z));
				}
			}
		}
		System.out.println(answer);
	}
}