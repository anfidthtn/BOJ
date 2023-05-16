import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			String[] strs = br.readLine().split(" ");
			int[] check = new int[200];
			for (int i = 0; i < strs[0].length(); i++) {
				check[strs[0].charAt(i)]++;
			}
			for (int i = 0; i < strs[1].length(); i++) {
				check[strs[1].charAt(i)]--;
			}
			boolean ch = true;
			for (int i = 'a'; i <= 'z'; i++) {
				if (check[i] != 0) {
					ch = false;
					break;
				}
			}
			sb.append(strs[0]).append(" & ").append(strs[1]);
			sb.append(ch ? " are anagrams.\n" : " are NOT anagrams.\n");
		}
		System.out.print(sb.toString());
	}
}