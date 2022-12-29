import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Set<String> set = new TreeSet<>();
		int answer = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (check(set, str)) {
				answer++;
			}
			set.add(str);
		}
		System.out.println(answer);
	}

	public static boolean check(Set<String> set, String string) {
		for (int rot = 0; rot < string.length(); rot++) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < string.length(); i++) {
				sb.append(string.charAt((i + rot) % string.length()));
			}
			if (set.contains(sb.toString())) {
				return false;
			}
		}
		return true;
	}
}