import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		boolean[] isNotPrime = new boolean[4_000_001];
		isNotPrime[1] = true;
		for(int p = 2; p <= 4_000_000; p++) {
			if (!isNotPrime[p]) {
				for(long notp = (long) p * p; notp <= 4_000_000; notp += p) {
					isNotPrime[(int) notp] = true;
				}
			}
		}
		int count = 0;
		for(int i = A; i <= B; i++) {
			if (!isNotPrime[i] && check(i, D)) {
				count++;
			}
		}
		System.out.println(count);
	}
	public static boolean check(int num, int D) {
		while(num > 0) {
			if (num % 10 == D) {
				return true;
			}
			num /= 10;
		}
		return false;
	}
}