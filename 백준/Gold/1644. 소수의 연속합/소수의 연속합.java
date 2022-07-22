import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		boolean[] isNotPrime = new boolean[N + 2];
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		for (int i = 4; i <= N; i += 2) {
			isNotPrime[i] = true;
		}
		for (int i = 3; (long) i * i <= N; i += 2) {
			if (!isNotPrime[i]) {
				for (long notP = i * i; notP <= N; notP += i) {
					isNotPrime[(int) notP] = true;
				}
			}
		}
		int left = 2;
		int right = 1;
		int sum = 0;
		while (left <= N && right <= N) {
			if (left > right) {
				sum += get(isNotPrime, ++right);
			}
			else if (sum == N) {
				if (!isNotPrime[left] && !isNotPrime[right]) {
					count++;
				}
				sum -= get(isNotPrime, left++);
			}
			else if (sum < N) {
				sum += get(isNotPrime, ++right);
			}
			else {
				sum -= get(isNotPrime, left++);
			}
		}
		System.out.println(count);
	}
	public static int get(boolean[] isNotPrime, int num) {
		if (isNotPrime[num]) {
			return 0;
		}
		else {
			return num;
		}
	}
}