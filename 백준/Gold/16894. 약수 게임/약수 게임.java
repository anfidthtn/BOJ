import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	// 대충 이 코드로
	// 6 2
	// 3 3
	// 2 5
	// 1 7
	// 1 11
	// 1 13
	// 1 17
	// 1 19
	// 1 23
	// 0 29
	// 1 31
	// 0 37
	// 0 41
	// 9958865716800
	// 6 2
	// 3 3
	// 2 5
	// 1 7
	// 1 11
	// 1 13
	// 1 17
	// 1 19
	// 1 23
	// 1 29
	// 0 31
	// 0 37
	// 0 41
	// 9316358251200
	// 10752
	// 약수 최대 개수 10752개 확인
	// static long[] primes;
	// static int[] selected;
	// static int len;
	// static int ans;
	// static final long MAXVALUE = 10_000_000_000_000l;
	//
	// public static void main(String[] args) throws IOException {
	// primes = new long[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41 };
	// selected = new int[13];
	// len = 13;
	// ans = 1;
	// check(0, 1, 1);
	// System.out.print(ans);
	// }
	//
	// public static void check(int idx, long beforeValue, int beforeAns) {
	// if (beforeValue > MAXVALUE) {
	// return;
	// }
	// if (idx == len) {
	// if (beforeAns == 10752) {
	// long num = 1;
	// for (int i = 0; i < len; i++) {
	// System.out.println(selected[i] + " " + primes[i]);
	// for (int j = 0; j < selected[i]; j++) {
	// num *= primes[i];
	// }
	// }
	// System.out.print(num);
	// System.out.println();
	// }
	// ans = Math.max(ans, beforeAns);
	// return;
	// }
	// long n = 1;
	// int p = 0;
	// while (beforeValue * n <= MAXVALUE) {
	// selected[idx] = p;
	// check(idx + 1, beforeValue * n, beforeAns * (p + 1));
	// p++;
	// n *= primes[idx];
	// }
	// }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		if (N == 1) {
			System.out.print("koosaga");
			return;
		}
		List<Long> list = new ArrayList<>();
		for (long i = 2; i * i <= N; i++) {
			if (N % i == 0) {
				list.add(i);
				if (i * i != N) {
					list.add(N / i);
				}
			}
		}
		if (list.size() == 0) {
			System.out.print("koosaga");
			return;
		}
		list.sort(Long::compareTo);
		boolean[] results = new boolean[list.size()];
		for (int i = 0; i < list.size(); i++) {
			results[i] = true;
			for (int j = 0; j < i; j++) {
				if (list.get(i) % list.get(j) == 0) {
					results[i] = false;
					if (!results[j]) {
						results[i] = true;
						break;
					}
				}
			}
			if (!results[i]) {
				System.out.print("koosaga");
				return;
			}
		}
		System.out.print("cubelover");
	}
}