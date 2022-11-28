import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer>[] sets = new ArrayList[1000];
		for (int i = 0; i < 1000; i++) {
			sets[i] = new ArrayList<>();
		}
		boolean[] isprime = new boolean[1_000_001];
		Arrays.fill(isprime, true);
		List<Long> primes = new ArrayList<>();
		for (int i = 2; i <= 1000000; i++) {
			if (isprime[i]) {
				primes.add((long) i);
				for (long np = (long) i * i; np <= 1_000_000; np += i) {
					isprime[(int) np] = false;
				}
			}
		}
		for (int i = 2; i <= 1000000; i++) {
			int res = 1;
			int num = i;
			for (long prime : primes) {
				if (prime * prime > i) {
					break;
				}
				int temp1 = 1;
				int temp2 = (int) prime;
				while (num % prime == 0) {
					temp1 += temp2;
					temp2 *= prime;
					num /= prime;
				}
				res *= temp1;
			}
			if (num > 1) {
				res *= (1 + num);
			}
			int bad = Math.abs(res - i * 2);
			if (bad < 1000) {
				sets[bad].add(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		int t = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int stop = Integer.parseInt(st.nextToken());
			int bad = Integer.parseInt(st.nextToken());
			if (start == 0) {
				break;
			}
			int count = 0;
			for (int i = 0; i <= bad; i++) {
				count += getLower(sets[i], stop + 1);
				count -= getLower(sets[i], start);
			}
			sb.append("Test ").append(t).append(": ").append(count).append("\n");
			t++;
		}
		System.out.print(sb.toString());
	}

	public static int getLower(List<Integer> list, int value) {
		int ans = -1;
		int left = 0;
		int right = list.size() - 1;
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (list.get(mid) < value) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}
}