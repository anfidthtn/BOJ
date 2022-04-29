import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int[] status = new int[M + 1];
			for(int i = 1; i <= M; i++) {
				status[i] = Integer.parseInt(st.nextToken());
			}
			int count = 0;
			StringBuilder tSb = new StringBuilder();
			for(int i = M; i > 1; i--) {
				if (status[i] != i) {
					int index = findValue(status, i);
					count++;
					tSb.append(index).append(" ");
					flip(status, index);
					if (status[1] > 0) {
						count++;
						tSb.append(1).append(" ");
						status[1] *= -1;
					}
					count++;
					tSb.append(i).append(" ");
					flip(status, i);
				}
			}
			if (status[1] < 0) {
				count++;
				tSb.append(1).append(" ");
			}
			sb.append(count).append(" ").append(tSb).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static int findValue(int[] status, int value) {
		for(int i = value; i >= 1; i--) {
			if (Math.abs(status[i]) == value) {
				return i;
			}
		}
		return 0;
	}
	public static void flip(int[] status, int idx) {
		for(int i = 1; i <= idx / 2; i++) {
			int temp = status[i];
			status[i] = - status[idx - i + 1];
			status[idx - i + 1] = - temp;
		}
		if ((idx & 1) == 1) {
			status[idx / 2 + 1] *= -1;
		}
	}
}