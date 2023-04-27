import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] counts = new int[2_000_001];
		for(int i = 0; i < N; i++) {
			counts[Integer.parseInt(br.readLine()) + 1_000_000]++;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= 2_000_000; i++) {
			while(counts[i] > 0) {
				sb.append(i - 1_000_000).append("\n");
				counts[i]--;
			}
		}
		System.out.print(sb.toString());
	}
}