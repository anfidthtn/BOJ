import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> T = new ArrayList<>();
		for(int i = 1; i * (i + 1) / 2 <= 1000; i++) {
			T.add(i * (i + 1) / 2);
		}
		for (int tNum = 1; tNum <= t; tNum++) {
			int K = Integer.parseInt(br.readLine());
			if (check(T, K, 0, 0, 0)) sb.append(1);
			else sb.append(0);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
	
	public static boolean check(ArrayList<Integer> T, int target, int sum, int idx, int count) {
		if (count == 3) {
			if (target == sum) return true;
			return false;
		}
		for(int i = idx; i < T.size(); i++) {
			if (target < sum + T.get(i)) continue;
			if (check(T, target, sum + T.get(i), i, count + 1)) return true;
		}
		return false;
	}
}