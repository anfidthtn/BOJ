import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int G = Integer.parseInt(br.readLine());
			int[] nums = new int[G];
			for(int i = 0; i < G; i++) {
				nums[i] = Integer.parseInt(br.readLine());
			}
			int m = G;
			boolean[] div = new boolean[1_000_001];
			while(true) {
				for(int i = 0; i < m; i++) {
					div[i] = false;
				}
				boolean check = true;
				for(int i = 0; i < G; i++) {
					if (div[nums[i] % m]) {
						check = false;
						break;
					}
					else {
						div[nums[i] % m] = true;
					}
				}
				if (check) {
					sb.append(m).append("\n");
					break;
				}
				m++;
			}
		}
		System.out.print(sb.toString());
	}
}