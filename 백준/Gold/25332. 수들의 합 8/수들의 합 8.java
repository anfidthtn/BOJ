import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stA = new StringTokenizer(br.readLine());
		StringTokenizer stB = new StringTokenizer(br.readLine());
		Map<Long, Integer> map = new TreeMap<>();
		map.put(0L, 1);
		long answer = 0;
		long before = 0;
		for(int i = 0; i < N; i++) {
			before += Integer.parseInt(stA.nextToken()) - Integer.parseInt(stB.nextToken());
			if (map.containsKey(before)) {
				answer += map.get(before);
				map.put(before, map.get(before) + 1);
			}
			else {
				map.put(before, 1);
			}
		}
		System.out.print(answer);
	}
}