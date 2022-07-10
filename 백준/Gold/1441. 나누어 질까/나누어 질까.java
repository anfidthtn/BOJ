import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static List<Long> numsList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		Set<Long> numsSet = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num == 1) {
				System.out.println(R - L + 1);
				return;
			}
			numsSet.add((long) num);
		}
		numsList = new ArrayList<>(numsSet);
		numsList.sort(Long::compareTo);
		for(int i = numsList.size() - 1; i > 0; i--) {
			boolean process = true;
			for(int j = i - 1; j >= 0 && process; j--){
				if (numsList.get(i) % numsList.get(j) == 0) {
					process = false;
					numsList.remove(i);
				}
			}
		}
		System.out.println(check(R, 0, 0, 1) - check(L - 1, 0, 0, 1));
	}
	public static int check(int num, int idx, int count, long value) {
		if (idx == numsList.size()) {
			if (count == 0) {
				return 0;
			}
			if (count % 2 == 1) {
				return num / (int) value;
			}
			else {
				return num / (int) value * (-1);
			}
		}
		int result = 0;
		result += check(num, idx + 1, count, value);
		long lcm = getLCM(value, numsList.get(idx));
		if (lcm <= num) {
			result += check(num, idx + 1, count + 1, lcm);
		}
		return result;
	}
	public static long getGCD(long a, long b) {
		if (a < b) {
			long temp = a;
			a = b;
			b = temp;
		}
		while(b > 0) {
			long temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
	public static long getLCM(long a, long b) {
		long gcd = getGCD(a, b);
		return a / gcd * b;
	}
}