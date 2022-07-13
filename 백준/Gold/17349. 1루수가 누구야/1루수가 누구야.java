import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] firstBaseMan = new boolean[10];
		int[][] rules = new int[10][2];
		for(int i = 1; i <= 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rules[i][0] = Integer.parseInt(st.nextToken());
			rules[i][1] = Integer.parseInt(st.nextToken());
		}
		int who = -1;
		for(int i = 1; i <= 9; i++) {
			firstBaseMan[i] = true;
			
			int falseCount = 0;
			for(int[] rule : rules) {
				if (rule[0] == 1) {
					if (!firstBaseMan[rule[1]]) {
						falseCount++;
					}
				}
				else {
					if (firstBaseMan[rule[1]]) {
						falseCount++;
					}
				}
			}
			if (falseCount == 1) {
				if (who == -1) {
					who = i;
				}
				else {
					System.out.println(-1);
					return;
				}
			}
			firstBaseMan[i] = false;
		}
		System.out.println(who);
	}
}