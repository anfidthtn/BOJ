import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		Map<Integer, Set<Integer>> points = new TreeMap<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			if (!points.containsKey(x)) {
				points.put(x, new TreeSet<>());
			}
			int y = Integer.parseInt(st.nextToken());
			points.get(x).add(y);
		}
		
		int count = 0;
		for(int x : points.keySet()) {
			if (!points.containsKey(x + A)) {
				continue;
			}
			for(int y : points.get(x)) {
				if (!points.get(x).contains(y + B)) {
					continue;
				}
				if (!points.get(x + A).contains(y)) {
					continue;
				}
				if (!points.get(x + A).contains(y + B)) {
					continue;
				}
				count++;
			}
		}
		System.out.println(count);
	}
}