import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long count = 0;
		Map<Long, Integer> treeMap = new TreeMap<>();
		treeMap.put(0L, 1);
		long presum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			presum += Integer.parseInt(st.nextToken());
			if (treeMap.containsKey(presum - K)) {
				count += treeMap.get(presum - K);
			}
			if (!treeMap.containsKey(presum)) {
				treeMap.put(presum, 1);
			}
			else {
				treeMap.put(presum, treeMap.get(presum) + 1);
			}
		}
		System.out.println(count);
	}
}