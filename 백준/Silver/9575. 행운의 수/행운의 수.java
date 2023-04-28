import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] cases = { 5, 55, 555, 5555, 55555, 8, 58, 558, 5558, 55558, 85, 585, 5585, 55585, 88, 588, 5588, 55588,
				855, 5855, 55855, 858, 5858, 55858, 885, 5885, 55885, 888, 5888, 55888, 8555, 58555, 8558, 58558, 8585,
				58585, 8588, 58588, 8855, 58855, 8858, 58858, 8885, 58885, 8888, 58888, 85555, 85558, 85585, 85588,
				85855, 85858, 85885, 85888, 88555, 88558, 88585, 88588, 88855, 88858, 88885, 88888

		};
		for (int tNum = 1; tNum <= t; tNum++) {
			int aa = Integer.parseInt(br.readLine());
			int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int bb = Integer.parseInt(br.readLine());
			int[] B = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int cc = Integer.parseInt(br.readLine());
			int[] C = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			boolean[] visited = new boolean[90001];
			for (int a = 0; a < aa; a++) {
				for (int b = 0; b < bb; b++) {
					for (int c = 0; c < cc; c++) {
						visited[A[a] + B[b] + C[c]] = true;
					}
				}
			}
			int count = 0;
			for(int c : cases) {
				if (visited[c]) {
					count++;
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}