import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<String> hear = new TreeSet<>();
		for(int i = 0; i < N; i++) {
			hear.add(br.readLine());
		}
		List<String> results = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			String target = br.readLine();
			if (hear.contains(target)) {
				results.add(target);
			}
		}
		results.sort(String::compareTo);
		System.out.println(results.size());
		StringBuilder sb = new StringBuilder();
		for(String result : results) {
			sb.append(result).append("\n");
		}
		System.out.print(sb.toString());
	}
}