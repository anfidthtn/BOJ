import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	long N;
	StringBuilder sb;

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.process();
	}

	public void process() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		processA();
		processB();
		processC();
		System.out.print(sb.toString());
	}

	public void processA() {
		long ans = 0;
		for (int i = 1; i <= N; i++) {
			ans += (i - 1) / 2;
		}
		sb.append(ans).append("\n");
	}

	public void processB() {
		Set<Integer> set = new TreeSet<>();
		for (long i = 1; i * i <= N; i++) {
			if (N % i == 0) {
				set.add((int) (i));
				set.add((int) (N / i));
			}
		}
		List<Integer> list = new ArrayList<>(set);
		list.sort(Integer::compareTo);
		long ans = 0;
		for (int target : list) {
			int left = 0;
			int right = list.size() - 1;
			while (left <= right) {
				if (list.get(left) + list.get(right) == target) {
					ans++;
					left++;
					right--;
				} else if (list.get(left) + list.get(right) < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		sb.append(ans).append("\n");
	}

	public void processC() {
		if (N == 1) {
			sb.append("0\n");
			return;
		}
		boolean[] primeArr = new boolean[(int) N + 1];
		Arrays.fill(primeArr, true);
		for (int i = 2; i <= N; i++) {
			if (primeArr[i]) {
				for (long p = (long) i * i; p <= N; p += i) {
					primeArr[(int) p] = false;
				}
			}
		}
		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i <= N; i++) {
			if (primeArr[i]) {
				primes.add(i);
			}
		}
		long ans = 0;
		for (int i = 0; i < primes.size() - 1; i++) {
			if (primes.get(i) + 2 == primes.get(i + 1)) {
				ans++;
			}
		}
		sb.append(ans).append("\n");
	}
}