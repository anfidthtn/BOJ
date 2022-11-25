import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long L, R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Long.parseLong(st.nextToken());
		R = Long.parseLong(st.nextToken());
		long answer = 0;
		for(int i = 0; i < 31; i++) {
			answer += getSub(i, 1, 1, 1 << i, (1 << i) - 1);
		}
		System.out.println(answer);
	}
	public static long getSub(int need, int before, int straight, long num, long range) {
		long numMax = num + range;
		if (R < num || numMax < L) {
			return 0;
		}
		if (straight == 3) {
			return Math.min(R, numMax) - Math.max(L, num) + 1;
		}
		if (need == 0) {
			return 0;
		}
		return getSub(need - 1, 0, before == 0 ? straight + 1 : 1, num, range >> 1)
				+ getSub(need - 1, 1, before == 1 ? straight + 1 : 1, num + (range >> 1) + 1, range >> 1);
	}
}