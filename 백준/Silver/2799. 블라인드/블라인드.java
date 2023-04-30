import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		br.readLine();
		int[] anss = new int[5];
		for (int i = 0; i < R * 5; i += 5) {
			String a = br.readLine();
			String b = br.readLine();
			String c = br.readLine();
			String d = br.readLine();
			br.readLine();
			for (int j = 1; j < C * 5 + 1; j += 5) {
				if (a.charAt(j) == '.') {
					anss[0]++;
				} else if (b.charAt(j) == '.') {
					anss[1]++;
				} else if (c.charAt(j) == '.') {
					anss[2]++;
				} else if (d.charAt(j) == '.') {
					anss[3]++;
				} else {
					anss[4]++;
				}
			}
		}
		for (int i = 0; i < 5; i++) {
			System.out.print(anss[i] + " ");
		}
	}
}