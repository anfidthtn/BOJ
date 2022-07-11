import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * a 와 a + d의 공약수 = g라고 하면
	 * a` * g = a
	 * a` * g + d` * g = a + d 이므로
	 * a + 2d = a + d + d` * g 이므로 공약수가 g이다.
	 * 즉, 단항은 항 자체, 두 항 이상은 두 항의 공약수가 전체구간 공약수이다.
	 */
//	static Long[] nums;
//	static Long[] preSum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Long a = Long.parseLong(st.nextToken());
		Long d = Long.parseLong(st.nextToken());
		int q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().charAt(0) == '1') {
				sb.append(getRangeSum(a, d, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
			}
			else {				
				sb.append(getRangeGCD(a, d, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static Long getRangeSum(Long a, Long d, int l, int r) {
		return (r * (2 * a + (r - 1) * d) - (l - 1) * (2 * a + (l - 2) * d)) / 2;
	}
	public static Long getRangeGCD(Long a, Long d, int l, int r) {
		if (l == r) {
			return a + d * (l - 1);
		}
		else {
			return getGCD(a + d * (l - 1), a + d * l);
		}
	}
	public static Long getGCD(Long a, Long b) {
		if (a < b) {
			Long temp = a;
			a = b;
			b = temp;
		}
		while(b > 0) {
			Long temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}