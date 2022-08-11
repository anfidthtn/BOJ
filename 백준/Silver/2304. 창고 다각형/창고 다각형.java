import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] heights = new int[1002];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			heights[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		int[] fronts = new int[1002];
		for(int i = 1; i <= 1000; i++) {
			fronts[i] = Math.max(fronts[i - 1], heights[i]);
		}
		int[] backs = new int[1002];
		for(int i = 1000; i >= 1; i--) {
			backs[i] = Math.max(backs[i + 1], heights[i]);
		}
		int count = 0;
		for(int i = 1; i <= 1000; i++) {
			count+= Math.min(fronts[i], backs[i]);
		}
		System.out.println(count);
	}
}