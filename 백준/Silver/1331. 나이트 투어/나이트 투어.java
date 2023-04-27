import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String before = br.readLine();
		String first = before;
		Set<String> set = new TreeSet<>();
		set.add(before);
		for (int i = 0; i < 35; i++) {
			String now = br.readLine();
			if (set.contains(now)) {
				System.out.print("Invalid");
				return;
			}
			set.add(now);
			int a = Math.abs(before.charAt(0) - now.charAt(0));
			int b = Math.abs(before.charAt(1) - now.charAt(1));
			if (a + b != 3 || a == 0 || b == 0) {
				System.out.print("Invalid");
				return;
			}
			before = now;
		}
		int a = Math.abs(before.charAt(0) - first.charAt(0));
		int b = Math.abs(before.charAt(1) - first.charAt(1));
		if (a + b != 3 || a == 0 || b == 0) {
			System.out.print("Invalid");
			return;
		}
		System.out.println("Valid");
	}
}