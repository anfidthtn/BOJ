import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static List<String> original, sortA, sortD;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		original = new ArrayList<>();
		sortA = new ArrayList<>();
		sortD = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String name = br.readLine();
			original.add(name);
			sortA.add(name);
			sortD.add(name);
		}
		sortA.sort(String::compareTo);
		sortD.sort((a, b) -> b.compareTo(a));
		if (check(original, sortA)) {
			System.out.print("INCREASING");
		}
		else if (check(original, sortD)) {
			System.out.print("DECREASING");
		}
		else {
			System.out.print("NEITHER");			
		}
	}

	public static boolean check(List<String> a, List<String> b) {
		for (int i = 0; i < N; i++) {
			if (!a.get(i).equals(b.get(i))) {
				return false;
			}
		}
		return true;
	}
}