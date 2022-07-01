import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int mul = 1;
		int[] counts = new int[100_001];
		int minus = 0;
		for(int i = 1; i <= N; i++) {
			int num = Math.abs(Integer.parseInt(st.nextToken()));
			if (num == 0) {
				System.out.println("mint chocolate");
				return;
			}
			int value = num;
			for(int p = 2; p * p <= num; p++) {
				if (value % p == 0) {
					int count = 0;
					while(value % p == 0) {
						count++;
						value /= p;
					}
					minus += plus(counts, p, count, mul);
				}
			}
			if (value > 1) {
				minus += plus(counts, value, 1, mul);
			}
			if (i == N) {
				break;
			}
			if (st.nextToken().charAt(0) == '/') {
				mul = -1;
			}
			else {
				mul = 1;
			}
		}
		if (minus == 0) {
			System.out.println("mint chocolate");
		}
		else {
			System.out.println("toothpaste");
		}
	}
	public static int plus(int[] counts, int p, int count, int mul) {
		int value = mul * count;
		counts[p] += value;
		if (mul < 0) {
			if (counts[p] - value >= 0 && counts[p] < 0) {
				return 1;
			}
		}
		else {
			if (counts[p] - value < 0 && counts[p] >= 0) {
				return -1;
			}
		}
		return 0;
	}
}