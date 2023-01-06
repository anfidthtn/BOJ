import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int g = 0;
		int before = Integer.parseInt(br.readLine());
		for (int i = 1; i < N; i++) {
			int next = Integer.parseInt(br.readLine());
			g = gcd(Math.abs(next - before), g);
			before = next;
		}
		StringBuilder sb = new StringBuilder();
		Set<Long> set = new TreeSet<>();
		for (long i = 2; i * i <= g; i++) {
			if (g % i == 0) {
				set.add(i);
				set.add(g / i);
			}
		}
		set.add((long) g);
		List<Long> list = new ArrayList<>(set);
		list.sort(Long::compareTo);
		for (long ans : list) {
			sb.append(ans).append(" ");
		}
		System.out.print(sb.toString());
	}

	public static int gcd(int A, int B) {
		while (B > 0) {
			int temp = A % B;
			A = B;
			B = temp;
		}
		return A;
	}
}