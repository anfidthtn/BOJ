import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] answer;
	static int count;
	static int[] selected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new int[12];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 2; i < 12; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		selected = new int[12];
		count = 0;
		check(2, 0);
		System.out.println(count);
	}
	public static void check(int idx, int sum) {
		if (idx == 12) {
			if (sum >= 5) {
				count++;
			}
			return;
		}
		for(int i = 1; i <= 5; i++) {
			if (selected[idx - 2] == i && selected[idx - 1] == i) {
				continue;
			}
			selected[idx] = i;
			check(idx + 1, answer[idx] == i ? sum + 1 : sum);
		}
	}
}