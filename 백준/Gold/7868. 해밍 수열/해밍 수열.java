import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] prime = new long[3];
		prime[0] = Long.parseLong(st.nextToken());
		prime[1] = Long.parseLong(st.nextToken());
		prime[2] = Long.parseLong(st.nextToken());
		int target = Integer.parseInt(st.nextToken());

		Set<Long> set = new TreeSet<>();
		for (long num1 = 1l; Math.log10(num1) <= 18; num1 *= prime[0]) {
			for (long num2 = 1l; Math.log10(num1) + Math.log10(num2) <= 18; num2 *= prime[1]) {
				for (long num3 = 1l; Math.log10(num1) + Math.log10(num2) + Math.log10(num3) <= 18; num3 *= prime[2]) {
					set.add(num1 * num2 * num3);
					if (Math.log10(num1) + Math.log10(num2) + Math.log10(num3) + Math.log10(prime[2]) > 18) {
						break;
					}
				}
				if (Math.log10(num1) + Math.log10(num2) + Math.log10(prime[1]) > 18) {
					break;
				}
			}
			if (Math.log10(num1) + Math.log10(prime[0]) > 18) {
				break;
			}
		}
		List<Long> list = new ArrayList<>(set);
		list.sort(Long::compareTo);
		System.out.println(list.get(target));
	}
}