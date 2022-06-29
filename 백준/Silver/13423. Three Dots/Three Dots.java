import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int count = 0;
			int N = Integer.parseInt(br.readLine());
			int[] dots = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.sort(dots);
			for(int i = 0; i < N; i++) {
				int left = i - 1;
				int right = i + 1;
				while(0 <= left && right < N) {
					if (dots[i] - dots[left] == dots[right] - dots[i]) {
						count++;
						left--;
						right++;
					}
					else if (dots[i] - dots[left] < dots[right] - dots[i]) {
						left--;
					}
					else {
						right++;
					}
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}