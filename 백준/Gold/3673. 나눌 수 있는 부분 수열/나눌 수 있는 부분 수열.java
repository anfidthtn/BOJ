import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			Map<Integer, Integer> map = new TreeMap<>();
			map.put(0, 1);
			int before = 0;
			long answer = 0;
			for(int i = 0; i < N; i++) {
				before += Integer.parseInt(st.nextToken());
				before %= D;
				if (map.containsKey((before + D) % D)){
					answer += map.get((before + D) % D);
				}
				if (map.containsKey(before)) {
					map.put(before, map.get(before) + 1);
				}
				else {
					map.put(before, 1);
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
}