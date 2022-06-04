import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; ; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			if ((L | P | V) == 0) {
				break;
			}
			sb.append("Case ").append(tNum).append(": ");
			int count = 0;
			count += V / P * L;
			V %= P;
			count += Math.min(V, L);
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}